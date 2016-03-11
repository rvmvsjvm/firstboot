package demo.scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TickTok {
	
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 1000*10)
    public void reportCurrentTime() {
   		System.out.println("Testing scheduler from heroku free service"+dateFormat.format(new Date()));
		
    }

}
