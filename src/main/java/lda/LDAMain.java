package lda;

import util.ConstantUtil;

import java.io.IOException;
import java.util.Map;


/******************************************************************************
 * @author:xyd
 * @department:CasCeep
 * @version:
 * @note: 创建时间:2015-10-28 上午02:01:58
 ****************************************************************************** 
 */
public class LDAMain {

	public static void main(String[] args) throws IOException {
		String path = ConstantUtil.LDA_CORPUS;
		Map<String, Double>[] topicMap = getTopicMap(path, "GBK", 15, 15);
		LdaUtil.explain(topicMap);
	}

	// public void testAddDocument() throws Exception
	// {
	// List<String> doc1 = new ArrayList<String>();
	// doc1.add("hello");
	// doc1.add("word");
	// List<String> doc2 = new ArrayList<String>();
	// doc2.add("hankcs");
	// Corpus corpus = new Corpus();
	// corpus.addDocument(doc1);
	// corpus.addDocument(doc2);
	// System.out.println(corpus);
	// }
	/**
	 * @throws IOException
	 * @function:
	 */
	public static Map<String, Double>[] getTopicMap(String path, String ecoding, int topicNum, int wordNum)
			throws IOException {
		// 1. 从磁盘载入语料
		Corpus corpus = Corpus.load(path, ecoding);
		// 2. 创建 LDA 采样器
		// System.out.println(corpus.getDocument().length);
		// System.out.println(corpus.getVocabularySize());
		// System.out.println(corpus.getVocabulary().toString());

		LdaGibbsSampler ldaGibbsSampler = new LdaGibbsSampler(corpus.getDocument(), corpus.getVocabularySize());
		// 3. 训练，目标10个主题
		ldaGibbsSampler.gibbs(topicNum);
		// 4. phi 矩阵是唯一有用的东西，用 LdaUtil 来展示最终的结果
		double[][] phi = ldaGibbsSampler.getPhi();
		System.out.println(phi.length);
		for (double[] ds : phi) {
			System.out.println(ds.length);
		}

		Map<String, Double>[] topicMap = LdaUtil.translate(phi, corpus.getVocabulary(), wordNum);
		return topicMap;
	}
}
