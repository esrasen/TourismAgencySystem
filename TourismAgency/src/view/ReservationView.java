package view;

import entity.Reservation;

import javax.swing.*;

public class ReservationView extends Layout{


    private JPanel container;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JRadioButton otoparkRadioButton;
    private JRadioButton wiFiRadioButton;
    private JRadioButton yüzmeHavuzuRadioButton;
    private JRadioButton fitnessCenterRadioButton;
    private JRadioButton spaRadioButton;
    private JRadioButton odaServisiRadioButton;
    private JRadioButton conciergeRadioButton;
    private JTextField textField4;
    private JTextField textField5;
    private JFormattedTextField formattedTextField1;
    private JFormattedTextField formattedTextField2;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JRadioButton TVRadioButton;
    private JRadioButton oyunKonsoluRadioButton;
    private JRadioButton projeksiyonRadioButton;
    private JRadioButton minibarRadioButton;
    private JRadioButton kasaRadioButton;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
    private JButton rezervasyonOluşturButton;

    public ReservationView(Reservation reservation){
        this.add(container);
        this.guiInitilaze(1000, 600);
    }
}
