package org.smartact.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActController {
    private static final Logger log = LoggerFactory.getLogger(ActController.class);

    @PostMapping
    public void issue(CompletionActDto completionAct) {
        log.debug("processing {}", completionAct);


    }

    @GetMapping
    public void getAct() {

    }
}
