package com.authine.cloudpivot.web.api.utils;

import com.authine.cloudpivot.web.api.bean.HBizAttachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-01-19 00:02
 * @Description: 发送邮件工具类
 */
public class SendEmailUtils {

    @Autowired
    private static JavaMailSenderImpl javaMailSender;

    /**
     * @param subject        : 邮件标题
     * @param text           : 邮件内容(支持html)
     * @param to             : 发件人
     * @param from           : 收件人
     * @param attachmentList : 附件对象
     * @return : boolean true 发送成功 false 发送失败
     * @Author: wangyong
     * @Date: 2020/1/19 0:48
     * @Description: 发送邮件
     */
    public static boolean sendEmail(String subject, String text, String to, String from, List<HBizAttachment> attachmentList) {

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            // 设置标题
            helper.setSubject(subject);

            // 设置邮件内容
            helper.setText(text, true);

            // 设置发件人
            helper.setTo(to);

            // 设置收件人
            helper.setFrom(from);

            // 设置附件

            if (null != attachmentList && attachmentList.size() != 0) {
                for (HBizAttachment hBizAttachment : attachmentList) {
                    helper.addAttachment(hBizAttachment.getName(), new File("/sftp/sftpuser/upload/" + hBizAttachment.getRefId() + hBizAttachment.getName()));
                }
            }
            javaMailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
