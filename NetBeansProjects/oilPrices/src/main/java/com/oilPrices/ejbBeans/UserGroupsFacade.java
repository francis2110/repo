/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.ejbBeans;

import com.oilPrices.entities.UserGroups;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fran
 */
@Stateless
public class UserGroupsFacade extends AbstractFacade<UserGroups> {
    @PersistenceContext(unitName = "com.mycompany_oilPrices_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserGroupsFacade() {
        super(UserGroups.class);
    }
    
}
