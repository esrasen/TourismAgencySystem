package view;

import business.UserManager;
import core.ComboItem;
import core.Helper;
import entity.User;
import entity.UserRole;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout {
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
    private JButton btn_cncl;
    private JLabel fld_img;
    private JLabel fld_user_name;
    private JPopupMenu userMenu;
    private Object[] col_user;
    private DefaultTableModel tmdl_user = new DefaultTableModel();



    public AdminView(User user) {
        this.add(container);
        this.guiInitilaze(800, 600);
        this.user = user;
        this.userManager = new UserManager();
        if (this.user == null) {
            dispose();
        }


        this.lbl_welcome.setText(this.lbl_welcome.getText());
        this.fld_user_name.setText(this.user.getUserName());
        loadComponent();
        loadUserTable(null);
        loadUserFilter();
        loadUserInitComponent();
        btn_filter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadUserComponent();
            }
        });
        btn_cncl.addActionListener(e -> {
            this.cmb_role_filter.setSelectedItem(null);
            loadUserTable(null);
        });
    }


    private void loadComponent() {
        this.btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginView loginView = new LoginView();
            }
        });
    }

    private void loadUserTable(ArrayList<Object[]> userList) {
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

        ArrayList<User> userListBySearch = this.userManager.searchForTable(

                ((ComboItem) this.cmb_role_filter.getSelectedItem()).getValue());

        ArrayList<Object[]> modelRowListBySearch = this.userManager.getForTable(this.col_user.length, userListBySearch);
        loadUserTable(modelRowListBySearch);


    }

    public void loadUserInitComponent() {


        tableRowSelect(this.tbl_user);
        this.userMenu = new JPopupMenu();
        this.userMenu.add("Yeni").addActionListener(e -> {
            UserView userView = new UserView(new User());
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });
        this.userMenu.add("Düzenle").addActionListener(e -> {
            if (this.tbl_user.getSelectedRow() == -1) {
                Helper.showMsg("select");
            } else {
                int selectedRow = tbl_user.getSelectedRow();
                int selectedId = (int) tbl_user.getValueAt(selectedRow, 0);
                UserView userView = new UserView(this.userManager.getById(selectedId));
                userView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadUserTable(null);
                    }
                });
            }
        });
        this.userMenu.add("Sil").addActionListener(e -> {
            if (this.tbl_user.getSelectedRow() == -1) {
                Helper.showMsg("select");
            } else {
                int selectedRow = tbl_user.getSelectedRow();
                int selectedId = (int) tbl_user.getValueAt(selectedRow, 0);
                if (this.userManager.delete(selectedId)) {
                    Helper.showMsg("done");
                    loadUserTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_user.setComponentPopupMenu(this.userMenu);

    }

    public void loadUserFilterRole() {
        this.cmb_role_filter.removeAllItems();
        for (UserRole role : UserRole.values()) {
            this.cmb_role_filter.addItem(new ComboItem(role.ordinal(), role.name()));
        }
        this.cmb_role_filter.setSelectedItem(null);
    }

}
