package by.vls.model.user;

public class User {
    public static final int PRIVATE_USER = 1; // private person
    public static final int BUSINESS_USER = 2; // company or private entrepreneur
    private String userLogin;
    private String userPassword;
    private String userName;
    private int userID;
    private int userStatus;
    private String pswFromDB;
    private boolean emailValid;
    private int userStatusDB;

    private int personId, companyId;

    public User() {
        userLogin = "-1";
        userID = -1;
        userPassword = "-1";
        userStatus = -1;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public void setUserLogin(String login) {
        userLogin = login;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserPassword(String password) {
        userPassword = password;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserID(Integer userid) {
        userID = userid;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserStatus(int status) {
        userStatus = status;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserName(String name) {
        userName = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserPswFromDB(String password) {
        pswFromDB = password;
    }

    public String getUserPswFromDB() {
        return pswFromDB;
    }

    public void setEmailValid(boolean emailValid) {
        this.emailValid = emailValid;
    }

    public boolean getEmailValid() {
        return emailValid;
    }

    public void setUserStatusDB(int status) {
        userStatusDB = status;
    }

    public int getUserStatusDB() {
        return userStatusDB;
    }
}

