package com.authine.cloudpivot.web.api.utils;

import com.authine.cloudpivot.web.api.bean.QualitativeAssessContent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: wangyong
 * @Date: 2020-01-09 23:27
 * @Description: 创建定性考核考核内容
 */
public class CreateQualitativeAssessContentUtils {

    public static List<QualitativeAssessContent> create(String parentId) {
        List<QualitativeAssessContent> qualitativeAssessContents = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            QualitativeAssessContent qualitativeAssessContent = new QualitativeAssessContent();
            qualitativeAssessContent.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            qualitativeAssessContent.setParentId(parentId);
            switch (i) {
                case 1:
                    qualitativeAssessContent.setEvaluationContent("德");
                    qualitativeAssessContent.setEvaluationScore(20);
                    qualitativeAssessContent.setEvaluationKps("理想信念坚定；工作作风扎实；注重学习，政策理论水平高；忠诚企业，对企业负责，具有良好的人格品质。");
                    qualitativeAssessContent.setSortKey(1D);
                    break;
                case 2:
                    qualitativeAssessContent.setEvaluationContent("能");
                    qualitativeAssessContent.setEvaluationScore(15);
                    qualitativeAssessContent.setEvaluationKps("具有本专业扎实的理论知识和丰富的项目管理经验；工作思路清晰，考虑问题全面，有前瞻性；沟通协调能力，统筹能力强，能够科学制定管控计划，合理组织落实，有序推进片区项目管控；能够针对情况变化，及时调整工作计划；能够解决工作中遇到的复杂问题；改革创新意识强，能够不断推进片区项目管理的体制机制创新、管理创新、实践创新。");
                    qualitativeAssessContent.setSortKey(2D);
                    break;
                case 3:
                    qualitativeAssessContent.setEvaluationContent("勤");
                    qualitativeAssessContent.setEvaluationScore(20);
                    qualitativeAssessContent.setEvaluationKps("事业心和进取心强，工作有激情，勤勉敬业，敢于负责、严格管理；注重实际，讲求实效，严谨务实，诚实守信，服务现场。");
                    qualitativeAssessContent.setSortKey(3D);
                    break;
                case 4:
                    qualitativeAssessContent.setEvaluationContent("绩");
                    qualitativeAssessContent.setEvaluationScore(30);
                    qualitativeAssessContent.setEvaluationKps("认真履行岗位职责，工作效率高，能够高质量的完成片区管控工作，所负责片区工程项目安全、质量、工期等有序可控，经济效益、社会效益良好。");
                    qualitativeAssessContent.setSortKey(4D);
                    break;
                case 5:
                    qualitativeAssessContent.setEvaluationContent("廉");
                    qualitativeAssessContent.setEvaluationScore(15);
                    qualitativeAssessContent.setEvaluationKps("遵守企业党风廉政建设的各项规定；严格约束配偶、子女及身边工作人员；自觉接受组织监督，作风正派、严于律已。");
                    qualitativeAssessContent.setSortKey(5D);
                    break;
            }
            qualitativeAssessContents.add(qualitativeAssessContent);
        }

        return qualitativeAssessContents;
    }

}
