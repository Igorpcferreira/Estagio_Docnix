package Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//Isolar a criação do EntityManeger e esconder o código do EntityManagerFactory
public class JpaUtil {
    private static EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("FormularioJava_Hibernate");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }


}
