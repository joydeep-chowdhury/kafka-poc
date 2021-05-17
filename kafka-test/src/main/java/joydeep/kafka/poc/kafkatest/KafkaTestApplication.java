package joydeep.kafka.poc.kafkatest;

import joydeep.kafka.poc.kafkatest.domains.Greetings;
import joydeep.kafka.poc.kafkatest.producers.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class KafkaTestApplication {
    @Autowired
	private KafkaProducer kafkaProducer;

	public static void main(String[] args) {
		SpringApplication.run(KafkaTestApplication.class, args);
	}

	@PostConstruct
	public void postInit(){
		kafkaProducer.sendMessage("test-topic","hello");
		kafkaProducer.sendMessage("test-topic",new Greetings("Hello how are you","Welcome Message"));
	}

}
