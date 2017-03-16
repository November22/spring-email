package spring.email.config;

import java.util.Properties;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

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
	
//	@Bean
//	public VelocityEngineFactoryBean velocityEngine(){
//		VelocityEngineFactoryBean velocityEngine = new VelocityEngineFactoryBean();
//		Properties props = new Properties();
//		
//		//设置从类路径下加载Velocity模板
//		props.setProperty("resource.loader", "class");
//		props.setProperty("class.resource.loader.class", ClassPathResource.class.getName());
//		velocityEngine.setVelocityProperties(props);
//		
//		return velocityEngine;
//	}
	
	@Bean
	public ClassLoaderTemplateResolver emailTemplateResolver(){
		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
		resolver.setPrefix("mail/");
		resolver.setTemplateMode("HTML5");
		resolver.setCharacterEncoding("UTF-8");
		resolver.setOrder(1);
		return resolver;
	}
	
	@Bean
	public SpringTemplateEngine springTemplateEngine(Set<ITemplateResolver> resolvers){
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolvers(resolvers);
		return engine;
	}
}
