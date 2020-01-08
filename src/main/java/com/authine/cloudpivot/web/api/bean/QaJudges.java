package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-01-08 11:39
 * @Description:
 */
@Data
public class QaJudges {

    public String id;
    public String parentId;
    public String department;
    public String judge;

}
