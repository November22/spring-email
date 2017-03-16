package spring.email.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.email.config.RootConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class TestEmail {
	@Autowired
	private MailSender mailSender;
	
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
}
