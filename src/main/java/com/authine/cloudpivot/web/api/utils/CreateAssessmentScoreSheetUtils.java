package com.authine.cloudpivot.web.api.utils;

import com.authine.cloudpivot.web.api.bean.AssessmentResult;
import com.authine.cloudpivot.web.api.bean.AssessmentScoreSheet;
import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;

import java.util.*;

/**
 * @Author: wangyong
 * @Date: 2020-01-08 16:29
 * @Description: 创建管控组负责人年度考核内的专家定量考核明细
 */
public class CreateAssessmentScoreSheetUtils {

    public static Map<String, List<AssessmentScoreSheet>> create(String parentId) {
        Map<String, List<AssessmentScoreSheet>> map = new HashMap<>();

        List<AssessmentScoreSheet> safe = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            AssessmentScoreSheet assessmentScoreSheet = new AssessmentScoreSheet();
            assessmentScoreSheet.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            assessmentScoreSheet.setParentId(parentId);
            switch (i) {
                case 1:
                    assessmentScoreSheet.setScoringStandard("1.片区内发生死亡1～2人或同等安全责任事故，管控组管控不到位、监管不力的，每死亡1人扣5分；");
                    break;
                case 2:
                    assessmentScoreSheet.setScoringStandard("2.片区内在建项目因安全、环保、职业健康事故或事件被局项管会问责，管控组管控不到位、监管不力的，每问责1次扣3分；");
                    break;
                case 3:
                    assessmentScoreSheet.setScoringStandard("3.项目部被建设单位、地方政府及其行业主管部门针对安全、环保和职业健康工作通报批评，管控组管控不到位、监管不力的，每次扣2分；");
                    break;
                case 4:
                    assessmentScoreSheet.setScoringStandard("4.股份公司、中国铁路总公司稽查片区项目，稽查发现问题较多，并下发通报，管控组管控不到位、监管不力的，每通报一次扣2分。");
                    break;
            }
            safe.add(assessmentScoreSheet);
        }
        map.put("安全管理", safe);

        List<AssessmentScoreSheet> quality = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            AssessmentScoreSheet assessmentScoreSheet = new AssessmentScoreSheet();
            assessmentScoreSheet.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            assessmentScoreSheet.setParentId(parentId);
            switch (i) {
                case 1:
                    assessmentScoreSheet.setScoringStandard("1.片区内发生直接经济损失30万元～300万元（含）质量责任事故，被安委会处理的，管控组管控不到位、监管不力的,每发生一起扣5分;   ");
                    break;
                case 2:
                    assessmentScoreSheet.setScoringStandard("2.片区内在建项目因严重质量问题被局项管会问责的，管控组管控不到位、监管不力的，每问责1次扣3分；");
                    break;
                case 3:
                    assessmentScoreSheet.setScoringStandard("3.片区内在建项目因质量问题受到中国铁路总公司、股份公司等单位通报，管控组管控不到位、监管不力的，每通报1次扣2分；");
                    break;
                case 4:
                    assessmentScoreSheet.setScoringStandard("4、片区内在建项目因工程实体质量检测不合格，同时造成经济损失达30万元以上的，每次扣0.5分。\n" +
                            "（如第1条和第4条同时出现，则遵从第1条进行扣分）");
                    break;
            }
            quality.add(assessmentScoreSheet);
        }
        map.put("质量管理", quality);

        List<AssessmentScoreSheet> sc = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            AssessmentScoreSheet assessmentScoreSheet = new AssessmentScoreSheet();
            assessmentScoreSheet.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            assessmentScoreSheet.setParentId(parentId);
            switch (i) {
                case 1:
                    assessmentScoreSheet.setScoringStandard("1、片区内重点项目（投资项目除外)因项目自身施工组织原因未完成局下达的年度施工产值计划，局管控组指导、帮扶不力的，每个项目扣0.5分； ");
                    break;
                case 2:
                    assessmentScoreSheet.setScoringStandard("2、片区内在建重点项目因自身施工组织不力，施工进度严重滞后，未完成业主下达的节点工期目标，局管控组指导、帮扶不力的，每次扣0.5分；");
                    break;
                case 3:
                    assessmentScoreSheet.setScoringStandard("3、不能积极协助局生产部门合理调配片区内施工资源的，每次扣0.5分；");
                    break;
                case 4:
                    assessmentScoreSheet.setScoringStandard("4、片区内项目物资、机械管理出现严重问题，被上级单位通报，管控组管控不到位、监管不力的，每次扣1分。");
                    break;
            }
            sc.add(assessmentScoreSheet);
        }
        map.put("进度及施工组织管理", sc);

        List<AssessmentScoreSheet> skill = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            AssessmentScoreSheet assessmentScoreSheet = new AssessmentScoreSheet();
            assessmentScoreSheet.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            assessmentScoreSheet.setParentId(parentId);
            switch (i) {
                case 1:
                    assessmentScoreSheet.setScoringStandard("1、管控组发现片区内项目部方案未经评审和批准现场即施工，未要求停工或督促尽快完成方案评审的，每发现1次扣1分；");
                    break;
                case 2:
                    assessmentScoreSheet.setScoringStandard("2、片区内项目部不严格按照施工方案组织实施且存在安全、质量隐患，未发现并督促整改的，每发现1次扣1分； ");
                    break;
                case 3:
                    assessmentScoreSheet.setScoringStandard("3、不积极参加片区内项目施工组织设计评审，无故缺席的，每次扣0.5分；");
                    break;
                case 4:
                    assessmentScoreSheet.setScoringStandard("4、未按要求每半年对项目工程施工技术管理六项制度建设和执行工作进行稽查的，每次扣0.5分。");
                    break;
            }
            skill.add(assessmentScoreSheet);
        }
        map.put("技术管理", skill);

        List<AssessmentScoreSheet> engine = new ArrayList<>();
        for (int i = 1; i <= 1; i++) {
            AssessmentScoreSheet assessmentScoreSheet = new AssessmentScoreSheet();
            assessmentScoreSheet.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            assessmentScoreSheet.setParentId(parentId);
            switch (i) {
                case 1:
                    assessmentScoreSheet.setScoringStandard("未能发现项目部分包管理违反局管理规定的，每发现一次扣1分。");
                    break;
            }
            engine.add(assessmentScoreSheet);
        }
        map.put("工程分包管理", engine);

        List<AssessmentScoreSheet> credit = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            AssessmentScoreSheet assessmentScoreSheet = new AssessmentScoreSheet();
            assessmentScoreSheet.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            assessmentScoreSheet.setParentId(parentId);
            switch (i) {
                case 1:
                    assessmentScoreSheet.setScoringStandard("1、片区内某个公路资质获评等级为B，扣0.5分（片区内无参评项目或首次进入的地区，延用以往评比等级或套用当地评比办法的除外），获评等级为C或者D，扣1分；");
                    break;
                case 2:
                    assessmentScoreSheet.setScoringStandard("2、片区内某个公路资质获评等级比上一年获评等级下降，扣1分（片区内无参评项目除外）；");
                    break;
                case 3:
                    assessmentScoreSheet.setScoringStandard("3、根据局铁路信用评价半年考核结果，片区内出现处罚铁路项目，每个项目扣0.5分；");
                    break;
                case 4:
                    assessmentScoreSheet.setScoringStandard("4.片区在建项目因工程进度滞后、质量、安全问题，业主每来函投诉一次扣0.5分；");
                    break;
                case 5:
                    assessmentScoreSheet.setScoringStandard("5. 片区内项目受到新闻媒体负面报道，对局声誉造成负面影响的，每报道一次扣1分。");
                    break;
            }
            credit.add(assessmentScoreSheet);
        }
        map.put("铁路、公路信用评价", credit);

        List<AssessmentScoreSheet> program = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            AssessmentScoreSheet assessmentScoreSheet = new AssessmentScoreSheet();
            assessmentScoreSheet.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            assessmentScoreSheet.setParentId(parentId);
            switch (i) {
                case 1:
                    assessmentScoreSheet.setScoringStandard("1、不能积极协调处理职责内局下属单位施工生产方面事宜，相关公司投诉到局生产部门并被核实的，每出现1次扣0.5分；  ");
                    break;
                case 2:
                    assessmentScoreSheet.setScoringStandard("2、对片区内重点项目存在的重大问题未及时、如实向局分管领导、局主管生产领导和相关部门汇报，每次扣2分； ");
                    break;
                case 3:
                    assessmentScoreSheet.setScoringStandard("3、每次重点项目管控没有与业主、监理进行沟通，每少一次扣1分；");
                    break;
                case 4:
                    assessmentScoreSheet.setScoringStandard("4. 对子（分）公司存在整改不到位或虚假回复现象没发现，视后果的严重性，每次扣1～2分。");
                    break;
            }
            program.add(assessmentScoreSheet);
        }
        map.put("沟通协调、重大问题报告", program);

        List<AssessmentScoreSheet> control = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            AssessmentScoreSheet assessmentScoreSheet = new AssessmentScoreSheet();
            assessmentScoreSheet.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            assessmentScoreSheet.setParentId(parentId);
            switch (i) {
                case 1:
                    assessmentScoreSheet.setScoringStandard("1、未按计划安排对项目进行管控，又未事先调整计划，每少一次扣1分； ");
                    break;
                case 2:
                    assessmentScoreSheet.setScoringStandard("2、未及时上报管控检查、总结及计划资料，每次扣1分；");
                    break;
                case 3:
                    assessmentScoreSheet.setScoringStandard("3、资料填写及上报不符合局要求，每次扣0.5分；");
                    break;
                case 4:
                    assessmentScoreSheet.setScoringStandard("4、对片区内重难点及高风险项目的管控，组长无故不带队的，每次扣0.5分；");
                    break;
                case 5:
                    assessmentScoreSheet.setScoringStandard("5、管控检查不仔细，不全面，存在走过场现象，每次扣0.5分。");
                    break;
            }
            control.add(assessmentScoreSheet);
        }
        map.put("管控工作规范性", control);

        List<AssessmentScoreSheet> service = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            AssessmentScoreSheet assessmentScoreSheet = new AssessmentScoreSheet();
            assessmentScoreSheet.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            assessmentScoreSheet.setParentId(parentId);
            switch (i) {
                case 1:
                    assessmentScoreSheet.setScoringStandard("1、未按管控工作计划或局领导指示要求开展对项目培训指导的，每少1次扣1分；");
                    break;
                case 2:
                    assessmentScoreSheet.setScoringStandard("2、管控培训走过场，没有取得实效，授课无讲义，每次扣0.5分；");
                    break;
                case 3:
                    assessmentScoreSheet.setScoringStandard("3、不积极协助局机关相关部门对片区内项目的培训工作，每次扣0.5分；");
                    break;
                case 4:
                    assessmentScoreSheet.setScoringStandard("4、片区内项目存在的困难，管控组在职责范围内不积极帮助解决的，每次扣0.5分。");
                    break;
            }
            service.add(assessmentScoreSheet);
        }
        map.put("对片区内在建项目的服务、指导", service);

        List<AssessmentScoreSheet> discipline = new ArrayList<>();
        for (int i = 1; i <= 1; i++) {
            AssessmentScoreSheet assessmentScoreSheet = new AssessmentScoreSheet();
            assessmentScoreSheet.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            assessmentScoreSheet.setParentId(parentId);
            switch (i) {
                case 1:
                    assessmentScoreSheet.setScoringStandard("发生了违反廉政规定的情况，每次扣2分。 ");
                    break;
            }
            discipline.add(assessmentScoreSheet);
        }
        map.put("稽查纪律", discipline);

        return map;
    }

}
