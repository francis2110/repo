/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.jsf.ManagedBeans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import javax.faces.bean.ManagedBean;


/**
 *
 * @author fran
 */

@Named(value = "resetBean")
@RequestScoped
public class resetBean{

   private  String resetTxt;

    /**
     * Creates a new instance of resetBean
     */
    @PostConstruct
    public void init() {
        System.out.println("Se mete en resetBean.");
    }

    public String getResetTxt() {
        return resetTxt;
    }

    public void setResetTxt(String resetTxt) {
        this.resetTxt = resetTxt;
    }

    public void reset() {
        String s = getResetTxt();
        setResetTxt(null);
    }
}
