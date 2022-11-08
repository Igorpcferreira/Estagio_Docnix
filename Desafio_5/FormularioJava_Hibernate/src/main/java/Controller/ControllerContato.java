package Controller;
import DAO.*;
import Model.*;

import javax.persistence.EntityManager;
import java.util.List;


public class ControllerContato {
    DaoContato daoContato = new DaoContato();

    public void incluirContato(Contato contato){
        daoContato.incluir(contato);
    }

    public List<Contato> listarContato(int idRegistro, EntityManager em){
        return DaoContato.listarContato(idRegistro, em);
    }

    public void excluirContato(Integer id, Contato contato){
        daoContato.excluir(id, contato);
    }

    public void editarContato(Contato contato){
        daoContato.editar(contato);
    }
}
