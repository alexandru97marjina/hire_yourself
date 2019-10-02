package com.marjina.hire_yourself.common.helper.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EMailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String email,String password){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("Password recovery on hire_proj");
        String msgBuilder = String.format(
                "Password recovery on hire_proj for user - %s\nYour password is - %s", email, password);
        msg.setText(msgBuilder);

        javaMailSender.send(msg);
    }

}
