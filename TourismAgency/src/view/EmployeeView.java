package view;

import business.HotelManager;
import business.UserManager;
import entity.Hotel;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EmployeeView extends Layout{
    private JTabbedPane tab_menu;
    private JButton btn_logout;
    private JTable tbl_hotel;
    private JPanel container;
    private JPanel pnl_top;
    private JPanel pnl_hotel;
    private JScrollPane scl_hotel;
    private JTabbedPane tabbedPane1;
    private JLabel lbl_welcome;
    private Object[] col_hotel;
    private HotelManager hotelManager;
    private User user;
    private UserManager userManager;
    private Hotel hotel;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private JPopupMenu hotelMenu;


    public EmployeeView(User user) {
        this.add(container);
        this.hotelManager = new HotelManager();
        this.hotel = hotel;
        this.user = user;
        this.userManager = new UserManager();
        tab_menu.setTabPlacement(JTabbedPane.LEFT);
        this.guiInitilaze(1200, 600);


        this.lbl_welcome.setText(this.lbl_welcome.getText() + this.user.getUserName());
        loadComponent();
        loadHotelTable();
        loadHotelComponent();
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


    public void loadHotelTable() {
        Object[] col_hotel ={"ID", "Adı", "Şehir", "Bölge", "Adres", "e-mail", "Telefon", "Yıldız","Otopark", "WiFi", "Havuz", "Spa", "Fitness", "Konsiyerj", "Oda Servisi"};
        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel.length);
        this.createTable(this.tmdl_hotel, this.tbl_hotel, col_hotel, hotelList);

    }
    public void loadHotelComponent() {
        tableRowSelect(this.tbl_hotel);
        this.hotelMenu = new JPopupMenu();
        this.hotelMenu.add("Yeni Otel Ekle").addActionListener(e -> {
            HotelView hotelView = new HotelView(null);
            hotelView.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    loadHotelTable();
                }
            });

        });


        this.tbl_hotel.setComponentPopupMenu(this.hotelMenu);

    }





}
