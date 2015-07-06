/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.jsf.ManagedBeans;

import com.oilPrices.Utils.NavigationBean;
import com.oilPrices.Utils.Utils;
import com.oilPrices.ejbBeans.CarsFacade;
import com.oilPrices.ejbBeans.UsersFacade;
import com.oilPrices.entities.Cars;
import com.oilPrices.entities.CarsPK;
import com.oilPrices.entities.Users;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

import javax.inject.Named;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ViewScoped;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author fran
 */
@Named(value = "addUserCarsBean")
@RequestScoped
public class addUserCarsBean implements Serializable {
    
    @Inject
    CarsFacade carsEjb;
    final static String gasolina = "gasolina", gasoleo = "gasoleo", biodiesel_ = "biodiesel", bioetanol_ = "bioetanol";
    private String manufacturingDateTxt, insertCarDateTxt, numberPlate, carModel, fuelType;
    private List<SelectItem> productsListCb;
    private Calendar insertCarDate;
    private Date manufacturingDate;
    @Inject
    UsersFacade userEjb;
    @Inject
    NavigationBean navigationBean;

    /**
     * Creates a new instance of addUserCarsBean
     */
    Utils utils = new Utils();
    
    @PostConstruct
    public void init() {
        productsListCb = new ArrayList<>();
        productsListCb.add(new SelectItem("", "Select one*:"));
        productsListCb.add(new SelectItem(gasolina, gasolina));
//        productsListCb.add(new SelectItem(gasolina98_, gasolina98_));
        productsListCb.add(new SelectItem(gasoleo, gasoleo));
//        productsListCb.add(new SelectItem(nuevoGasoleo_A, nuevoGasoleo_A));
        productsListCb.add(new SelectItem(biodiesel_, biodiesel_));
        productsListCb.add(new SelectItem(bioetanol_, bioetanol_));
        
        System.out.println("products added to combobox");
        
    }
    
    public CarsFacade getCarsEjb() {
        return carsEjb;
    }
    
    public void setCarsEjb(CarsFacade carsEjb) {
        this.carsEjb = carsEjb;
    }
    
    public List<SelectItem> getProductsListCb() {
        return productsListCb;
    }
    
    public void setProductsListCb(List<SelectItem> productsListCb) {
        this.productsListCb = productsListCb;
    }
    
    public Utils getUtils() {
        return utils;
    }
    
    public void setUtils(Utils utils) {
        this.utils = utils;
    }
    
    public String getCarModel() {
        return carModel;
    }
    
    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
    
    public String getManufacturingDateTxt() {
        return manufacturingDateTxt;
    }
    
    public void setManufacturingDateTxt(String manufacturingDateTxt) {
        this.manufacturingDateTxt = manufacturingDateTxt;
    }
    
    public String getInsertCarDateTxt() {
        return insertCarDateTxt;
    }
    
    public void setInsertCarDateTxt(String insertCarDateTxt) {
        this.insertCarDateTxt = insertCarDateTxt;
    }
    
    public Calendar getInsertCarDate() {
        return insertCarDate;
    }
    
    public void setInsertCarDate(Calendar insertCarDate) {
        this.insertCarDate = insertCarDate;
    }
    
    public String getNumberPlate() {
        return numberPlate;
    }
    
    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }
    
    public String getFuelType() {
        return fuelType;
    }
    
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
    
    public Date getManufacturingDate() {
        return manufacturingDate;
    }
    
    public void setManufacturingDate(Date manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }
    
    public void verifyCarModel(FacesContext context, UIComponent toValidate, Object value) {
        utils.nullValidationComponet(context, toValidate, value, "carModelNull");
    }
    
    public void verifyOilSelection(FacesContext context, UIComponent toValidate, Object value) {
        utils.nullValidationComponet(context, toValidate, value, "oilSelectionNull");
//        if (value == "" || value == null) {
//            String loadMessage = utils.loadMessage("oilSelectionNull");
//            utils.messageToUicomponent(context, toValidate, loadMessage);
//        }
    }
    
    public void verifyManufacturingDate(FacesContext context, UIComponent toValidate, Object value) {
        try {
            if (value.toString().isEmpty()) {
                utils.nullValidationComponet(context, toValidate, value, "manufacturingDateNull");
            } else {
//                String date = value.toString();
//                getDateFromString(date);

                Calendar.getInstance().setTime((Date) value);
            }
            
        } catch (Exception e) {
            utils.messageToUicomponent(context, toValidate, utils.loadMessage("dateFormatError"));
        }
        
    }

//    public Date getDateFromString(String dateTxt) {
//        String[] splitDate = dateTxt.split("/");
//        manufacturingDate = Calendar.getInstance();
//        manufacturingDate.set(Calendar.DAY_OF_MONTH, new Integer(splitDate[0]));
//        manufacturingDate.set(Calendar.MONTH, new Integer(splitDate[1]) - 1);
//        manufacturingDate.set(Calendar.YEAR, new Integer(splitDate[2]));
//        return manufacturingDate.getTime();
//    }
    public void verityNumberPlate(FacesContext context, UIComponent toValidate, Object value) {
        utils.nullValidationComponet(context, toValidate, value, "numberPlateNull");
    }
    
    public void createCar(ActionEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Principal userPrincipal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            Cars car = new Cars();
            car.setCarModel(getCarModel());
            car.setCarDate(getManufacturingDate());
            car.setDateRegistered(new Date());
            car.setFuelType(getFuelType());
            Users user = userEjb.getUserbyUsername(userPrincipal.getName());
            car.setCarsPK(new CarsPK(getNumberPlate(), user.getUserID()));
            user.getCarsCollection().add(car);
            userEjb.edit(user);
            //carsEjb.create(car);
            setCarModel(null);
            setFuelType(null);
            setManufacturingDate(null);
            setNumberPlate(null);
            System.out.println("Coche añadido correctamente.");
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Coche guardado", "El coche se ha añadido correctamente a la base de datos."));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Coche no guardado", "No se ha podido grabar su coche en la base de datos. Inténtelo de nuevo."));
        }
        
    }

    public void reset() {
        setCarModel(null);
    }
}
