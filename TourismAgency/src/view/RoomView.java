package view;

import business.HotelManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.*;

import javax.swing.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.List;

public class RoomView extends Layout{
    private JPanel container;
    private JButton btn_room_save;
    private JComboBox cmb_room_hotel;
    private JComboBox cmb_room_pension;
    private JComboBox cmb_room_season;
    private JComboBox cmb_room_type;
    private JTextField fld_room_stock;
    private JTextField fld_room_adultprc;
    private JTextField fld_room_bed_count;
    private JTextField fld_room_m2;
    private JTextField fld_room_childprc;
    private JRadioButton rd_btn_tv;
    private JRadioButton rd_btn_minibar;
    private JRadioButton rd_btn_console;
    private JRadioButton rd_btn_safebox;
    private JRadioButton rd_btn_projection;
    private JPanel pnl_left;
    private JPanel pnl_right;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private RoomManager roomManager;
    private Room room;


    public RoomView(Room room){
        this.add(container);
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
        this.roomManager = new RoomManager();
        this.room = room;
        this.guiInitilaze(700, 400);


        for (Hotel hotel : this.hotelManager.findAll()){
            this.cmb_room_hotel.addItem(new ComboItem(hotel.getId(), hotel.getHotelName()));
        }
        this.cmb_room_hotel.setSelectedItem(null);

        cmb_room_hotel.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Seçilen oteli al
                    ComboItem selectedHotel = (ComboItem) cmb_room_hotel.getSelectedItem();

                    // Seçili otelin ID'sini kullanarak ilgili pansion tiplerini getir
                    List<PensionType> pensionTypes = hotelManager.getPensionTypesByHotelId(selectedHotel.getKey());
                    List<Season> seasons = hotelManager.getSeasonsByHotelId(selectedHotel.getKey());

                    // Pansion tiplerini combo box'a ekle
                    cmb_room_pension.removeAllItems();
                    for (PensionType pensionType : pensionTypes) {
                        cmb_room_pension.addItem(new ComboItem(pensionType.getId(), pensionType.getPensionOption().name()));
                    }

                    cmb_room_pension.setSelectedItem(null);


                    cmb_room_season.removeAllItems();
                    for (Season season : seasons) {
                        cmb_room_season.addItem(season.getComboItem());
                    }

                    cmb_room_season.setSelectedItem(null);
                }
            }
        });

        this.cmb_room_type.setModel(new DefaultComboBoxModel<>(RoomOption.values()));
        this.cmb_room_type.setSelectedItem(null);


        btn_room_save.addActionListener(e ->  {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_room_stock, this.fld_room_adultprc, this.fld_room_bed_count, this.fld_room_m2, this.fld_room_childprc})){
                Helper.showMsg("fill");
            }else {
                boolean result;
                if (this.room == null){
                    this.room = new Room();
                    ComboItem selectedHotel = (ComboItem) this.cmb_room_hotel.getSelectedItem();
                    ComboItem selectedSeason = (ComboItem) this.cmb_room_season.getSelectedItem();
                    ComboItem selectedPension = (ComboItem) this.cmb_room_pension.getSelectedItem();
                    this.room.setHotelId(selectedHotel.getKey());
                    this.room.setSeasonId(selectedSeason.getKey());
                    this.room.setPensionTypeId(selectedPension.getKey());
                    this.room.setRoomOption((RoomOption) this.cmb_room_type.getSelectedItem());
                    this.room.setStock(Integer.parseInt(this.fld_room_stock.getText()));
                    this.room.setAdultPrice(new BigDecimal(this.fld_room_adultprc.getText()));
                    this.room.setBedCount(Integer.parseInt(this.fld_room_bed_count.getText()));
                    this.room.setMeter(Integer.parseInt(this.fld_room_m2.getText()));
                    this.room.setChildPrice(new BigDecimal(this.fld_room_childprc.getText()));
                    this.room.setTv(this.rd_btn_tv.isSelected());
                    this.room.setMinibar(this.rd_btn_minibar.isSelected());
                    this.room.setGameConsole(this.rd_btn_console.isSelected());
                    this.room.setSafeBox(this.rd_btn_safebox.isSelected());
                    this.room.setProjection(this.rd_btn_projection.isSelected());


                    result = this.roomManager.save(this.room);
                }
                else{

                    result = this.roomManager.update(this.room);
                }

                if (result){
                    Helper.showMsg("done");
                    this.dispose();
                }else {
                    Helper.showMsg("err");
                }
            }
        });
    }


}
