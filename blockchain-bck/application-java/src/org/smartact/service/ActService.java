package org.smartact.service;

import org.papernet.CompletionAct;
import org.springframework.stereotype.Service;

@Service
public interface ActService {
    CompletionAct issue(CompletionAct act);
}
