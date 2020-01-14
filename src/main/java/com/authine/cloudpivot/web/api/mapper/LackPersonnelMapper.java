package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.bean.FinalTotalResult;
import com.authine.cloudpivot.web.api.bean.LackPersonnelAssessInfo;
import com.authine.cloudpivot.web.api.bean.LackPersonnelInfo;
import com.authine.cloudpivot.web.api.bean.LackPersonnelapplyinfo;

import java.util.List;

public interface LackPersonnelMapper {
    List<LackPersonnelInfo> findLackPersonnelList(LackPersonnelAssessInfo lackPersonnelAssessInfo);

    void savescore(LackPersonnelapplyinfo lackPersonnelapplyInfo);

    void resetmaindutyscore(String applyId);

    void countscore(LackPersonnelapplyinfo lackPersonnelapplyInfo);

    FinalTotalResult countfinalscore(LackPersonnelapplyinfo lackPersonnelapplyInfo);

    void updateFinalscore(Double result, String applyId);
}
