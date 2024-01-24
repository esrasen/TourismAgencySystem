package business;

import core.Helper;
import dao.HotelDao;
import entity.Hotel;

import java.util.ArrayList;

public class HotelManager {
    private final HotelDao hotelDao;

    public HotelManager() {
        this.hotelDao = new HotelDao();
    }

    public ArrayList<Object[]> getForTable(int size) {
        ArrayList<Object[]> hotelRowList = new ArrayList<>();
        for (Hotel hotel : this.findAll()) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = hotel.getId();
            rowObject[i++] = hotel.getHotelName();
            rowObject[i++] = hotel.getCity();
            rowObject[i++] = hotel.getRegion();
            rowObject[i++] = hotel.getAddress();
            rowObject[i++] = hotel.getEmail();
            rowObject[i++] = hotel.getPhoneNumber();
            rowObject[i++] = hotel.getStars();
            rowObject[i++] = hotel.isFreePark() ? "Var" : "Yok";
            rowObject[i++] = hotel.isFreeWifi() ? "Var" : "Yok";
            rowObject[i++] = hotel.isSwimmingPool() ? "Var" : "Yok";
            rowObject[i++] = hotel.isSpa() ? "Var" : "Yok";
            rowObject[i++] = hotel.isFitnessCenter() ? "Var" : "Yok";
            rowObject[i++] = hotel.isHotelConcierge() ? "Var" : "Yok";
            rowObject[i++] = hotel.isHouseKeeping() ? "Var" : "Yok";

            hotelRowList.add(rowObject);
        }
        return hotelRowList;
    }

    public ArrayList<Hotel> findAll() {
        return this.hotelDao.findAll();
    }

    public Hotel getById(int id) {
        return this.hotelDao.getById(id);
    }

    public boolean save(Hotel hotel) {
        if (hotel.getId() != 0) {
            Helper.showMsg("error");
        }
        return this.hotelDao.save(hotel);
    }

    public boolean update(Hotel hotel) {
        if (getById(hotel.getId()) == null) {
            Helper.showMsg("notFound");
        }
        return this.hotelDao.update(hotel);
    }

    public boolean delete(int id) {
        if (getById(id) == null) {
            Helper.showMsg(id + "ID kayıtlı otel bulunamadı !");
            return false;
        }
        return this.hotelDao.delete(id);
    }

}
