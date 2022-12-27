package DAO;

import DAO.Generics.DaoGenericsExec;
import Model.*;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class DaoContato extends DaoGenericsExec <Contato> {
    public static List<Contato> listar(int idRegistroData, EntityManager entityManager){


            Query consultaDadosAlternativos = entityManager.createQuery("Select dadosContatos from Contato as dadosContatos where id_registro_data = :id");
            List<Contato> listaDeContatos= null;
            consultaDadosAlternativos.setParameter("id", idRegistroData);
            listaDeContatos = consultaDadosAlternativos.getResultList();
            entityManager.close();

            return listaDeContatos;

    }

}
