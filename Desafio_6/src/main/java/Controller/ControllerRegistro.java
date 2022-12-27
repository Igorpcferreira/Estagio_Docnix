package Controller;
import DAO.*;
import Model.RegistroDataForm;


import javax.persistence.EntityManager;
import java.util.List;


public class ControllerRegistro {
    DaoRegistroForm daoRegistroForm = new DaoRegistroForm();

    public void incluir(RegistroDataForm registroDataForm){
        daoRegistroForm.incluir(registroDataForm);
    }
    public List<RegistroDataForm> listar(EntityManager em){
        return daoRegistroForm.listar(em);
    }
    public void editar(RegistroDataForm registroDataForm){
        daoRegistroForm.editar(registroDataForm);
    }
    public void excluir(Integer id, RegistroDataForm dados){
        daoRegistroForm.excluir(id, dados);
    }
}

