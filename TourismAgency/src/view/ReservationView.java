package view;

import business.HotelManager;
import business.ReservationManager;
import business.RoomManager;
import core.Helper;
import entity.Hotel;
import entity.Reservation;
import entity.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class ReservationView extends Layout{


    private JPanel container;
    private JTextField fld_reservation_hotelname;
    private JTextField fld_reservation_city;
    private JTextField fld_reservation_star;
    private JRadioButton rd_btn_park;
    private JRadioButton rd_btn_wifi;
    private JRadioButton rd_btn_pool;
    private JRadioButton rd_btn_fitness;
    private JRadioButton rd_btn_spa;
    private JRadioButton rd_btn_housekeeping;
    private JRadioButton rd_btn_concierge;
    private JTextField fld_reservation_room_option;
    private JTextField fld_reservation_pension_type;
    private JFormattedTextField fld_reservation_start_date;
    private JFormattedTextField fld_reservation_end_date;
    private JTextField fld_reservation_prc;
    private JTextField fld_reservation_bed;
    private JTextField fld_reservation_m2;
    private JRadioButton rd_btn_tv;
    private JRadioButton rd_btn_console;
    private JRadioButton rd_btn_projection;
    private JRadioButton rd_btn_minibar;
    private JRadioButton rd_btn_safebox;
    private JTextField fld_reservation_guest_count;
    private JTextField fld_reservation_guest_mail;
    private JTextField fld_reservation_guest_name;
    private JTextField fld_reservation_guest_ID_no;
    private JTextField fld_reservation_guest_mpno;
    private JButton btn_reservation_save;
    private RoomManager roomManager;
    private Reservation reservation;
    private ReservationManager reservationManager;
    private Room room;
    private Hotel hotel;
    private HotelManager hotelManager;
    private String checkInDate;
    private String checkOutDate;
    private int adultCount;
    private int childCount;

    public ReservationView(Reservation reservation ,Room selectedRoom, String checkInDate, String checkOutDate, int adultCount, int childCount) {
        this.room = selectedRoom;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.add(container);
        this.guiInitilaze(1000, 600);
        this.reservation = reservation;
        this.reservationManager = new ReservationManager();
        this.hotelManager = new HotelManager();
        hotel = hotelManager.getById(room.getHotelId());



        fld_reservation_hotelname.setText(hotel.getHotelName());
        fld_reservation_city.setText(hotel.getCity());
        fld_reservation_star.setText(String.valueOf(hotel.getStars()));
        rd_btn_wifi.setSelected(hotel.isFreeWifi());
        rd_btn_park.setSelected(hotel.isFreePark());
        rd_btn_pool.setSelected(hotel.isSwimmingPool());
        rd_btn_fitness.setSelected(hotel.isFitnessCenter());
        rd_btn_spa.setSelected(hotel.isSpa());
        rd_btn_housekeeping.setSelected(hotel.isHouseKeeping());
        rd_btn_concierge.setSelected(hotel.isHotelConcierge());

        fld_reservation_room_option.setText(String.valueOf(room.getRoomOption()));
        fld_reservation_pension_type.setText(String.valueOf(room.getPensionOptionName()));
        fld_reservation_start_date.setText(this.checkInDate);
        fld_reservation_end_date.setText(this.checkOutDate);
        fld_reservation_bed.setText(String.valueOf(room.getBedCount()));
        fld_reservation_m2.setText(String.valueOf(room.getMeter()));
        rd_btn_tv.setSelected(room.isTv());
        rd_btn_console.setSelected(room.isGameConsole());
        rd_btn_projection.setSelected(room.isProjection());
        rd_btn_minibar.setSelected(room.isMinibar());
        rd_btn_safebox.setSelected(room.isSafeBox());
        fld_reservation_guest_count.setText(String.valueOf(adultCount + childCount));
        fld_reservation_prc.setText(String.valueOf(totalPrice(adultCount, childCount, room.getAdultPrice(), room.getChildPrice())));



        btn_reservation_save.addActionListener(e -> {
            if(Helper.isFieldListEmpty(new JTextField[] {fld_reservation_guest_name, fld_reservation_guest_mail, fld_reservation_guest_ID_no, fld_reservation_guest_mpno})){
                Helper.showMsg("fill");
            }else {
                boolean result;
                if (this.reservation == null){
                    this.reservation = new Reservation();
                    this.reservation.setRoomId(room.getId());
                    this.reservation.setCheckInDate(LocalDate.parse(fld_reservation_start_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    this.reservation.setCheckOutDate(LocalDate.parse(fld_reservation_end_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    this.reservation.setTotalPrice(totalPrice(adultCount, childCount, room.getAdultPrice(), room.getChildPrice()));
                    this.reservation.setTotalGuest(adultCount + childCount);
                    this.reservation.setGuestName(fld_reservation_guest_name.getText());
                    this.reservation.setGuestIDNumber(fld_reservation_guest_ID_no.getText());
                    this.reservation.setGuestMail(fld_reservation_guest_mail.getText());
                    this.reservation.setGuestPhone(fld_reservation_guest_mpno.getText());


                    result = this.reservationManager.save(this.reservation);

                }else {

                    result = this.reservationManager.update(this.reservation);
                }



                if (result){
                    Helper.showMsg("done");
                    dispose();
                }else {
                    Helper.showMsg("error");
                }
            }

        });

    }

    public BigDecimal totalPrice(int adultCount, int childCount, BigDecimal adultPrice, BigDecimal childPrice){
        LocalDate endDate = LocalDate.parse(fld_reservation_end_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate startDate = LocalDate.parse(fld_reservation_start_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Period period = startDate.until(endDate);
        long day = period.getDays();

       // int day = Integer.parseInt(fld_reservation_end_date.getText()) - Integer.parseInt(fld_reservation_start_date.getText());
        BigDecimal adultTotal = adultPrice.multiply(BigDecimal.valueOf(adultCount)).multiply(BigDecimal.valueOf(day));
        BigDecimal childTotal = childPrice.multiply(BigDecimal.valueOf(childCount)).multiply(BigDecimal.valueOf(day));

        return adultTotal.add(childTotal);
    }

}