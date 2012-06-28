package blogreco.hardcorey.com.github;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.ArrayRealVector;

public class DocWordMtx {

	private ArrayList<String> blogList;//blogs as rows
	private ArrayList<String> wordList;//words as colomns
	private Array2DRowRealMatrix docWordMtx;//a doc-word matrix
	private LinkedList<Map<String , Double>> tempDocWord;//a Map is word-freq in one blog
	
	
	public DocWordMtx() {
		this.blogList = new ArrayList<String>();
		this.tempDocWord=new LinkedList<Map<String,Double>>();
		this.docWordMtx=new Array2DRowRealMatrix();
	}

	public DocWordMtx(int blogNum) {
		this.blogList = new ArrayList<String>(blogNum);
		this.tempDocWord=new LinkedList<Map<String,Double>>();
		this.docWordMtx=new Array2DRowRealMatrix();
	}

	public DocWordMtx(List<String> col, List<String> row, Array2DRowRealMatrix docWordMtx) {
		this.blogList =(ArrayList<String>) col;
		this.wordList = (ArrayList<String>)row;
		this.docWordMtx=docWordMtx;
	}

	public void addBlog(String ID, Map<String, Double> word_freq) {
		if (blogList.contains(ID))
			return;
		this.blogList.add(ID);
		tempDocWord.add(word_freq);
	}

	public void addBlog(BlogProfile bp) {
		addBlog(bp.getID(), bp.getDescr());
	}

	//count words for docWordMtx
	public int countWords() {
		TreeSet<String> set=new TreeSet<String>();
		for (Map<String, Double> map : this.tempDocWord) {
			set.addAll(map.keySet());
		}
		wordList=new ArrayList<String>(set.size());
		return set.size();
	}

	public void createDocWordMtx() {

		docWordMtx=new Array2DRowRealMatrix(this.blogList.size(), this.countWords());
		int blogCounter=0;//the row number,namely the blog nubmer
		
		for (Map<String, Double> map : this.tempDocWord) {
			for (String s:map.keySet()) {
				if (!wordList.contains(s)) 
					wordList.add(s);
				//add word frequce to docWordMtx
				docWordMtx.addToEntry(blogCounter, wordList.indexOf(s), map.get(s).doubleValue());
			}
			blogCounter++;
		}
	}
	
	public Array2DRowRealMatrix getDocWordMtx() {
		return this.docWordMtx;
	}
	
	public ArrayRealVector getBlog(String ID){
		return (ArrayRealVector)docWordMtx.getRowVector(blogList.indexOf(ID));
	}
	
	public ArrayList<String> getBlogList(){
		return this.blogList;
	}
	
	public ArrayList<String> getWordList(){
		return this.wordList;
	}
}