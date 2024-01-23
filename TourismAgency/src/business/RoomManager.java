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
    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> roomRowList = new ArrayList<>();
        for(Room room : this.findAll()){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i ++] = room.getId();
            rowObject[i ++] = room.getHotelId();
            rowObject[i ++] = room.getPensionTypeId();
            rowObject[i ++] = room.getSeasonId();
            rowObject[i ++] = room.getRoomOption();
            rowObject[i ++] = room.getAdultPrice();
            rowObject[i ++] = room.getChildPrice();
            rowObject[i ++] = room.getBedCount();
            rowObject[i ++] = room.getMeter();
            rowObject[i ++] = room.getStock();

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


}
