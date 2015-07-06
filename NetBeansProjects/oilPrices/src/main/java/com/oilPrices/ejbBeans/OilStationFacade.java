/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.ejbBeans;

import com.oilPrices.entities.OilStation;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fran
 */
@Stateless
public class OilStationFacade extends AbstractFacade<OilStation> {
    @PersistenceContext(unitName = "com.mycompany_oilPrices_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OilStationFacade() {
        super(OilStation.class);
    }
    public OilStation oilStationExists(OilStation o) {
        List<OilStation> oList = em.createNamedQuery("OilStation.stationExists").
                setParameter("rotulo",o.getRotulo()).
                setParameter("latitud",o.getLatitud()).
                setParameter("longitud",o.getLongitud()).getResultList();
        if(oList.isEmpty())return null;
        return oList.get(0);

    }
}
