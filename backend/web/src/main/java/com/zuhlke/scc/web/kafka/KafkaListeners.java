package com.zuhlke.scc.web.kafka;

import com.zuhlke.scc.web.dto.CustomMessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaListeners {

    private static final String TEST_TOPIC = "scc";
    private static final String TEST_TOPIC_CUSTOM = "scc-custom";

    private static final String TEST_GROUP = "test-group";
    private static final String TEST_GROUP_NEW = "test-group-new";

    private static final String SIMPLE_CONTAINER_FACTORY = "simpleKafkaListenerContainerFactory";
    private static final String CUSTOM_CONTAINER_FACTORY = "customKafkaListenerContainerFactory";

    @KafkaListener(topics = TEST_TOPIC, groupId = TEST_GROUP, containerFactory = SIMPLE_CONTAINER_FACTORY)
    public void listener1(String message) {
        log.info("Message: '" + message + "' from listener1");
    }

    @KafkaListener(topics = TEST_TOPIC, groupId = TEST_GROUP, containerFactory = SIMPLE_CONTAINER_FACTORY)
    public void listener2(String message) {
        log.info("Message: '" + message + "' from listener2");
    }

    @KafkaListener(topics = TEST_TOPIC, groupId = TEST_GROUP_NEW, containerFactory = SIMPLE_CONTAINER_FACTORY)
    public void listener3(String message) {
        log.info("Message: '" + message + "' from listener3");
    }

    @KafkaListener(topics = TEST_TOPIC_CUSTOM, groupId = TEST_GROUP, containerFactory = CUSTOM_CONTAINER_FACTORY)
    public void customListener(CustomMessageRequest message, Acknowledgment acknowledgment) {
        log.info("Message: '" + message.getPayload() + "' from customListener");
        acknowledgment.acknowledge();
    }

}
