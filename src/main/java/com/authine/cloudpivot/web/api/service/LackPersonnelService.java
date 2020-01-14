package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.bean.*;

import java.util.List;

public interface LackPersonnelService {
    List<LackPersonnelInfo> findLackPersonnelList(LackPersonnelAssessInfo lackPersonnelAssessInfo);

    void savescore(LackPersonnelapplyinfo lackPersonnelapplyInfo);

    void resetmaindutyscore(String applyId);

    void countscore(LackPersonnelapplyinfo lackPersonnelapplyInfo);

    FinalTotalResult  countfinalscore(LackPersonnelapplyinfo lackPersonnelapplyInfo);

    void updateFinalscore(Double result,String applyId);
}
