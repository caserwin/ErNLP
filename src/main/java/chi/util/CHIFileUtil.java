package chi.util;

import base.BaseFileUtil;

import java.io.*;
import java.util.*;

/**
 * @author yiding
 */
public class CHIFileUtil extends BaseFileUtil {

    /**
     * 按行写入文件
     */
    public static void writeFileByLine(String filePath, HashMap<String, HashMap<String, Float>> categoryKeyWord) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "utf-8"));
        for (Map.Entry entry : categoryKeyWord.entrySet()) {
            String cate = entry.getKey().toString();
            HashMap<String, Float> wordCHI = (HashMap<String, Float>) entry.getValue();
            for (Map.Entry wordAndCHIentry : wordCHI.entrySet()) {
                String word = wordAndCHIentry.getKey().toString();
                float chi = (float) wordAndCHIentry.getValue();
                bw.write(cate + "\t" + word + "\t" + chi);
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }

    /**
     * 获得类别
     */
    public static Set<String> getCategory(String filePath) {
        File file = new File(filePath);
        return new HashSet<>(Arrays.asList(Objects.requireNonNull(file.list())));
    }

    /**
     * 获得模型
     */
    public static HashMap<String, HashMap<String, Float>> getCHIModel(String filePath) throws IOException {
        HashMap<String, HashMap<String, Float>> categoryKeyWord = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] str = line.split("\\s+");
            if (!categoryKeyWord.containsKey(str[0])) {
                categoryKeyWord.put(str[0], new HashMap<String, Float>() {{
                    put(str[1], Float.parseFloat(str[2]));
                }});
            } else {
                categoryKeyWord.get(str[0]).put(str[1], Float.parseFloat(str[2]));
            }
        }
        br.close();
        return categoryKeyWord;
    }
}
