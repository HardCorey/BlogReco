package blogreco.hardcorey.com.github;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.math.linear.Array2DRowRealMatrix;

public class BlogConcurSimGenerator {

	private ArrayList<String> blogList;
	private TreeMap<String, TreeSet<String>> usr_blog;//a user and its visited blog list
	private Array2DRowRealMatrix arraySimMtx;//blog-blog similarity matrix
	
	public BlogConcurSimGenerator(int blogNum) {
		blogList=new ArrayList<String>(blogNum);
		arraySimMtx=new Array2DRowRealMatrix(blogNum,blogNum);
		usr_blog=new TreeMap<String, TreeSet<String>>();
	}

	public BlogConcurSimGenerator() {
		blogList=new ArrayList<String>();
		arraySimMtx=new Array2DRowRealMatrix();
		usr_blog=new TreeMap<String, TreeSet<String>>();
	}

	public void addUser(String usr, String blogID) {
		if (this.usr_blog.containsKey(usr)) 
			this.usr_blog.get(usr).add(blogID);
		else {
			TreeSet<String> temp=new TreeSet<String>();
			temp.add(blogID);
			this.usr_blog.put(usr, temp);
		}
		if(!blogList.contains(blogID)) 
			blogList.add(blogID);
	}

	public void addUser(String usr, List<String> visitedBlogs) {
		if (this.usr_blog.containsKey(usr)) {
			for (String ID : visitedBlogs) {
				this.usr_blog.get(usr).add(ID);
			}
		}
		else {
			TreeSet<String> temp=new TreeSet<String>(blogList);
			this.usr_blog.put(usr, temp);
		}
		for (String s : visitedBlogs) {
			if(!blogList.contains(s)) 
				blogList.add(s);
		}
	}

	public void genArraySimMtx() {
		
		for (Entry<String, TreeSet<String>> entry : usr_blog.entrySet()) {
			for (String blog1 : entry.getValue()) {
				int ind1=blogList.indexOf(blog1);
				for (String blog2: entry.getValue()) {
					int ind2=blogList.indexOf(blog2);
					if(!blog1.equals(blog2)) {
						arraySimMtx.addToEntry(ind1, ind2, 1);
						arraySimMtx.addToEntry(ind2, ind1, 1);
					}
				}
			}
		}
/*		//find max in arraySimMtx
		int dim=arraySimMtx.getRowDimension();
		double max=0;
		for (int i=0; i<dim; i++) {
			for (int j=i;j<dim;j++) {
				if (arraySimMtx.getEntry(i, j)>max) 
					max=arraySimMtx.getEntry(i, j);
			}
		}
*/
	}
	
	public BlogSimMtx getSimMtx() {
		BlogSimMtx simMtx=new BlogSimMtx(arraySimMtx, blogList);
		return simMtx;
	}
}

























