package demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;

import demo.domain.Player;
import demo.domain.Team;
import demo.repository.TeamRepository;

@SpringBootApplication
@EnableScheduling
public class Application {

	@Autowired
	TeamRepository teamRepository;

	@Autowired
	JdbcTemplate jdbctemplate;

	@Autowired
	private JavaMailSender javaMailSender;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	public void init() {
		List<Team> list = new ArrayList<>();

		Set<Player> set = new HashSet<>();
		set.add(new Player("Ram1", "Ram1"));
		set.add(new Player("Buckets", "Guard"));
		set.add(new Player("Dizzy", "Guard"));

		list.add(new Team("Ram", "Ram", set));
		list.add(new Team("india", "icci", null));

		teamRepository.save(list);

		jdbctemplate.execute();

		MimeMessage mail = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			helper.setTo("jaleel.akbar@gmail.com");
			helper.setReplyTo("contact@haipick.com");
			helper.setFrom("contact@haipick.com");
			helper.setSubject("Lorem ipsum");
			helper.setText("Lorem ipsum dolor sit amet [...]");
		} catch (MessagingException e) {
			e.printStackTrace();
		} finally {
		}
		javaMailSender.send(mail);
		// return helper;
	}

}