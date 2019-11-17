package org.smartact.controller;

import org.papernet.CompletionAct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartact.service.ActService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("act")
public class ActController {
    private static final Logger log = LoggerFactory.getLogger(ActController.class);

    private final ActService actService;

    public ActController(ActService actService) {
        this.actService = actService;
    }

    @PostMapping
    public @ResponseBody CompletionActDto issue(@RequestBody CompletionActDto completionActDto) {
        log.info("processing {}", completionActDto);

        // TODO remove (mock)
        completionActDto.setUuid(UUID.randomUUID());
        completionActDto.setDateTime(LocalDateTime.now().toString());
        completionActDto.setState(CompletionAct.ISSUED);

        return completionActDto;

        // TODO
//        CompletionAct issued = actService.issue(completionActDto.toAct());
//        return CompletionActDto.from(issued);
    }

    @GetMapping
    public void getAct() {

    }
}
