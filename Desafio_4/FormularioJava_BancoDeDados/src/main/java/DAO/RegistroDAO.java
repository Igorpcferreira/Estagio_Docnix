package DAO;

import java.sql.*;

import Controller.ControllerContato;
import Model.Registro;

public class RegistroDAO {
    ConnectionFactory con = new ConnectionFactory();
    ControllerContato controllerContato = new ControllerContato();

    public int incluir(Registro registro) throws SQLException {
        Connection con = ConnectionFactory.criarConexao();
        int aux = 0;
        con.setAutoCommit(false);
        String sql = "INSERT INTO registro ( nome, email, cpf, endereco, telefone, escolaridade, genero, datadenascimento) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
        int idMaster = 0;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, registro.getNome());
            preparedStatement.setString(2, registro.getEmail());
            preparedStatement.setString(3, registro.getCpf());
            preparedStatement.setString(4, registro.getEndereco());
            preparedStatement.setString(5, registro.getTelefone());
            preparedStatement.setString(6, registro.getEscolaridade());
            preparedStatement.setString(7, registro.getGenero());
            preparedStatement.setString(8, registro.getDataDeNascimento());
            preparedStatement.execute();


            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                idMaster = resultSet.getInt("id_registro");
            }
            registro.setID(String.valueOf(idMaster));
            con.commit();


        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
        }


        return idMaster;
    }

    public static void listar() throws SQLException{
        Connection con = ConnectionFactory.criarConexao();

        String sql = "SELECT * FROM registro";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                Registro registro = new Registro();
                registro.setID(String.valueOf(rst.getInt("id_registro")));
                registro.setNome(rst.getString("nome"));
                registro.setEmail(rst.getString("email"));
                registro.setCpf(rst.getString("cpf"));
                registro.setEndereco(rst.getString("endereco"));
                registro.setTelefone(rst.getString("telefone"));
                registro.setEscolaridade(rst.getString("escolaridade"));
                registro.setGenero(rst.getString("genero"));
                registro.setDataDeNascimento(rst.getString("datadenascimento"));
                registro.setArrayList(ControllerContato.listar(Integer.valueOf(registro.getID())));
                System.out.println(registro.toString());
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





    public int excluir(String id) throws SQLException {
        Connection con = ConnectionFactory.criarConexao();
        con.setAutoCommit(false);
        String sql = "DELETE FROM registro WHERE id_registro = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
            preparedStatement.setInt(1, Integer.valueOf(id));
            preparedStatement.executeUpdate();
            con.commit();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
        }
        return Integer.valueOf(id);
    }

    public static void editar(String id, Registro registro) throws SQLException{

        Connection con = ConnectionFactory.criarConexao();

        String sql = "UPDATE registro" +
                " SET nome = ?, email = ?, cpf = ?, endereco = ?, telefone = ?, escolaridade = ?, genero = ?, datadenascimento = ?" +
                " WHERE id_registro = ?;";
        try(PreparedStatement preparedStatement = con.prepareStatement(sql)){
            preparedStatement.setString(1, registro.getNome());
            preparedStatement.setString(2, registro.getEmail());
            preparedStatement.setString(3, registro.getCpf());
            preparedStatement.setString(4, registro.getEndereco());
            preparedStatement.setString(5, registro.getTelefone());
            preparedStatement.setString(6, registro.getEscolaridade());
            preparedStatement.setString(7, registro.getGenero());
            preparedStatement.setString(8, registro.getDataDeNascimento());
            preparedStatement.setInt(9,Integer.valueOf(id));
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            con.rollback();
        }

    }

}
