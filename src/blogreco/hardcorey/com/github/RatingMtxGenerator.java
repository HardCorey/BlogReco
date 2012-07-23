package blogreco.hardcorey.com.github;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import org.apache.commons.math.linear.Array2DRowRealMatrix;

public class RatingMtxGenerator {

	private ArrayList<String> blogList;
	private ArrayList<String> userList;
	private TreeMap<String, TreeSet<String>> usr_blog;//a user and its visited blog list
	private Array2DRowRealMatrix ratingMtx;//blog-blog similarity matrix

	public RatingMtxGenerator() {
		blogList=new ArrayList<String>();
		userList=new ArrayList<String>();
		ratingMtx=new Array2DRowRealMatrix();
	}
/*
 * add one user and one visited blog 
 */
	public void addUser(String usr, String blogID) {
		if (!this.userList.contains(usr)) 
			this.userList.add(usr);
		if(!blogList.contains(blogID)) 
			blogList.add(blogID);
		ratingPlus(userList.indexOf(usr), blogList.indexOf(blogID));
	}

	public void addUser(String usr, List<String> visitedBlogs) {
		if (!this.userList.contains(usr)) 
			this.userList.add(usr);
		for (String s : visitedBlogs) {
			if(!blogList.contains(s)) 
				blogList.add(s);
			ratingPlus(userList.indexOf(usr), blogList.indexOf(s));
		}
	}
	
	private void ratingPlus(int i,int j) {
		if (ratingMtx.getEntry(i, j)<5) 
			ratingMtx.addToEntry(i, j, 1);
	}

	public Array2DRowRealMatrix getRatingMtx() {
		return this.ratingMtx;
	}
}
