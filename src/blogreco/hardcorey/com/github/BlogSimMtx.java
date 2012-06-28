package blogreco.hardcorey.com.github;

import java.util.ArrayList;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.ArrayRealVector;

public class BlogSimMtx {

	private Array2DRowRealMatrix simMtx;
	private ArrayList<String> blogList;

	public BlogSimMtx() {
		// TODO Auto-generated constructor stub
	}
	
	public BlogSimMtx(Array2DRowRealMatrix m,ArrayList<String> blogList) {
		this.blogList=blogList;
		this.simMtx=m;
	}

	public ArrayList<String> getBlogList() {
		return blogList;
	}

	public void setBlogList(ArrayList<String> blogList) {
		this.blogList = blogList;
	}

	public void setSimMtx(Array2DRowRealMatrix simMtx) {
		this.simMtx = simMtx;
	}

	public Array2DRowRealMatrix getSimMtx() {
		return this.simMtx;
	}

	public double getSim(int row,int col) {
		return this.simMtx.getEntry(row, col);
	}

	public double getSim(String IDa,String IDb) {
		int row=blogList.indexOf(IDa);
		int col=blogList.indexOf(IDb);
		return getSim(row, col);
	}

	public ArrayList<String> getMostSimBlogs(String ID,int num){

		int i=blogList.indexOf(ID);
		ArrayRealVector simVector=(ArrayRealVector)this.simMtx.getRowVector(i);
		int listLength=Math.min(num, simVector.getDimension()-1);
		ArrayList<String> blogListSortedBySim=new ArrayList<String>(listLength);
		int index;

		for (int j=0;j<listLength;j++) {
			index=simVector.getMaxIndex();
			blogListSortedBySim.set(j, blogList.get(index));
			simVector.setEntry(index, 0);
		}
		return blogListSortedBySim;
	}
/*
 * get the Nth most similar blog of the given blog
 */
	public String getNthMostBlogs(String ID,int N){

		int ind=blogList.indexOf(ID);
		double[] simRow=this.simMtx.getRow(ind);
		String[] blogs=(String[]) this.blogList.toArray();
		int i=0;

		while (!(i==N)) {
			for (int j=i+1;j<simRow.length;j++) {
				if (simRow[i]>simRow[j]) {
					double temp=simRow[i];
					simRow[i]=simRow[j];
					simRow[j]=temp;
					String t=blogs[i];
					blogs[i]=blogs[j];
					blogs[j]=t;
				}
			}
			i++;
		}
		return blogs[N];
	}
	
	public int getBlogNum() {
		return this.blogList.size();
	}
}