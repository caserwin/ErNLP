package base;

import java.io.*;
import java.util.*;

/**
 * @author yiding
 */
public abstract class BaseFileUtil {

    /**
     * 按行读取文件
     */
    public static ArrayList<String> readFileByLine(String filePath) throws IOException {
        ArrayList<String> lineList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "GBK"));
        String line;
        while ((line = br.readLine()) != null) {
            lineList.add(line.trim());
        }
        br.close();
        return lineList;
    }

    /**
     * 获取文本内容
     */
    public static String readFileAllContent(String dicPath) throws IOException {
        StringBuilder strb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dicPath), "GBK"));
        String line;
        while ((line = br.readLine()) != null) {
            strb.append(line.trim());
        }
        br.close();
        return strb.toString();
    }

    /**
     * 遍历得到文件夹下所有文件
     */
    public static void getAllPath(String filePath, ArrayList<String> fileList) {
        File files = new File(filePath);
        for (File file : files.listFiles()) {
            if (file.isDirectory()) {
                getAllPath(file.getPath(), fileList);
            } else {
                fileList.add(file.getPath());
            }
        }
    }
}
