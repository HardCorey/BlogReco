package blogreco.hardcorey.com.github;

import org.apache.commons.math.linear.Array2DRowRealMatrix;

public abstract class DocWordBlogSimGenerator {

	protected DocWordMtx docWordMtx;//a doc-word matrix, blog as row, word as column
	protected BlogSimMtx blogSimMtx;//the blog similarity matrix
//	protected Array2DRowRealMatrix simMtx;//blog-blog similarity matrix

	public abstract void genSimMtx(double[] params) ;

	public DocWordBlogSimGenerator() {
	}

	public DocWordBlogSimGenerator(DocWordMtx docWordMtx) {
		this.docWordMtx=docWordMtx;
		this.blogSimMtx=new BlogSimMtx();
		this.blogSimMtx.setBlogList(docWordMtx.getBlogList());
		int blogSum=docWordMtx.getBlogList().size();
		Array2DRowRealMatrix arrayMtx=new Array2DRowRealMatrix(blogSum, blogSum);
		this.blogSimMtx.setSimMtx(arrayMtx);
	}

	public BlogSimMtx getSimMtx() {
		return this.blogSimMtx;
	}
}