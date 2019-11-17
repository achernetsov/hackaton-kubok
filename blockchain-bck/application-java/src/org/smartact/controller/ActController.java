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

    @PutMapping(path = "/{uuid}/customerAgree")
    public CompletionActDto customerAgree(@PathVariable("uuid") String uuid) {
        CompletionAct act = actService.customerAgree(uuid);
        return CompletionActDto.from(act);
    }

    @PutMapping(path = "/{uuid}/customerRefuse")
    public CompletionActDto customerRefuse(@PathVariable("uuid") String uuid, @RequestBody RefuseDto refuseDto) {
        CompletionAct act = actService.customerRefuse(uuid, refuseDto.getReason());
        return CompletionActDto.from(act);
    }

    @PutMapping(path = "/{uuid}/controlAgree")
    public CompletionActDto controlAgree(@PathVariable("uuid") String uuid) {
        CompletionAct act = actService.controlAgree(uuid);
        return CompletionActDto.from(act);
    }

    @PutMapping(path = "/{uuid}/controlRefuse")
    public CompletionActDto controlRefuse(@PathVariable("uuid") String uuid, @RequestBody RefuseDto refuseDto) {
        CompletionAct act = actService.controlRefuse(uuid, refuseDto.getReason());
        return CompletionActDto.from(act);
    }

    @PutMapping(path = "/{uuid}/accountingAgree")
    public CompletionActDto accountingAgree(@PathVariable("uuid") String uuid) {
        CompletionAct act = actService.accountingAgree(uuid);
        return CompletionActDto.from(act);
    }

    @PutMapping(path = "/{uuid}/accountingRefuse")
    public CompletionActDto accountingRefuse(@PathVariable("uuid") String uuid, @RequestBody RefuseDto refuseDto) {
        CompletionAct act = actService.accountingRefuse(uuid, refuseDto.getReason());
        return CompletionActDto.from(act);
    }
}
