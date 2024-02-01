package business;

import dao.RoomDao;
import entity.Room;

import java.util.ArrayList;

public class RoomManager {
    private final RoomDao roomDao;

    public RoomManager() {
        this.roomDao = new RoomDao();
    }
    public ArrayList<Room> findAll() {
        return this.roomDao.findAll();
    }
    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> rooms){
        ArrayList<Object[]> roomRowList = new ArrayList<>();
        for(Room room : rooms){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i ++] = room.getId();
            rowObject[i ++] = room.getHotelName();
            rowObject[i ++] = room.getPensionOptionName();
            rowObject[i ++] = room.getRoomOption();
            rowObject[i ++] = room.getStock();
            rowObject[i ++] = room.getAdultPrice();
            rowObject[i ++] = room.getChildPrice();
            rowObject[i ++] = room.getBedCount();
            rowObject[i ++] = room.getMeter();
            rowObject[i ++] = room.isTv() ? "Var" : "Yok";
            rowObject[i ++] = room.isMinibar() ? "Var" : "Yok";
            rowObject[i ++] = room.isGameConsole() ? "Var" : "Yok";
            rowObject[i ++] = room.isSafeBox() ? "Var" : "Yok";
            rowObject[i ++] = room.isProjection() ? "Var" : "Yok";

            roomRowList.add(rowObject);
        }
        return roomRowList;
    }

    public Room getById(int id){ return this.roomDao.getById(id); }

    public boolean save(Room room){
        if (room.getId() != 0){
            return false;
        }
        return this.roomDao.save(room);
    }

    public boolean update(Room room){
        if (this.getById(room.getId()) == null){
            return false;
        }
        return this.roomDao.update(room);
    }

    public boolean delete(int id){
        if (this.getById(id) == null){
            return false;
        }
        return this.roomDao.delete(id);
    }

    public ArrayList<Room> searchForTable(String hotelName, String city, String checkInDate, String checkOutDate){
        return this.roomDao.searchForRoom(hotelName, city, checkInDate, checkOutDate);
    }


}
