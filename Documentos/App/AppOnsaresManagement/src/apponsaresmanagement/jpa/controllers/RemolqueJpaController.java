/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.jpa.controllers;

import apponsaresmanagement.jpa.controllers.exceptions.IllegalOrphanException;
import apponsaresmanagement.jpa.controllers.exceptions.NonexistentEntityException;
import apponsaresmanagement.jpa.controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import apponsaresmanagement.jpa.entities.Mantenimiento;
import apponsaresmanagement.jpa.entities.Remolque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author fran
 */
public class RemolqueJpaController implements Serializable {

    public RemolqueJpaController() {
        this.emf = javax.persistence.Persistence.createEntityManagerFactory("OnsaresPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Remolque remolque) throws PreexistingEntityException, Exception {
        if (remolque.getMantenimientoCollection() == null) {
            remolque.setMantenimientoCollection(new ArrayList<Mantenimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Mantenimiento> attachedMantenimientoCollection = new ArrayList<Mantenimiento>();
            for (Mantenimiento mantenimientoCollectionMantenimientoToAttach : remolque.getMantenimientoCollection()) {
                mantenimientoCollectionMantenimientoToAttach = em.getReference(mantenimientoCollectionMantenimientoToAttach.getClass(), mantenimientoCollectionMantenimientoToAttach.getIdMantenimiento());
                attachedMantenimientoCollection.add(mantenimientoCollectionMantenimientoToAttach);
            }
            remolque.setMantenimientoCollection(attachedMantenimientoCollection);
            em.persist(remolque);
            for (Mantenimiento mantenimientoCollectionMantenimiento : remolque.getMantenimientoCollection()) {
                Remolque oldRemolquematriculaOfMantenimientoCollectionMantenimiento = mantenimientoCollectionMantenimiento.getRemolquematricula();
                mantenimientoCollectionMantenimiento.setRemolquematricula(remolque);
                mantenimientoCollectionMantenimiento = em.merge(mantenimientoCollectionMantenimiento);
                if (oldRemolquematriculaOfMantenimientoCollectionMantenimiento != null) {
                    oldRemolquematriculaOfMantenimientoCollectionMantenimiento.getMantenimientoCollection().remove(mantenimientoCollectionMantenimiento);
                    oldRemolquematriculaOfMantenimientoCollectionMantenimiento = em.merge(oldRemolquematriculaOfMantenimientoCollectionMantenimiento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRemolque(remolque.getMatricula()) != null) {
                throw new PreexistingEntityException("Remolque " + remolque + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Remolque remolque) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Remolque persistentRemolque = em.find(Remolque.class, remolque.getMatricula());
            Collection<Mantenimiento> mantenimientoCollectionOld = persistentRemolque.getMantenimientoCollection();
            Collection<Mantenimiento> mantenimientoCollectionNew = remolque.getMantenimientoCollection();
            List<String> illegalOrphanMessages = null;
            for (Mantenimiento mantenimientoCollectionOldMantenimiento : mantenimientoCollectionOld) {
                if (!mantenimientoCollectionNew.contains(mantenimientoCollectionOldMantenimiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mantenimiento " + mantenimientoCollectionOldMantenimiento + " since its remolquematricula field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Mantenimiento> attachedMantenimientoCollectionNew = new ArrayList<Mantenimiento>();
            for (Mantenimiento mantenimientoCollectionNewMantenimientoToAttach : mantenimientoCollectionNew) {
                mantenimientoCollectionNewMantenimientoToAttach = em.getReference(mantenimientoCollectionNewMantenimientoToAttach.getClass(), mantenimientoCollectionNewMantenimientoToAttach.getIdMantenimiento());
                attachedMantenimientoCollectionNew.add(mantenimientoCollectionNewMantenimientoToAttach);
            }
            mantenimientoCollectionNew = attachedMantenimientoCollectionNew;
            remolque.setMantenimientoCollection(mantenimientoCollectionNew);
            remolque = em.merge(remolque);
            for (Mantenimiento mantenimientoCollectionNewMantenimiento : mantenimientoCollectionNew) {
                if (!mantenimientoCollectionOld.contains(mantenimientoCollectionNewMantenimiento)) {
                    Remolque oldRemolquematriculaOfMantenimientoCollectionNewMantenimiento = mantenimientoCollectionNewMantenimiento.getRemolquematricula();
                    mantenimientoCollectionNewMantenimiento.setRemolquematricula(remolque);
                    mantenimientoCollectionNewMantenimiento = em.merge(mantenimientoCollectionNewMantenimiento);
                    if (oldRemolquematriculaOfMantenimientoCollectionNewMantenimiento != null && !oldRemolquematriculaOfMantenimientoCollectionNewMantenimiento.equals(remolque)) {
                        oldRemolquematriculaOfMantenimientoCollectionNewMantenimiento.getMantenimientoCollection().remove(mantenimientoCollectionNewMantenimiento);
                        oldRemolquematriculaOfMantenimientoCollectionNewMantenimiento = em.merge(oldRemolquematriculaOfMantenimientoCollectionNewMantenimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = remolque.getMatricula();
                if (findRemolque(id) == null) {
                    throw new NonexistentEntityException("The remolque with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Remolque remolque;
            try {
                remolque = em.getReference(Remolque.class, id);
                remolque.getMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The remolque with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Mantenimiento> mantenimientoCollectionOrphanCheck = remolque.getMantenimientoCollection();
            for (Mantenimiento mantenimientoCollectionOrphanCheckMantenimiento : mantenimientoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Remolque (" + remolque + ") cannot be destroyed since the Mantenimiento " + mantenimientoCollectionOrphanCheckMantenimiento + " in its mantenimientoCollection field has a non-nullable remolquematricula field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(remolque);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Remolque> findRemolqueEntities() {
        return findRemolqueEntities(true, -1, -1);
    }

    public List<Remolque> findRemolqueEntities(int maxResults, int firstResult) {
        return findRemolqueEntities(false, maxResults, firstResult);
    }

    private List<Remolque> findRemolqueEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Remolque.class));
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

    public Remolque findRemolque(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Remolque.class, id);
        } finally {
            em.close();
        }
    }

    public int getRemolqueCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Remolque> rt = cq.from(Remolque.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
