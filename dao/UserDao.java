package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.User;

public class UserDao extends User {

  public void register(String login, String password, String name, String sector, String birth, String CPF){
        String sql = "INSERT INTO USR" +
                    "VALUES (?,?,?,?,?,?)";

        try{
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement smt = con.prepareStatement(sql);

                smt.setString(1, login);
                smt.setString(2, password);
                smt.setString(3,  name);
                smt.setString(4, sector);
                smt.setString(5, birth);
                smt.setString(6, CPF);
            smt.executeUpdate(); // Executa Comando no SQL
            smt.close(); // Finaliza o PreparedStatement
            con.close(); // Finaliza a Conexão com o BD
        } catch (SQLException e){
            System.out.println("Error ao Registrar o Usuario!");
        }
    }

    public List<model.User> getAll(){
        List<model.User> users = null;
        String sql = "SELECT *" +
                    "FROM USR";

        try{
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement smt = con.prepareStatement(sql);
            ResultSet rs;

            rs = smt.executeQuery(); // Executa Comando no SQL e rs recebe os valores

            if(rs!=null){
                while(rs.next()){
                    model.User u = new User();
                    u.setLogin(rs.getString("USR_LOGIN"));
                    u.setPassword(rs.getString("USR_SENHA"));
                    u.setName(rs.getString("USR_NAME"));
                    u.setDepartment(rs.getString("USR_STR_COD"));
                    u.setBirthDate(rs.getString("USR_BIRTH"));
                    u.setCPF(rs.getString("USR_CPF"));
                    users.add(u);
                }
            }

            rs.close(); // Finaliza os dados da Query
            smt.close(); // Finaliza o PreparedStatement
            con.close(); // Finaliza a Conexão com o BD
        } catch (SQLException e){
            System.out.println("Error ao Buscar Todos os Usuarios!");
        }

        return users;
    }

}
