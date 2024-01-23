package view;

import business.UserManager;
import core.ComboItem;
import entity.User;
import entity.UserRole;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminView extends Layout{
    private UserManager userManager;
    private User user;
    private JPanel container;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JTable tbl_user;
    private JButton btn_filter;
    private JComboBox cmb_role_filter;
    private JPanel w_top;
    private JPanel w_bottom;
    private Object[] col_user ;
    private DefaultTableModel tmdl_user= new DefaultTableModel();



    public AdminView(User user) {
        this.add(container);
        this.guiInitilaze(800, 600);
        this.user = user;
        this.userManager = new UserManager();
        if (this.user == null) {
            dispose();
        }


        this.lbl_welcome.setText(this.lbl_welcome.getText() + this.user.getUserName());
        loadComponent();
        loadUserTable(null);
        loadUserFilter();
        btn_filter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadUserComponent();
            }
        });
    }



    private void loadComponent () {
        this.btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginView loginView = new LoginView();
            }
        });
    }

    private void loadUserTable (ArrayList<Object[]> userList) {
        this.col_user = new Object[]{"ID", "Name", "Surname", "Password", "Role"};
       if (userList == null) {
           userList = this.userManager.getForTable(col_user.length, this.userManager.findAll());
       }
       createTable(this.tmdl_user, this.tbl_user, this.col_user, userList);
    }

    public void loadUserFilter() {

        this.cmb_role_filter.setModel(new DefaultComboBoxModel<>(UserRole.values()));
        this.cmb_role_filter.setSelectedItem(null);
        loadUserFilterRole();

    }
    public void loadUserComponent() {

        ComboItem roleFilterSelectedItem = (ComboItem) this.cmb_role_filter.getSelectedItem();
        int userId = 0;
        if (roleFilterSelectedItem != null){
            userId = roleFilterSelectedItem.getKey();
        }
        ArrayList<User> userListBySearch = this.userManager.searchForTable(
                userId,
                ((ComboItem) this.cmb_role_filter.getSelectedItem()).getValue());


        ArrayList<Object[]> modelRowListBySearch = this.userManager.getForTable(this.col_user.length, userListBySearch);
        loadUserTable(modelRowListBySearch);
    }
    public void loadUserFilterRole() {
        this.cmb_role_filter.removeAllItems();
        for(User role : userManager.findAll()) {
           this.cmb_role_filter.addItem(new ComboItem(role.getId(), role.getRole().name()));
        }
        this.cmb_role_filter.setSelectedItem(null);
    }





}
