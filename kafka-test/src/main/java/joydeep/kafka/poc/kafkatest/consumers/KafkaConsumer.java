package joydeep.kafka.poc.kafkatest.consumers;

import joydeep.kafka.poc.kafkatest.domains.Greetings;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "test-topic", groupId = "groupId")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group groupId: " + message);
    }

    @KafkaListener(
            topics = "test-topic",
            containerFactory = "greetingKafkaListenerContainerFactory",groupId = "customGroupId")
    public void greetingListener(Greetings greeting) {
        System.out.println("Recieved greeting in group customGroupId: "+greeting);
    }


}
