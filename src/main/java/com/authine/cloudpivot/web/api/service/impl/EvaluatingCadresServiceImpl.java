package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.bean.EvaluatingCadres;
import com.authine.cloudpivot.web.api.bean.EvaluatingCadresList;
import com.authine.cloudpivot.web.api.bean.SEvaluatingCadresList;
import com.authine.cloudpivot.web.api.mapper.EvaluatingCadresMapper;
import com.authine.cloudpivot.web.api.service.EvaluatingCadresService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author:lfh
 * @Date: 2020/1/7 11:22
 * @Description：新选拔干部民主评议表service层
 */
@Service
public class EvaluatingCadresServiceImpl implements EvaluatingCadresService {

    @Resource
    private EvaluatingCadresMapper evaluatingCadresMapper;
    /**
     * 根据id获取发起新选拔干部民主评议表的全部信息
     * @param id
     * @return
     */
    @Override
    public EvaluatingCadres getEvaluatingCadresInfo(String id) {
        return evaluatingCadresMapper.getEvaluatingCadresInfo(id);
    }

    /**
     * 更新发起新选拔干部民主评议表主表结果
     * @param info
     */
    @Override
    public void updateEvaluatingCadresInfo(Map<String, Object> info) {
        evaluatingCadresMapper.updateEvaluatingCadresInfo(info);
    }

    /**
     * 根据unit获取从0到最大投票人数的新选拔干部民主评议表的id
     * @param map
     * @return
     */
    @Override
    public List<String> getEvaluatingCadresIdByUnit(Map map) {
        return evaluatingCadresMapper.getEvaluatingCadresIdByUnit(map);
    }

    /**
     *获取全部的发起新选拔干部民主评议表的 评测干部表信息
     * @param id
     * @return
     */
    @Override
    public List<SEvaluatingCadresList> getAllSEvaluatingCadresListData(String id) {
        return evaluatingCadresMapper.getAllSEvaluatingCadresListData(id);
    }

    /**
     * 获取全部的新选拔干部民主评议表的 评测干部表信息
     * @param id
     * @return
     */
    @Override
    public List<EvaluatingCadresList> getAllEvaluatingCadresListData(String id) {
        return evaluatingCadresMapper.getAllEvaluatingCadresListData(id);
    }

    /**
     * 更新 发起新选拔干部民主评议表的评测干部表结果
     * @param sec
     */
    @Override
    public void updateAllEvaluatingCadres(List<SEvaluatingCadresList> sec) {
        evaluatingCadresMapper.updateAllEvaluatingCadres(sec);
    }
}
