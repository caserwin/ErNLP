package ig;

import util.bean.DocBaseBean;

import java.io.File;
import java.io.PrintStream;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author yidxue
 */
public class WordDocMatrix {
    private HashMap<String, ArrayList<Short>> matrix = new HashMap<>();
    private int docCount = 0;
    private String[] contents = {"眼睛 明亮 健康 身体 发达", "高大 身材 胳膊 勇猛 四肢", "胳膊 健康 身体 高大 健康", "美国 很 发达", "美国 经济 富强", "日本 富强 科技 发达"};


    public ArrayList<IGDocBean> buildContextList() {
        ArrayList<IGDocBean> list = new ArrayList<>();
        int id = 0;
        for (String content : contents) {
            String[] words = content.split("\\s+");
            list.add(new IGDocBean(id++, content, words));
        }
        docCount = list.size();
        return list;
    }


    /**
     * 构建文档-词频 矩阵
     */
    public void buildMatrix(ArrayList<IGDocBean> DocList) {
        for (IGDocBean doc : DocList) {
            String[] words = doc.getWords();
            for (String word : words) {

                if (matrix.containsKey(word)){

                }



            }
        }
    }

    //打印矩阵的前几行，输出到文件，以作验证（如果全部打印文件会因太大而加载过慢，甚至可能打不开）
    public static void main(String[] args) {
        WordDocMatrix inst = new WordDocMatrix();
        try {


            File file = new File("/Users/cisco/matrix");
            file.createNewFile();
            PrintStream ps = new PrintStream(file);
            inst.printMatrix(ps, inst.matrix);
            //inst.printMatrix(System.out,inst.matrix);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //输出matrix
    public void printMatrix(PrintStream out, HashMap<String, ArrayList<Short>> matrix) {
        Iterator<Entry<String, ArrayList<Short>>> iter = matrix.entrySet().iterator();
        try {
            while (iter.hasNext()) {
                Entry<String, ArrayList<Short>> entry = iter.next();
                out.print(entry.getKey());
                out.print("\t");
                for (int i = 0; i < docnumber; i++) {
                    out.print(String.valueOf(entry.getValue().get(i)));
                    out.print("\t");
                }
                out.println();
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
