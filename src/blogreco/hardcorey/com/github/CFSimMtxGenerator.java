package blogreco.hardcorey.com.github;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
/*
 * generates user/blog similarity matrix for CF algorithm
 */
public abstract class CFSimMtxGenerator {

	protected Array2DRowRealMatrix arrayUBMtx;

	public CFSimMtxGenerator(UserBlogMtx mtx) {
		arrayUBMtx=mtx.getArrayUBMtx();
	}
	
//	abstract void  genCFSimMtx(String Metrics, double neighbor);
	abstract void  genCFSimMtx();//Pearson correlation
	abstract SimMtx  getCFSimMtx();
	
}

