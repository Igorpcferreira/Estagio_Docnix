package Controller;
import DAO.RegistroDAO;
import Model.Registro;

import java.sql.SQLException;

public class ControllerRegistro {
    static RegistroDAO registroDAO = new RegistroDAO();
    public static int incluir(Registro registro) throws SQLException {
        return registroDAO.incluir(registro);

    }

    public void listar() throws SQLException{
        RegistroDAO.listar();
    }

    public int excluir(String id) throws SQLException{
        return registroDAO.excluir(id);
    }

    public void editar (String id, Registro registro) throws SQLException{
        RegistroDAO.editar(id,registro);
    }
}

