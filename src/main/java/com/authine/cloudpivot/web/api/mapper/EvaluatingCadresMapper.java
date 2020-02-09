package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.bean.EvaluatingCadres;
import com.authine.cloudpivot.web.api.bean.EvaluatingCadresList;
import com.authine.cloudpivot.web.api.bean.SEvaluatingCadresList;

import java.util.List;
import java.util.Map;

/**
 * @Author:lfh
 * @Date: 2020/1/7 11:24
 * @Description：
 */

public interface EvaluatingCadresMapper {

    /**
     * 根据id获取发起新选拔干部民主评议表的全部信息
     *
     * @param id
     * @return
     */
    EvaluatingCadres getEvaluatingCadresInfo(String id);

    /**
     * 根据unit获取从0到最大投票人数的新选拔干部民主评议表的id
     *
     * @param map
     * @return
     */
    List<String> getEvaluatingCadresIdByUnit(Map map);

    /**
     * 更新发起新选拔干部民主评议表主表结果
     *
     * @param info
     */
    void updateEvaluatingCadresInfo(Map<String, Object> info);

    /**
     * 获取全部的发起新选拔干部民主评议表的 评测干部表信息
     *
     * @param id
     * @return
     */
    List<SEvaluatingCadresList> getAllSEvaluatingCadresListData(String id);

    /**
     * 获取全部的新选拔干部民主评议表的 评测干部表信息
     *
     * @param id
     * @return
     */
    List<EvaluatingCadresList> getAllEvaluatingCadresListData(String id);

    /**
     * 更新 发起新选拔干部民主评议表的评测干部表结果
     *
     * @param sec
     */

    void updateAllEvaluatingCadres(List<SEvaluatingCadresList> sec);


}
