package demo.scheduled;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	@Autowired
	private JavaMailSender javaMailSender;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 60000*10)
    public void reportCurrentTime() {
    	MimeMessage mail = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			helper.setTo("jaleel.akbar@gmail.com");
			helper.setCc("venkat.sagiraju01@gmail.com");
			helper.setReplyTo("contact@haipick.com");
			helper.setFrom("contact@haipick.com");
			helper.setSubject("Testing scheduler from heroku free service"+dateFormat.format(new Date()));
			helper.setText("Testing scheduler from heroku free service"+dateFormat.format(new Date()));
		} catch (MessagingException e) {
			e.printStackTrace();
		} finally {
		}
		javaMailSender.send(mail);
    }
}