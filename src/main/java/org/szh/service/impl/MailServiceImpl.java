package org.szh.service.impl;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.szh.beans.MailMsg;
import org.szh.beans.Result;
import org.szh.repository.MailRepository;
import org.szh.service.MailService;

@Service("mailService")
public class MailServiceImpl implements MailService {

	@Resource
	private MailRepository mailRepository;

	@Resource
	private MongoTemplate mongoTemplate;

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String from;

	private static final String BASE_PATH = File.separator + "usr" + File.separator + "test" + File.separator;

	/**
	 * @param base
	 * @return
	 * @description:简单邮件
	 */
	@Async
	@Override
	public Result<?> sendSimpleMail(MailMsg base) {
		// 简单邮件信息
		SimpleMailMessage message = new SimpleMailMessage();
		// 邮件发送者
		message.setFrom(from);
		// 邮件接收者
		message.setTo(base.getTo().split(";"));
		// 邮件主题
		message.setSubject(base.getSubject());
		// 邮件的主体
		message.setText(base.getContent());
		javaMailSender.send(message);
		base.setSendDt(new Date());
		mailRepository.save(base);
		return new Result<>(base);
	}

	@Override
	public Result<?> historyRecord(String to) {
		List<MailMsg> list = mailRepository.findByTo(to);
		return new Result<>(list);
	}

	/**
	 * @param base
	 * @return
	 * @description:html邮件
	 */
	@Async
	@Override
	public Result<?> sendHtmlMail(MailMsg base) {
		Calendar calendar = Calendar.getInstance();
		Date time = calendar.getTime();
		MimeMessage mimeMessage = null;
		try {
			mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMeassgeHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMeassgeHelper.setFrom(from);
			mimeMeassgeHelper.setTo(base.getTo());
			mimeMeassgeHelper.setSubject(base.getSubject());
//			 StringBuilder text = new StringBuilder();
//			 text.append("<h1>HTML格式邮件</h1>")
//			 .append("<p style='color:#F00'>very goods！</p>")
//			 .append("<p style='text-align:right'>jehon</p>");
//			content= text.toString()
			mimeMeassgeHelper.setText(base.getContent(), true);
			mimeMeassgeHelper.setSentDate(time);
			javaMailSender.send(mimeMessage);
			base.setSendDt(time);
			mailRepository.save(base);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return new Result<>(base);
	}

	/**
	 * @param base
	 * @return
	 * @description：附件邮件
	 */
	@Async
	@Override
	public Result<?> sendAttachmentMail(MailMsg base) {
		Calendar calendar = Calendar.getInstance();
		Date time = calendar.getTime();
		MimeMessage mimeMessage = null;
		try {
			mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMeassgeHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMeassgeHelper.setFrom(from);
			mimeMeassgeHelper.setTo(base.getTo());
			mimeMeassgeHelper.setSubject(base.getSubject());
			mimeMeassgeHelper.setText(base.getContent());
			mimeMeassgeHelper.setSentDate(time);
			String path = BASE_PATH + "mail.txt";
			FileSystemResource file = new FileSystemResource(new File(path));
			String fileName = path.substring(path.lastIndexOf(File.separator) + 1);
			mimeMeassgeHelper.addAttachment(fileName, file);
			javaMailSender.send(mimeMessage);
			base.setSendDt(time);
			mailRepository.save(base);
		} catch (Exception e) {
			System.out.println("发送文件失败：" + e.getMessage());
		}
		return new Result<>(base);
	}

	/**
	 * @param base
	 * @return
	 * @description:静态资源邮件
	 */
	@Async
	@Override
	public Result<?> sendInlineResourceMail(MailMsg base) {
		Calendar calendar = Calendar.getInstance();
		Date time = calendar.getTime();
		String Id = "130263432989384704";
//		content = "<html><body>图片邮件：<img src=\'cid:" + Id + "\' ></body></html>";
		String path = BASE_PATH + "mail.jpg";
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMeassgeHelper = new MimeMessageHelper(message, true);
			mimeMeassgeHelper.setFrom(from);
			mimeMeassgeHelper.setTo(base.getTo());
			mimeMeassgeHelper.setSubject(base.getSubject());
			mimeMeassgeHelper.setText(base.getContent(), true);
			mimeMeassgeHelper.setSentDate(time);
			FileSystemResource res = new FileSystemResource(new File(path));
			mimeMeassgeHelper.addInline(Id, res);
			javaMailSender.send(message);
			base.setSendDt(time);
			mailRepository.save(base);
		} catch (MessagingException e) {
		}
		return new Result<>(base);
	}
}
