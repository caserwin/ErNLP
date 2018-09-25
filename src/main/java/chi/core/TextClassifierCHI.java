package chi.core;

import chi.util.CHIFileUtil;
import util.CommonUtil;
import util.ConstantUtil;
import util.SegmentUtil;
import util.bean.CategoryBean;
import util.bean.TermBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yidxue
 */
public class TextClassifierCHI {

    private int segType = 1;

    public CategoryBean[] classifier(String query, int topN) throws Exception {
        // 先分词
        List<TermBean> termList = SegmentUtil.segment(query, segType);
        // 加载模型
        HashMap<String, HashMap<String, Float>> wordcategory = CHIFileUtil.getKeyCateCHIModel(ConstantUtil.CHI_MODEL);
        // 计算 query 和每个类别的相关程度，用sum(CHI_i) 表示
        return textClassifier(wordcategory, termList, topN);
    }

    private CategoryBean[] textClassifier(HashMap<String, HashMap<String, Float>> wordcategory, List<TermBean> termList, int topN) throws Exception {
        // 生成 doc-weight 模型
        Map<String, Float> cateWeightRes = new HashMap<>();

        for (TermBean termBean : termList) {
            HashMap<String, Float> categoryWeight = wordcategory.get(termBean.getWord());
            for (Map.Entry kv : categoryWeight.entrySet()) {
                String cate = kv.getKey().toString();
                float weight = (float) kv.getValue();

                if (!cateWeightRes.containsKey(cate)) {
                    cateWeightRes.put(cate, weight);
                } else {
                    cateWeightRes.put(cate, cateWeightRes.get(cate) + weight);
                }
            }
        }

        // get topN document
        return CommonUtil.getTopNCate(cateWeightRes, topN);
    }
}