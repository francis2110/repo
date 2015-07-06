/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.ejbBeans;

import com.oilPrices.entities.OilStation;
import com.oilPrices.entities.Precios;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fran
 */
@Stateless
public class PreciosFacade extends AbstractFacade<Precios> {

    @PersistenceContext(unitName = "com.mycompany_oilPrices_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PreciosFacade() {
        super(Precios.class);
    }

    public List<Precios> getPreciosByDate(OilStation o, Date d) {
        return getEntityManager().createNamedQuery("Precios.findByDateandOilStation").
                setParameter("productsoilStationidOilStation", o.getIdOilStation()).
                setParameter("updateDate", d).getResultList();

    }

    public List<Precios> getproductoPricesByDate(OilStation o, Date d, String prodName) {
        return getEntityManager().createNamedQuery("Precios.findProductsByDate").
                setParameter("productsoilStationidOilStation", o.getIdOilStation()).
                setParameter("updateDate", d).
                setParameter("prodName", prodName).getResultList();
    }

}
