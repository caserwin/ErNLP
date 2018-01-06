package tfidf.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import base.BaseFileUtil;

/**
 * @author yiding
 */
public class TFIDFFileUtil extends BaseFileUtil {

    /**
     * 按行写入文件
     */
    public static void writeFileByLine(String filePath, HashMap<String, Integer> hashMap, int textNumber) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "utf-8"));
        bw.write("text_Number" + "\t" + textNumber);
        bw.newLine();
        for (Map.Entry entry : hashMap.entrySet()) {
            String word = entry.getKey().toString();
            String docNum = entry.getValue().toString();
            bw.write(word + "\t" + docNum);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }


    /**
     * 获取模型
     */
    public static HashMap<String, Integer> getModelForIDF(String filePath) throws Exception {
        HashMap<String, Integer> wordIDF = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] str = line.trim().split("\\s+");
            wordIDF.put(str[0].trim(), Integer.parseInt(str[1]));
        }
        br.close();
        return wordIDF;
    }

}
