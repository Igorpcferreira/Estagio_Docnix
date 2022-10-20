package Controller;
import DAO.ContatoDAO;
import Model.Contato;

import java.sql.SQLException;
import java.util.ArrayList;

public class ControllerContato {

    public void incluir(ArrayList<Contato> arrayList, int idMaster) throws SQLException {
        ContatoDAO.incluir(arrayList, idMaster);
    }

    public static ArrayList<Contato> listar(int id) throws SQLException{
        return ContatoDAO.listar(id);
    }

    public static int excluir(String ID) throws SQLException {
        return ContatoDAO.excluir(ID);
    }

    public static void editar(String id, Contato contato) throws SQLException{
        ContatoDAO.editar(id,contato);
    }

}
