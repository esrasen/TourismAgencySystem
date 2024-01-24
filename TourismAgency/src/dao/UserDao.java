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


    public ArrayList<User> searchForTable (String role){
        String select = "SELECT * FROM public.user ";
        ArrayList<String> whereList = new ArrayList<>();

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


    public boolean save(User user){
        String query = "INSERT INTO public.user(user_name, password, role, name, surname) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setString(1, user.getUserName());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getRole().toString());
            pr.setString(4, user.getName());
            pr.setString(5, user.getSurname());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(User user){
        String query = "UPDATE public.user SET user_name=?, password=?, role=?, name=?, surname=? WHERE id=?";
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setString(1, user.getUserName());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getRole().toString());
            pr.setString(4, user.getName());
            pr.setString(5, user.getSurname());
            pr.setInt(6, user.getId());
            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int id){
        String query = "DELETE FROM public.user WHERE id=?";
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public User getById(int id){
        User obj = null;
        String query = "SELECT * FROM public.user WHERE id = ?";
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
