package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Contato;

public class ContatoDAO {
    ConnectionFactory con = new ConnectionFactory();


    public static void incluir(ArrayList<Contato> arrayList, int idMaster) throws SQLException {

        Connection con = ConnectionFactory.criarConexao();
        try {
            String sql = "INSERT INTO contato (id_registro, nome, email, telefone) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            if ((arrayList.isEmpty())) {
                preparedStatement.setInt(1, idMaster);
                preparedStatement.setString(2, " ");
                preparedStatement.setString(3, " ");
                preparedStatement.setString(4, " ");
                preparedStatement.executeUpdate();
            } else {
                for (int i = 0; i < arrayList.size(); i++) {
                    preparedStatement.setInt(1, idMaster);
                    preparedStatement.setString(2, arrayList.get(i).getNomeContato());
                    preparedStatement.setString(3, arrayList.get(i).getEmailContato());
                    preparedStatement.setString(4, arrayList.get(i).getTelefoneContato());
                    preparedStatement.executeUpdate();
                }
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static ArrayList<Contato> listar(int id) throws SQLException{
        ArrayList<Contato> arrayList = new ArrayList();
        Connection con = ConnectionFactory.criarConexao();

        String sql = "SELECT * FROM contato where id_registro = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                Contato contato = new Contato();
                contato.setID((String.valueOf(rst.getInt("id_contato")))); //rever
                contato.setNomeContato(rst.getString("nome"));
                contato.setEmailContato(rst.getString("email"));
                contato.setTelefoneContato(rst.getString("telefone"));
                arrayList.add(contato);
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arrayList;
    }

    public static int excluir(String id) throws SQLException{
        Connection con = ConnectionFactory.criarConexao();
        con.setAutoCommit(false);
        String sql = ("DELETE FROM contato WHERE id_registro = ?");

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            preparedStatement.execute();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
        }
        return Integer.parseInt(id);
    }

    public static void editar(String id, Contato contato) throws SQLException {
        Connection con = ConnectionFactory.criarConexao();

        String sql = "UPDATE contato" +
                " SET nome = ?, email = ?, telefone = ?" +
                " WHERE id_registro = ? AND id_contato = ?;";
        try(PreparedStatement preparedStatement = con.prepareStatement(sql)){
            preparedStatement.setString(1, contato.getNomeContato());
            preparedStatement.setString(2, contato.getEmailContato());
            preparedStatement.setString(3, contato.getTelefoneContato());
            preparedStatement.setInt(4,Integer.valueOf(id));
            preparedStatement.setInt(5,Integer.valueOf(contato.getID()));
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            con.rollback();
        }
    }
}
