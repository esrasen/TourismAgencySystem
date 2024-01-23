package dao;

import core.DbConnection;

import java.sql.*;
import java.util.ArrayList;

import entity.User;
import entity.UserRole;

public class UserDao extends BaseDao{
    public UserDao() {
        super();
    }

    public ArrayList<User> findAll() {
        ArrayList<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM public.user ORDER BY id ASC";
        try {
            ResultSet rs = this.conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                userList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }



    public User findByLogin(String username, String password) {
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_name = ? AND password = ?";
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setString(1, username);
            pr.setString(2, password);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public User match(ResultSet rs) throws SQLException {
        User obj = new User();
        obj.setId(rs.getInt("id"));
        obj.setUserName(rs.getString("user_name"));
        obj.setPassword(rs.getString("password"));
        obj.setRole(UserRole.valueOf(UserRole.class, rs.getString("role")));
        obj.setName(rs.getString("name"));
        obj.setSurname(rs.getString("surname"));

        return obj;
    }


    public ArrayList<User> searchForTable (int userId, String role){
        String select = "SELECT * FROM public.user ";
        ArrayList<String> whereList = new ArrayList<>();

        if (userId != 0){
            whereList.add("id = " + userId);
        }
        if (role != null){
            whereList.add("role = '" + role + "'");
        }
        String whereStr = String.join(" AND ", whereList);
        String query = select ;
        if(whereStr.length() > 0){
            query += " WHERE " + whereStr;
        }
        return selectByQuery(query);
    }

public ArrayList<User> selectByQuery(String query) {
        ArrayList<User> userList = new ArrayList<>();
        try {
            ResultSet rs = this.conn.createStatement().executeQuery(query);
            while (rs.next()) {
                userList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
