package me.pada.blog.oauth;

import me.pada.core.anotation.EnablePadaStarter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnablePadaStarter
@SpringBootApplication
@EnableAuthorizationServer
public class PadaBlogOAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(PadaBlogOAuthApplication.class, args);
	}

	@EnableWebSecurity
	static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable();
		}
	}

	@Bean
	public JavaMailSender javaMailSender(){
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

		javaMailSender.setProtocol("SMTP");
		javaMailSender.setHost("127.0.0.1");
		javaMailSender.setPort(25);

		return javaMailSender;
	}
}