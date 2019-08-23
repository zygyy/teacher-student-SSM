package com.zy.util;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * @author 周宇
 * @university ycit.com
 * @creat 2019/7/18 15:04
 */
public class Mail {
    public static String myEmailAccount = "zy1297799065@163.com";
    public static String myEmailPassword = "1297799065zhouyu";//163邮箱的账号与密码

    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
    // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
    public static String myEmailSMTPHost = "smtp.163.com";//注意修改为163邮箱


    /**
     * 创建一封只包含文本的简单邮件
     * @param session     和服务器交互的会话
     * @param sendMail    发件人邮箱
     * @param receiveMail 收件人邮箱
     *
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,int randomArise) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "zy1297799065@163.com", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, receiveMail, "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject("修改密码——发送邮件", "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent("验证码为："+randomArise, "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();
        return message;
    }

    /**
     *
     * @param receiveMailAccount  获取的邮箱
     * @param randomArise         生成的随机数
     * @throws Exception
     */
    public void sendMail(String receiveMailAccount,int randomArise)throws Exception{
        Properties properties=new Properties();//参数配置
        properties.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        properties.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        properties.setProperty("mail.smtp.auth", "true");            // 需要请求认证

        final String smtpPort = "465";
        properties.setProperty("mail.smtp.port", smtpPort);
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.socketFactory.port", smtpPort);

        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(properties);
        session.setDebug(true);  // 设置为debug模式, 可以查看详细的发送log日志
        // 3. 创建一封邮件
        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount,randomArise);

        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();
        transport.connect(myEmailAccount, myEmailPassword);

        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());

        // 7. 关闭连接
        transport.close();
    }
}

