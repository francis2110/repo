/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.jpa.controllers;

import apponsaresmanagement.jpa.controllers.exceptions.NonexistentEntityException;
import apponsaresmanagement.jpa.entities.Mantenimiento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import apponsaresmanagement.jpa.entities.Remolque;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author fran
 */
public class MantenimientoJpaController implements Serializable {

    public MantenimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mantenimiento mantenimiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Remolque remolquematricula = mantenimiento.getRemolquematricula();
            if (remolquematricula != null) {
                remolquematricula = em.getReference(remolquematricula.getClass(), remolquematricula.getMatricula());
                mantenimiento.setRemolquematricula(remolquematricula);
            }
            em.persist(mantenimiento);
            if (remolquematricula != null) {
                remolquematricula.getMantenimientoCollection().add(mantenimiento);
                remolquematricula = em.merge(remolquematricula);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mantenimiento mantenimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mantenimiento persistentMantenimiento = em.find(Mantenimiento.class, mantenimiento.getIdMantenimiento());
            Remolque remolquematriculaOld = persistentMantenimiento.getRemolquematricula();
            Remolque remolquematriculaNew = mantenimiento.getRemolquematricula();
            if (remolquematriculaNew != null) {
                remolquematriculaNew = em.getReference(remolquematriculaNew.getClass(), remolquematriculaNew.getMatricula());
                mantenimiento.setRemolquematricula(remolquematriculaNew);
            }
            mantenimiento = em.merge(mantenimiento);
            if (remolquematriculaOld != null && !remolquematriculaOld.equals(remolquematriculaNew)) {
                remolquematriculaOld.getMantenimientoCollection().remove(mantenimiento);
                remolquematriculaOld = em.merge(remolquematriculaOld);
            }
            if (remolquematriculaNew != null && !remolquematriculaNew.equals(remolquematriculaOld)) {
                remolquematriculaNew.getMantenimientoCollection().add(mantenimiento);
                remolquematriculaNew = em.merge(remolquematriculaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mantenimiento.getIdMantenimiento();
                if (findMantenimiento(id) == null) {
                    throw new NonexistentEntityException("The mantenimiento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mantenimiento mantenimiento;
            try {
                mantenimiento = em.getReference(Mantenimiento.class, id);
                mantenimiento.getIdMantenimiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mantenimiento with id " + id + " no longer exists.", enfe);
            }
            Remolque remolquematricula = mantenimiento.getRemolquematricula();
            if (remolquematricula != null) {
                remolquematricula.getMantenimientoCollection().remove(mantenimiento);
                remolquematricula = em.merge(remolquematricula);
            }
            em.remove(mantenimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mantenimiento> findMantenimientoEntities() {
        return findMantenimientoEntities(true, -1, -1);
    }

    public List<Mantenimiento> findMantenimientoEntities(int maxResults, int firstResult) {
        return findMantenimientoEntities(false, maxResults, firstResult);
    }

    private List<Mantenimiento> findMantenimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mantenimiento.class));
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

    public Mantenimiento findMantenimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mantenimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getMantenimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mantenimiento> rt = cq.from(Mantenimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
