package view;

import business.UserManager;
import core.ComboItem;
import core.Helper;
import entity.User;
import entity.UserRole;

import javax.swing.*;

public class UserView extends Layout{
    private JPanel container;
    private JTextField fld_user_name;
    private JTextField fld_user_pass;
    private JComboBox cmb_user_role;
    private JTextField fld_name;
    private JTextField fld_surname;
    private JButton btn_user_save;
    private UserManager userManager;
    private User user;


    public UserView(User user) {
        this.userManager = new UserManager();
        this.user = user;
        this.add(container);
        this.guiInitilaze(600, 400);



        for (User users : this.userManager.findAll()){
            this.cmb_user_role.addItem(new ComboItem(users.getId(), users.getRole().name()));
        }
        this.cmb_user_role.setModel(new DefaultComboBoxModel<>(UserRole.values()));

        if (this.user.getId() != 0) {
            this.fld_user_name.setText(this.user.getUserName());
            this.fld_user_pass.setText(this.user.getPassword());
            this.cmb_user_role.setSelectedItem(this.user.getRole());
            this.fld_name.setText(this.user.getName());
            this.fld_surname.setText(this.user.getSurname());
            ComboItem defaultUser = new ComboItem(this.user.getId(), this.user.getRole().name());
            this.cmb_user_role.getModel().setSelectedItem(defaultUser);
        }
        this.btn_user_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[] {this.fld_user_name, this.fld_user_pass, this.fld_name, this.fld_surname})){
                Helper.showMsg("fill");
            }else {
                boolean result ;
                UserRole selectedUser ;
                if (cmb_user_role.getSelectedItem() instanceof ComboItem){
                    selectedUser =user.getRole();
                }else {
                    selectedUser = (UserRole) cmb_user_role.getSelectedItem();
                }
                String roleValue = selectedUser.name();
                UserRole selectedUserRole = UserRole.valueOf(UserRole.class, roleValue);
                this.user.setUserName(fld_user_name.getText());
                this.user.setPassword(fld_user_pass.getText());
                this.user.setRole(selectedUserRole);
                this.user.setName(fld_name.getText());
                this.user.setSurname(fld_surname.getText());

                if (this.user.getId() == 0) {
                    result = this.userManager.save(this.user);
                }else {
                    result = this.userManager.update(this.user);
                }
                if (result){
                    Helper.showMsg("done");
                    this.dispose();
                }else {
                    Helper.showMsg("error");
                }
            }
        });
    }


}
