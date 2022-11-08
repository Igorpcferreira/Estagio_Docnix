package DAO;

import Controller.ControllerContato;
import DAO.Generics.DaoGenericsExec;
import Model.*;
import Util.JpaUtil;
import javax.persistence.EntityManager;
import java.util.List;

public class DaoRegistro extends DaoGenericsExec <RegistroDataForm> {

    static ControllerContato controllerContato = new ControllerContato();
    public static void listarRegistro(EntityManager entityManager) {
        EntityManager em = JpaUtil.getEntityManager();

        List<RegistroDataForm> listaDadosRegistro =
                entityManager.createQuery("Select dadosFormulario from RegistroDataForm as dadosFormulario")
                .getResultList();

        List<Contato> listaDeContatos = null;

        for (int i = 0; i < listaDadosRegistro.size(); i++) {
            listaDeContatos = controllerContato.listarContato(listaDadosRegistro.get(i).getIdRegistro(), entityManager);
            listaDadosRegistro.get(i).setListadeContato(listaDeContatos);
            System.out.println(listaDadosRegistro.get(i).toString());
        }

    }

}
