package by.vls.model.user;

import by.vls.database.DataBaseConnector;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;

public class UserValidator {
    public static final int NEW_USER = 0; // unknown user
    public static final int IDENT_USER = 1; //correct login, incorrect password;
    public static final int AUTH_USER_BSN = 2; //login - ok; password - ok; user - business
    public static final int AUTH_USER_PRVT = 3; //login - ok; password - ok; user - private person
    public static final int AUTH_USER_BSN_CLNT = 4; //login - ok; password - ok; user - business client;
    public static final int AUTH_USER_PRVT_CLNT = 5; //login - ok; password - ok; user - private client;

    private static final String USER1_LOGIN = "4denver@mail.ru";
    private static final String USER1_PSW = "vlsadmin";
    private static final String USER1_NAME = "Unknown User";
    private static final Integer USER1_ID = 1;

    public User validateUser(User user, int flg) throws Exception { //flg = 0 for auth procedure, flg = 1 - cookies
        DataBaseConnector db = new DataBaseConnector();
        if (user.getUserID() == -1) {
            db.getUserFromDB(user, user.getUserLogin());
            System.out.println("Get user from DB login: " + user.getUserLogin());
            System.out.println("Input psw: " + user.getUserPassword());
            if (user.getEmailValid()) {
                boolean checking = false;
                try {
                    if (flg == 0) {
                        checking = PasswordEncoder.check(user.getUserPassword(), user.getUserPswFromDB());
                        System.out.println("Matching pswds");
                        System.out.println("Input: " + user.getUserPassword());
                        System.out.println("From db: " + user.getUserPswFromDB());
                    } else {
                        String[] dbpsw = user.getUserPswFromDB().split("\\$");
                        if (user.getUserPassword().equals(dbpsw[1])) checking = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (checking) {
                    user.setUserStatus(user.getUserStatusDB());
                    user.setUserName(USER1_NAME);
                } else user.setUserStatus(NEW_USER);
            } else user.setUserStatus(IDENT_USER);
        } else if (user.getUserID() != -1) {
            db.getUserFromDB(user, user.getUserID());
            if (user.getEmailValid()) {
                boolean checking = false;
                try {
                    if (flg == 1) {
                        String[] dbpsw = user.getUserPswFromDB().split("\\$");
                        if (user.getUserPassword().equals(dbpsw[1])) checking = true;
                    } else checking = PasswordEncoder.check(user.getUserPassword(), user.getUserPswFromDB());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (checking) {
                    user.setUserStatus(user.getUserStatusDB());
                    user.setUserName(USER1_NAME);
                } else user.setUserStatus(NEW_USER);
            } else user.setUserStatus(IDENT_USER);
        }
        return user;
    }

    public void checkCookies(HttpServletRequest request, User user) throws Exception {
        UserValidator valid = new UserValidator();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userid")) {
                    System.out.println("Cookie User id: " + cookie.getValue());
                    String id = new String(Base64.decodeBase64(cookie.getValue()));
                    user.setUserID(Integer.parseInt(id));
                    System.out.println("Cookie User id decoded: " + user.getUserID());
                } else if (cookie.getName().equals("userpsw")) {
                    user.setUserPassword(cookie.getValue());
                    System.out.println("Cookie Userpsw: " + cookie.getValue());
                }
            }
            valid.validateUser(user, 1);
        } else user.setUserStatus(UserValidator.NEW_USER); //если нет куков то новый юзер
    }

    public boolean checkEmail(String email) {
        User user = new User();
        DataBaseConnector db = new DataBaseConnector();
        db.getUserFromDB(user, email);
        if (user.getUserID() == -1) return true;
        else return false;
    }

    public int registerNewUser(User user) {
        //need to add encrypting password and key
        DataBaseConnector db = new DataBaseConnector();
        //user.setPswKey("1");
        try {
            user.setUserPassword(PasswordEncoder.getSaltedHash(user.getUserPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.addUserToDB(user);
        EmailSender emlValid = new EmailSender();
        System.out.println("++User before e-mail validation: ");
        System.out.println("User login: " + user.getUserLogin());
        System.out.println("PasswordEncoder: " + user.getUserPassword());
        System.out.println("Id: " + user.getUserID());
        System.out.println("PasswordEncoder from db: " + user.getUserPswFromDB());
        emlValid.validateEmail(user);
        return 0;
    }
}
