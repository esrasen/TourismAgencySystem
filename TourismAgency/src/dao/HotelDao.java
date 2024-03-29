package dao;

import entity.Hotel;
import entity.PensionOption;
import entity.PensionType;
import entity.Season;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelDao extends BaseDao {

    public HotelDao() {
        super();
    }

    public ArrayList<Hotel> findAll() {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel ORDER BY id ASC";
        try {
            ResultSet rs = this.conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                hotelList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }

    public Hotel match(ResultSet rs) throws SQLException {
        Hotel obj = new Hotel();
        obj.setId(rs.getInt("id"));
        obj.setHotelName(rs.getString("hotel_name"));
        obj.setAddress(rs.getString("address"));
        obj.setCity(rs.getString("city"));
        obj.setRegion(rs.getString("region"));
        obj.setEmail(rs.getString("email"));
        obj.setPhoneNumber(rs.getString("phone_number"));
        obj.setStars(rs.getInt("stars"));
        obj.setFreeWifi(rs.getBoolean("free_wifi"));
        obj.setFreePark(rs.getBoolean("free_park"));
        obj.setFitnessCenter(rs.getBoolean("fitness_center"));
        obj.setSpa(rs.getBoolean("spa"));
        obj.setSwimmingPool(rs.getBoolean("swimming_pool"));
        obj.setHotelConcierge(rs.getBoolean("hotel_concierge"));
        obj.setHouseKeeping(rs.getBoolean("house_keeping"));

        return obj;

    }


    public boolean save(Hotel hotel) {
        String query = "INSERT INTO public.hotel(hotel_name, " +
                "address, city, region, email, phone_number," +
                " stars, free_wifi, free_park, fitness_center, " +
                "spa, swimming_pool, hotel_concierge, house_keeping) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setString(1, hotel.getHotelName());
            pr.setString(2, hotel.getAddress());
            pr.setString(3, hotel.getCity());
            pr.setString(4, hotel.getRegion());
            pr.setString(5, hotel.getEmail());
            pr.setString(6, hotel.getPhoneNumber());
            pr.setInt(7, hotel.getStars());
            pr.setBoolean(8, hotel.isFreeWifi());
            pr.setBoolean(9, hotel.isFreePark());
            pr.setBoolean(10, hotel.isFitnessCenter());
            pr.setBoolean(11, hotel.isSpa());
            pr.setBoolean(12, hotel.isSwimmingPool());
            pr.setBoolean(13, hotel.isHotelConcierge());
            pr.setBoolean(14, hotel.isHouseKeeping());
            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Hotel getById(int id) {
        Hotel obj = null;
        String query = "SELECT * FROM public.hotel WHERE id = ?";
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


    public boolean update(Hotel hotel) {
        String query = "UPDATE public.hotel SET hotel_name=?, address=?, city=? , email=?, phone_number=?, stars=? WHERE id=?";

        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setString(1, hotel.getHotelName());
            pr.setInt(2, hotel.getId());
            pr.setString(3, hotel.getAddress());
            pr.setString(4, hotel.getCity());
            pr.setString(5, hotel.getEmail());
            pr.setString(6, hotel.getPhoneNumber());
            pr.setInt(7, hotel.getStars());
            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public List<PensionType> getPensionTypesByHotelId(int hotelId) {
        List<PensionType> pensionTypeList = new ArrayList<>();
        String sql = "SELECT * FROM public.pensiontype WHERE hotel_id = ?";
        try {
            PreparedStatement pr = this.conn.prepareStatement(sql);
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                PensionType obj = new PensionType();
                obj.setId(rs.getInt("id"));
                obj.setHotelId(rs.getInt("hotel_id"));
                obj.setPensionOption(PensionOption.valueOf(PensionOption.class, rs.getString("pension_option")));
                pensionTypeList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pensionTypeList;
    }

    public List<Season> getSeasonsByHotelId(int hotelId) {
        List<Season> seasons = new ArrayList<>();
        String sql = "SELECT * FROM public.season WHERE hotel_id = ?";
        try {
            PreparedStatement pr = this.conn.prepareStatement(sql);
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Season obj = new Season();
                obj.setId(rs.getInt("id"));
                obj.setHotelId(rs.getInt("hotel_id"));
                obj.setStartDate(LocalDate.parse(rs.getString("start_date")));
                obj.setEndDate(LocalDate.parse(rs.getString("end_date")));
                seasons.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasons;
    }

}
