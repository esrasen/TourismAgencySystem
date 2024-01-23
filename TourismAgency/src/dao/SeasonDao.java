package dao;

import core.DbConnection;
import entity.Season;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SeasonDao extends BaseDao{

    public SeasonDao() {
        super();
    }

    public ArrayList<Season> findAll() {
        return this.selectByQuery("SELECT * FROM public.season ORDER BY id ASC");
    }

    public ArrayList<Season> selectByQuery(String query) {
        System.out.println(query);
        ArrayList<Season> seasons = new ArrayList<>();
        try {
            ResultSet rs = this.conn.createStatement().executeQuery(query);
            while (rs.next()) {
                seasons.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasons;
    }

    public Season match(ResultSet rs) throws SQLException {
        Season obj = new Season();
        obj.setId(rs.getInt("id"));
        obj.setHotelId(rs.getInt("hotel_id"));
        obj.setStartDate(LocalDate.parse(rs.getString("start_date")));
        obj.setEndDate(LocalDate.parse(rs.getString("end_date")));

        return obj;
    }

    public boolean save(Season season) {
        String query = "INSERT INTO public.season(hotel_id, start_date, end_date) VALUES (?, ?, ?)";

        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, season.getHotelId());
            pr.setDate(2, Date.valueOf(season.getStartDate()));
            pr.setDate(3, Date.valueOf(season.getEndDate()));
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Season season) {
        String query = "UPDATE public.season SET hotel_id=?, start_date=?, end_date=? WHERE id=?";

        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, season.getHotelId());
            pr.setDate(2, Date.valueOf(season.getStartDate()));
            pr.setDate(3, Date.valueOf(season.getEndDate()));
            pr.setInt(4, season.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.season WHERE id=?";

        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Season getById(int id) {
        Season obj = null;
        String query = "SELECT * FROM public.season WHERE id = ?";
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }


}
