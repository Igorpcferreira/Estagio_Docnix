package DAO.Generics;
import Util.*;

import javax.persistence.EntityManager;

public abstract class DaoGenericsExec <T> {
    EntityManager em = JpaUtil.getEntityManager();

    public void incluir(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.flush();
        em.getTransaction().commit();

    }



    public void editar(T entity) {

        em.getTransaction().begin();
        em.merge(entity);
        em.flush();
        em.getTransaction().commit();
    }


    public void excluir(Integer id, T entity) {
        Object objetoARemover = em.find(entity.getClass(), id);

        em.getTransaction().begin();
        em.remove(objetoARemover);
        em.getTransaction().commit();
    }
}
