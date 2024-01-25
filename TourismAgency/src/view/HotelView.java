package view;

import business.HotelManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;

import javax.swing.*;

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
    private JTextField fld_hotel_stars;
    private JComboBox cmb_hotel_stars;
    private Hotel hotel;
    private HotelManager hotelManager;

    public HotelView(Hotel hotel){
        this.add(container);
        this.guiInitilaze(600, 800);
        this.hotel = hotel;
        this.hotelManager = new HotelManager();


        if (this.hotel != null){
            this.fld_hotel_name.setText(this.hotel.getHotelName());
            this.fld_hotel_city.setText(this.hotel.getCity());
            this.fld_hotel_region.setText(this.hotel.getRegion());
            this.fld_hotel_address.setText(this.hotel.getAddress());
            this.fld_hotel_email.setText(this.hotel.getEmail());
            this.fld_hotel_phone.setText(this.hotel.getPhoneNumber());
            this.fld_hotel_stars.setText(String.valueOf(this.hotel.getStars()));
            this.rd_btn_park.setSelected(this.hotel.isFreePark());
            this.rd_btn_wifi.setSelected(this.hotel.isFreeWifi());
            this.rd_btn_pool.setSelected(this.hotel.isSwimmingPool());
            this.rd_btn_fitness.setSelected(this.hotel.isFitnessCenter());
            this.rd_btn_spa.setSelected(this.hotel.isSpa());
            this.rd_btn_housekeeping.setSelected(this.hotel.isHouseKeeping());
            this.rd_btn_concierge.setSelected(this.hotel.isHotelConcierge());

        }
        btn_hotel_save.addActionListener(e ->  {
            JTextField[] checkFieldList ={
                    this.fld_hotel_name,
                    this.fld_hotel_city,
                    this.fld_hotel_region,
                    this.fld_hotel_address,
                    this.fld_hotel_email,
                    this.fld_hotel_phone,
                    this.fld_hotel_stars
            };
            JRadioButton[] checkRadioList ={
                    this.rd_btn_park,
                    this.rd_btn_wifi,
                    this.rd_btn_pool,
                    this.rd_btn_fitness,
                    this.rd_btn_spa,
                    this.rd_btn_housekeeping,
                    this.rd_btn_concierge
            };
            if(Helper.isFieldListEmpty(checkFieldList) && Helper.isRadioListEmpty(checkRadioList)){
                Helper.showMsg("fill");
            }else{
                boolean result ;
                boolean isPark = this.rd_btn_park.isSelected();
                boolean isWifi = this.rd_btn_wifi.isSelected();
                boolean isPool = this.rd_btn_pool.isSelected();
                boolean isFitness = this.rd_btn_fitness.isSelected();
                boolean isSpa = this.rd_btn_spa.isSelected();
                boolean isHousekeeping = this.rd_btn_housekeeping.isSelected();
                boolean isConcierge = this.rd_btn_concierge.isSelected();



                if (this.hotel == null){
                    Hotel hotels = new Hotel(
                            this.fld_hotel_name.getText(),
                            this.fld_hotel_city.getText(),
                            this.fld_hotel_region.getText(),
                            this.fld_hotel_address.getText(),
                            this.fld_hotel_email.getText(),
                            this.fld_hotel_phone.getText(),
                            Integer.parseInt(this.fld_hotel_stars.getText()),
                            isPark,
                            isWifi,
                            isPool,
                            isFitness,
                            isSpa,
                            isHousekeeping,
                            isConcierge
                    );

                    result = this.hotelManager.save(hotels);
                }else{
                    this.hotel.setHotelName(this.fld_hotel_name.getText());
                    this.hotel.setCity(this.fld_hotel_city.getText());
                    this.hotel.setRegion(this.fld_hotel_region.getText());
                    this.hotel.setAddress(this.fld_hotel_address.getText());
                    this.hotel.setEmail(this.fld_hotel_email.getText());
                    this.hotel.setPhoneNumber(this.fld_hotel_phone.getText());
                    this.hotel.setStars(Integer.parseInt(this.fld_hotel_stars.getText()));
                    this.hotel.setFreePark(isPark);
                    this.hotel.setFreeWifi(isWifi);
                    this.hotel.setSwimmingPool(isPool);
                    this.hotel.setFitnessCenter(isFitness);
                    this.hotel.setSpa(isSpa);
                    this.hotel.setHouseKeeping(isHousekeeping);
                    this.hotel.setHotelConcierge(isConcierge);

                    result = this.hotelManager.update(this.hotel);
                }

                if (result) {
                    Helper.showMsg("done");
                    dispose();
                }else {
                    Helper.showMsg("error");
                }
            }
        });
    }
}