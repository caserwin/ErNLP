## 说明

基于HMM(隐马尔可夫模型)做的分词算法。对每个词语打上SBME标签，这里的标签可以看出隐含状态序列，每个字可以看成观测序列，所以得到HMM的参数以后，只要使用viterbi算法就可以分词了。

我实现的比较简单，就是基于训练数据集构建了状态矩阵、发射矩阵、初始状态分布、、然后使用一个viterbi 算法的开源版本，来分词。

就想把HMM做分词，这条路走一遍的。

开源 viterbi 算法实现: https://github.com/hankcs/Viterbi

## 使用
先添加预料训练HMM的参数，再分词。
```
public class HMMSegmentDemo {
    public static void main(String[] args) {
        ArrayList<String> corpus = new ArrayList<>();
        corpus.add("我/S 在/S 人/B 民/M 广/M 场/E 吃/S 炸/B 鸡/E");
        corpus.add("家/B 里/E 的/S 炸/B 鸡/E");
        corpus.add("KFC/S 的/S 汉/B 堡/M 包/E");
        corpus.add("广/B 场/E 很/S 漂/B 亮/E");
        corpus.add("汉/B 堡/M 包/E 很/S 好/B 吃/E");

        HMMSegment hmmSegment = new HMMSegment();
        hmmSegment.train(corpus);
        System.out.println(hmmSegment.segment("我在广场吃汉堡包"));
    }
}
```

输出：
```
我  在  广场  吃  汉堡包
```