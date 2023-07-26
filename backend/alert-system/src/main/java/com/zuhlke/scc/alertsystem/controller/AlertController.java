package com.zuhlke.scc.alertsystem.controller;

import com.zuhlke.scc.alertsystem.dto.CustomMessageRequest;
import com.zuhlke.scc.alertsystem.dto.MessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AlertController {

    @Value("${topic.name}")
    private String topic;

    @Value("${topic-custom.name}")
    private String topicCustom;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, Object> customKafkaTemplate;

    @PostMapping("/send-message")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendMessage(@RequestBody MessageRequest messageRequest) {
        log.info("Send message to topic " + topic);


        kafkaTemplate.send(topic, messageRequest.getMessage());
    }

    @PostMapping("/send-custom-message")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendCustomMessage(@RequestBody CustomMessageRequest messageRequest) {
        customKafkaTemplate.send(topicCustom, messageRequest);
    }
}
