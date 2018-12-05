package segment.demo;

import segment.core.HMMSegment;
import java.util.ArrayList;

/**
 * Created by yidxue on 2018/12/4
 */
public class HMMSegmentDemo {
    public static void main(String[] args) {
        ArrayList<String> corpus = new ArrayList<>();
        corpus.add("我/S 在/S 人/B 民/M 广/M 场/E 吃/S 炸/B 鸡/E");
        corpus.add("KFC/S 的/S 炸/B 鸡/E");
        corpus.add("KFC/S 的/S 汉/B 堡/M 包/E");
        corpus.add("广/B 场/E 很/S 漂/B 亮/E");
        corpus.add("汉/B 堡/M 包/E 很/S 好/B 吃/E");
        corpus.add("在/S 广/B 场/E");

        HMMSegment hmmSegment = new HMMSegment();
        hmmSegment.train(corpus);
        System.out.println(hmmSegment.segment("我在广场吃炸鸡"));
    }
}
