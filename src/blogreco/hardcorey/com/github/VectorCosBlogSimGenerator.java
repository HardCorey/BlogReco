package blogreco.hardcorey.com.github;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.ArrayRealVector;

/*
 * calculate the similarity matrix based on the simple vector angle
 */
public class VectorCosBlogSimGenerator extends DocWordBlogSimGenerator{

	private Array2DRowRealMatrix arrayDocWordMtx;
	private Array2DRowRealMatrix arraySimMtx;

	public VectorCosBlogSimGenerator() {
		arrayDocWordMtx=docWordMtx.getDocWordMtx();
		arraySimMtx=blogSimMtx.getSimMtx();
	}
	
/*
 * this similarity matrics does not need a param
 */
	@Override
	public void genSimMtx(double[] params) {
		genSimMtx();
	}
	
	public void genSimMtx() {

		ArrayRealVector rowVector=new ArrayRealVector(arrayDocWordMtx.getRowDimension());
		ArrayRealVector colVector=new ArrayRealVector(arrayDocWordMtx.getColumnDimension());
		for (int i=0;i<arrayDocWordMtx.getRowDimension();i++) {
			rowVector=(ArrayRealVector)arrayDocWordMtx.getRowVector(i);
			for (int j=i;j<arrayDocWordMtx.getColumnDimension();j++) {
				colVector=(ArrayRealVector)arrayDocWordMtx.getColumnVector(j);
				arraySimMtx.setEntry(i, j, rowVector.dotProduct(colVector)/(rowVector.getNorm()*colVector.getNorm()));
			}
		}
		arraySimMtx=arraySimMtx.add((Array2DRowRealMatrix)arraySimMtx.transpose());
	}
	
	@Override
	public BlogSimMtx getSimMtx() {
		return blogSimMtx;
	}
}

