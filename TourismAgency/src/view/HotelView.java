package view;

import business.HotelManager;
import entity.Hotel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelView extends Layout{
    private JPanel container;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_city;
    private JTextField fld_hotel_region;
    private JTextField fld_hotel_address;
    private JTextField fld_hotel_email;
    private JTextField fld_hotel_phone;
    private JRadioButton rd_btn_park;
    private JRadioButton rd_btn_wifi;
    private JRadioButton rd_btn_pool;
    private JRadioButton rd_btn_fitness;
    private JRadioButton rd_btn_spa;
    private JRadioButton rd_btn_housekeeping;
    private JRadioButton rd_btn_concierge;
    private JButton btn_hotel_save;
    private JComboBox cmb_hotel_stars;
    private Hotel Hotel;
    private HotelManager hotelManager;

    public HotelView(Hotel hotel){
        this.add(container);
        this.guiInitilaze(600, 800);
        this.Hotel = hotel;
        this.hotelManager = new HotelManager();


        btn_hotel_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
