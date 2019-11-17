package org.smartact.controller;

import org.papernet.CompletionAct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartact.service.ActService;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("act")
public class ActController {

    private static final Logger log = LoggerFactory.getLogger(ActController.class);

    private final ActService actService;

    public ActController(ActService actService) {
        this.actService = actService;
    }

    @PostMapping
    public @ResponseBody
    CompletionActDto issue(@RequestBody CompletionActDto completionActDto) {
        log.info("processing {}", completionActDto);
        CompletionAct issued = actService.issue(completionActDto.toAct());
        return CompletionActDto.from(issued);
    }

    @GetMapping(path = "/last")
    public CompletionActDto getLastAct() {
        List<CompletionAct> acts = actService.getActs();
        if (acts.isEmpty()) {
            return null;
        }
        acts.sort(Comparator.comparing(CompletionAct::getDateTime).reversed());
        return CompletionActDto.from(acts.get(0));
    }

    @GetMapping(path = "/{uuid}")
    public CompletionActDto getAct(@PathVariable("uuid") String uuid) {
        CompletionAct act = actService.get(uuid);
        return CompletionActDto.from(act);
    }

    @GetMapping(path = "/all")
    public List<CompletionActDto> getActs() {
        List<CompletionAct> acts = actService.getActs();
        return acts.stream()
                .map(CompletionActDto::from)
                .collect(Collectors.toList());
    }
}
