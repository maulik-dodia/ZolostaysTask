package com.zolostaystask.forgotPwd;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.zolostaystask.database.DBHelper;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by techniche-android on 26/7/17.
 */

public class ForgotPwdModelImpl implements ForgotPwdModel {

    @Override
    public void doSendEmail(Context mContext, final String email, final OnSendingEmailCompleteListener listener) {

        if (isConnectedToInternet(mContext)) {
            DBHelper dbHelper = new DBHelper(mContext);
            if (dbHelper.checkForExistingUser(email)) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (sendEmail(email)) {
                                listener.onEmailSent(true);
                            } else {
                                listener.onEmailSent(false);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            } else {
                listener.onUserNotFound();
            }
        } else {
            listener.onInternetNoAvailable();
        }
    }

    public boolean isConnectedToInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null) {
            return false;
        }
        if (!networkInfo.isConnected()) {
            return false;
        }
        if (!networkInfo.isAvailable()) {
            return false;
        }
        return true;
    }

    private boolean sendEmail(String recipientEmail) {
        boolean emailSentStatus;
        final String fromEmail = "sociallogin007@gmail.com";
        final String password = "loginsocial007";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail));
            message.setSubject("New Password");
            message.setText("Dear user your new password is 1213!");

            /*MimeBodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            messageBodyPart = new MimeBodyPart();
            String file = "path of file to be attached";
            String fileName = "attachmentName"
            DataSource source = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);*/

            Transport.send(message);
            emailSentStatus = true;
        } catch (MessagingException e) {
            emailSentStatus = false;
            throw new RuntimeException(e);
        }
        return emailSentStatus;
    }
}