package joydeep.kafka.poc.kafkatest.producers;

import joydeep.kafka.poc.kafkatest.domains.Greetings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaProducer {
    @Autowired
    @Qualifier("string")
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    @Qualifier("custom")
    private KafkaTemplate<String, Greetings> customKafkaTemplate;

    public void sendMessage(String topicName, String msg) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, msg);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + msg + "] with offset=[" + result.getRecordMetadata()
                                                                                      .offset()
                        + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + msg + "] due to : " + ex.getMessage());
            }
        });
    }


    public void sendMessage(String topicName,Greetings greetings){
        ListenableFuture<SendResult<String, Greetings>> future = customKafkaTemplate.send(topicName, greetings);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Greetings>>() {

            @Override
            public void onSuccess(SendResult<String, Greetings> result) {
                System.out.println("Sent message=[" + greetings + "] with offset=[" + result.getRecordMetadata()
                        .offset()
                        + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + greetings + "] due to : " + ex.getMessage());
            }
        });
    }
}
