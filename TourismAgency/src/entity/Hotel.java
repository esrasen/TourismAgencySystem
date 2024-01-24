package entity;
public class Hotel {

    private int id;
    private String hotelName;
    private String city;
    private String region;
    private String address;
    private String email;
    private String phoneNumber;
    private double stars;
    private boolean freePark;
    private boolean freeWifi;
    private boolean swimmingPool;
    private boolean fitnessCenter;
    private boolean spa;
    private boolean hotelConcierge;
    private boolean houseKeeping;

    public Hotel() {
    }

    public Hotel(int id, String hotelName, String city, String region, String address, String email, String phoneNumber, double stars, boolean freePark, boolean freeWifi, boolean swimmingPool, boolean fitnessCenter, boolean spa, boolean hotelConcierge, boolean houseKeeping) {
        this.id = id;
        this.hotelName = hotelName;
        this.city = city;
        this.region = region;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.stars = stars;
        this.freePark = freePark;
        this.freeWifi = freeWifi;
        this.swimmingPool = swimmingPool;
        this.fitnessCenter = fitnessCenter;
        this.spa = spa;
        this.hotelConcierge = hotelConcierge;
        this.houseKeeping = houseKeeping;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public boolean isFreePark() {
        return freePark;
    }

    public void setFreePark(boolean freePark) {
        this.freePark = freePark;
    }

    public boolean isFreeWifi() {
        return freeWifi;
    }

    public void setFreeWifi(boolean freeWifi) {
        this.freeWifi = freeWifi;
    }

    public boolean isSwimmingPool() {
        return swimmingPool;
    }

    public void setSwimmingPool(boolean swimmingPool) {
        this.swimmingPool = swimmingPool;
    }

    public boolean isFitnessCenter() {
        return fitnessCenter;
    }

    public void setFitnessCenter(boolean fitnessCenter) {
        this.fitnessCenter = fitnessCenter;
    }

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }

    public boolean isHotelConcierge() {
        return hotelConcierge;
    }

    public void setHotelConcierge(boolean hotelConcierge) {
        this.hotelConcierge = hotelConcierge;
    }

    public boolean isHouseKeeping() {
        return houseKeeping;
    }

    public void setHouseKeeping(boolean houseKeeping) {
        this.houseKeeping = houseKeeping;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + id +
                ", hotelName='" + hotelName + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", starts=" + stars +
                ", freePark=" + freePark +
                ", freeWifi=" + freeWifi +
                ", swimmingPool=" + swimmingPool +
                ", fitnessCenter=" + fitnessCenter +
                ", spa=" + spa +
                ", hotelConcierge=" + hotelConcierge +
                ", houseKeeping=" + houseKeeping +
                '}';
    }
}
