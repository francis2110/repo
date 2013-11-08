/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.jpa.controllers;

import apponsaresmanagement.jpa.controllers.exceptions.NonexistentEntityException;
import apponsaresmanagement.jpa.controllers.exceptions.PreexistingEntityException;
import apponsaresmanagement.jpa.entities.Averias;
import apponsaresmanagement.jpa.entities.AveriasPK;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author fran
 */
public class AveriasJpaController implements Serializable {

    public AveriasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Averias averias) throws PreexistingEntityException, Exception {
        if (averias.getAveriasPK() == null) {
            averias.setAveriasPK(new AveriasPK());
        }
        averias.getAveriasPK().setRemolquematricula(averias.getRemolque().getMatricula());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(averias);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAverias(averias.getAveriasPK()) != null) {
                throw new PreexistingEntityException("Averias " + averias + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Averias averias) throws NonexistentEntityException, Exception {
        averias.getAveriasPK().setRemolquematricula(averias.getRemolque().getMatricula());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            averias = em.merge(averias);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AveriasPK id = averias.getAveriasPK();
                if (findAverias(id) == null) {
                    throw new NonexistentEntityException("The averias with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AveriasPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Averias averias;
            try {
                averias = em.getReference(Averias.class, id);
                averias.getAveriasPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The averias with id " + id + " no longer exists.", enfe);
            }
            em.remove(averias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Averias> findAveriasEntities() {
        return findAveriasEntities(true, -1, -1);
    }

    public List<Averias> findAveriasEntities(int maxResults, int firstResult) {
        return findAveriasEntities(false, maxResults, firstResult);
    }

    private List<Averias> findAveriasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Averias.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Averias findAverias(AveriasPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Averias.class, id);
        } finally {
            em.close();
        }
    }

    public int getAveriasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Averias> rt = cq.from(Averias.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
