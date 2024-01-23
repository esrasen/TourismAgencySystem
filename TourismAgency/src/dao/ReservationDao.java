package dao;

import entity.Reservation;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationDao extends BaseDao{

    public ReservationDao() {
        super();
    }

    public ArrayList<Reservation> findAll(){
        ArrayList<Reservation> reservationList = new ArrayList<>();
        String query = "SELECT * FROM public.reservation ORDER BY id ASC";
        try{
            ResultSet rs = this.conn.createStatement().executeQuery(query);
            while (rs.next()){
                reservationList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return reservationList;
    }

    public Reservation match(ResultSet rs) throws SQLException {
        Reservation obj = new Reservation();
        obj.setId(rs.getInt("id"));
        obj.setRoomId(rs.getInt("room_id"));
        obj.setGuestName(rs.getString("guest_name"));
        obj.setGuestSurname(rs.getString("guest_surname"));
        obj.setGuestIDNumber(rs.getString("guest_id_number"));
        obj.setCheckInDate(LocalDate.parse(rs.getString("check_in_date")));
        obj.setCheckOutDate(LocalDate.parse(rs.getString("check_out_date")));
        obj.setTotalPrice(rs.getBigDecimal("total_price"));

        return obj;
    }

    public boolean save(Reservation reservation){
        String query = "INSERT INTO public.reservation" +
                "(" +
                "room_id," +
                " guest_name, " +
                "guest_surname, " +
                "guest_id_number, " +
                "check_in_date, " +
                "check_out_date, " +
                "total_price" +
                ")" +
                " VALUES (?,?,?,?,?,?,?)";
        try{
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, reservation.getRoomId());
            pr.setString(2, reservation.getGuestName());
            pr.setString(3, reservation.getGuestSurname());
            pr.setString(4, reservation.getGuestIDNumber());
            pr.setDate(5, Date.valueOf(reservation.getCheckInDate()));
            pr.setDate(6, Date.valueOf(reservation.getCheckOutDate()));
            pr.setBigDecimal(7, reservation.getTotalPrice());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Reservation reservation){
        String query = "UPDATE public.reservation SET " +
                "room_id=?," +
                " guest_name=?," +
                " guest_surname=?," +
                " guest_id_number=?, " +
                "check_in_date=?," +
                " check_out_date=?, " +
                "total_price=? " +
                "WHERE id=?";
        try{
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, reservation.getRoomId());
            pr.setString(2, reservation.getGuestName());
            pr.setString(3, reservation.getGuestSurname());
            pr.setString(4, reservation.getGuestIDNumber());
            pr.setDate(5, Date.valueOf(reservation.getCheckInDate()));
            pr.setDate(6, Date.valueOf(reservation.getCheckOutDate()));
            pr.setBigDecimal(7, reservation.getTotalPrice());
            pr.setInt(8, reservation.getId());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int id){
        String query = "DELETE FROM public.reservation WHERE id=?";
        try{
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
    }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public Reservation getById(int id){
        Reservation obj = null;
        String query = "SELECT * FROM public.reservation WHERE id = ?";
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
