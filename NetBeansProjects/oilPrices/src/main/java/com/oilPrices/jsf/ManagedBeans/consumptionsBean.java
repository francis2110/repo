/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.jsf.ManagedBeans;

import com.oilPrices.Utils.Utils;
import com.oilPrices.Utils.ConsumptionTbl;
import com.oilPrices.ejbBeans.CarsFacade;
import com.oilPrices.ejbBeans.RefuelingFacade;
import com.oilPrices.ejbBeans.UsersFacade;
import com.oilPrices.entities.Cars;
import com.oilPrices.entities.Refueling;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 *
 * @author fran
 */
@Named(value = "consumptionsBean")
@RequestScoped
public class consumptionsBean {

    private String car;
    private Utils utils;
    private List<SelectItem> carsLstCb;
    private List<Cars> carLst;
    private ConsumptionTbl cTblBean;
   private List<ConsumptionTbl> carsTblLst;
    private boolean tableVisibility;
    @Inject
    UsersFacade usersEjb;
    @Inject
    CarsFacade carsEjb;

    /**
     * Creates a new instance of consumptionsBean
     */
    @PostConstruct
    public void init() {
        carsLstCb = new ArrayList<>();
        String userName = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        carLst = (List<Cars>) usersEjb.getUserbyUsername(userName).getCarsCollection();
        carsLstCb = new ArrayList<>();
        carsLstCb.add(new SelectItem("", "Seleccione coche: "));
        Iterator it = carLst.iterator();
        while (it.hasNext()) {
            Cars itemCar = (Cars) it.next();
            carsLstCb.add(new SelectItem(itemCar.getCarsPK().getMatricula(), itemCar.getCarModel()));
        }
        setCarsTblLst(new ArrayList<ConsumptionTbl>());
        setTableVisibility(false);
        setUtils(new Utils());
        System.out.println("products added to combobox");
    }

    public boolean isTableVisibility() {
        return tableVisibility;
    }

    public void setTableVisibility(boolean tableVisibility) {
        this.tableVisibility = tableVisibility;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public Utils getUtils() {
        return utils;
    }

    public void setUtils(Utils utils) {
        this.utils = utils;
    }

    public List<SelectItem> getCarsLstCb() {
        return carsLstCb;
    }

    public void setCarsLstCb(List<SelectItem> carsLstCb) {
        this.carsLstCb = carsLstCb;
    }

    public List<Cars> getCarLst() {
        return carLst;
    }

    public void setCarLst(List<Cars> carLst) {
        this.carLst = carLst;
    }

    public ConsumptionTbl getcTblBean() {
        return cTblBean;
    }

    public void setcTblBean(ConsumptionTbl cTblBean) {
        this.cTblBean = cTblBean;
    }

    public List<ConsumptionTbl> getCarsTblLst() {
        return carsTblLst;
    }

    public void setCarsTblLst(List<ConsumptionTbl> carsTblLst) {
        this.carsTblLst = carsTblLst;
    }

 

    public UsersFacade getUsersEjb() {
        return usersEjb;
    }

    public void setUsersEjb(UsersFacade usersEjb) {
        this.usersEjb = usersEjb;
    }

    public CarsFacade getCarsEjb() {
        return carsEjb;
    }

    public void setCarsEjb(CarsFacade carsEjb) {
        this.carsEjb = carsEjb;
    }

    public void viewCarConsumption() {
        List<Cars> carsConsumptionsLst;
        if (getCar()==null||getCar().isEmpty()) {
            carsConsumptionsLst = carsEjb.findAll();
        } else {
            carsConsumptionsLst = carsEjb.getCarsByMatricula(getCar());
        }

        Iterator carsIt = carsConsumptionsLst.iterator();
        while (carsIt.hasNext()) {
            Cars c = (Cars) carsIt.next();
            Collection<Refueling> refuelingCollection = c.getRefuelingCollection();

            Iterator it = refuelingCollection.iterator();
            while (it.hasNext()) {
                Refueling refueling = (Refueling) it.next();
                setcTblBean(new ConsumptionTbl());
                getcTblBean().calculateConsumption(refueling.getKilometers(), refueling.getLiters());
                getcTblBean().setCars(c);
                getcTblBean().setRefuelingDate(refueling.getRefuelingDate());
                getCarsTblLst().add(getcTblBean());
                
            }

        }
        if (getCarsTblLst().size() > 0) {
            setTableVisibility(true);
        } else {
            setTableVisibility(false);
        }

    }

}
