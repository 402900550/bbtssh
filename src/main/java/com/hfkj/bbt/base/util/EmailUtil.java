package com.hfkj.bbt.base.util;


import com.hfkj.bbt.base.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * 邮件发送工具类
 *
 * @since
 * @version 1.0
 * @author qhliao
 * @date 2017-7-28
 */
public class EmailUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);
    private MimeMessage mimeMsg;
    private Session session;
    private Properties props;
    private String username;
    private String password;
    private Multipart mp;

    public EmailUtil(String smtp) {
        setSmtpHost(smtp);
        createMimeMessage();
    }

    /**
     * 设置smtp地址
     * 
     * @param hostName
     *            smtp地址
     */
    private void setSmtpHost(String hostName) {
        if (props == null) {
            props = System.getProperties();
        }
        props.put("mail.smtp.host", hostName);
    }

    /**
     * 创建mime回话
     * 
     * @return true成功，false失败
     */
    private boolean createMimeMessage() {
        try {
            session = Session.getDefaultInstance(props, null);
        } catch (Exception e) {
            LOGGER.info("获取邮件会话错误！" + e);
            return false;
        }
        try {
            mimeMsg = new MimeMessage(session);
            mp = new MimeMultipart();
            return true;
        } catch (Exception e) {
            LOGGER.info("创建MIME邮件对象失败！" + e);
            return false;
        }
    }

    /**
     * 定义SMTP是否需要验证
     * 
     * @param need
     *            boolean
     */
    private void setNeedAuth(boolean need) {
        if (props == null) {
            props = System.getProperties();
        }
        if (need) {
            props.put("mail.smtp.auth", "true");
        } else {
            props.put("mail.smtp.auth", "false");
        }
    }

    /**
     * 设置发送方的账号密码
     * 
     * @param name
     *            账号
     * @param pass
     *            密码
     */
    private void setNamePass(String name, String pass) {
        this.username = name;
        this.password = pass;
    }

    /**
     * 定义邮件主题
     * 
     * @param mailSubject
     *            主题
     * @return boolean true：成功 false:失败
     */
    private boolean setSubject(String mailSubject) {
        try {
            mimeMsg.setSubject(mailSubject);
            return true;
        } catch (Exception e) {
            LOGGER.info("定义邮件主题发生错误！" + e);
            return false;
        }
    }

    /**
     * 定义邮件正文
     * 
     * @param mailBody
     *            正文
     * @return boolean true：成功 false:失败
     */
    private boolean setBody(String mailBody) {
        try {
            BodyPart bp = new MimeBodyPart();
            bp.setContent("" + mailBody, "text/html;charset=GBK");
            mp.addBodyPart(bp);
            return true;
        } catch (Exception e) {
            LOGGER.info("定义邮件正文时发生错误！" + e);
            return false;
        }
    }

    /**
     * 设置发信人
     * 
     * @param from
     *            发送人
     * @return boolean true：成功 false:失败
     */
    private boolean setFrom(String from) {
        try {
            // 发信人
            mimeMsg.setFrom(new InternetAddress(from));
            return true;
        } catch (Exception e) {
            LOGGER.info("设置发信人错误！" + e);
            return false;
        }
    }

    /**
     * 定义收信人
     * 
     * @param to
     *            收信人
     * @return boolean true：成功 false:失败
     */
    private boolean setTo(String to) {
        if (to == null) {
            return false;
        }
        try {
            mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            return true;
        } catch (Exception e) {
            LOGGER.info("定义收信人错误！" + e);
            return false;
        }
    }

    /**
     * 定义抄送人
     * 
     * @param copyto
     *            收信人
     * @return boolean true：成功 false:失败
     */
    private boolean setCopyTo(String copyto) {
        if (null == copyto) {
            return true;
        }
        try {
            mimeMsg.setRecipients(Message.RecipientType.CC, (Address[]) InternetAddress.parse(copyto));
            return true;
        } catch (Exception e) {
            LOGGER.info("定义抄送人错误！" + e);
            return false;
        }
    }

    /**
     * 发送邮件模块
     * 
     * @return boolean true：成功 false:失败
     */
    private boolean sendOut() {
        try {
            mimeMsg.setContent(mp);
            mimeMsg.saveChanges();
            LOGGER.info("邮件发送中，请稍后...");
            Session mailSession = Session.getInstance(props, null);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect((String) props.get("mail.smtp.host"), username, password);
            transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
            LOGGER.info("邮件发送成功！");
            transport.close();
            return true;
        } catch (Exception e) {
            LOGGER.info("邮件失败！" + e);
            return false;
        }
    }

    /**
     * 发送邮件公用方法
     *
     *            密码
     * @return boolean true：成功 false:失败
     */
    public static boolean sendEmail(EmailParam emailParam) {
        EmailUtil theMail = new EmailUtil(emailParam.getSmtp());
        // 验证
        theMail.setNeedAuth(true);
        if (!theMail.setSubject(emailParam.getSubject())) {
            return false;
        }
        if (!theMail.setBody(emailParam.getContent())) {
            return false;
        }
        if (!theMail.setTo(emailParam.getTo())) {
            return false;
        }
        if (!theMail.setFrom(emailParam.getFrom())) {
            return false;
        }
        if (!theMail.setCopyTo(emailParam.getCopyto())) {
            return false;
        }
        theMail.setNamePass(emailParam.getUsername(), emailParam.getPassword());
        if (!theMail.sendOut()) {
            return false;
        }
        return true;
    }




//    /**
//     * 发送邮件 给最高权限的管理员发送
//     * @param users
//     */
//    public static void sendRepairEmail(List<User> users, Equipment Equipment, String schoolName){
//
//        for (User user:users){
//            String email=user.getEmail();
//            if (ComUtil.stringIsNotNull(email)){
//                sendEmail(email,Equipment,schoolName);
//            }
//        }
//
//    }
//
//    private static void sendEmail(String email,Equipment Equipment,String schoolName) {
//        MyProperties myProperties=new MyProperties();
//        try {
//            Properties properties = myProperties.getProperties();
//            EmailParam emailParam = new EmailParam();
//            emailParam.setFrom(properties.getProperty("mail.username"));
//            emailParam.setUsername(properties.getProperty("mail.username"));
//            emailParam.setPassword(properties.getProperty("mail.password"));
//            emailParam.setSmtp(properties.getProperty("mail.smtp"));
//            emailParam.setTo(email);
//            emailParam.setSubject("物联网智慧教室应用管理系统--设备异常");
//            emailParam.setContent("你好管理员,学校:"+schoolName+"\t设备号:"+Equipment.getEquipmentNo()+"出现异常,需要维修!");
//            EmailUtil.sendEmail(emailParam);
//        } catch (IOException e) {
//            LOGGER.error("获取配置文件失败!");
//        }
//
//    }









}