package blogreco.hardcorey.com.github;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeMap;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealVector;

public class UserBlogMtx {

	private ArrayList<String> blogList;
	private LinkedList<String> userList;
	private Array2DRowRealMatrix arrayUBMtx;//blog-blog similarity matrix
	
	public double getRating(String usrID,String blogID) {
		return this.arrayUBMtx.getEntry(this.userList.indexOf(usrID), this.blogList.indexOf(blogID));
	}
	
	public RealVector getRatingsByUser(String userID) {
		return arrayUBMtx.getRowVector(userList.indexOf(userID));
	}
	
	public RealVector getRatingsByBlog(String blogID) {
		return arrayUBMtx.getColumnVector(userList.indexOf(blogID));
	}
	
	
	public ArrayList<String> getBlogList() {
		return blogList;
	}
	public void setBlogList(ArrayList<String> blogList) {
		this.blogList = blogList;
	}
	public LinkedList<String> getUserList() {
		return userList;
	}
	public void setUserList(LinkedList<String> userList) {
		this.userList = userList;
	}
	public Array2DRowRealMatrix getArrayUBMtx() {
		return arrayUBMtx;
	}
	public void setArrayUBMtx(Array2DRowRealMatrix arrayUBMtx) {
		this.arrayUBMtx = arrayUBMtx;
	}

	
	
}
