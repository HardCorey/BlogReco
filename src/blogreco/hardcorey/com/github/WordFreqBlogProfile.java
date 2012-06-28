package blogreco.hardcorey.com.github;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class WordFreqBlogProfile implements BlogProfile{

	private String ID;
	private TreeMap<String, Double> word_freq;
	
	public WordFreqBlogProfile() {
	}
	
	@Override
	public void setDescr(String ID, Map<String, Double> m) {
		this.word_freq=(TreeMap<String, Double>)m;
		this.ID=ID;
	}
	
	@Override
	public void setDescr(String ID, String content) {
		this.ID=ID;
		
		Scanner scan=new Scanner(content);
		word_freq=new TreeMap<String, Double>();
		Stemmer stemmer=new Stemmer();
		String lowerContent;
		char[] ca;
		String stem;
		double freq;
		//scan the blog content and get stem from each word
		scan.useDelimiter("[^a-zA-Z]+|\\s+");
		while(scan.hasNext()) {
			lowerContent=scan.next().toLowerCase();
			ca=lowerContent.toCharArray();
			stemmer.add(ca,ca.length);
			stemmer.stem();
			stem=stemmer.toString();
			if(stem.length()<2) 
				break;
				if (word_freq.containsKey(stem)) {
					freq=word_freq.get(stem).doubleValue();
					word_freq.put(stem,new Double(freq+1));
				}
				else
					word_freq.put(stem,new Double(1));
		}
		//divide word_freq by the total word count to normalize it
		double word_sum=0;
		for (Double d: word_freq.values()) 
			word_sum+=d.doubleValue();
		for (Entry<String, Double> entry : word_freq.entrySet())
			entry.setValue(entry.getValue()/word_sum);
	}
	
	@Override
	public String getID() {
		return this.ID;
	}
	
	@Override
	public Map<String, Double> getDescr() {
		return this.word_freq;
	}
	
	@Override
	public void setDescr(String ID,FileInputStream in) {
		
	}
}
