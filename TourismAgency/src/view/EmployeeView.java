package view;

import business.HotelManager;
import business.PensionTypeManager;
import business.SeasonManager;
import business.UserManager;
import core.ComboItem;
import core.Helper;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EmployeeView extends Layout{
    private JTabbedPane tab_menu;
    private JButton btn_logout;
    private JTable tbl_hotel;
    private JTable tbl_season;
    private JPanel container;
    private JPanel pnl_top;
    private JPanel pnl_hotel;
    private JScrollPane scl_hotel;
    private JLabel lbl_welcome;
    private JPanel pnl_season;
    private JComboBox cmb_hotel_filter;
    private JButton btn_save_season;
    private JButton btn_cncl_season;
    private Object[] col_hotel;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionType pensionType;
    private PensionTypeManager pensionTypeManager;
    private Season season;
    private User user;
    private UserManager userManager;
    private Hotel hotel;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_season = new DefaultTableModel();
    private DefaultTableModel tmdl_pension = new DefaultTableModel();
    private JPopupMenu hotelMenu;
    private JFormattedTextField fld_strt_date;
    private JFormattedTextField fld_end_date;
    private JPanel pnl_pension_type;
    private JComboBox cmb_pension_hotel_filter;
    private JComboBox<PensionOption> cmb_pension_type_filter;
    private JTable tbl_pension;
    private JButton btn_pension_save;
    private JPanel pnl_room;
    private JPanel pnl_reservation;
    private JButton btn_cncl_pension;


    public EmployeeView(User user) {
        this.add(container);
        this.hotelManager = new HotelManager();
        this.hotel = hotel;
        this.seasonManager = new SeasonManager();
        this.pensionTypeManager = new PensionTypeManager();
        this.pensionType = pensionType;
        this.pensionType = new PensionType();

        this.season = new Season();
        this.season = season;
        this.user = user;
        this.userManager = new UserManager();
        tab_menu.setTabPlacement(JTabbedPane.LEFT);
        this.guiInitilaze(1200, 600);


        this.lbl_welcome.setText(this.lbl_welcome.getText() + this.user.getUserName());
        loadComponent();
        loadHotelTable();
        loadHotelComponent();
        loadSeasonTable(null);
        loadSeasonFilterHotel();
        loadSeasonComponent();
        loadPensionTable(null);
        loadPensionFilterHotel();
        loadPensionComponent();

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


    public void loadSeasonTable(ArrayList<Object[]> seasonList) {
        Object[] col_season ={"ID", "Otel ID", "Başlangıç Tarihi ","Bitiş Tarihi "};
        if (seasonList == null){
            seasonList = this.seasonManager.getForTable(col_season.length, this.seasonManager.findAll());
        }
        this.createTable(this.tmdl_season, this.tbl_season, col_season, seasonList);

    }
    public void loadSeasonFilterHotel() {
        this.cmb_hotel_filter.removeAllItems();
        for (Hotel obj : this.hotelManager.findAll()){
            this.cmb_hotel_filter.addItem(new ComboItem(obj.getId(), obj.getHotelName()));
        }
        this.cmb_hotel_filter.setSelectedItem(null);
    }
    public void loadSeasonComponent() {
        tableRowSelect(this.tbl_season);

        btn_save_season.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[] {this.fld_strt_date, this.fld_end_date})){
                Helper.showMsg("fill");
            }else {
                boolean result ;
                ComboItem selectedHotel = (ComboItem) this.cmb_hotel_filter.getSelectedItem();
                this.season.setHotelId(selectedHotel.getKey());
                this.season.setStartDate(LocalDate.parse(this.fld_strt_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                this.season.setEndDate(LocalDate.parse(this.fld_end_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                if (this.season.getId() == 0) {
                    result = this.seasonManager.save(this.season);
                }else {
                    result = this.seasonManager.update(this.season);
                }
                if (result){
                    Helper.showMsg("done");
                    loadSeasonTable(null);
                }else {
                    Helper.showMsg("error");
                }
            }
        });

        btn_cncl_season.addActionListener(e -> {
            loadSeasonFilterHotel();
        });

    }


    public void loadPensionTable(ArrayList<Object[]> pensionList) {
        Object[] col_pension ={"ID", "Otel ID", "Pansiyon Tipi"};
        if (pensionList == null) {
            pensionList = this.pensionTypeManager.getForTable(col_pension.length, this.pensionTypeManager.findAll());
        }
        this.createTable(this.tmdl_pension, this.tbl_pension, col_pension, pensionList);
        }
    public void loadPensionFilterHotel() {
        this.cmb_pension_hotel_filter.removeAllItems();
        for (Hotel obj : this.hotelManager.findAll()){
            this.cmb_pension_hotel_filter.addItem(new ComboItem(obj.getId(), obj.getHotelName()));
        }
        this.cmb_pension_hotel_filter.setSelectedItem(null);
    }
    public void loadPensionComponent() {
        tableRowSelect(this.tbl_pension);

        this.cmb_pension_type_filter.setModel(new DefaultComboBoxModel<>(PensionOption.values()));
        cmb_pension_type_filter.setSelectedItem(null);

        btn_pension_save.addActionListener(e -> {

            //String str = this.cmb_pension_type_filter.getSelectedItem().toString();
            if (Helper.isFieldListEmpty(new JTextField[] {})){
                Helper.showMsg("fill");
            }else {
                boolean result ;
                ComboItem selectedHotel = (ComboItem) this.cmb_pension_hotel_filter.getSelectedItem();
                this.pensionType.setHotelId(selectedHotel.getKey());
                this.pensionType.setPensionOption((PensionOption) this.cmb_pension_type_filter.getSelectedItem());

                if (this.pensionType.getId() == 0) {
                    result = this.pensionTypeManager.save(this.pensionType);
                }else {
                    result = this.pensionTypeManager.update(this.pensionType);
                }
                if (result){
                    Helper.showMsg("done");
                    loadPensionTable(null);
                }else {
                    Helper.showMsg("error");
                }
            }
        });

        btn_cncl_pension.addActionListener(e -> {
            loadPensionFilterHotel();
            cmb_pension_type_filter.setSelectedItem(null);
        });

    }




}
