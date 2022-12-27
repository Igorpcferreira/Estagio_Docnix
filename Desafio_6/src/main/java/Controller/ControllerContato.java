package Controller;
import DAO.*;
import Model.*;


import javax.persistence.EntityManager;
import java.util.List;


public class ControllerContato {
    DaoContato daoContato = new DaoContato();

    public void incluir(Contato contato){
        daoContato.incluir(contato);
    }
    public List<Contato> listar(Integer indexDaListaDeFormulario, EntityManager em){
        return daoContato.listar(indexDaListaDeFormulario, em);
    }
    public void editar(Contato contato){
        daoContato.editar(contato);
    }
    public void excluir(Integer ID,Contato contato ){
        daoContato.excluir(ID, contato);
    }
}
