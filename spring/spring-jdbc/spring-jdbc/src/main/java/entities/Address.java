package entities;

public class Address {
    private int addressId;
    private String houseNumber;
    private String area;
    private String city;
    private String state;

    public Address(){

    }
    public Address (int addressId,String houseNumber, String area, String city, String state) {
        this.addressId = addressId;
        this.houseNumber = houseNumber;
        this.area = area;
        this.city = city;
        this.state = state;
    }

    public int getAddressId () {
        return addressId;
    }

    public void setAddressId (int addressId) {
        this.addressId = addressId;
    }

    public String getHouseNumber () {
        return houseNumber;
    }

    public void setHouseNumber (String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getArea () {
        return area;
    }

    public void setArea (String area) {
        this.area = area;
    }

    public String getCity () {
        return city;
    }

    public void setCity (String city) {
        this.city = city;
    }

    public String getState () {
        return state;
    }

    public void setState (String state) {
        this.state = state;
    }

    @Override
    public String toString () {
        return "Address{" +
                "addressId" + addressId + '\'' +
                "houseNumber='" + houseNumber + '\'' +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}