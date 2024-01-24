package business;

import core.Helper;
import dao.UserDao;
import entity.User;
import entity.UserRole;

import java.util.ArrayList;

public class UserManager {

    private  final UserDao userDao;

    public UserManager(){
        this.userDao = new UserDao();
    }
    public User findByLogin(String username, String password){
        return this.userDao.findByLogin(username, password);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<User> users) {
        ArrayList<Object[]> userRowList = new ArrayList<>();
        for (User user : users) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = user.getId();
            rowObject[i++] = user.getName();
            rowObject[i++] = user.getSurname();
            rowObject[i++] = user.getPassword();
            rowObject[i++] = user.getRole();

            userRowList.add(rowObject);
        }
        return userRowList;
    }

    public ArrayList<User> findAll() {
        return this.userDao.findAll();
    }

    public boolean save(User user) {
        if (this.getById(user.getId()) != null) {
            Helper.showMsg("error");
            return false;
        }
        return this.userDao.save(user);
    }

    public boolean update(User user) {
        if (this.getById(user.getId()) == null) {
            return false;
        }
        return this.userDao.update(user);
    }

    public boolean delete(int id) {
        if (this.getById(id) == null) {
            return false;
        }
        return this.userDao.delete(id);
    }

    public User getById(int id) {
        return this.userDao.getById(id);
    }


    public ArrayList<User> searchForTable(String role) {
        return this.userDao.searchForTable(role);
    }
}

