package moine.config.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaProcessor {
    // 발행, 구독 토픽 이름 설정
    String INPUT = "event-in"; // 구독하는 토픽
//    String OUTPUT = "event-out";
    String OUTPUT1 = "signedUpTopic"; // 발행할 토픽

    @Input(INPUT)
    SubscribableChannel inboundTopic();

    @Output(OUTPUT1)
    MessageChannel outboundTopic();

}
