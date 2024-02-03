package dao;

import entity.PensionOption;
import entity.PensionType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PensionTypeDao extends BaseDao{

    public PensionTypeDao() {
        super();
    }

    public ArrayList<PensionType> findAll(){
        ArrayList<PensionType> pensionTypeList = new ArrayList<>();
        String sql = "SELECT * FROM public.pensiontype ORDER BY id ASC";
        try{
            ResultSet rs = this.conn.createStatement().executeQuery(sql);
            while (rs.next()){
                pensionTypeList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pensionTypeList;
    }
    public PensionType match(ResultSet rs) throws SQLException {
        PensionType obj = new PensionType();
        obj.setId(rs.getInt("id"));
        obj.setHotelId(rs.getInt("hotel_id"));
        obj.setPensionOption(PensionOption.valueOf(PensionOption.class, rs.getString("pension_option")));

        return obj;
    }

    public boolean save(PensionType pensionType){

            String query = "INSERT INTO public.pensiontype(hotel_id, pension_option) VALUES (?,?)";
            try {
                PreparedStatement pr = this.conn.prepareStatement(query);
                pr.setInt(1, pensionType.getHotelId());
                pr.setString(2, pensionType.getPensionOption().name());
                return pr.executeUpdate() != -1;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return true;
    }

    public boolean update(PensionType pensionType){
        String query = "UPDATE public.pensiontype SET hotel_id=?, pension_option=? WHERE id=?";
        try{
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, pensionType.getHotelId());
            pr.setString(2, pensionType.getPensionOption().toString());
            pr.setInt(3, pensionType.getId());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }


    public PensionType getById(int id){
        PensionType obj = null;
        String query = "SELECT * FROM public.pensiontype WHERE id = ?";
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
