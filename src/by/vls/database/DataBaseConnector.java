package by.vls.database;

import java.sql.*;
import java.util.*;

import by.vls.model.user.Address;
import by.vls.model.user.Company;
import by.vls.model.user.Person;
import by.vls.model.user.User;
import by.vls.web.GetCalcForm;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataBaseConnector {
    private static final int tomcatflg = 1;//0-work with main; 1-work in tomcatcontainer
    private static final float COST_DIFF = 30000f;
    private static final float COST_INS_MARGIN = 0.0019f;

    private Connection getConnectionFromAppContext() {
        Context ctx = null;
        DataSource dataSource;
        Connection con = null;
        try {
            ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/vls");
            try {
                con = dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (NamingException e) {
            e.printStackTrace();
        }
        return con;
    }

    //getting base rate parameters
    public HashMap getBaseRateTable() {
        HashMap baseRateTable;
        if (tomcatflg == 0) {
            ContextListenerMock vlsf = new ContextListenerMock();
            baseRateTable = (HashMap) vlsf.testSQL().get("baseRate");
        } else {
            baseRateTable = (HashMap) GetCalcForm.getContext().getAttribute("configcalc");
            baseRateTable = (HashMap) baseRateTable.get("baseRate");
        }
        return baseRateTable;
    }

    public float getBaseCurrencyMargin() {
        return 0.02f;
    }

    //object type margin table
    public HashMap getObjTypeMarginTable() {
        HashMap objTypeTable;
        if (tomcatflg == 0) {
            ContextListenerMock vlsf = new ContextListenerMock();
            objTypeTable = (HashMap) vlsf.testSQL().get("objTypeMargin");
        } else {
            objTypeTable = (HashMap) GetCalcForm.getContext().getAttribute("configcalc");
            objTypeTable = (HashMap) objTypeTable.get("objTypeMargin");
        }
        return objTypeTable;
    }

    //object age margin table
    public HashMap getObjAgeMarginTable() {
        HashMap objAgeTable;
        if (tomcatflg == 0) {
            ContextListenerMock vlsf = new ContextListenerMock();
            objAgeTable = (HashMap) vlsf.testSQL().get("objAgeTable");
        } else {
            objAgeTable = (HashMap) GetCalcForm.getContext().getAttribute("configcalc");
            objAgeTable = (HashMap) objAgeTable.get("objAgeTable");
        }
        return objAgeTable;
    }

    public float getObjCostDicount() {
        return 0f;
    }

    public float getSegDiscount() {
        return 0f;
    }

    public float getRegClientDiscount() {
        return 0f;
    }

    //methods of forms config: obj types, prepay sizes etc.
    public void getFormConfig() {

    }

    //getting insurance rate parameters
    public HashMap getInsureTypeTable() {
        HashMap insureTypeTable;
        if (tomcatflg == 0) {
            ContextListenerMock vlsf = new ContextListenerMock();
            insureTypeTable = (HashMap) vlsf.testSQL().get("insureTypeTable");
        } else {
            insureTypeTable = (HashMap) GetCalcForm.getContext().getAttribute("configcalc");
            insureTypeTable = (HashMap) insureTypeTable.get("insureTypeTable");
        }
        return insureTypeTable;
    }

    //additional insurance rate because of object age
    public HashMap getInsureAgeTable() {
        HashMap insureAgeTable;
        if (tomcatflg == 0) {
            ContextListenerMock vlsf = new ContextListenerMock();
            insureAgeTable = (HashMap) vlsf.testSQL().get("insureAgeTable");
        } else {
            insureAgeTable = (HashMap) GetCalcForm.getContext().getAttribute("configcalc");
            insureAgeTable = (HashMap) insureAgeTable.get("insureAgeTable");
        }
        return insureAgeTable;
    }

    public float getCostDiff() {
        return COST_DIFF;
    }

    public float getCostDiffRate() {
        return COST_INS_MARGIN;
    }

    public User getUserFromDB(User user, String login) {
        Statement stmt = null;
        ResultSet rs1 = null;
        Connection con = null;
        String REQ1 = "SELECT user_id, user_psw, email_valid, user_status_id, person_id, company_id FROM users WHERE user_login=\"" + login + "\";";

        if (tomcatflg == 0) {
            ContextListenerMock vlsf = new ContextListenerMock();
            con = vlsf.getConnection();
        } else {
            con = getConnectionFromAppContext();
        }

        try {
            stmt = con.createStatement();
            rs1 = stmt.executeQuery(REQ1);
            if (rs1.next()) {
                user.setUserID(rs1.getInt("user_id"));
                user.setUserPswFromDB(rs1.getString("user_psw"));
                user.setEmailValid(rs1.getBoolean("email_valid"));
                user.setUserStatusDB(rs1.getInt("user_status_id"));
                user.setPersonId(rs1.getInt("person_id"));
                System.out.println("setPersonId(login): " + user.getPersonId());
                user.setCompanyId(rs1.getInt("company_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) {
            }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs1.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
        return user;
    }

    public User getUserFromDB(User user, int userid) {
        Statement stmt = null;
        ResultSet rs1 = null;
        Connection con = null;
        String REQ1 = "SELECT user_login, user_psw, email_valid, user_status_id, person_id, company_id FROM users WHERE user_id=\"" + userid + "\";";

        if (tomcatflg == 0) {
            ContextListenerMock vlsf = new ContextListenerMock();
            con = vlsf.getConnection();
        } else {
            con = getConnectionFromAppContext();
        }

        try {
            stmt = con.createStatement();
            rs1 = stmt.executeQuery(REQ1);
            if (rs1.next()) {
                user.setUserLogin(rs1.getString("user_login"));
                user.setUserPswFromDB(rs1.getString("user_psw"));
                user.setEmailValid(rs1.getBoolean("email_valid"));
                user.setUserStatusDB(rs1.getInt("user_status_id"));
                user.setPersonId(rs1.getInt("person_id"));
                System.out.println("setPersonId(userId): " + user.getPersonId());
                user.setCompanyId(rs1.getInt("company_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) {
            }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs1.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
        return user;
    }

    public void setUserPswChecked(String psw) {
        Statement stmt = null;
        Connection con = null;
        String dbReqValidEmail = "UPDATE users SET email_valid=\"1\" WHERE user_psw=\"" + psw + "\";";

        if (tomcatflg == 0) {
            ContextListenerMock vlsf = new ContextListenerMock();
            con = vlsf.getConnection();
        } else {
            con = getConnectionFromAppContext();
        }

        try {
            stmt = con.createStatement();
            stmt.executeUpdate(dbReqValidEmail);
            System.out.println("Try to check e-mail is valid");
            System.out.println("For password: " + psw);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) {
            }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }

        }
    }

    public void addUserToDB(User user) {
        PreparedStatement stmt = null;
        Connection con = null;
        String REQ1 = "INSERT INTO users (user_login, user_psw, user_status_id) " +
                "VALUES (?,?,?);";

        if (tomcatflg == 0) {
            ContextListenerMock vlsf = new ContextListenerMock();
            con = vlsf.getConnection();
        } else {
            con = getConnectionFromAppContext();
        }

        try {
            stmt = con.prepareStatement(REQ1);
            stmt.setString(1, user.getUserLogin());
            stmt.setString(2, user.getUserPassword());
            stmt.setInt(3, user.getUserStatus());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) {
            }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }

        }
    }

    public void saveProfilePrivateToDB(Person person) {
        if (person.getPersonId() != 0) {
            Person tempPerson = getPersonFromDB(person.getPersonId()); //getting addressID
            person.setAddressId(tempPerson.getAddressId());
            person.getPersonAddress().setAddressId(tempPerson.getAddressId());
            updatePersonInDB(person);
        } else {
            addPersonToDB(person);
            updateUserInDB(person);
        }

    }

    private void updateUserInDB(User user) {
        String dbReqUpdAddress = "UPDATE users SET " +
                "person_id=?,company_id=? " +
                "WHERE user_id=?;";
        Connection con;
        PreparedStatement prepStatement = null;
        if (tomcatflg == 0) {
            ContextListenerMock vlsf = new ContextListenerMock();
            con = vlsf.getConnection();
        } else {
            con = getConnectionFromAppContext();
        }
        try {
            prepStatement = con.prepareStatement(dbReqUpdAddress);
            prepStatement.setInt(1, user.getPersonId());
            prepStatement.setInt(2, user.getCompanyId());
            prepStatement.setInt(3, user.getUserID());
            prepStatement.execute();

        } catch (
                SQLException e)

        {
            e.printStackTrace();
        } finally

        {
            try {
                prepStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getProfilePrivateFromDB(Person person) {


        Person tempPerson = getPersonFromDB(person.getPersonId());
        System.out.println("Person id from user: " + person.getPersonId());
        System.out.println("User id: " + person.getUserID());
        person.setGender(tempPerson.getGender());
        person.setFirstName(tempPerson.getFirstName());
        person.setLastName(tempPerson.getLastName());
        person.setPatronymicName(tempPerson.getPatronymicName());
        person.setBirthDate(tempPerson.getBirthDate());
        person.setAddressId(tempPerson.getAddressId());
        person.setPersonAddress(tempPerson.getPersonAddress());
        person.setPhoneNumber(tempPerson.getPhoneNumber());
        person.setEmployer(tempPerson.getEmployer());
        person.setPosition(tempPerson.getPosition());
        person.setMonthlyIncome(tempPerson.getMonthlyIncome());
        person.setSegment(tempPerson.getSegment());
        System.out.println("Private profile load:" + person.getLastName() + tempPerson.getLastName());
    }

    private int checkAddressExistence(Address address) {
        int id = -1;
        String dbReqAddAddress = "SELECT id FROM address WHERE " +
                "postal_code=? AND province=? AND district=? AND city=? AND street=? AND building=? AND room=?;";
        Connection con;
        ResultSet resultSet = null;
        PreparedStatement prepStatement = null;

        if (tomcatflg == 0) {
            ContextListenerMock vlsf = new ContextListenerMock();
            con = vlsf.getConnection();
        } else {
            con = getConnectionFromAppContext();
        }

        try {
            prepStatement = con.prepareStatement(dbReqAddAddress);
            prepStatement.setInt(1, address.getPostalCode());
            prepStatement.setString(2, address.getProvince());
            prepStatement.setString(3, address.getDistrict());
            prepStatement.setString(4, address.getCity());
            prepStatement.setString(5, address.getStreet());
            prepStatement.setString(6, address.getBuilding());
            prepStatement.setString(7, address.getRoom());

            resultSet = prepStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (
                SQLException e)

        {
            e.printStackTrace();
        } finally

        {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                prepStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return id;
    }

    private void addAddressToDB(Address address) {
        String dbReqAddAddress = "INSERT INTO address (postal_code ,province, district, city, street, building, room) "
                + "VALUES (?,?,?,?,?,?,?);";
        Connection con;
        PreparedStatement prepStatement = null;
        ResultSet generatedKey = null;
        if (tomcatflg == 0) {
            ContextListenerMock vlsf = new ContextListenerMock();
            con = vlsf.getConnection();
        } else {
            con = getConnectionFromAppContext();
        }
        try {
            prepStatement = con.prepareStatement(dbReqAddAddress, Statement.RETURN_GENERATED_KEYS);
            prepStatement.setInt(1, address.getPostalCode());
            prepStatement.setString(2, address.getProvince());
            prepStatement.setString(3, address.getDistrict());
            prepStatement.setString(4, address.getCity());
            prepStatement.setString(5, address.getStreet());
            prepStatement.setString(6, address.getBuilding());
            prepStatement.setString(7, address.getRoom());
            prepStatement.execute();
            generatedKey = prepStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                address.setAddressId(generatedKey.getInt(1));
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prepStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    private void updateAddressInDB(Address address) {
        String dbReqUpdAddress = "UPDATE address SET " +
                "postal_code=?,province=?,district=?,city=?,street=?,building=?,room=? " +
                "WHERE id=?;";
        Connection con;
        PreparedStatement prepStatement = null;
        if (tomcatflg == 0) {
            ContextListenerMock vlsf = new ContextListenerMock();
            con = vlsf.getConnection();
        } else {
            con = getConnectionFromAppContext();
        }
        try {
            prepStatement = con.prepareStatement(dbReqUpdAddress);
            prepStatement.setInt(1, address.getPostalCode());
            prepStatement.setString(2, address.getProvince());
            prepStatement.setString(3, address.getDistrict());
            prepStatement.setString(4, address.getCity());
            prepStatement.setString(5, address.getStreet());
            prepStatement.setString(6, address.getBuilding());
            prepStatement.setString(7, address.getRoom());
            prepStatement.setInt(8, address.getAddressId());
            prepStatement.execute();

        } catch (
                SQLException e)

        {
            e.printStackTrace();
        } finally

        {
            try {
                prepStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Address getAddressFromDB(int addressId) {
        Address address = new Address();
        String dbReqGetAddress = "SELECT * FROM address WHERE " +
                "id=?;";
        Connection con;
        ResultSet resultSet = null;
        PreparedStatement prepStatement = null;

        if (tomcatflg == 0) {
            ContextListenerMock vlsf = new ContextListenerMock();
            con = vlsf.getConnection();
        } else {
            con = getConnectionFromAppContext();
        }

        try {
            prepStatement = con.prepareStatement(dbReqGetAddress);
            prepStatement.setInt(1, addressId);

            resultSet = prepStatement.executeQuery();

            if (resultSet.next()) {
                address.setPostalCode(resultSet.getInt("postal_code"));
                address.setProvince(resultSet.getString("province"));
                address.setDistrict(resultSet.getString("district"));
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setBuilding(resultSet.getString("building"));
                address.setRoom(resultSet.getString("room"));
            }
        } catch (
                SQLException e)

        {
            e.printStackTrace();
        } finally

        {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                prepStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return address;
    }

    private int checkPersonExistence(Person person) { //return the id of existed person by full name, or -1 if it doesn't exist
        int id = -1;
        String dbReqCheckPersonExistence = "SELECT id FROM persons WHERE phonenumber=? AND firstname=? AND " +
                "lastname=? AND patronymicname=? AND position=?;";
        Connection con = null;
        ResultSet resultSet = null;
        PreparedStatement prepStatement = null;

        if (tomcatflg == 0) {
            ContextListenerMock vlsf = new ContextListenerMock();
            con = vlsf.getConnection();
        } else {
            con = getConnectionFromAppContext();
        }

        try {
            prepStatement = con.prepareStatement(dbReqCheckPersonExistence);
            prepStatement.setString(1, person.getPhoneNumber());
            prepStatement.setString(2, person.getFirstName());
            prepStatement.setString(3, person.getLastName());
            prepStatement.setString(4, person.getPatronymicName());
            prepStatement.setString(5, person.getPosition());

            resultSet = prepStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                prepStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return id;
    }

    private void addPersonToDB(Person person) {
        String dbReqAddPerson = "INSERT INTO persons " +
                "(gender,firstname,lastname,patronymicname,birthdate,address_id,phonenumber," +
                "employer,position,monthlyincome,segment) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
        Connection con;
        PreparedStatement prepStatement = null;
        ResultSet generatedKey = null;

        addAddressToDB(person.getPersonAddress());
        person.setAddressId(person.getPersonAddress().getAddressId());

        if (tomcatflg == 0) {
            ContextListenerMock vlsf = new ContextListenerMock();
            con = vlsf.getConnection();
        } else {
            con = getConnectionFromAppContext();
        }

        try {
            prepStatement = con.prepareStatement(dbReqAddPerson, Statement.RETURN_GENERATED_KEYS);
            prepStatement.setString(1, person.getGender());
            prepStatement.setString(2, person.getFirstName());
            prepStatement.setString(3, person.getLastName());
            prepStatement.setString(4, person.getPatronymicName());
            prepStatement.setString(5, convertDate_ddmmyyy_ToMysql(person.getBirthDate()));
            prepStatement.setInt(6, person.getAddressId());
            prepStatement.setString(7, person.getPhoneNumber());
            prepStatement.setString(8, person.getEmployer());
            prepStatement.setString(9, person.getPosition());
            prepStatement.setInt(10, person.getMonthlyIncome());
            prepStatement.setInt(11, person.getSegment());
            prepStatement.execute();
            generatedKey = prepStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                person.setPersonId(generatedKey.getInt(1));
            }

        } catch (
                SQLException e)

        {
            e.printStackTrace();
        } finally

        {
            try {
                generatedKey.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                prepStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    private void updatePersonInDB(Person person) {
        String dbReqUpdPerson = "UPDATE persons " +
                "SET gender=?,firstname=?,lastname=?,patronymicname=?,birthdate=?,phonenumber=?," +
                "employer=?,position=?,monthlyincome=?,segment=? "
                + "WHERE id=?;";
        Connection con;
        PreparedStatement prepStatement = null;

        updateAddressInDB(person.getPersonAddress());
        System.out.println("Person address updating: ");
        System.out.println("Address id: " + person.getAddressId());
        System.out.println("Address street: " + person.getPersonAddress().getStreet());


        if (tomcatflg == 0) {
            ContextListenerMock vlsf = new ContextListenerMock();
            con = vlsf.getConnection();
        } else {
            con = getConnectionFromAppContext();
        }

        try {
            prepStatement = con.prepareStatement(dbReqUpdPerson);
            prepStatement.setString(1, person.getGender());
            prepStatement.setString(2, person.getFirstName());
            prepStatement.setString(3, person.getLastName());
            prepStatement.setString(4, person.getPatronymicName());
            prepStatement.setString(5, convertDate_ddmmyyy_ToMysql(person.getBirthDate()));
            prepStatement.setString(6, person.getPhoneNumber());
            prepStatement.setString(7, person.getEmployer());
            prepStatement.setString(8, person.getPosition());
            prepStatement.setInt(9, person.getMonthlyIncome());
            prepStatement.setInt(10, person.getSegment());
            prepStatement.setInt(11, person.getPersonId());

            prepStatement.executeUpdate();

        } catch (
                SQLException e)

        {
            e.printStackTrace();
        } finally

        {
            try {
                prepStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    private Person getPersonFromDB(int personId) {
        Person person = new Person();
        String dbReqGetPerson = "SELECT * FROM persons WHERE " +
                "id=?;";
        Connection con;
        ResultSet resultSet = null;
        PreparedStatement prepStatement = null;

        if (tomcatflg == 0) {
            ContextListenerMock vlsf = new ContextListenerMock();
            con = vlsf.getConnection();
        } else {
            con = getConnectionFromAppContext();
        }

        try {
            prepStatement = con.prepareStatement(dbReqGetPerson);
            prepStatement.setInt(1, personId);
            resultSet = prepStatement.executeQuery();

            if (resultSet.next()) {
                person.setGender(resultSet.getString("gender"));
                person.setFirstName(resultSet.getString("firstname"));
                person.setLastName(resultSet.getString("lastname"));
                person.setPatronymicName(resultSet.getString("patronymicname"));
                person.setBirthDate(convertDateMysql_To_ddmmyyyy(resultSet.getString("birthdate")));
                person.setAddressId(resultSet.getInt("address_id"));
                person.setPhoneNumber(resultSet.getString("phonenumber"));
                person.setEmployer(resultSet.getString("employer"));
                person.setPosition(resultSet.getString("position"));
                person.setMonthlyIncome(resultSet.getInt("monthlyincome"));
                person.setSegment(resultSet.getInt("segment"));

                person.setPersonAddress(getAddressFromDB(person.getAddressId()));
            }
        } catch (
                SQLException e)

        {
            e.printStackTrace();
        } finally

        {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                prepStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return person;
    }

    private boolean checkCompanyExistence(Company company) {
        boolean companyExistance = false;
        String dbReqAddAddress = "SELECT id FROM companies WHERE " +
                "id=?;";
        Connection con;
        ResultSet resultSet = null;
        PreparedStatement prepStatement = null;

        if (tomcatflg == 0) {
            ContextListenerMock vlsf = new ContextListenerMock();
            con = vlsf.getConnection();
        } else {
            con = getConnectionFromAppContext();
        }

        try {
            prepStatement = con.prepareStatement(dbReqAddAddress);
            prepStatement.setInt(1, company.getUserID());
            resultSet = prepStatement.executeQuery();
            if (resultSet.next()) {
                companyExistance = true;
            }

        } catch (
                SQLException e)

        {
            e.printStackTrace();
        } finally

        {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                prepStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return companyExistance;
    }

    private void addCompanyToDB(Company company) {
        String dbReqAddCompany = "INSERT INTO companies " +
                "(comp_name,comp_form,comp_registration_date,business_type,phone_number,monthly_income," +
                "number_of_staff,number_of_cars,number_of_lorrys,number_of_trucks,number_of_build_mach," +
                "number_of_farm_mach,number_of_equipment_lines,number_of_office_area,number_of_other_area," +
                "ceo_id,contact_person_id,address_id) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        Connection con;
        PreparedStatement prepStatement = null;
        ResultSet generatedKey = null;

        addAddressToDB(company.getCompOfficeAddress());
        company.setAddressId(company.getCompOfficeAddress().getAddressId());
        addPersonToDB(company.getCeo());
        company.setCeoId(company.getCeo().getPersonId());
        addPersonToDB(company.getContactPerson());
        company.setContactPersonId(company.getContactPerson().getPersonId());


        if (tomcatflg == 0) {
            ContextListenerMock vlsf = new ContextListenerMock();
            con = vlsf.getConnection();
        } else {
            con = getConnectionFromAppContext();
        }

        try {
            prepStatement = con.prepareStatement(dbReqAddCompany, Statement.RETURN_GENERATED_KEYS);
            prepStatement.setString(1, company.getCompName());
            prepStatement.setString(2, company.getCompForm());
            prepStatement.setString(3, convertDate_ddmmyyy_ToMysql(company.getCompRegistrationDate()));
            prepStatement.setString(4, company.getBusinessType());
            prepStatement.setString(5, company.getPhoneNumber());
            prepStatement.setInt(6, company.getMonthlyIncome());
            prepStatement.setInt(7, company.getNumberOfStaff());
            prepStatement.setInt(8, company.getNumberOfCars());
            prepStatement.setInt(9, company.getNumberOfLorrys());
            prepStatement.setInt(10, company.getNumberOfTrucks());
            prepStatement.setInt(11, company.getNumberOfBuildMach());
            prepStatement.setInt(12, company.getNumberOfFarmMach());
            prepStatement.setInt(13, company.getNumberOfEquipmentLines());
            prepStatement.setInt(14, company.getNumberOfOfficeArea());
            prepStatement.setInt(15, company.getNumberOfOtherArea());
            prepStatement.setInt(16, company.getCeoId());
            prepStatement.setInt(17, company.getContactPersonId());
            prepStatement.setInt(18, company.getAddressId());
            prepStatement.executeUpdate();

            generatedKey = prepStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                company.setCompanyId(generatedKey.getInt(1));
            }

        } catch (
                SQLException e)

        {
            e.printStackTrace();
        } finally

        {
            try {
                prepStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    private void updateCompanyInDB(Company company) {
        String dbReqUpdCompany = "UPDATE companies " +
                "SET comp_name=?,comp_form=?,comp_registration_date=?,business_type=?,phone_number=?,monthly_income=?," +
                "number_of_staff=?,number_of_cars=?,number_of_lorrys=?,number_of_trucks=?,number_of_build_mach=?," +
                "number_of_farm_mach=?,number_of_equipment_lines=?,number_of_office_area=?,number_of_other_area=? " +
                "WHERE id=?;";
        Connection con;
        Company tempCompany = new Company();
        tempCompany.setCompanyId(company.getCompanyId());
        getCompanyFromDB(tempCompany); // for getting ceoId, contactPersonId, addressId
        System.out.println("+++Temp company Id: " + tempCompany.getCompanyId());

        System.out.println("+++Address id: " + tempCompany.getAddressId());
        System.out.println("+++CEO id: " + tempCompany.getCeoId());
        System.out.println("+++ContPers id: " + tempCompany.getContactPersonId());

        company.getCeo().setPersonId(tempCompany.getCeoId());
        company.getContactPerson().setPersonId(tempCompany.getContactPersonId());
        company.getCompOfficeAddress().setAddressId(tempCompany.getAddressId());

        updateAddressInDB(company.getCompOfficeAddress());
        updatePersonInDB(company.getCeo());
        updatePersonInDB(company.getContactPerson());

        PreparedStatement prepStatement = null;

        if (tomcatflg == 0) {
            ContextListenerMock vlsf = new ContextListenerMock();
            con = vlsf.getConnection();
        } else {
            con = getConnectionFromAppContext();
        }

        try {
            prepStatement = con.prepareStatement(dbReqUpdCompany);

            prepStatement.setString(1, company.getCompName());
            prepStatement.setString(2, company.getCompForm());
            prepStatement.setString(3, convertDate_ddmmyyy_ToMysql(company.getCompRegistrationDate()));
            prepStatement.setString(4, company.getBusinessType());
            prepStatement.setString(5, company.getPhoneNumber());
            prepStatement.setInt(6, company.getMonthlyIncome());
            prepStatement.setInt(7, company.getNumberOfStaff());
            prepStatement.setInt(8, company.getNumberOfCars());
            prepStatement.setInt(9, company.getNumberOfLorrys());
            prepStatement.setInt(10, company.getNumberOfTrucks());
            prepStatement.setInt(11, company.getNumberOfBuildMach());
            prepStatement.setInt(12, company.getNumberOfFarmMach());
            prepStatement.setInt(13, company.getNumberOfEquipmentLines());
            prepStatement.setInt(14, company.getNumberOfOfficeArea());
            prepStatement.setInt(15, company.getNumberOfOtherArea());
            prepStatement.setInt(16, company.getCompanyId());
            prepStatement.executeUpdate();

        } catch (
                SQLException e)

        {
            e.printStackTrace();
        } finally

        {
            try {
                prepStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void getCompanyFromDB(Company company) {
        String dbReqGetCompany = "SELECT * FROM companies WHERE " +
                "id=?;";
        Connection con;
        ResultSet resultSet = null;
        PreparedStatement prepStatement = null;

        if (tomcatflg == 0) {
            ContextListenerMock vlsf = new ContextListenerMock();
            con = vlsf.getConnection();
        } else {
            con = getConnectionFromAppContext();
        }

        try {
            prepStatement = con.prepareStatement(dbReqGetCompany);
            prepStatement.setInt(1, company.getCompanyId());
            resultSet = prepStatement.executeQuery();

            if (resultSet.next()) {
                company.setCompName(resultSet.getString("comp_name"));
                company.setCompForm(resultSet.getString("comp_form"));
                company.setCompRegistrationDate(convertDateMysql_To_ddmmyyyy(resultSet.getString("comp_registration_date")));
                company.setBusinessType(resultSet.getString("business_type"));
                company.setPhoneNumber(resultSet.getString("phone_number"));
                company.setMonthlyIncome(resultSet.getInt("monthly_income"));
                company.setNumberOfStaff(resultSet.getInt("number_of_staff"));
                company.setNumberOfCars(resultSet.getInt("number_of_cars"));
                company.setNumberOfLorrys(resultSet.getInt("number_of_lorrys"));
                company.setNumberOfTrucks(resultSet.getInt("number_of_trucks"));
                company.setNumberOfBuildMach(resultSet.getInt("number_of_build_mach"));
                company.setNumberOfFarmMach(resultSet.getInt("number_of_farm_mach"));
                company.setNumberOfEquipmentLines(resultSet.getInt("number_of_equipment_lines"));
                company.setNumberOfOfficeArea(resultSet.getInt("number_of_office_area"));
                company.setNumberOfOtherArea(resultSet.getInt("number_of_other_area"));
                company.setCeoId(resultSet.getInt("ceo_id"));
                company.setContactPersonId(resultSet.getInt("contact_person_id"));
                company.setAddressId(resultSet.getInt("address_id"));

                company.setCompOfficeAddress(getAddressFromDB(company.getAddressId()));
                company.setCeo(getPersonFromDB(company.getCeoId()));
                company.setContactPerson(getPersonFromDB(company.getContactPersonId()));

            }
        } catch (
                SQLException e)

        {
            e.printStackTrace();
        } finally

        {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                prepStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void saveProfileCompanyToDB(Company company) {

        if (company.getCompanyId() != 0) {
            updateCompanyInDB(company);
        } else {
            addCompanyToDB(company);
            updateUserInDB(company);
        }
    }

    public static String convertDate_ddmmyyy_ToMysql(String in_date) {
        String out_date;
        if (in_date != null) {

            String[] splitdate = in_date.split("\\.");
            out_date = splitdate[2] + "-" + splitdate[1] + "-" + splitdate[0];
            return out_date;
        } else {
            return ("1900-01-01");
        }
    }

    public static String convertDateMysql_To_ddmmyyyy(String in_date) {
        String out_date;
        if (in_date != null) {
            String[] splitdate = in_date.split("-");
            out_date = splitdate[2] + "." + splitdate[1] + "." + splitdate[0];
            return out_date;
        } else {
            return ("01.01.1900");
        }
    }
}
