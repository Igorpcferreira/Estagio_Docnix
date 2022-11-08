package DAO;

import DAO.Generics.DaoGenericsExec;
import Model.*;
import Util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class DaoContato extends DaoGenericsExec <Contato> {
    public static List<Contato> listarContato(int idRegistroData, EntityManager entityManager){
        EntityManager em = JpaUtil.getEntityManager();



        Query consultaContatos = entityManager
                .createQuery("Select dadosContatos from Contato as dadosContatos where id_registro_data = :id");
        List<Contato> listaDeContatos = null;


        consultaContatos.setParameter("id", idRegistroData);
        listaDeContatos = consultaContatos.getResultList();
        return listaDeContatos;
    }

}
