package blogreco.hardcorey.com.github;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.math.linear.Array2DRowRealMatrix;

/*
 * Generates a matrix: users as rows, blogs as columns
 * each row indicates one-user-visited blogs
 * each element mean one blog's rating by user, 5 at max
 */
public class UserBlogMtxGenerator {

	private ArrayList<String> blogList;
	private LinkedList<String> userList;
	private TreeMap<String, TreeMap<String, Integer>> userBlogMap;//a user and its visited blog list
	private Array2DRowRealMatrix arrayUBMtx;//blog-blog similarity matrix

	UserBlogMtxGenerator(int blogNum) {
		blogList=new ArrayList<String>(blogNum);
		userList=new LinkedList<String>();
		userBlogMap=new TreeMap<String, TreeMap<String,Integer>>();
	}

	UserBlogMtxGenerator() {
		blogList=new ArrayList<String>();
		userList=new LinkedList<String>();
		userBlogMap=new TreeMap<String, TreeMap<String,Integer>>();
	}

	/*
	 * input a user and a blog it visited
	 */
	public void inputUserBlog(String userID, String blogID) {
		if (!userList.contains(userID)) //new user
			this.userList.add(userID);
		if (!blogList.contains(blogID)) //new blog
			this.blogList.add(blogID);
		TreeMap<String, Integer> temp=new TreeMap<String, Integer>();
		if (userBlogMap.containsKey(userID)) {//existed user
			temp=userBlogMap.get(userID);
			if (temp.containsKey(blogID)) //visited blog
				temp.put(blogID, Math.max(temp.get(blogID)+1,5));//rating is 5 at max
			else //unvisited blog
				temp.put(blogID,1);
		}
		else { //new user
			temp.put(blogID, 1);
			userBlogMap.put(userID, temp);
		}
	}
	
	public void inputUserBlogs(String userID, List<String> blogList) {
		 for (String blogID : blogList) {
			inputUserBlog(userID, blogID);
		}
	}
/*
 * convert TreeMap<String, TreeMap<String, Integer>>
 */
	public void genUserBlogMtx() {
		int userNum=this.userList.size();
		int blogNum=this.blogList.size();
		arrayUBMtx=new Array2DRowRealMatrix(userNum,blogNum);
		TreeMap<String, Integer> temp;
		TreeSet<String> tempBlogs;
		for (int i=0; i<userList.size(); i++) {//for each user
			temp=this.userBlogMap.get(userList.get(i));//map of blogs the user visited
			tempBlogs=new TreeSet<String>(temp.keySet());
			for (String string : tempBlogs) {
				arrayUBMtx.setEntry(i,this.blogList.indexOf(string), temp.get(string));
			}
		}
	}
	
	public UserBlogMtx getMtx() {
		UserBlogMtx mtx=new UserBlogMtx();
		mtx.setUserList(this.userList);
		mtx.setBlogList(this.blogList);
		mtx.setArrayUBMtx(this.arrayUBMtx);
		return mtx;
	}
}