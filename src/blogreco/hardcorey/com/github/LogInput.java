package blogreco.hardcorey.com.github;

import java.util.ArrayList;
import java.util.LinkedList;

public class LogInput {

	LinkedList<String> userList;
	ArrayList<String> blogList;
	int blogNum;
	int userNum;
	
	public LogInput(int blogNum) {
		this.blogNum=blogNum;
		blogList=new ArrayList<String>(blogNum);
		this.userList=new LinkedList<String>();
	}
	
	public void inputOneLog(String userID, String blogID) {
		
	}
}
