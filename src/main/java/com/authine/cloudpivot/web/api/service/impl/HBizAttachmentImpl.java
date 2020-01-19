package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.bean.HBizAttachment;
import com.authine.cloudpivot.web.api.mapper.HBizAttachmentMapper;
import com.authine.cloudpivot.web.api.service.IHBizAttachment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2020-01-19 00:11
 * @Description:
 */
@Service
public class HBizAttachmentImpl implements IHBizAttachment {

    @Resource
    HBizAttachmentMapper hBizAttachmentMapper;

    @Override
    public List<HBizAttachment> getAttachment(Map map) {
        return hBizAttachmentMapper.getAttachment(map);
    }

    @Override
    public HBizAttachment getAttachmentById(String id) {
        return hBizAttachmentMapper.getAttachmentById(id);
    }
}
