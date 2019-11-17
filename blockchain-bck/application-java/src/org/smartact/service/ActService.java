package org.smartact.service;

import org.papernet.CompletionAct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActService {

    CompletionAct issue(CompletionAct act);

    CompletionAct get(String uuid);

    List<CompletionAct> getActs();

    CompletionAct customerAgree(String uuid);

    CompletionAct customerRefuse(String uuid, String rejectReason);

    CompletionAct controlAgree(String uuid);

    CompletionAct controlRefuse(String uuid, String rejectReason);

    CompletionAct accountingAgree(String uuid);

    CompletionAct accountingRefuse(String uuid, String rejectReason);

    CompletionAct getLastAddedAct();
}
