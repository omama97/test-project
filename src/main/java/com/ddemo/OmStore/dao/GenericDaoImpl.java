package com.ddemo.OmStore.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class GenericDaoImpl<T> implements GenericDao<T> {

    private static EntityManagerFactory emf = null;
    private final Class<T> entityClass;

    public GenericDaoImpl(Class<T> entityClass) {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
        createEntityManagerFactory();
        this.entityClass = entityClass;
        System.out.println("entityClass =========== " + entityClass);
    }

    @Override
    public void create(T t) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void edit(T t) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void remove(Object obj) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Object object = em.find(entityClass, obj);
            em.remove(object);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public T find(Object obj) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            T t = em.find(entityClass, obj);
            t.toString();
            em.getTransaction().commit();
            return t;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<T> findall() throws Exception {
        return findEntities(true, -1, -1);
    }

    private List<T> findEntities(boolean all, int maxResult, int firstResult) throws Exception {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResult);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<T> findEntities(int maxResult, int firstResult) throws Exception {
        return findEntities(false, maxResult, firstResult);
    }

    @Override
    public List<T> findAllById(String tableName, int id) throws Exception {
        EntityManager em = getEntityManager();
        Query q = em.createNativeQuery("SELECT * FROM " + tableName + " WHERE id = :emp_id", entityClass);
        q.setParameter("emp_id", id);
        return q.getResultList();
    }

    private static void createEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("OmStoreUnit");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

	

}
