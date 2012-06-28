package blogreco.hardcorey.com.github;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.ArrayRealVector;

public class test {

	int i;
	
	public test() {
		i=0;
		init();
	}

	private void init() {
		i++;
	}
	
	public void out() {
		System.out.println(i);
	}
	
	public static void main(String[] args) throws IOException {

		Integer i=new Integer(5);
		int j=23;
		Map<String, Integer> map=new TreeMap<String, Integer>();
		map.put("str", j);
		System.out.println(map);
		System.out.println(map);
		System.out.println(map);

/*		double[][] d= {{1,2,3,3,5},{3,4,2,2,6},{1,4,2,5,5},{1,1,2,3,3}};
		double [][] d1= {{1,2},{1,1}};
		double[][] d2= {{3,1},{2,2}};
		Array2DRowRealMatrix m1=new Array2DRowRealMatrix(d1);
		Array2DRowRealMatrix m2=new Array2DRowRealMatrix(d2);
		Array2DRowRealMatrix mm=new Array2DRowRealMatrix(d);
		test t=new test(d);
*/		
/*		test t=new test();
		Array2DRowRealMatrix am=t.getM();
		am.setEntry(1, 1, 100);
		System.out.println(am);
		System.out.println(t.getMat());
*/
/*		Stemmer stem=new Stemmer();
		String s="students";
		FileInputStream fis=new FileInputStream(new File("e:/R.txt"));
		System.out.println();
		Scanner scan=new Scanner(fis);
		scan.useDelimiter("[^a-zA-Z]+|\\s+");
		while(scan.hasNext()) {
			s=scan.next().toLowerCase();
			char[] ca=s.toCharArray();
			stem.add(ca,ca.length);
			stem.stem();
			if(stem.getResultLength()>1)
			System.out.println(stem.toString());
		}
*/
/*		Stemmer stem=new Stemmer();
		String s="joyfully";
		char[] ca=s.toCharArray();
		stem.add(ca,ca.length);
		stem.stem();
		if(stem.getResultLength()>1)
		System.out.println(stem.toString());
*/
		
		
/*		double[] d= {1,2,1,1,2};
		ArrayRealVector arv=new ArrayRealVector(d);
		double[] d2= {1,2,2,2,2};
		ArrayRealVector arv2=new ArrayRealVector(d2);
		System.out.println(arv.getNorm());
		System.out.println(arv.dotProduct(arv2));
*/
/*		double[][] m= {{1,2,1},{2,2,1},{1,1,1}};
		Array2DRowRealMatrix mat=new Array2DRowRealMatrix(m);
		System.out.println(mat.add(mat.transpose()));
*/	
	}
}



















