package blogreco.hardcorey.com.github;

public abstract class DocWordBlogSimGenerator {

	protected DocWordMtx docWordMtx;//a doc-word matrix
	protected BlogSimMtx simMtx;//the blog similarity matrix
//	protected Array2DRowRealMatrix simMtx;//blog-blog similarity matrix
	
	public abstract void genSimMtx(double[] params) ;
	
	public DocWordBlogSimGenerator() {
	}

	public DocWordBlogSimGenerator(DocWordMtx docWordMtx) {
		this.docWordMtx=docWordMtx;
		this.simMtx=new BlogSimMtx();
		this.simMtx.setBlogList(docWordMtx.getBlogList());
	}
	
	public BlogSimMtx getSimMtx() {
		return this.simMtx;
	}
}