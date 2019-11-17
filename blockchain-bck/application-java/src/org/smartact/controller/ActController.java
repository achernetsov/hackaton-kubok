package org.smartact.controller;

import org.papernet.CompletionAct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("act")
public class ActController {
    private static final Logger log = LoggerFactory.getLogger(ActController.class);

    @PostMapping
    public @ResponseBody CompletionActDto issue(@RequestBody CompletionActDto completionAct) {
        log.debug("processing {}", completionAct);

        completionAct.setUuid(UUID.randomUUID());
        completionAct.setDateTime(LocalDateTime.now().toString());
        completionAct.setState(CompletionAct.ISSUED);

        return completionAct;
    }

    @GetMapping
    public void getAct() {

    }
}
