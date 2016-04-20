/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.jpa.controllers;

import apponsaresmanagement.jpa.controllers.exceptions.NonexistentEntityException;
import apponsaresmanagement.jpa.controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import apponsaresmanagement.jpa.entities.Transportista;
import apponsaresmanagement.jpa.entities.Cliente;
import apponsaresmanagement.jpa.entities.Viaje;
import apponsaresmanagement.jpa.entities.ViajePK;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author fran
 */
public class ViajeJpaController implements Serializable {

    public ViajeJpaController() {
        this.emf = javax.persistence.Persistence.createEntityManagerFactory("OnsaresPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Viaje viaje) throws PreexistingEntityException, Exception {
        if (viaje.getViajePK() == null) {
            viaje.setViajePK(new ViajePK());
        }
        viaje.getViajePK().setTransportistaNIF(viaje.getTransportista().getNif());
        viaje.getViajePK().setClienteCifNif(viaje.getCliente().getCifNif());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Transportista transportista = viaje.getTransportista();
            if (transportista != null) {
                transportista = em.getReference(transportista.getClass(), transportista.getNif());
                viaje.setTransportista(transportista);
            }
            Cliente cliente = viaje.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getCifNif());
                viaje.setCliente(cliente);
            }
            em.persist(viaje);
            if (transportista != null) {
                transportista.getViajeCollection().add(viaje);
                transportista = em.merge(transportista);
            }
            if (cliente != null) {
                cliente.getViajeCollection().add(viaje);
                cliente = em.merge(cliente);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findViaje(viaje.getViajePK()) != null) {
                throw new PreexistingEntityException("Viaje " + viaje + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Viaje viaje) throws NonexistentEntityException, Exception {
        viaje.getViajePK().setTransportistaNIF(viaje.getTransportista().getNif());
        viaje.getViajePK().setClienteCifNif(viaje.getCliente().getCifNif());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Viaje persistentViaje = em.find(Viaje.class, viaje.getViajePK());
            Transportista transportistaOld = persistentViaje.getTransportista();
            Transportista transportistaNew = viaje.getTransportista();
            Cliente clienteOld = persistentViaje.getCliente();
            Cliente clienteNew = viaje.getCliente();
            if (transportistaNew != null) {
                transportistaNew = em.getReference(transportistaNew.getClass(), transportistaNew.getNif());
                viaje.setTransportista(transportistaNew);
            }
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getCifNif());
                viaje.setCliente(clienteNew);
            }
            viaje = em.merge(viaje);
            if (transportistaOld != null && !transportistaOld.equals(transportistaNew)) {
                transportistaOld.getViajeCollection().remove(viaje);
                transportistaOld = em.merge(transportistaOld);
            }
            if (transportistaNew != null && !transportistaNew.equals(transportistaOld)) {
                transportistaNew.getViajeCollection().add(viaje);
                transportistaNew = em.merge(transportistaNew);
            }
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getViajeCollection().remove(viaje);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getViajeCollection().add(viaje);
                clienteNew = em.merge(clienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ViajePK id = viaje.getViajePK();
                if (findViaje(id) == null) {
                    throw new NonexistentEntityException("The viaje with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ViajePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Viaje viaje;
            try {
                viaje = em.getReference(Viaje.class, id);
                viaje.getViajePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The viaje with id " + id + " no longer exists.", enfe);
            }
            Transportista transportista = viaje.getTransportista();
            if (transportista != null) {
                transportista.getViajeCollection().remove(viaje);
                transportista = em.merge(transportista);
            }
            Cliente cliente = viaje.getCliente();
            if (cliente != null) {
                cliente.getViajeCollection().remove(viaje);
                cliente = em.merge(cliente);
            }
            em.remove(viaje);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Viaje> findViajeEntities() {
        return findViajeEntities(true, -1, -1);
    }

    public List<Viaje> findViajeEntities(int maxResults, int firstResult) {
        return findViajeEntities(false, maxResults, firstResult);
    }

    private List<Viaje> findViajeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Viaje.class));
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

    public Viaje findViaje(ViajePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Viaje.class, id);
        } finally {
            em.close();
        }
    }

    public int getViajeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Viaje> rt = cq.from(Viaje.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Viaje> findTravelsByDriverNif(String Nif) {
        EntityManager em = getEntityManager();
        return em.createNamedQuery("Viaje.findByTransportistaNIF").setParameter("transportistaNIF", Nif).getResultList();

    }

    public int getTravelsInYear(String year) {
        //gets the number of travels in a year
        EntityManager em = getEntityManager();
        Calendar initDate = Calendar.getInstance();
        initDate.set(Calendar.DAY_OF_MONTH, 31);
        initDate.set(Calendar.MONTH, 11);
        initDate.set(Calendar.YEAR, Integer.parseInt(year)-1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(Calendar.DAY_OF_MONTH, 1);
        endDate.set(Calendar.MONTH, 0);
        endDate.set(Calendar.YEAR, Integer.parseInt(year)+1);
        return ((Number) em.createNamedQuery("Viaje.getYearTravels")
                .setParameter("initDate", initDate.getTime())
                .setParameter("endDate", endDate.getTime()).getSingleResult()).intValue();
    }

    public List<Viaje> getClientTravelsInPeriod(String clientNif, Date initDate, Date endDate) {
        //method that returns given travels by client in a period of time
        EntityManager em = getEntityManager();
        return em.createNamedQuery("Viaje.getClientTravelsByDate")
                .setParameter("clientNif", clientNif)
                .setParameter("initDate", initDate)
                .setParameter("endDate", endDate).getResultList();
    }

    public List<Viaje> getDriverTravelsinPeriod(String driverNif, Date initDate, Date endDate) {
        //method that returns done travels by driver:
        EntityManager em = getEntityManager();
        return em.createNamedQuery("Viaje.getDriverTravelsByDate")
                .setParameter("driverNif", driverNif)
                .setParameter("initDate", initDate)
                .setParameter("endDate", endDate).getResultList();
    }

    public List<Viaje> getTravelsByContainer(String containerNum) {
        EntityManager em = getEntityManager();
        return em.createNamedQuery("Viaje.findByNumeroContenedor").setParameter("numeroContenedor", containerNum).getResultList();
    }

    public List<Viaje> getUnpaidTravels(String clientState) {
        EntityManager em = getEntityManager();
        return em.createNamedQuery("Viaje.findByEstadoCliente").setParameter("estadoCliente", clientState).getResultList();
    }

    public List<Viaje> getTravelsOfBill(String idFactura) {
        EntityManager em = getEntityManager();
        return em.createNamedQuery("Viaje.getTravelsByBillCode").setParameter("idFactura", idFactura).getResultList();
    }

    public List<Viaje> getTravelByAlbaran(String albaran) {
        EntityManager em = getEntityManager();
        return em.createNamedQuery("Viaje.findByAlbaran").setParameter("albaran", albaran).getResultList();
    }
}
