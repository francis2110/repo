/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.jpa.controllers;

import apponsaresmanagement.jpa.controllers.exceptions.IllegalOrphanException;
import apponsaresmanagement.jpa.controllers.exceptions.NonexistentEntityException;
import apponsaresmanagement.jpa.controllers.exceptions.PreexistingEntityException;
import apponsaresmanagement.jpa.entities.Cliente;
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
public class ClienteJpaController implements Serializable {

    public ClienteJpaController() {
        this.emf = javax.persistence.Persistence.createEntityManagerFactory("OnsaresPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) throws PreexistingEntityException, Exception {
        if (cliente.getViajeCollection() == null) {
            cliente.setViajeCollection(new ArrayList<Viaje>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Viaje> attachedViajeCollection = new ArrayList<Viaje>();
            for (Viaje viajeCollectionViajeToAttach : cliente.getViajeCollection()) {
                viajeCollectionViajeToAttach = em.getReference(viajeCollectionViajeToAttach.getClass(), viajeCollectionViajeToAttach.getViajePK());
                attachedViajeCollection.add(viajeCollectionViajeToAttach);
            }
            cliente.setViajeCollection(attachedViajeCollection);
            em.persist(cliente);
            for (Viaje viajeCollectionViaje : cliente.getViajeCollection()) {
                Cliente oldClienteOfViajeCollectionViaje = viajeCollectionViaje.getCliente();
                viajeCollectionViaje.setCliente(cliente);
                viajeCollectionViaje = em.merge(viajeCollectionViaje);
                if (oldClienteOfViajeCollectionViaje != null) {
                    oldClienteOfViajeCollectionViaje.getViajeCollection().remove(viajeCollectionViaje);
                    oldClienteOfViajeCollectionViaje = em.merge(oldClienteOfViajeCollectionViaje);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCliente(cliente.getCifNif()) != null) {
                throw new PreexistingEntityException("Cliente " + cliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getCifNif());
            Collection<Viaje> viajeCollectionOld = persistentCliente.getViajeCollection();
            Collection<Viaje> viajeCollectionNew = cliente.getViajeCollection();
            List<String> illegalOrphanMessages = null;
            for (Viaje viajeCollectionOldViaje : viajeCollectionOld) {
                if (!viajeCollectionNew.contains(viajeCollectionOldViaje)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Viaje " + viajeCollectionOldViaje + " since its cliente field is not nullable.");
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
            cliente.setViajeCollection(viajeCollectionNew);
            cliente = em.merge(cliente);
            for (Viaje viajeCollectionNewViaje : viajeCollectionNew) {
                if (!viajeCollectionOld.contains(viajeCollectionNewViaje)) {
                    Cliente oldClienteOfViajeCollectionNewViaje = viajeCollectionNewViaje.getCliente();
                    viajeCollectionNewViaje.setCliente(cliente);
                    viajeCollectionNewViaje = em.merge(viajeCollectionNewViaje);
                    if (oldClienteOfViajeCollectionNewViaje != null && !oldClienteOfViajeCollectionNewViaje.equals(cliente)) {
                        oldClienteOfViajeCollectionNewViaje.getViajeCollection().remove(viajeCollectionNewViaje);
                        oldClienteOfViajeCollectionNewViaje = em.merge(oldClienteOfViajeCollectionNewViaje);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cliente.getCifNif();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getCifNif();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Viaje> viajeCollectionOrphanCheck = cliente.getViajeCollection();
            for (Viaje viajeCollectionOrphanCheckViaje : viajeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Viaje " + viajeCollectionOrphanCheckViaje + " in its viajeCollection field has a non-nullable cliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public List<Cliente> findClienteByName(String name) {
        EntityManager em = getEntityManager();
        return em.createNamedQuery("Cliente.findByNombre").setParameter("nombre", name).getResultList();
    }

    public List<Cliente> findClientesContainsName(String name) {
        EntityManager em = getEntityManager();
        name = "%" + name + "%";
        return em.createNamedQuery("Cliente.containsName").setParameter("nombre", name).getResultList();
    }
    
}
