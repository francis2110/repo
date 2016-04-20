/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.ejbBeans;

import com.oilPrices.entities.Cars;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fran
 */
@Stateless
public class CarsFacade extends AbstractFacade<Cars> {

    @PersistenceContext(unitName = "com.mycompany_oilPrices_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarsFacade() {
        super(Cars.class);
    }

    public List<Cars> getCarsByMatricula(String serialNumber) {
        List resultList = em.createNamedQuery("Cars.findByMatricula").
                setParameter("matricula", serialNumber).getResultList();
        if (resultList.size() > 0) {
            return resultList;
        } else {
            return null;
        }
    }

}
