package spring.email.test;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.email.config.RootConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class TestEmail {
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	@Test
	public void test_send_txt(){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("****@163.com");
		message.setTo("****@126.com");
		message.setSubject("主题");
		message.setText("正文内容");
		//发送
		mailSender.send(message);
	}
	
	/**
	 * 发送附件内容
	 * @throws MessagingException 
	 */
	@Test
	public void test_send_attachment() throws MessagingException{
		//为了发送附件，需要创建MIME类型的消息
		MimeMessage message = mailSender.createMimeMessage();
		//第二个参数true，表示他是一个Multipart类型email
		MimeMessageHelper helper = new MimeMessageHelper(message , true);
		
		helper.setFrom("****@163.com");
		helper.setTo("****@126.com");
		helper.setSubject("主题");
		helper.setText("正文内容");
		ClassPathResource c = new ClassPathResource("/spring-cloud.pdf");
		helper.addAttachment("微服务架构.pdf", c);
		
		mailSender.send(message);
	}
	
	/**
	 * 发送富文本内容
	 * @throws MessagingException 
	 */
	@Test
	public void test_send_rich_text() throws MessagingException{
		MimeMessage message = mailSender.createMimeMessage();
		//最后一个参数设置html页面编码
		MimeMessageHelper helper = new MimeMessageHelper(message , true ,"utf-8");
		helper.setFrom("****@163.com");
		helper.setTo("****@126.com");
		helper.setSubject("主题");
		
		//第二个参数表明第一个参数传入的是HTML
		helper.setText("<html><body><img src='cid:logo' />"
				+ "<h4>主题名称</h4> <h1>哇哈哈</h1></body></html>" , true );
		
		ClassPathResource img = new ClassPathResource("/hashiqi.jpg");
		//名称与图片对应
		helper.addInline("logo", img);
		
		mailSender.send(message);
	}
}
