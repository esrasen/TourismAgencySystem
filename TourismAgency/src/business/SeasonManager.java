package business;

import dao.SeasonDao;
import entity.Season;

import java.util.ArrayList;

public class SeasonManager {
    private final SeasonDao seasonDao;

    public SeasonManager() {
        this.seasonDao = new SeasonDao();
    }

    public ArrayList<Season> findAll() {
        return this.seasonDao.findAll();
    }
    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> seasons){
        ArrayList<Object[]> seasonRowList = new ArrayList<>();
        for(Season season : seasons){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i ++] = season.getId();
            rowObject[i ++] = season.getHotelId();
            rowObject[i ++] = season.getStartDate().toString();
            rowObject[i ++] = season.getEndDate().toString();
            seasonRowList.add(rowObject);
        }
        return seasonRowList;
    }

    public Season getById(int id){ return this.seasonDao.getById(id); }

    public boolean save(Season season){
        if (season.getId() != 0){
            return false;
        }
        return this.seasonDao.save(season);
    }
    public boolean update(Season season){
        if (this.getById(season.getId()) == null){
            return false;
        }
        return this.seasonDao.update(season);
    }

}
