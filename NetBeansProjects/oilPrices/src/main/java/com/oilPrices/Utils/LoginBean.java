/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.Utils;

import com.oilPrices.ejbBeans.GroupsFacade;
import com.oilPrices.ejbBeans.UserGroupsFacade;
import com.oilPrices.ejbBeans.UsersFacade;
import com.oilPrices.entities.Groups;
import com.oilPrices.entities.UserGroups;
import com.oilPrices.entities.UserGroupsPK;
import com.oilPrices.entities.Users;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;

/**
 *
 * @author fran
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @Inject
    UsersFacade usersEjb;
    @Inject
    GroupsFacade groupsEjb;
    @Inject
    UserGroupsFacade userGroupsEjb;
    @Inject
    NavigationBean navigationEjb;
    String userId, passwordTxt, password2Txt;
    Utils utils = new Utils();

    public UsersFacade getUsersEjb() {
        return usersEjb;
    }

    public void setUsersEjb(UsersFacade usersEjb) {
        this.usersEjb = usersEjb;
    }

    public NavigationBean getNavigationEjb() {
        return navigationEjb;
    }

    public void setNavigationEjb(NavigationBean navigationEjb) {
        this.navigationEjb = navigationEjb;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswordTxt() {
        return passwordTxt;
    }

    public void setPasswordTxt(String passwordTxt) {
        this.passwordTxt = passwordTxt;
    }

    public String getPassword2Txt() {
        return password2Txt;
    }

    public void setPassword2Txt(String password2Txt) {
        this.password2Txt = password2Txt;
    }

    public void verifyUser(FacesContext context, UIComponent toValidate, Object value) {
        String loadMessage = "";
        if (value.toString().isEmpty()) {
            loadMessage = utils.loadMessage("userNull");
        } else if (usersEjb.getUserbyUsername(value.toString()) != null) {
            loadMessage = utils.loadMessage("userRepeated");
        }
        if (!loadMessage.isEmpty()) {
            utils.messageToUicomponent(context, toValidate, loadMessage);
        }
    }

    public void verifyPassword(FacesContext context, UIComponent toValidate, Object value) {
        String loadMessage = "";
        setPasswordTxt(value.toString());

        if (getPasswordTxt().isEmpty()) {
            loadMessage = utils.loadMessage("passwordNull");
        }
        if (!loadMessage.isEmpty()) {
            utils.messageToUicomponent(context, toValidate, loadMessage);
        }
    }

    public void confirmPassword(FacesContext context, UIComponent toValidate, Object value) {
        String loadMessage = "";
        Password password = (Password) toValidate.findComponent("passwordTxt");
        setPasswordTxt(password.getValue().toString());
        if (value.toString().isEmpty()) {
            loadMessage = utils.loadMessage("confirmPasswordNull");
        } else if (!getPasswordTxt().equals(value.toString())) {
            loadMessage = utils.loadMessage("passwordsNotMatch");
        }

        if (!loadMessage.isEmpty()) {
            utils.messageToUicomponent(context, toValidate, loadMessage);
        }
    }

    public String loginUser() {
        //method for logging user. We not verify that user exist because of we have
        //a validation method for that
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {

            request.login(getUserId(), getPasswordTxt());
        } catch (ServletException ex) {
            System.out.println(ex.getMessage());
            return "loginError";
        }

        if (request.isUserInRole("admin")) {
            return "admin/welcomeAdmin?faces-redirect=true";
        } else if (request.isUserInRole("user")) {
            return "users/welcomeUser?faces-redirect=true";
        }
        return "";
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
            System.out.println("Usuario desconectado correctamente.");
            return navigationEjb.gotoInitPage();
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
        }
        return "";
    }

    public String createUser() {
        Users user = new Users();
        user.setUserID(usersEjb.count() + 1);
        user.setUsername(getUserId());
        user.setPassword(utils.getSHA256String(getPasswordTxt()));
        usersEjb.create(user);
        setUserRolePermissions(user);
        return getNavigationEjb().gotoLoginPage();
    }

    public void setUserRolePermissions(Users user) {
        UserGroups userGroups = new UserGroups();
        userGroups.setUsers(user);
        userGroups.setGroups(groupsEjb.find(1));
        userGroups.setUserGroupsPK(new UserGroupsPK(user.getUserID(), new Integer("1"), new Integer("1"), user.getUserID()));
        userGroupsEjb.create(userGroups);

    }

}
