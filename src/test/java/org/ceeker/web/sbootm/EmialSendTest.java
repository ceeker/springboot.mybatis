package org.ceeker.web.sbootm;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.velocity.VelocityEngineUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class EmialSendTest {

	@Autowired
	private JavaMailSender mailSender;

	@Ignore
	@Test
	public void sendSimpleMail() throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("409273291@qq.com");
		message.setTo("409273291@qq.com");
		message.setSubject("主题：简单邮件");
		message.setText("测试邮件内容");
		mailSender.send(message);
	}

	@Test
	public void sendAttachmentsMail() throws Exception {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("409273291@qq.com");
		helper.setTo("409273291@qq.com");
		helper.setSubject("主题：有附件");
		helper.setText("有附件的邮件");
		File file=new File(Thread.currentThread().getContextClassLoader().getResource("static/imgs/logo.png").getFile());

		FileSystemResource file1 = new FileSystemResource(file);
		helper.addAttachment("附件-1.jpg", file1);
		helper.addAttachment("附件-2.jpg", file1);

		mailSender.send(mimeMessage);

	}

	@Test@Ignore
	public void sendInlineMail() throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("409273291@qq.com");
		helper.setTo("409273291@qq.com");
		helper.setSubject("主题：嵌入静态资源");
		helper.setText("<html><body><img src=\"cid:weixin\" ></body></html>", true);
		FileSystemResource file = new FileSystemResource(new File("weixin.jpg"));
		helper.addInline("weixin", file);
		mailSender.send(mimeMessage);

	}

	@Test@Ignore
	public void sendTemplateMail() throws Exception {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("409273291@qq.com");
		helper.setTo("409273291@qq.com");
		helper.setSubject("主题：模板邮件");

		Map<String, Object> model = new HashMap<>();
		model.put("username", "hello");
		VelocityEngine velocityEngine = new VelocityEngine();
		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "template.vm", "UTF-8", model);
		helper.setText(text, true);
		mailSender.send(mimeMessage);
	}

}