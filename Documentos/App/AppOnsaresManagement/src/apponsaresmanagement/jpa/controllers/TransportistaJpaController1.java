/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.jpa.controllers;

import apponsaresmanagement.jpa.controllers.exceptions.IllegalOrphanException;
import apponsaresmanagement.jpa.controllers.exceptions.NonexistentEntityException;
import apponsaresmanagement.jpa.controllers.exceptions.PreexistingEntityException;
import apponsaresmanagement.jpa.entities.Transportista;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import apponsaresmanagement.jpa.entities.Viaje;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author fran
 */
public class TransportistaJpaController1 implements Serializable {

    public TransportistaJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Transportista transportista) throws PreexistingEntityException, Exception {
        if (transportista.getViajeCollection() == null) {
            transportista.setViajeCollection(new ArrayList<Viaje>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Viaje> attachedViajeCollection = new ArrayList<Viaje>();
            for (Viaje viajeCollectionViajeToAttach : transportista.getViajeCollection()) {
                viajeCollectionViajeToAttach = em.getReference(viajeCollectionViajeToAttach.getClass(), viajeCollectionViajeToAttach.getViajePK());
                attachedViajeCollection.add(viajeCollectionViajeToAttach);
            }
            transportista.setViajeCollection(attachedViajeCollection);
            em.persist(transportista);
            for (Viaje viajeCollectionViaje : transportista.getViajeCollection()) {
                Transportista oldTransportistaOfViajeCollectionViaje = viajeCollectionViaje.getTransportista();
                viajeCollectionViaje.setTransportista(transportista);
                viajeCollectionViaje = em.merge(viajeCollectionViaje);
                if (oldTransportistaOfViajeCollectionViaje != null) {
                    oldTransportistaOfViajeCollectionViaje.getViajeCollection().remove(viajeCollectionViaje);
                    oldTransportistaOfViajeCollectionViaje = em.merge(oldTransportistaOfViajeCollectionViaje);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTransportista(transportista.getNif()) != null) {
                throw new PreexistingEntityException("Transportista " + transportista + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Transportista transportista) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Transportista persistentTransportista = em.find(Transportista.class, transportista.getNif());
            Collection<Viaje> viajeCollectionOld = persistentTransportista.getViajeCollection();
            Collection<Viaje> viajeCollectionNew = transportista.getViajeCollection();
            List<String> illegalOrphanMessages = null;
            for (Viaje viajeCollectionOldViaje : viajeCollectionOld) {
                if (!viajeCollectionNew.contains(viajeCollectionOldViaje)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Viaje " + viajeCollectionOldViaje + " since its transportista field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Viaje> attachedViajeCollectionNew = new ArrayList<Viaje>();
            for (Viaje viajeCollectionNewViajeToAttach : viajeCollectionNew) {
                viajeCollectionNewViajeToAttach = em.getReference(viajeCollectionNewViajeToAttach.getClass(), viajeCollectionNewViajeToAttach.getViajePK());
                attachedViajeCollectionNew.add(viajeCollectionNewViajeToAttach);
            }
            viajeCollectionNew = attachedViajeCollectionNew;
            transportista.setViajeCollection(viajeCollectionNew);
            transportista = em.merge(transportista);
            for (Viaje viajeCollectionNewViaje : viajeCollectionNew) {
                if (!viajeCollectionOld.contains(viajeCollectionNewViaje)) {
                    Transportista oldTransportistaOfViajeCollectionNewViaje = viajeCollectionNewViaje.getTransportista();
                    viajeCollectionNewViaje.setTransportista(transportista);
                    viajeCollectionNewViaje = em.merge(viajeCollectionNewViaje);
                    if (oldTransportistaOfViajeCollectionNewViaje != null && !oldTransportistaOfViajeCollectionNewViaje.equals(transportista)) {
                        oldTransportistaOfViajeCollectionNewViaje.getViajeCollection().remove(viajeCollectionNewViaje);
                        oldTransportistaOfViajeCollectionNewViaje = em.merge(oldTransportistaOfViajeCollectionNewViaje);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = transportista.getNif();
                if (findTransportista(id) == null) {
                    throw new NonexistentEntityException("The transportista with id " + id + " no longer exists.");
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
            Transportista transportista;
            try {
                transportista = em.getReference(Transportista.class, id);
                transportista.getNif();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transportista with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Viaje> viajeCollectionOrphanCheck = transportista.getViajeCollection();
            for (Viaje viajeCollectionOrphanCheckViaje : viajeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Transportista (" + transportista + ") cannot be destroyed since the Viaje " + viajeCollectionOrphanCheckViaje + " in its viajeCollection field has a non-nullable transportista field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(transportista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Transportista> findTransportistaEntities() {
        return findTransportistaEntities(true, -1, -1);
    }

    public List<Transportista> findTransportistaEntities(int maxResults, int firstResult) {
        return findTransportistaEntities(false, maxResults, firstResult);
    }

    private List<Transportista> findTransportistaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Transportista.class));
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

    public Transportista findTransportista(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Transportista.class, id);
        } finally {
            em.close();
        }
    }

    public int getTransportistaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Transportista> rt = cq.from(Transportista.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
