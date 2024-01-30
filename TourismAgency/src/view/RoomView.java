package view;

import entity.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

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



    public RoomView(Room room){
        this.add(container);
        this.guiInitilaze(600, 400);



        btn_room_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


}
