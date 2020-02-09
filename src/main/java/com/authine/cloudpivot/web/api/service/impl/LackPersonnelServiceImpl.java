package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.bean.FinalTotalResult;
import com.authine.cloudpivot.web.api.bean.LackPersonnelAssessInfo;
import com.authine.cloudpivot.web.api.bean.LackPersonnelInfo;
import com.authine.cloudpivot.web.api.bean.LackPersonnelapplyinfo;
import com.authine.cloudpivot.web.api.mapper.LackPersonnelMapper;
import com.authine.cloudpivot.web.api.service.LackPersonnelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LackPersonnelServiceImpl implements LackPersonnelService {
    @Resource
    private LackPersonnelMapper lackPersonnelMapper;

    @Override
    public List<LackPersonnelInfo> findLackPersonnelList(LackPersonnelAssessInfo lackPersonnelAssessInfo) {
        return lackPersonnelMapper.findLackPersonnelList(lackPersonnelAssessInfo);
    }

    @Override
    public void savescore(LackPersonnelapplyinfo lackPersonnelapplyInfo) {
        lackPersonnelMapper.savescore(lackPersonnelapplyInfo);
    }

    @Override
    public void resetmaindutyscore(String applyId) {
        lackPersonnelMapper.resetmaindutyscore(applyId);
    }

    @Override
    public void countscore(LackPersonnelapplyinfo lackPersonnelapplyInfo) {
        lackPersonnelMapper.countscore(lackPersonnelapplyInfo);
    }

    @Override
    public FinalTotalResult countfinalscore(LackPersonnelapplyinfo lackPersonnelapplyInfo) {
        return lackPersonnelMapper.countfinalscore(lackPersonnelapplyInfo);
    }

    @Override
    public void updateFinalscore(Double result, String applyId) {
        lackPersonnelMapper.updateFinalscore(result, applyId);
    }
}
