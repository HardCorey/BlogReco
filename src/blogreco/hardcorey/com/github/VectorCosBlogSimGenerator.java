package blogreco.hardcorey.com.github;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.ArrayRealVector;

/*
 * calculate the similarity matrix based on the simple vector angle
 */
public class VectorCosBlogSimGenerator extends DocWordBlogSimGenerator{

	public VectorCosBlogSimGenerator(DocWordMtx docWordMtx) {
		super(docWordMtx);
	}
	
/*
 * this similarity matrics does not need a param
 */
	@Override
	public void genSimMtx(double[] params) {
		genSimMtx();
	}

	public void genSimMtx() {
		ArrayRealVector row1,row2=new ArrayRealVector(arrayDocWordMtx.getRowDimension());
//		ArrayRealVector row2=new ArrayRealVector(arrayDocWordMtx.getRowDimension());
		for (int i=0;i<arrayDocWordMtx.getRowDimension();i++) {
			row1=(ArrayRealVector)arrayDocWordMtx.getRowVector(i);
			for (int j=i;j<arrayDocWordMtx.getRowDimension();j++) {
				row2=(ArrayRealVector)arrayDocWordMtx.getColumnVector(j);
				arraySimMtx.setEntry(i, j, row1.dotProduct(row2)/(row1.getNorm()*row2.getNorm()));
			}
		}
		arraySimMtx=arraySimMtx.add((Array2DRowRealMatrix)arraySimMtx.transpose());
	}
	
}