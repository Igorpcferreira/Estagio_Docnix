package Util;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class JpaUtil {
    public static EntityManager getEntityManager(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        return em;
}


}
