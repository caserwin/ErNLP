# ErNLP
## 1. 说明
主要参考阅读的开源工具包源码包括hanlp、librec。希望能自己实现一些简单的文本挖掘算法。本项目使用hanlp作为第三方分词器，在此基础上进行二次开发和封装。后期将不断完善。
项目通过 common.conf 配置模型存放路径和语料。
```
nlp {
  model {
    tfidf = "model/tfidf.txt"
    idf = "model/idf.txt"
    chi = "model/chi.txt"
  }
  corpus = "SogouC/Reduced"
}
```

通过[ConstantUtil.java 类](https://github.com/caserwin/ErNLP/blob/master/src/main/java/util/ConstantUtil.java)读取配置


## 2. API
### 2.1 CHI 卡方检验
使用场景：用于提取每个类别的关键词语、文本分类<br>
提取关键词使用示例：https://github.com/caserwin/ErNLP/blob/master/src/main/java/chi/demo/ExtractKeyWordDemo.java <br>
文本分类使用示例：https://github.com/caserwin/ErNLP/blob/master/src/main/java/chi/demo/TextClassifierDemo.java <br>
详细内容：https://github.com/caserwin/ErNLP/tree/master/src/main/java/chi

### 2.2 TFIDF
使用场景：用于提取指定文档关键词语、query 查询匹配最相关的文档。<br>
提取关键词语使用示例：https://github.com/caserwin/ErNLP/blob/master/src/main/java/tfidf/demo/QueryKeyWordExtractDemo.java <br>
query 查询匹配使用示例：https://github.com/caserwin/ErNLP/blob/master/src/main/java/tfidf/demo/SearchRelateDocumentDemo.java <br>
详细内容：https://github.com/caserwin/ErNLP/tree/master/src/main/java/tfidf

### 2.3 HMM分词
详细内容：https://github.com/caserwin/ErNLP/tree/master/src/main/java/segment/hmm
示例：https://github.com/caserwin/ErNLP/tree/master/src/main/java/segment/hmm/demo