package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
   //Estabelecendo a conexão
    public static Connection criarConexao() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Formulario_BD_Igor", "postgres", "123");
        return con;
    }
}