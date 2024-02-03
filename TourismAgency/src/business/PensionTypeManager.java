package business;

import dao.PensionTypeDao;
import entity.PensionType;

import java.util.ArrayList;

public class PensionTypeManager {
    private final PensionTypeDao pensionTypeDao;

    public PensionTypeManager() {
        this.pensionTypeDao = new PensionTypeDao();
    }

    public ArrayList<PensionType> findAll() {
        return this.pensionTypeDao.findAll();
    }

    public ArrayList getForTable(int size) {
        ArrayList<Object[]> pensionTypeRowList = new ArrayList<>();
        for (PensionType pensionType : this.findAll()) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = pensionType.getId();
            rowObject[i++] = pensionType.getHotelId();
            rowObject[i++] = pensionType.getPensionOption();

            pensionTypeRowList.add(rowObject);
        }
        return pensionTypeRowList;
    }

    public PensionType getById(int id) {
        return this.pensionTypeDao.getById(id);
    }
    public boolean save(PensionType pensionType){
        if (pensionType.getId() != 0){
            return false;
        }
        return this.pensionTypeDao.save(pensionType);
    }

    public boolean update(PensionType pensionType){
        if (this.getById(pensionType.getId()) == null){
            return false;
        }
        return this.pensionTypeDao.update(pensionType);
    }
}
