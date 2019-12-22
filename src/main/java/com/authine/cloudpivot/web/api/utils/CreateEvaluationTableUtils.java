package com.authine.cloudpivot.web.api.utils;

import com.authine.cloudpivot.web.api.bean.EvaluationTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 批量创建机关部门考核评价表
 */
public class CreateEvaluationTableUtils {


    public static List<EvaluationTable> getEvaluationTable(String parentId) {
        List<EvaluationTable> evaluationTables = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            EvaluationTable evaluationTable = new EvaluationTable();
            // id
            evaluationTable.setId(UUID.randomUUID().toString().replaceAll("-", ""));

            // parentId
            evaluationTable.setParentId(parentId);

            // 评分标准
            evaluationTable.setScaleOfMark("优秀：9-10；良好：7-8；一般：5-6； 较差：1-4");

            // 设置测评项目和测评内容
            switch (i) {
                case 0: {
                    evaluationTable.setAssessmentProject("理论学习及执行力");
                    evaluationTable.setAssessmentContent("着力加强学习型团队建设，注重学习与本部门相关的党和国家方针、政策、制度规定及业务知识，干部员工积极参加局内外培训和授课；部门坚决执行局重大战略决策部署，认真开展各项工作，干部员工较好地履行岗位职责。");
                } break;
                case 1: {
                    evaluationTable.setAssessmentProject("敬业精神");
                    evaluationTable.setAssessmentContent("干部员工忠诚企业，坚持高标准、严要求，敢于争创一流，具有强烈的事业心和责任感，自觉维护企业利益。");
                } break;
                case 2: {
                    evaluationTable.setAssessmentProject("合作共事");
                    evaluationTable.setAssessmentContent("干部员工主动加强与基层单位、相关部门的沟通联系，工作不推诿、不扯皮；积极主动配合其他部门工作，高效保质完成所分配的工作任务，及时进行结果反馈和后续跟进。");
                } break;
                case 3: {
                    evaluationTable.setAssessmentProject("工作思路");
                    evaluationTable.setAssessmentContent("部门整体工作思路清晰，具有系统性和前瞻性，加强业务系统的管理体制机制建设，重视系统人才培养，不断提升系统业务管理水平。");
                } break;
                case 4: {
                    evaluationTable.setAssessmentProject("业务能力");
                    evaluationTable.setAssessmentContent("部门干部员工业务能力较强，能够解决本业务系统重难点问题；坚持深入基层开展调研检查，具有发现问题、提出问题和解决问题的能力。");
                } break;
                case 5: {
                    evaluationTable.setAssessmentProject("创新能力");
                    evaluationTable.setAssessmentContent("整体创新意识较强，联系企业和基层实际创新开展工作；主动提出建议，为领导当好参谋；注重检验创新成果，具有自我修正及再创新的意识和能力。");
                } break;
                case 6: {
                    evaluationTable.setAssessmentProject("工作实效");
                    evaluationTable.setAssessmentContent("部门年度工作目标圆满完成，改革创新工作成效显著，推动基层单位业务管理工作规范化、标准化、精细化水平显著提升。 ");
                } break;
                case 7: {
                    evaluationTable.setAssessmentProject("服务意识");
                    evaluationTable.setAssessmentContent("能够始终坚持基层导向，注重联系基层客观实际，注重开展调查研究；部门干部员工能够积极反馈基层单位诉求，对待基层单位及员工热情周到，解决问题不拖延、不推诿。");
                } break;
                case 8: {
                    evaluationTable.setAssessmentProject("组织纪律");
                    evaluationTable.setAssessmentContent("部门严格遵守股份公司、局各项管理制度，模范遵守工作纪律；干部员工工作积极主动，敢于担当，乐于奉献。");
                } break;
                case 9: {
                    evaluationTable.setAssessmentProject("廉洁从业");
                    evaluationTable.setAssessmentContent("部门干部员工能够严格执行中央“八项规定”，坚持作风正派，自觉抵制不良嗜好；严格要求自己，坚持廉洁自律，不对基层单位搞摊派，不以工作关系为个人谋私利。");
                } break;
                default:
                    throw new IllegalStateException("Unexpected value: " + i);
            }

            evaluationTables.add(evaluationTable);
        }
        return evaluationTables;
    }


}
