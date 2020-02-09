package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.bean.LeadPerson;


import java.util.List;

/**
 * <p>
 * 领导人员 Mapper 接口
 * </p>
 *
 * @author zsh
 * @since 2019-11-26
 */

public interface LeadpersonMapper {

    List<LeadPerson> getByDeptId(String deptId);
}
