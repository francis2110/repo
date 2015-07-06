/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.ejbBeans;

import com.oilPrices.entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author fran
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "com.mycompany_oilPrices_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }

    public Users getUserbyUsername(String userName) {
        List resultList = getEntityManager().createNamedQuery("Users.findByUsername").
                setParameter("username", userName).getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        return (Users) resultList.get(0);
    }
   

}
