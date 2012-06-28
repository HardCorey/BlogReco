package blogreco.hardcorey.com.github;

import java.io.FileInputStream;
import java.util.Map;

public interface BlogProfile {

	//set Word-Frequency directly
	public void setDescr(String ID, Map<String, Double> m);
	//input blog content and generate Word-Frequency
	public void setDescr(String ID,String content);
	//set InputStream of the blog content and generate Word-Frequency
	public void setDescr(String ID, FileInputStream in);
	public String getID();
	public Map<String, Double> getDescr();
	
}
