package blogreco.hardcorey.com.github;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.SingularValueDecompositionImpl;
/*
 * this class generates blog similarity matrix(simMtx) according to SVD algorthm
 * but the SVD algorthm is cpu consuming, so if there are too many blogs, better not use it
 */
public class SVDBlogSimGenerator extends DocWordBlogSimGenerator{

	public SVDBlogSimGenerator(DocWordMtx docWordMtx) {
		super(docWordMtx);
	}
	@Override
	public void genSimMtx(double[] singValueRatio) {

		int row=arrayDocWordMtx.getRowDimension();
		int col=arrayDocWordMtx.getColumnDimension();
		SingularValueDecompositionImpl svd=new SingularValueDecompositionImpl(arrayDocWordMtx);
		double[] singValue=svd.getSingularValues();

		//reduce dimensions based on singValueRatio
		for(double i=singValue.length*singValueRatio[0];i<singValue.length;i++) {
			singValue[(int)i]=0;
		}
		
		//multiply u,s,v to get latent sematic info
		Array2DRowRealMatrix latent_arrayDocWordMtx=new Array2DRowRealMatrix(row,col);
		latent_arrayDocWordMtx=(Array2DRowRealMatrix) svd.getU().multiply(MatrixUtils.createRealDiagonalMatrix(singValue)).multiply(svd.getVT());

		//calculate the correlation of rows to get simMtx
		ArrayRealVector rowi=new ArrayRealVector(col);
		ArrayRealVector rowj=new ArrayRealVector(col);
		double cos=0;//angle between 2 vectors,namely the similarity
		for (int i=0;i<row;i++) {
			rowi.setSubVector(0, latent_arrayDocWordMtx.getRow(i));
			for(int j=i;j<col;j++) {
				rowj.setSubVector(0, latent_arrayDocWordMtx.getRow(j));
				cos=rowi.dotProduct(rowj)/(rowi.getNorm()*rowj.getNorm());
				arraySimMtx.setEntry(i, j, cos);
			}
		}
		arraySimMtx.add(arraySimMtx.transpose());//make simMtx a symetric matrix with diagonal elements are 0s
	}

	public void genSimMtx() {
		double[] d= {0.3};
		genSimMtx(d);
	}

	/*	public double getSim(String ID1,String ID2) {
		int i=blogList.indexOf(ID1);
		int j=blogList.indexOf(ID2);
		return this.simMtx.getEntry(i,j);
	}
	 */
}

















