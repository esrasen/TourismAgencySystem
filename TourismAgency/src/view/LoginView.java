package view;

import business.UserManager;
import core.Helper;
import entity.User;
import entity.UserRole;

import javax.swing.*;

public class LoginView extends Layout{
    private JPanel container;
    private JLabel lbl_welcome;
    private JPanel w_top;
    private JPanel w_bottom;
    private JTextField fld_username;
    private JPasswordField fld_password;
    private JButton btn_login;
    private JLabel lbl_username;
    private JLabel lbl_password;
    private final UserManager userManager;

    public LoginView() {
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitilaze(400, 400);
        btn_login.addActionListener(e -> {

            JTextField[] checkFieldList = {this.fld_username, this.fld_password};

            //Değerlendirme formu 9

            if ( Helper.isFieldListEmpty(checkFieldList) ) {
                Helper.showMsg("fill");
            }else {
                User loginUser = this.userManager.findByLogin(this.fld_username.getText(), this.fld_password.getText());
                if (loginUser == null){
                    Helper.showMsg("notFound");
                }else {
                    if (loginUser.getRole().equals(UserRole.ADMIN)) {
                        // Admin girişi
                        AdminView adminView = new AdminView(loginUser);
                        adminView.setVisible(true);
                        dispose();
                    } else if (loginUser.getRole().equals(UserRole.EMPLOYEE)) {
                        // Employee girişi
                        EmployeeView employeeView = new EmployeeView(loginUser);
                       employeeView.setVisible(true);
                        dispose();
                    } else {
                        Helper.showMsg("unknownRole");
                    }
                }
            }
        });
    }
}