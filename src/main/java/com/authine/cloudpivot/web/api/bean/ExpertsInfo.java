package com.authine.cloudpivot.web.api.bean;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * @Author:lfh
 * @Date: 2020/1/13 23:27
 * @Description：专家信息 根据此信息查询所以符合条件的专家
 */
@Data
public class ExpertsInfo {

    /**
     * 年度
     */
    @JsonAlias("annual")
    private String annual;


    /**
     * 申报级别
     */
    @JsonAlias("expertsDeclareRank")
    private String expertsDeclareRank;


    /**
     * 申报系别
     */
    @JsonAlias("declareDept")
    private String declareDept;

}
