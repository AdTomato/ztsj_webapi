package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.bean.HBizAttachment;

import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2020-01-19 00:10
 * @Description: 系统附件接口
 */
public interface IHBizAttachment {

    public List<HBizAttachment> getAttachment(Map map);

    public HBizAttachment getAttachmentById(String id);

}
