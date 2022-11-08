package Controller;
import DAO.DaoRegistro;
import Model.RegistroDataForm;

import javax.persistence.EntityManager;


public class ControllerRegistro {
    DaoRegistro daoRegistro = new DaoRegistro();

    public void incluirRegistro(RegistroDataForm registroDataForm){
        daoRegistro.incluir(registroDataForm);
    }

    public void listarRegistro(EntityManager em){
        DaoRegistro.listarRegistro(em);
    }

    public void excluirRegistro(Integer id, RegistroDataForm registroDataForm){
        daoRegistro.excluir(id, registroDataForm);
    }

    public void editarRegistro(RegistroDataForm registroDataForm){
        daoRegistro.editar(registroDataForm);
    }

}

