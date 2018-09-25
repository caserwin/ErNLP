package chi.demo;

import chi.core.TextClassifierCHI;
import util.bean.CategoryBean;

/**
 * @author yidxue
 */
public class TextClassifierDemo {
    public static void main(String[] args) throws Exception {
        // 训练模型
//        ExtractKeyWordCHI.train();

        // 文本分类
        String query = "考前我原以为新托福当场能像机考TOEFL一样给我一个成绩范围";
        CategoryBean[] categoryBeans = new TextClassifierCHI().classifier(query,5);
        for (CategoryBean cb : categoryBeans) {
            System.out.println(cb.toString());
            System.out.println("===================================================");
        }
    }
}