package org.smartact.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("act")
public class ActController {
    private static final Logger log = LoggerFactory.getLogger(ActController.class);

    @PostMapping
    public CompletionActDto issue(CompletionActDto completionAct) {
        log.info("processing {}", completionAct);

        return completionAct;
    }

    @GetMapping
    public void getAct() {

    }
}
