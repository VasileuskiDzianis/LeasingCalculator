package by.vls.model.user;

/**
 * Created by Zenbook on 05.02.2017.
 */
public class Address {
    private int addressId;
    private int postalCode;
    private String province;
    private String district;
    private String city;
    private String street;
    private String building;
    private String room;

   public String getFullAddress() {
        String printadr = new String();
        if (postalCode != 0) printadr += "инд. " + postalCode + ", ";
        if (province != null) printadr += "обл. " + province + ", ";
        if (district != null) printadr += "р-н " + district + ", ";
        if (city != null) printadr += city + ", ";
        if (street != null) printadr += street + ", ";
        if (building != null) printadr += building + ", ";
        if (room != null) printadr += room;
        return printadr;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getProvince() {
        return province;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getBuilding() {
        return building;
    }

    public String getRoom() {
        return room;
    }
}
