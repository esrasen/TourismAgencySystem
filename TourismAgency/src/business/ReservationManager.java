package business;

import dao.ReservationDao;
import entity.Reservation;

import java.util.ArrayList;

public class ReservationManager {
    private final ReservationDao reservationDao;

    public ReservationManager() {
        this.reservationDao = new ReservationDao();
    }

    public ArrayList<Reservation> findAll() {
        return this.reservationDao.findAll();
    }

    public ArrayList getForTable(int size) {
        ArrayList<Object[]> reservationRowList = new ArrayList<>();
        for (Reservation reservation : this.findAll()) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = reservation.getId();
            rowObject[i++] = reservation.getRoomId();
            rowObject[i++] = reservation.getCheckInDate();
            rowObject[i++] = reservation.getCheckOutDate();
            rowObject[i++] = reservation.getTotalPrice();
            rowObject[i++] = reservation.getTotalGuest();
            rowObject[i++] = reservation.getGuestName();
            rowObject[i++] = reservation.getGuestIDNumber();
            rowObject[i++] = reservation.getGuestMail();
            rowObject[i++] = reservation.getGuestPhone();

            reservationRowList.add(rowObject);
        }
        return reservationRowList;
    }

    public Reservation getById(int id) {
        return this.reservationDao.getById(id);
    }

    public boolean save(Reservation reservation){
        if (reservation.getId() != 0){
            return false;
        }
        return this.reservationDao.save(reservation);
    }

    public boolean update(Reservation reservation){
        if (this.getById(reservation.getId()) == null){
            return false;
        }
        return this.reservationDao.update(reservation);
    }

    public boolean delete(int id){
        if (this.getById(id) == null){
            return false;
        }
        return this.reservationDao.delete(id);
    }



}
