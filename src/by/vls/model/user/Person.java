package by.vls.model.user;

import by.vls.database.*;

public class Person extends User {
    private String gender;
    private String firstName;
    private String lastName;
    private String patronymicName;
    private String birthDate;
    private String phoneNumber;
    private String employer;
    private String position;
    private int monthlyIncome;
    private int addressId;
    private int postalCode;
    private String province;
    private String district;
    private String city;
    private String street;
    private String building;
    private String room;

    private int segment;

    public Person(User user) {
        this.setUserID(user.getUserID());
        this.setPersonId(user.getPersonId());
        this.setCompanyId(user.getCompanyId());
        this.setUserStatusDB(user.getUserStatusDB());
        this.setEmailValid(user.getEmailValid());
    }

    public Person() {

    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPatronymicName(String patronymicName) {
        this.patronymicName = patronymicName;
    }

    public String getPatronymicName() {
        return patronymicName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getEmployer() {
        return employer;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setMonthlyIncome(int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }


    /*public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrict() {
        return district;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getBuilding() {
        return building;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoom() {
        return room;
    }*/

    public void setSegment(int segment) {
        this.segment = segment;
    }

    public int getSegment() {
        return segment;
    }

    private Address personAddress = new Address();

    public Address getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(Address personAddress) {
        this.personAddress = personAddress;
    }

    public void setPostalCode(int postalCode) {
        personAddress.setPostalCode(postalCode);
    }

    public int getPostalCode() {
        return personAddress.getPostalCode();
    }

    public void setProvince(String province) {
        personAddress.setProvince(province);
    }

    public String getProvince() {
        return personAddress.getProvince();
    }

    public void setDistrict(String district) {
        personAddress.setDistrict(district);
    }

    public String getDistrict() {
        return personAddress.getDistrict();
    }

    public void setCity(String city) {
        personAddress.setCity(city);
    }

    public String getCity() {
        return personAddress.getCity();
    }

    public void setStreet(String street) {
        personAddress.setStreet(street);
    }

    public String getStreet() {
        return personAddress.getStreet();
    }

    public void setBuilding(String building) {
        personAddress.setBuilding(building);
    }

    public String getBuilding() {
        return personAddress.getBuilding();
    }

    public void setRoom(String room) {
        personAddress.setRoom(room);
    }

    public String getRoom() {
        return personAddress.getRoom();
    }

    public String getFullInfo() {
        String info = new String();
        info = "Пол: " + gender + "</br>";
        info += "Ф.И.О.: " + lastName + " " + firstName + " " + patronymicName + "</br>";
        info += "Адрес: " + personAddress.getFullAddress() + "</br>";
        info += "Дата рождения: " + birthDate + "</br></br>";
        info += "Номер телефона: " + phoneNumber + "</br>";
        info += "Работодатель: " + employer + "</br>";
        info += "Должность: " + position + "</br>";
        info += "Среднемесячный доход: " + monthlyIncome + " бел. руб. </br>";
        return (info);
    }

    private void calcSegment() {
        if (monthlyIncome < 1000) setSegment(1);
        else if (monthlyIncome >= 1000 & monthlyIncome < 3000) setSegment(2);
        else if (monthlyIncome >= 3000 & monthlyIncome < 5000) setSegment(3);
        else if (monthlyIncome >= 5000 & monthlyIncome < 10000) setSegment(4);
        else if (monthlyIncome >= 10000) setSegment(5);
    }

    public void saveProfile() {
        calcSegment();
        DataBaseConnector db = new DataBaseConnector();
        db.saveProfilePrivateToDB(this);
    }

    public void loadProfile() {
        DataBaseConnector db = new DataBaseConnector();
        db.getProfilePrivateFromDB(this);
    }

}