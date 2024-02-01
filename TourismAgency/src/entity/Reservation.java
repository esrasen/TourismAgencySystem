package entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reservation {

    private int id;
    private int roomId;
    private String guestName;
    private String guestIDNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BigDecimal totalPrice;
    private int totalGuest;
    private String guestMail;
    private String guestPhone;
    private int adultCount;
    private int childCount;


    public Reservation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }


    public String getGuestIDNumber() {
        return guestIDNumber;
    }

    public void setGuestIDNumber(String guestIDNumber) {
        this.guestIDNumber = guestIDNumber;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalGuest() {return totalGuest;}

    public void setTotalGuest(int totalGuest) {this.totalGuest = totalGuest;}

    public String getGuestMail() {return guestMail;}

    public void setGuestMail(String guestMail) {this.guestMail = guestMail;}

    public String getGuestPhone() {return guestPhone;}

    public void setGuestPhone(String guestPhone) {this.guestPhone = guestPhone;}

    public int getAdultCount() {return adultCount;}

    public void setAdultCount(int adultCount) {this.adultCount = adultCount;}

    public int getChildCount() {return childCount;}

    public void setChildCount(int childCount) {this.childCount = childCount;}

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", guestName='" + guestName + '\'' +
                ", guestIDNumber='" + guestIDNumber + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", totalPrice=" + totalPrice +
                ", totalGuest=" + totalGuest +
                ", guestMail='" + guestMail + '\'' +
                ", guestPhone='" + guestPhone + '\'' +
                ", adultCount=" + adultCount +
                ", childCount=" + childCount +
                '}';
    }
}
