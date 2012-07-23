package blogreco.hardcorey.com.github;


import java.util.ArrayList;
import org.apache.commons.math.linear.Array2DRowRealMatrix;

public class BlogSimMtx {

//	private Array2DRowRealMatrix arraySimMtx;
//	private ArrayList<String> blogList;

	private SimMtx simMtx;

	public BlogSimMtx() {
		simMtx=new SimMtx();
	}
	
	public BlogSimMtx(Array2DRowRealMatrix m,ArrayList<String> blogList) {
		simMtx=new SimMtx(m,blogList);
	}

	public ArrayList<String> getBlogList() {
		return simMtx.getItemList();
	}

	public void setBlogList(ArrayList<String> blogList) {
		this.simMtx.setItemList(blogList);
		}

	public void setSimMtx(Array2DRowRealMatrix simMtx) {
		this.simMtx.setSimMtx(simMtx);
		
	}

	public Array2DRowRealMatrix getSimMtx() {
		return this.simMtx.getSimMtx();
	}

	public double getSim(int row,int col) {
		return this.simMtx.getSimMtx().getEntry(row, col);
	}

	public double getSim(String IDa,String IDb) {
		return this.simMtx.getSim(IDa, IDb);
	}

	public ArrayList<String> getMostSimBlogs(String ID,int num){
		return this.simMtx.getMostSimItems(ID, num);
	}
	/*
	 * get the Nth most similar blog of the given blog
	 */
	public String getNthMostBlogs(String ID,int N){
		return this.simMtx.getNthMostSimItem(ID, N);
	}
	
	public int getBlogNum() {
		return this.simMtx.getItemList().size();
	}
}