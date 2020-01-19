package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.bean.HBizAttachment;

import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2020-01-19 00:09
 * @Description: 系统附件mapper
 */
public interface HBizAttachmentMapper {

    public List<HBizAttachment> getAttachment(Map map);

    public HBizAttachment getAttachmentById(String id);

}
