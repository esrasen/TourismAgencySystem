package entity;

public class PensionType {
    private int id;
    private int hotelId;
    private PensionOption pensionOption;

    public PensionType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public PensionOption getPensionOption() {
        return pensionOption;
    }

    public void setPensionOption(PensionOption pensionOption) {
        this.pensionOption = pensionOption;
    }
    @Override
    public String toString() {
        return "PensionType{" +
                "id=" + id +
                ", hotelId=" + hotelId +
                ", pensionOption=" + pensionOption +
                '}';
    }
}
