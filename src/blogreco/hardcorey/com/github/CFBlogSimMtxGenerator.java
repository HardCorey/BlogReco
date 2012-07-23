package blogreco.hardcorey.com.github;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.ArrayRealVector;
/*
 * generates blog similarity matrix for CF algorithm
 * as users are volatile rapidly as time go by, but blogs as relatively stable,
 * so we choose Item-based CF
 */
public class CFBlogSimMtxGenerator extends CFSimMtxGenerator {

	private SimMtx blogSimMtx;
	private Array2DRowRealMatrix arraySimMtx;
	
	public CFBlogSimMtxGenerator(UserBlogMtx mtx) {
		super(mtx);
		blogSimMtx=new SimMtx();
		blogSimMtx.setItemList(mtx.getBlogList());
		arraySimMtx=new Array2DRowRealMatrix(mtx.getBlogList().size(),mtx.getBlogList().size());
		blogSimMtx.setSimMtx(arraySimMtx);
	}

	@Override
	void genCFSimMtx() {
		ArrayRealVector cola,colb=new ArrayRealVector(arrayUBMtx.getColumnDimension());
		for (int i=0;i<arrayUBMtx.getColumnDimension();i++) {
			cola=(ArrayRealVector)arrayUBMtx.getColumnVector(i);
			for (int j=i;j<arrayUBMtx.getColumnDimension();j++) {
				colb=(ArrayRealVector)arrayUBMtx.getColumnVector(j);
				arraySimMtx.setEntry(i, j, cola.dotProduct(colb)/(cola.getNorm()*colb.getNorm()));
			}
		}
		arraySimMtx=arraySimMtx.add((Array2DRowRealMatrix)arraySimMtx.transpose());
	}

	@Override
	SimMtx getCFSimMtx() {
		// TODO Auto-generated method stub
		return this.blogSimMtx;
	}


}
