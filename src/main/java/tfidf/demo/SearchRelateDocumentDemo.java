package tfidf.demo;

import tfidf.core.SearchDocumentTFIDF;
import util.bean.RelateDocBean;

/**
 * @author cisco
 */
public class SearchRelateDocumentDemo {

    public static void main(String[] args) throws Exception {
        // train corpus
         SearchDocumentTFIDF.train();
        //
        String query = "互联网的互联网今年行情不好";
        RelateDocBean[] topNDocments = new SearchDocumentTFIDF().searchRelateDoc(query, 5);
        for (RelateDocBean rd : topNDocments) {
            System.out.println(rd.toString());
            System.out.println("===================================================");
        }
    }
}
