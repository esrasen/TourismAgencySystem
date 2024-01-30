package view;

import business.*;
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

public class EmployeeView extends Layout {
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
    private JComboBox<ComboItem> cmb_hotel_filter;
    private JButton btn_save_season;
    private JButton btn_cncl_season;
    private Object[] col_hotel;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionType pensionType;
    private PensionTypeManager pensionTypeManager;
    private RoomManager roomManager;

    private ReservationManager reservationManager;
    private Season season;
    private User user;
    private UserManager userManager;
    private Hotel hotel;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_season = new DefaultTableModel();
    private DefaultTableModel tmdl_pension = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private DefaultTableModel tmdl_reservation = new DefaultTableModel();
    private JPopupMenu hotelMenu;
    private JPopupMenu reservationMenu;
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
    private JTable tbl_room;
    private JTextField fld_room_hotelname;
    private JTextField fld_room_city;
    private JFormattedTextField fld_room_checkin;
    private JFormattedTextField fld_room_checkout;
    private JTextField fld_room_adult;
    private JTextField fld_room_child;
    private JButton btn_room_search;
    private JButton btn_room_reset;
    private JButton btn_add_room;
    private JPanel pnl_room_search;
    private JScrollPane scrl_room;
    private JTable tbl_reservation;
    private Object[] col_season;
    private Object[] col_room;


    public EmployeeView(User user) {
        this.add(container);
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
        this.roomManager = new RoomManager();
        this.reservationManager = new ReservationManager();
        this.pensionTypeManager = new PensionTypeManager();
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
        loadRoomTable(null);
        loadReservationComponent();
        loadReservationTable(null);
        loadReservationDeleteComponent();
        loadRoomComponent();

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


    public void loadHotelTable() {
        Object[] col_hotel = {"ID", "Adı", "Şehir", "Bölge", "Adres", "e-mail", "Telefon", "Yıldız", "Otopark", "WiFi", "Havuz", "Spa", "Fitness", "Konsiyerj", "Oda Servisi"};
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
        this.col_season = new Object[]{"ID", "Otel ID", "Başlangıç Tarihi ", "Bitiş Tarihi "};
        if (seasonList == null) {
            seasonList = this.seasonManager.getForTable(col_season.length, this.seasonManager.findAll());
        }
        this.createTable(this.tmdl_season, this.tbl_season, col_season, seasonList);

    }
    public void loadSeasonFilterHotel() {
        this.cmb_hotel_filter.removeAllItems();
        for (Hotel obj : hotelManager.findAll()) {
            this.cmb_hotel_filter.addItem(new ComboItem(obj.getId(), obj.getHotelName()));
        }
        this.cmb_hotel_filter.setSelectedItem(null);
    }
    public void loadSeasonComponent() {
        tableRowSelect(this.tbl_season);

        btn_save_season.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_strt_date, this.fld_end_date})) {
                Helper.showMsg("fill");
            } else {
                boolean result;
                ComboItem selectedHotel = (ComboItem) this.cmb_hotel_filter.getSelectedItem();
                this.season.setHotelId(selectedHotel.getKey());
                this.season.setStartDate(LocalDate.parse(this.fld_strt_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                this.season.setEndDate(LocalDate.parse(this.fld_end_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                if (this.season.getId() == 0) {
                    result = this.seasonManager.save(this.season);
                } else {
                    result = this.seasonManager.update(this.season);
                }
                if (result) {
                    Helper.showMsg("done");
                    loadSeasonTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }
        });

        btn_cncl_season.addActionListener(e -> {
            loadSeasonFilterHotel();
        });

    }


    public void loadPensionTable(ArrayList<Object[]> pensionList) {
        Object[] col_pension = {"ID", "Otel ID", "Pansiyon Tipi"};
        if (pensionList == null) {
            pensionList = this.pensionTypeManager.getForTable(col_pension.length);
        }
        this.createTable(this.tmdl_pension, this.tbl_pension, col_pension, pensionList);
    }
    public void loadPensionFilterHotel() {
        this.cmb_pension_hotel_filter.removeAllItems();
        for (Hotel obj : this.hotelManager.findAll()) {
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
            if (Helper.isFieldListEmpty(new JTextField[]{})) {
                Helper.showMsg("fill");
            } else {
                boolean result;
                ComboItem selectedHotel = (ComboItem) this.cmb_pension_hotel_filter.getSelectedItem();
                this.pensionType.setHotelId(selectedHotel.getKey());
                this.pensionType.setPensionOption((PensionOption) this.cmb_pension_type_filter.getSelectedItem());

                if (this.pensionType.getId() == 0) {
                    result = this.pensionTypeManager.save(this.pensionType);
                } else {
                    result = this.pensionTypeManager.update(this.pensionType);
                }
                if (result) {
                    Helper.showMsg("done");
                    loadPensionTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }
        });

        btn_cncl_pension.addActionListener(e -> {
            loadPensionFilterHotel();
            cmb_pension_type_filter.setSelectedItem(null);
        });

    }


    public void loadRoomTable(ArrayList<Object[]> roomList) {
        this.col_room =new Object[] {"ID", "Otel Adı", "Pansiyon", "Oda Tipi", "Stok", "Yetişkin Fiyat ", "Çocuk Fiyat", "Yatak Kapasitesi", "m2", "TV", "Minibar", "Konsol", "Kasa", "Projeksiyon"};
        if (roomList == null) {
            roomList = this.roomManager.getForTable(col_room.length);
        }
        this.createTable(this.tmdl_room, this.tbl_room, col_room, roomList);
    }
    public void loadRoomComponent() {
        tableRowSelect(this.tbl_room);
        btn_add_room.addActionListener(e -> {
            RoomView roomView = new RoomView(null);
            roomView.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    loadRoomTable(null);
                }
            });
        });
        btn_room_reset.addActionListener(e -> {
            loadRoomTable(null);
            fld_room_hotelname.setText("");
            fld_room_city.setText("");
            fld_room_checkin.setText("");
            fld_room_checkout.setText("");
            fld_room_adult.setText("");
            fld_room_child.setText("");
        });

       /* btn_room_search.addActionListener(e -> {
            String hotelName = fld_room_hotelname.getText();
            String city = fld_room_city.getText();
            String checkInDate = fld_room_checkin.getText();
            String checkOutDate = fld_room_checkout.getText();
            int adultCount = Integer.parseInt(fld_room_adult.getText());
            int childCount = Integer.parseInt(fld_room_child.getText());
            ArrayList<Room> roomListBySearch = this.roomManager.searchForTable(
                    hotelName, city, checkInDate, checkOutDate, adultCount, childCount);
            ArrayList<Object[]> roomList = this.roomManager.getForTable(col_room.length, roomListBySearch);
            this.createTable(this.tmdl_room, this.tbl_room, col_room, roomList);
        });*/
    }


    public void loadReservationTable(ArrayList<Object[]> reservationList) {
        Object[] col_reservation = {"ID", "Otel ID", "Giriş Tarihi", "Çıkış Tarihi ", "Toplam Tutar", "Misafir Sayısı ", "Misafir Adı ", "Misafir Kimlik No", "Mail", "Telefon"};
        if (reservationList == null) {
            reservationList = this.reservationManager.getForTable(col_reservation.length);
        }
        this.createTable(this.tmdl_reservation, this.tbl_reservation, col_reservation, reservationList);
    }
    public void loadReservationComponent() {
        tableRowSelect(this.tbl_room);
        this.reservationMenu = new JPopupMenu();
        this.reservationMenu.add("Rezervasyon Ekle").addActionListener(e -> {

            try {
                int selectedId = (int) this.tbl_room.getValueAt(this.tbl_room.getSelectedRow(), 0);
                boolean success = true;
                Room room = roomManager.getById(selectedId);


                String roomCheckInDate = this.fld_room_checkin.getText();
                String roomCheckOutDate = this.fld_room_checkout.getText();
                int roomAdult = Integer.parseInt(this.fld_room_adult.getText());
                int roomChild = Integer.parseInt(this.fld_room_child.getText());

                success = validationRoomField(roomAdult, roomChild, success, room);
                if (success) {
                    ReservationView reservationView = new ReservationView(null, room, roomCheckInDate, roomCheckOutDate, roomAdult, roomChild);
                    reservationView.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            loadRoomTable(null);
                            loadReservationTable(null);
                        }
                    });
                }
            } catch (NumberFormatException exception) {
                Helper.showMsg("typeError");
            }
        });
        this.tbl_room.setComponentPopupMenu(this.reservationMenu);

    }
    private boolean validationRoomField(int roomAdult, int roomChild, boolean success, Room room) {
        if (this.fld_room_adult.getText().isEmpty() || this.fld_room_child.getText().isEmpty()
                || this.fld_room_checkin.getText().isEmpty() || this.fld_room_checkout.getText().isEmpty()
                ||(roomAdult <= 0 && roomChild <= 0)  ) {
            Helper.showMsg("fill");
            success = false;
        }
        if (roomAdult + roomChild > room.getBedCount()) {
            Helper.showMsg("bedCountLimitExceeded");
            success = false;
        }
        return success;
    }
    public void loadReservationDeleteComponent() {
        tableRowSelect(this.tbl_reservation);
        this.reservationMenu = new JPopupMenu();
        this.reservationMenu.add("Rezervasyon Sil").addActionListener(e -> {
            int selectedId = (int) this.tbl_reservation.getValueAt(this.tbl_reservation.getSelectedRow(), 0);
            boolean result = this.reservationManager.delete(selectedId);
            if (result) {
                Helper.showMsg("done");
                loadReservationTable(null);
            } else {
                Helper.showMsg("error");
            }
        });
        this.tbl_reservation.setComponentPopupMenu(this.reservationMenu);

    }



}
