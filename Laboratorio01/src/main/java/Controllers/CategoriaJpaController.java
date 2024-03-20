/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Entities.Categoria;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entities.Juego;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author martinsanabria
 */
public class CategoriaJpaController implements Serializable {

     public CategoriaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("juegosPU");
    }
    public CategoriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;


    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Categoria categoria) {
        if (categoria.getJuegoCollection() == null) {
            categoria.setJuegoCollection(new ArrayList<Juego>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Juego> attachedJuegoCollection = new ArrayList<Juego>();
            for (Juego juegoCollectionJuegoToAttach : categoria.getJuegoCollection()) {
                juegoCollectionJuegoToAttach = em.getReference(juegoCollectionJuegoToAttach.getClass(), juegoCollectionJuegoToAttach.getIdjuego());
                attachedJuegoCollection.add(juegoCollectionJuegoToAttach);
            }
            categoria.setJuegoCollection(attachedJuegoCollection);
            em.persist(categoria);
            for (Juego juegoCollectionJuego : categoria.getJuegoCollection()) {
                Categoria oldIdcategoriaOfJuegoCollectionJuego = juegoCollectionJuego.getIdcategoria();
                juegoCollectionJuego.setIdcategoria(categoria);
                juegoCollectionJuego = em.merge(juegoCollectionJuego);
                if (oldIdcategoriaOfJuegoCollectionJuego != null) {
                    oldIdcategoriaOfJuegoCollectionJuego.getJuegoCollection().remove(juegoCollectionJuego);
                    oldIdcategoriaOfJuegoCollectionJuego = em.merge(oldIdcategoriaOfJuegoCollectionJuego);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Categoria categoria) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria persistentCategoria = em.find(Categoria.class, categoria.getIdcategoria());
            Collection<Juego> juegoCollectionOld = persistentCategoria.getJuegoCollection();
            Collection<Juego> juegoCollectionNew = categoria.getJuegoCollection();
            List<String> illegalOrphanMessages = null;
            for (Juego juegoCollectionOldJuego : juegoCollectionOld) {
                if (!juegoCollectionNew.contains(juegoCollectionOldJuego)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Juego " + juegoCollectionOldJuego + " since its idcategoria field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Juego> attachedJuegoCollectionNew = new ArrayList<Juego>();
            for (Juego juegoCollectionNewJuegoToAttach : juegoCollectionNew) {
                juegoCollectionNewJuegoToAttach = em.getReference(juegoCollectionNewJuegoToAttach.getClass(), juegoCollectionNewJuegoToAttach.getIdjuego());
                attachedJuegoCollectionNew.add(juegoCollectionNewJuegoToAttach);
            }
            juegoCollectionNew = attachedJuegoCollectionNew;
            categoria.setJuegoCollection(juegoCollectionNew);
            categoria = em.merge(categoria);
            for (Juego juegoCollectionNewJuego : juegoCollectionNew) {
                if (!juegoCollectionOld.contains(juegoCollectionNewJuego)) {
                    Categoria oldIdcategoriaOfJuegoCollectionNewJuego = juegoCollectionNewJuego.getIdcategoria();
                    juegoCollectionNewJuego.setIdcategoria(categoria);
                    juegoCollectionNewJuego = em.merge(juegoCollectionNewJuego);
                    if (oldIdcategoriaOfJuegoCollectionNewJuego != null && !oldIdcategoriaOfJuegoCollectionNewJuego.equals(categoria)) {
                        oldIdcategoriaOfJuegoCollectionNewJuego.getJuegoCollection().remove(juegoCollectionNewJuego);
                        oldIdcategoriaOfJuegoCollectionNewJuego = em.merge(oldIdcategoriaOfJuegoCollectionNewJuego);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = categoria.getIdcategoria();
                if (findCategoria(id) == null) {
                    throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria categoria;
            try {
                categoria = em.getReference(Categoria.class, id);
                categoria.getIdcategoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Juego> juegoCollectionOrphanCheck = categoria.getJuegoCollection();
            for (Juego juegoCollectionOrphanCheckJuego : juegoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Categoria (" + categoria + ") cannot be destroyed since the Juego " + juegoCollectionOrphanCheckJuego + " in its juegoCollection field has a non-nullable idcategoria field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(categoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Categoria> findCategoriaEntities() {
        return findCategoriaEntities(true, -1, -1);
    }

    public List<Categoria> findCategoriaEntities(int maxResults, int firstResult) {
        return findCategoriaEntities(false, maxResults, firstResult);
    }

    private List<Categoria> findCategoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categoria.class));
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

    public Categoria findCategoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categoria> rt = cq.from(Categoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
