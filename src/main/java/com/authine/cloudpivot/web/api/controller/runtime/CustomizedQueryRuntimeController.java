package com.authine.cloudpivot.web.api.controller.runtime;

import com.authine.cloudpivot.engine.api.model.runtime.BizObjectModel;
import com.authine.cloudpivot.engine.component.query.api.Page;
import com.authine.cloudpivot.web.api.handler.CustomizedOrigin;
import com.authine.cloudpivot.web.api.view.PageVO;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import com.authine.cloudpivot.web.api.view.runtime.QueryDataVO;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhangjie
 * @date 2019-12-09
 */
@Api(value = "定制的查询列表数据接口", tags = "运行时::定制的列表查询接口")
@RestController
@RequestMapping("/api/runtime/query")
@Slf4j
@CustomizedOrigin(level = 1)
public class CustomizedQueryRuntimeController extends QueryRuntimeController {

    /**
     * 列表查询方法
     * 如果需要定制查询方法，
     * 需要将QueryDataVO.customized设置为true
     * （即前端接口参数中将需要将QueryDataVO.customized设置为true）
     *
     * @param queryData 查询对象
     * @return page
     */
    @ApiOperation(value = "查询数据接口")
    @PostMapping("/list")
    @Override
    public ResponseResult<PageVO<BizObjectModel>> list(@RequestBody QueryDataVO queryData) {

        if (log.isDebugEnabled()) {
            log.debug("query list. customized = [{}]", queryData.isCustomized());
        }

        // 默认执行云枢产品列表查询方法, 请勿修改
        if (!queryData.isCustomized()) {
            return super.list(queryData);
        }

        // TODO 二次开发定制列表查询 ^-^
        // 如果需要定制列表查询方法，请在此处添加具体的实现
        // queryData.getSchemaCode() 数据模型编码
        // queryData.getQueryCode()  列表查询编码
        // 例： 自定义 模型编码schemaCode=test01, 列表查询编码queryCode=qu01 的查询方法
        // 则可以:
        if (log.isDebugEnabled()) {
            log.debug("二次开发代码.");
        }

        String schemaCode = "test01";
        String queryCode = "qu01";
        if (schemaCode.equals(queryData.getSchemaCode()) && queryCode.equals(queryData.getQueryCode())) {

            // TODO 自定义查询代码
            return null;

        } else {

            // TODO 其他自定义代码
            Page<BizObjectModel> data = new Page<BizObjectModel>() {
                @Override
                public long getTotal() {
                    return 0;
                }

                @Override
                public List<? extends BizObjectModel> getContent() {
                    return Lists.newArrayList();
                }
            };
            return this.getOkResponseResult(new PageVO<>(data), "获取数据成功");
        }
    }

//	@Override
//	public ResponseResult<PageVO<BizObjectModel>> listReverseSheet(@RequestBody QueryDataVO queryData) {
//		return super.listReverseSheet(queryData);
//	}
//
//	@Override
//	public void exportTemplate(@RequestBody Map<String, String> map, HttpServletResponse response) {
//		super.exportTemplate(map, response);
//	}
//
//	@Override
//	public void exportData(@RequestBody QueryDataVO queryData, HttpServletResponse response) {
//		super.exportData(queryData, response);
//	}
//
//	@Override
//	public String importFile(MultipartFile file) {
//		return super.importFile(file);
//	}
//
//	@Override
//	public ResponseResult<FileOperationResult> importData(@NotBlank(message = FILE_NAME_NOT_EMPTY)@RequestParam String fileName,
//	                                                      @NotBlank(message = BIZ_SCHEMA_CODE_NOT_EMPTY) @RequestParam String schemaCode,
//	                                                      @RequestParam(required = false) String queryCode,
//	                                                      @RequestParam(required=false) String queryField) {
//		return super.importData(fileName, schemaCode, queryCode, queryField);
//	}
//
//	@Override
//	public ResponseResult<FileOperationResult> importProgess() {
//		return super.importProgess();
//	}
//
//	@Override
//	public ResponseResult<List<CheckResultForRemoveBizObject>> checkForRemoveBizObject(@RequestBody Map<String, Object> params) {
//		return super.checkForRemoveBizObject(params);
//	}
//
//	@Override
//	public ResponseResult<Map<String, Object>> deleteData(@RequestBody Map<String, Object> params) {
//		return super.deleteData(params);
//	}
//
//	@Override
//	public void downloadErrorData(@NotBlank(message = BIZ_SCHEMA_CODE_NOT_EMPTY) @RequestBody Map<String, Object> params, HttpServletResponse response) {
//		super.downloadErrorData(params, response);
//	}
//
//	@Override
//	public ResponseResult<List<PairSettingModel>> genShortCodes(@RequestBody List<PairSettingModel> pairValueList) {
//		return super.genShortCodes(pairValueList);
//	}
//
//	@Override
//	public ResponseResult<PairSettingModel> getShortCode(@RequestParam String pairCode) {
//		return super.getShortCode(pairCode);
//	}
//
//	@Override
//	public ResponseResult<List<BizQueryHeaderModel>> getHeaders(String schemaCode, ClientType clientType) {
//		return super.getHeaders(schemaCode, clientType);
//	}
//
//	@Override
//	public ResponseResult<PageVO<BizObjectModel>> listSkipQuery(@RequestBody QueryDataVO queryData) {
//		return super.listSkipQuery(queryData);
//	}

}
