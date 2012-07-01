package blogreco.hardcorey.com.github;

import java.util.Calendar;
import java.util.Random;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.ArrayRealVector;
/*
 * this generator uses non-negative matrix factorization(NMF) to proceed doc-word matrix
 * to get blog similarity according to the latent factors
 * this algorthm's efficiency is better compared with SVD but need aprior information--the number of factors.
 * In practice, the number is better assigned as the number of categoris of blogs
 * 
 */
public class NMFBlogSimGenerator extends DocWordBlogSimGenerator{
	
	private Array2DRowRealMatrix arrayDocWordMtx;
	private Array2DRowRealMatrix arraySimMtx;
	int row;
	int col;

	public NMFBlogSimGenerator() {
		arrayDocWordMtx=docWordMtx.getDocWordMtx();
		arraySimMtx=blogSimMtx.getSimMtx();
	}

	/*
	 * factorize arrayDocWordMtx=>W*H
	 * @param parmas  params[0], usually smaller than blogs number, means how many facors the blogs have;
	 * 				  params[1] means how many loops this algorthm will go, set it 0 if you do not need it, default value is 50
 	 * 				  params[2], the minimum Euclidean distance between arrayDocWordMtx and W*H, default value is 15
	 */
	
	@Override
	public void genSimMtx(double[] params) {
		int r=Math.max(0, (int)params[0]);//factors
		int loops;
		double EuDis;
		row=arrayDocWordMtx.getRowDimension();
		col=arrayDocWordMtx.getColumnDimension();

		if (params.length>1)
			loops=Math.max(3, (int)params[1]);
		else
			loops=500;
		if (params.length>2)
			EuDis=params[2];
		else
			EuDis=15;

		Array2DRowRealMatrix W=initMatrix(row, r, 5);;
		Array2DRowRealMatrix H=initMatrix(r, col, 5);;

		for (int i=0;i<loops ;i++) {
			IterH(arrayDocWordMtx, W, H, r, col);
			IterW(arrayDocWordMtx, W, H, row, r);
			if (EuDistance(arrayDocWordMtx, W.multiply(H))<EuDis)
				break;
		}
		simMtxFromW(arraySimMtx);
	}

	private Array2DRowRealMatrix IterH(Array2DRowRealMatrix V,Array2DRowRealMatrix W,Array2DRowRealMatrix H,int Hrow, int Hcol) {
		Array2DRowRealMatrix numerator=(Array2DRowRealMatrix) W.transpose().multiply(V);
		Array2DRowRealMatrix denominator=(Array2DRowRealMatrix) W.transpose().multiply(W).multiply(H);
		double coef;
		for (int i=0;i<Hrow;i++) {
			for (int j=0;j<Hcol;j++) {
				coef=numerator.getEntry(i, j)/denominator.getEntry(i, j);
				H.setEntry(i, j, H.getEntry(i, j)*coef);
			}
		}
		return H;
	}
	
	private Array2DRowRealMatrix IterW(Array2DRowRealMatrix V,Array2DRowRealMatrix W,Array2DRowRealMatrix H,int Wrow, int Wcol) {
		Array2DRowRealMatrix numerator=(Array2DRowRealMatrix) V.multiply(H.transpose());
		Array2DRowRealMatrix denominator=(Array2DRowRealMatrix) W.multiply(H).multiply(H.transpose());
		double coef;
		for (int i=0;i<Wrow;i++) {
			for (int j=0;j<Wcol;j++) {
				coef=numerator.getEntry(i, j)/denominator.getEntry(i, j);
				W.setEntry(i, j, W.getEntry(i, j)*coef);
			}
		}
		return W;
	}
	
	private double EuDistance(Array2DRowRealMatrix m1, Array2DRowRealMatrix m2) {
		int row=m1.getRowDimension();
		int col=m2.getColumnDimension();
		double sum=0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				sum+=Math.pow(m1.getEntry(i, j)-m2.getEntry(i, j),2);
			}
		}
		return sum;
	}
	
	private void simMtxFromW(Array2DRowRealMatrix W) {
		ArrayRealVector v1,v2;
		for (int i=0;i<row;i++) {
			v1=(ArrayRealVector)arrayDocWordMtx.getRowVector(i);
			for (int j=i;j<col;j++) {
				v2=(ArrayRealVector)arrayDocWordMtx.getColumnVector(j);
				arraySimMtx.setEntry(i, j, v1.dotProduct(v2)/(v1.getNorm()*v2.getNorm()));
			}
		}
		arraySimMtx=(Array2DRowRealMatrix) arraySimMtx.add(arraySimMtx.transpose());
	}

	private Array2DRowRealMatrix initMatrix(int row, int col, double limit) {
		Array2DRowRealMatrix mtx=new Array2DRowRealMatrix(row, col);
		Calendar c=Calendar.getInstance();
		Random ran=new Random(c.getTimeInMillis());

		for (int i=0;i<row;i++) {
			for (int j=0;j<col;j++) {
				mtx.setEntry(i, j, ran.nextDouble()*limit);
			}
		}
		return mtx;
	}
}





















