package by.vasilevsky.leasing.service;

import javax.mail.*;
import javax.mail.internet.*;

import by.vasilevsky.leasing.domain.User;

import java.util.Properties;

/**
 * Created by Zenbook on 31.01.2017.
 */
public class EmailSender {
    public void validateEmail (User user){
        String host="smtp.yandex.ru";
        String account="4sol@tut.by";//change accordingly
        String password="Gj7LoP46";//change accordingly
        String to=user.getUserLogin();//change accordingly

        //Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", "465");


        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(account,password);
                    }
                });

        //Compose the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(account));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("VLS e-mail confirmation");
            message.setText("Для завершения регистрации пройдите по ссылке:"+"\n"+"http://localhost:8080/vls/emailvalid.do?user="+
            user.getUserPassword());

            //send the message
            Transport.send(message);

            System.out.println("message sent successfully...");

        } catch (MessagingException e) {e.printStackTrace();}
    }
}


