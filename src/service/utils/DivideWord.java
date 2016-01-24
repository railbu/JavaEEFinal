package service.utils;
import java.io.StringReader; 
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer; 
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream; 

import net.paoding.analysis.analyzer.PaodingAnalyzer; 
    
    public class DivideWord { 
        public static List<String> divide(String s) throws Exception {
        	List<String> words = new ArrayList<String>(); 
            Analyzer analyzer = new PaodingAnalyzer(); 
            String  indexStr = s;
            StringReader reader = new StringReader(indexStr); 
            TokenStream ts = analyzer.tokenStream(indexStr, reader); 
           
            Token t = ts.next(); 
            while (t != null) {
                words.add(t.termText());
                t = ts.next(); 
            }
            return words;
        } 
    } 
    
    
