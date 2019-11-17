package org.smartact.service;

import org.papernet.CompletionAct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActService {
    CompletionAct issue(CompletionAct act);

    CompletionAct get(String uuid);

    List<CompletionAct> getActs();
}
