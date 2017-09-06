package by.vasilevsky.leasing.domain;


import by.vasilevsky.leasing.dao.DataBaseConnector;


public class Company extends User {

    public Company() {
    }
    public Company(User user) {
        this.setUserID(user.getUserID());
        this.setPersonId(user.getPersonId());
        this.setCompanyId(user.getCompanyId());
        this.setEmailValid(user.getEmailValid());
        this.setUserStatusDB(user.getUserStatusDB());
    }
    private String compName;
    private String compForm;
    private String compRegistrationDate;
    private String businessType;
    private String phoneNumber;
    private int monthlyIncome;
    private int numberOfStaff;
    private int numberOfCars;
    private int numberOfLorrys;
    private int numberOfTrucks;
    private int numberOfBuildMach;
    private int numberOfFarmMach;
    private int numberOfEquipmentLines;
    private int numberOfOfficeArea;
    private int numberOfOtherArea;

    private int ceoId, contactPersonId, addressId;



    public int getCeoId() {
        return ceoId;
    }

    public void setCeoId(int ceoId) {
        this.ceoId = ceoId;
    }

    public int getContactPersonId() {
        return contactPersonId;
    }

    public void setContactPersonId(int contactPersonId) {
        this.contactPersonId = contactPersonId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setCompOfficeAddress(Address compOfficeAddress) {
        this.compOfficeAddress = compOfficeAddress;
    }

    public void setCeo(Person ceo) {
        this.ceo = ceo;
    }

    public void setContactPerson(Person contactPerson) {
        this.contactPerson = contactPerson;
    }




    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompForm() {
        return compForm;
    }

    public void setCompForm(String compForm) {
        this.compForm = compForm;
    }

    public String getCompRegistrationDate() {
        return compRegistrationDate;
    }

    public void setCompRegistrationDate(String compRegistrationDate) {
        this.compRegistrationDate = compRegistrationDate;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public int getNumberOfStaff() {
        return numberOfStaff;
    }

    public void setNumberOfStaff(int numberOfStaff) {
        this.numberOfStaff = numberOfStaff;
    }

    public int getNumberOfCars() {
        return numberOfCars;
    }

    public void setNumberOfCars(int numberOfCars) {
        this.numberOfCars = numberOfCars;
    }

    public int getNumberOfLorrys() {
        return numberOfLorrys;
    }

    public void setNumberOfLorrys(int numberOfLorrys) {
        this.numberOfLorrys = numberOfLorrys;
    }

    public int getNumberOfTrucks() {
        return numberOfTrucks;
    }

    public void setNumberOfTrucks(int numberOfTrucks) {
        this.numberOfTrucks = numberOfTrucks;
    }

    public int getNumberOfBuildMach() {
        return numberOfBuildMach;
    }

    public void setNumberOfBuildMach(int numberOfBuildMach) {
        this.numberOfBuildMach = numberOfBuildMach;
    }

    public int getNumberOfFarmMach() {
        return numberOfFarmMach;
    }

    public void setNumberOfFarmMach(int numberOfFarmMach) {
        this.numberOfFarmMach = numberOfFarmMach;
    }

    public int getNumberOfEquipmentLines() {
        return numberOfEquipmentLines;
    }

    public void setNumberOfEquipmentLines(int numberOfEquipmentLines) {
        this.numberOfEquipmentLines = numberOfEquipmentLines;
    }

    public int getNumberOfOfficeArea() {
        return numberOfOfficeArea;
    }

    public void setNumberOfOfficeArea(int numberOfOfficeArea) {
        this.numberOfOfficeArea = numberOfOfficeArea;
    }

    public int getNumberOfOtherArea() {
        return numberOfOtherArea;
    }

    public void setNumberOfOtherArea(int numberOfOtherArea) {
        this.numberOfOtherArea = numberOfOtherArea;
    }

    private Address compOfficeAddress = new Address();

    public Address getCompOfficeAddress() {
        return compOfficeAddress;
    }

    public void setPostalCode(int postalCode) {
        compOfficeAddress.setPostalCode(postalCode);
    }

    public int getPostalCode() {
        return compOfficeAddress.getPostalCode();
    }

    public void setProvince(String province) {
        compOfficeAddress.setProvince(province);
    }

    public String getProvince() {
        return compOfficeAddress.getProvince();
    }

    public void setDistrict(String district) {
        compOfficeAddress.setDistrict(district);
    }

    public String getDistrict() {
        return compOfficeAddress.getDistrict();
    }

    public void setCity(String city) {
        compOfficeAddress.setCity(city);
    }

    public String getCity() {
        return compOfficeAddress.getCity();
    }

    public void setStreet(String street) {
        compOfficeAddress.setStreet(street);
    }

    public String getStreet() {
        return compOfficeAddress.getStreet();
    }

    public void setBuilding(String building) {
        compOfficeAddress.setBuilding(building);
    }

    public String getBuilding() {
        return compOfficeAddress.getBuilding();
    }

    public void setRoom(String room) {
        compOfficeAddress.setRoom(room);
    }

    public String getRoom() {
        return compOfficeAddress.getRoom();
    }


    private Person ceo = new Person();

    public Person getCeo() {
        return ceo;
    }

    public void setCeoPosition(String position) {
        ceo.setPosition(position);
    }

    public String getCeoPosition() {
        return ceo.getPosition();
    }

    public void setCeoFirstName(String firstName) {
        ceo.setFirstName(firstName);
    }

    public String getCeoFirstName() {
        return ceo.getFirstName();
    }

    public void setCeoLastName(String lastName) {
        ceo.setLastName(lastName);
    }

    public String getCeoLastName() {
        return ceo.getLastName();
    }

    public void setCeoPatronymicName(String patronymicName) {
        ceo.setPatronymicName(patronymicName);
    }

    public String getCeoPatronymicName() {
        return ceo.getPatronymicName();
    }

    public void setCeoPhoneNumber(String phoneNumber) {
        ceo.setPhoneNumber(phoneNumber);
    }

    public String getCeoPhoneNumber() {
        return ceo.getPhoneNumber();
    }


    private Person contactPerson = new Person();

    public Person getContactPerson() {
        return contactPerson;
    }

    public void setContactPersonPosition(String position) {
        contactPerson.setPosition(position);
    }

    public String getContactPersonPosition() {
        return contactPerson.getPosition();
    }

    public void setContactPersonFirstName(String firstName) {
        contactPerson.setFirstName(firstName);
    }

    public String getContactPersonFirstName() {
        return contactPerson.getFirstName();
    }

    public void setContactPersonLastName(String lastName) {
        contactPerson.setLastName(lastName);
    }

    public String getContactPersonLastName() {
        return contactPerson.getLastName();
    }

    public void setContactPersonPatronymicName(String patronymicName) {
        contactPerson.setPatronymicName(patronymicName);
    }

    public String getContactPersonPatronymicName() {
        return contactPerson.getPatronymicName();
    }

    public void setContactPersonPhoneNumber(String phoneNumber) {
        contactPerson.setPhoneNumber(phoneNumber);
    }

    public String getContactPersonPhoneNumber() {
        return contactPerson.getPhoneNumber();
    }

    public String getFullInfo() {
        String info;
        info = "Название: " + compName + "</br>";
        info += "Форма собственности: " + compForm + "</br>";
        info += "Дата регистрации: " + compRegistrationDate + "</br>";
        info += "Вид деятельности: " + businessType + "</br>";
        info += "Номер телефона: " + phoneNumber + "</br>";
        info += "Адрес офиса: " + compOfficeAddress.getFullAddress() + "</br>";
        info += "Среднемесячная выручка, бел.руб.: " + monthlyIncome + "</br>";
        info += "Количество сотрудников: " + numberOfStaff + "</br></br></br>";
        info += "Основные средства: " + "</br></br>";
        info += "-Легковой автомобиль, ед.: " + numberOfCars + "</br>";
        info += "-Грузовой транспорт до 3.5 тонн, ед.: " + numberOfLorrys + "</br>";
        info += "-Грузовой транспорт свыше 3.5 тонн, ед.: " + numberOfTrucks + "</br>";
        info += "-Строительная техника, ед.: " + numberOfBuildMach + "</br>";
        info += "-Cельскохозяйственная техника, ед.: " + numberOfFarmMach + "</br>";
        info += "-Оборудование, линий: " + numberOfEquipmentLines + "</br>";
        info += "-Офисные площади, м.кв.: " + numberOfOfficeArea + "</br>";
        info += "-Прочие площади, м.кв.: " + numberOfOtherArea + "</br></br>";

        info += "Руководитель: " + "</br></br>";
        info += "-Должность: " + getCeoPosition() + "</br>";
        info += "-Фамилия: " + getCeoLastName() + "</br>";
        info += "-Имя: " + getCeoFirstName() + "</br>";
        info += "-Отчество: " + getCeoPatronymicName() + "</br>";
        info += "-Номер телефона: " + getCeoPhoneNumber() + "</br></br>";
        info += "Контактное лицо: " + "</br></br>";
        info += "-Должность: " + getContactPersonPosition() + "</br>";
        info += "-Фамилия: " + getContactPersonLastName() + "</br>";
        info += "-Имя: " + getContactPersonFirstName() + "</br>";
        info += "-Отчество: " + getContactPersonPatronymicName() + "</br>";
        info += "-Номер телефона: " + getContactPersonPhoneNumber() + "</br>";


        return (info);
    }

    private void calcSegment() {

    }

    public void saveProfile() {
        DataBaseConnector dbc = new DataBaseConnector();
        dbc.saveProfileCompanyToDB(this);

    }

    public void loadProfile() {
        DataBaseConnector db = new DataBaseConnector();
        db.getCompanyFromDB(this);
    }
}
