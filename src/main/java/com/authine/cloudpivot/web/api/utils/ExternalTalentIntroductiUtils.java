package com.authine.cloudpivot.web.api.utils;

import com.authine.cloudpivot.web.api.bean.InterviewCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: wangyong
 * @Date: 2020-01-17 16:52
 * @Description: 紧缺人才外部引进招聘工具类
 */
public class ExternalTalentIntroductiUtils {

    public static List<InterviewCondition> createInterviewCondition(String parentId, String userName) {
        List<InterviewCondition> interviewConditionList = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            InterviewCondition interviewCondition = new InterviewCondition();
            interviewCondition.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            interviewCondition.setParentId(parentId);
            interviewCondition.setSortKey(Double.parseDouble(i + ""));
            interviewCondition.setUId(userName);
            switch (i) {
                case 1:
                    interviewCondition.setGradeA("仪态仪表和语言表达");
                    interviewCondition.setEvaluationCriterion("衣着整洁，大方得体，举止稳重；表达流畅准确，逻辑思维清晰。优秀，得8-10分；较好，得5-7分；一般，得0-4分。");
                    interviewCondition.setFullMark(10);
                    interviewCondition.setScore(0D);
                    break;
                case 2:
                    interviewCondition.setGradeA("企业认同及岗位认知度");
                    interviewCondition.setEvaluationCriterion("熟悉四局情况，认同四局文化，了解四局未来发展方向和改革目标；对在四局平台所从事的岗位工作有清晰的认知，对进入四局后的职业发展有明确的规划，具有长期合作的意愿。优秀，得15-20分；较好，得10-14分；一般，得0-9分。");
                    interviewCondition.setFullMark(20);
                    interviewCondition.setScore(0D);
                    break;
                case 3:
                    interviewCondition.setGradeA("应聘动机和期望值");
                    interviewCondition.setEvaluationCriterion("能够客观分析目前岗位工作情况，更换岗位具有充分的主客观原因；应聘动机明确，看重企业发展平台，自我认知和定位清晰，对未来职业发展具有清晰的定位；具有与本人能力水平相匹配的期望值，对岗位和薪酬待遇明确表达期望。优秀，得15-20分；较好，得10-14分；一般，得0-9分。");
                    interviewCondition.setFullMark(20);
                    interviewCondition.setScore(0D);
                    break;
                case 4:
                    interviewCondition.setGradeA("专业知识");
                    interviewCondition.setEvaluationCriterion("理论基础扎实，应用能力和解决复杂问题的能力突出，具有丰富的从业经历和工作经验，善于就专业问题和行业发展现状及趋势等发表看法，敢于创新，思路清晰，表达准确。优秀，得40-50分；较好，得30-39分；一般，得0-29分。");
                    interviewCondition.setFullMark(50);
                    interviewCondition.setScore(0D);
                    break;
            }
            interviewConditionList.add(interviewCondition);
        }
        return interviewConditionList;
    }

}
