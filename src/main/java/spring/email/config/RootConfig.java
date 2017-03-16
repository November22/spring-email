package spring.email.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class RootConfig {
	
	@Bean
	public MailSender getMailSender(){
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setUsername("****");
		sender.setPassword("****");
		sender.setHost("smtp.163.com");
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		
		return sender;
	}
}
