/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.ejbBeans;

import com.oilPrices.entities.Products;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fran
 */
@Stateless
public class ProductsFacade extends AbstractFacade<Products> {
    @PersistenceContext(unitName = "com.mycompany_oilPrices_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductsFacade() {
        super(Products.class);
    }
     public Products getLastProduct(String lastProduct,String oilStationId){
        return (Products) getEntityManager().createNamedQuery("Products.findLastProduct")
                .setParameter(oilStationId, "oilStationidOilStation")
                               .setParameter(new Date().toString(), "fecha")
                 .setParameter(lastProduct, lastProduct).getResultList().get(0);
    }
    
}
