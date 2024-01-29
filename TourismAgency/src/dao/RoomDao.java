package dao;

import entity.PensionOption;
import entity.Room;
import entity.RoomOption;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDao extends BaseDao {
    public RoomDao() {
        super();
    }

    public ArrayList<Room> findAll() {
        ArrayList<Room> roomList = new ArrayList<>();
        String sql = "SELECT * FROM public.room " +
                "JOIN public.hotel ON room.hotel_id = hotel.id " +
                "JOIN public.pensionType ON room.pension_type_id = pensionType.id " +
                "ORDER BY room.id ASC";

        try {
            ResultSet rs = this.conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                roomList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public Room match(ResultSet rs) throws SQLException {
        Room obj = new Room();
        System.out.println(rs.toString());
        obj.setId(rs.getInt("id"));
        obj.setHotelName(rs.getString("hotel_name"));
        obj.setPensionOptionName(PensionOption.valueOf(PensionOption.class, rs.getString("pension_option")));
        obj.setAdultPrice(rs.getBigDecimal("adult_price"));
        obj.setChildPrice(rs.getBigDecimal("child_price"));
        obj.setRoomOption(RoomOption.valueOf(RoomOption.class, rs.getString("room_option")));
        obj.setStock(rs.getInt("stock"));
        obj.setBedCount(rs.getInt("bed_count"));
        obj.setMeter(rs.getInt("meter"));
        obj.setTv(rs.getBoolean("tv"));
        obj.setMinibar(rs.getBoolean("minibar"));
        obj.setGameConsole(rs.getBoolean("game_console"));
        obj.setProjection(rs.getBoolean("projection"));
        obj.setSafeBox(rs.getBoolean("safe_box"));

        return obj;
    }

    public boolean save(Room room) {
        String query = "INSERT INTO public.room(hotel_id, season_id, pension_type_id," +
                " adult_price, " +
                "child_price, " +
                "room_option, " +
                "stock," +
                " bed_count," +
                " meter," +
                " tv, " +
                "minibar," +
                " game_console, " +
                "projection," +
                " safe_box) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, room.getHotelId());
            pr.setInt(2, room.getSeasonId());
            pr.setInt(3, room.getPensionTypeId());
            pr.setBigDecimal(4, room.getAdultPrice());
            pr.setBigDecimal(5, room.getChildPrice());
            pr.setString(6, room.getRoomOption().toString());
            pr.setInt(7, room.getStock());
            pr.setInt(8, room.getBedCount());
            pr.setInt(9, room.getMeter());
            pr.setBoolean(10, room.isTv());
            pr.setBoolean(11, room.isMinibar());
            pr.setBoolean(12, room.isGameConsole());
            pr.setBoolean(13, room.isProjection());
            pr.setBoolean(14, room.isSafeBox());
            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean update(Room room){
        String query = "UPDATE public.room SET hotel_id=?, season_id=?, pension_type_id=?," +
                " adult_price=?, " +
                "child_price=?, " +
                "room_option=?, " +
                "stock=?," +
                " bed_count=?," +
                " meter=?," +
                " tv=?, " +
                "minibar=?," +
                " game_console=?, " +
                "projection=?," +
                " safe_box=? WHERE id=?";
        try{
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, room.getHotelId());
            pr.setInt(2, room.getSeasonId());
            pr.setInt(3, room.getPensionTypeId());
            pr.setBigDecimal(4, room.getAdultPrice());
            pr.setBigDecimal(5, room.getChildPrice());
            pr.setString(6, room.getRoomOption().toString());
            pr.setInt(7, room.getStock());
            pr.setInt(8, room.getBedCount());
            pr.setInt(9, room.getMeter());
            pr.setBoolean(10, room.isTv());
            pr.setBoolean(11, room.isMinibar());
            pr.setBoolean(12, room.isGameConsole());
            pr.setBoolean(13, room.isProjection());
            pr.setBoolean(14, room.isSafeBox());
            pr.setInt(15, room.getId());
            return pr.executeUpdate() != -1;


        }catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }

    public boolean delete(int id){
        String query = "DELETE FROM public.room WHERE id=?";
        try{
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public Room getById(int id){
        Room obj = null;
        String query = "SELECT * FROM public.room WHERE id = ?";
        try{
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = this.match(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return obj;
    }

}
