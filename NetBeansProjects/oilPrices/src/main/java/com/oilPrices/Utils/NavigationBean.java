/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.Utils;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author fran
 */
@Named(value = "navigationBean")
@RequestScoped
public class NavigationBean {

    /**
     * Creates a new instance of NavigationBean
     */
    public NavigationBean() {
    }

    public String gotoRegisterPage() {
        return "/register?faces-redirect=true";
    }

    public String gotoInitPage() {
        return "/oilLocation?faces-redirect=true";
    }

    public String gotoLoginPage() {
        return "/login?faces-redirect=true";
    }

    public String gotoAddUserCar() {
        return "/users/addCar?faces-redirect=true";
    }

    public String gotoUserLocation() {
        return "/users/userLocation?faces-redirect=true";
    }
    public String gotoNewFillUp(){
    return "/users/newFillUp?faces-redirect-true";
    }
    public String gotoCarsConsumption(){
    return "/users/viewConsumptions?faces-redirect-true";
    }
    public String goReset(){
    return "/reset?faces-redirect-true";
    }

}
