package DAO.Generics;
import Util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public abstract class DaoGenericsExec <T> {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("FormularioJava_Servlet");


    public void incluir(T entity) {
        EntityManager em = JpaUtil.getEntityManager(emf);
        em.getTransaction().begin();
        em.persist(entity);
        em.flush();
        em.getTransaction().commit();
    }



    public void editar(T entity) {
        EntityManager em = JpaUtil.getEntityManager(emf);
        em.getTransaction().begin();
        em.merge(entity);
        em.flush();
        em.getTransaction().commit();
    }


    public void excluir(Integer id, T entity) {
        EntityManager em = JpaUtil.getEntityManager(emf);
        Object objetoARemover = em.find(entity.getClass(), id);
        em.getTransaction().begin();
        em.remove(objetoARemover);
        em.getTransaction().commit();
    }
}
