package blogreco.hardcorey.com.github;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/*
 * SelfAdaReco can recommend a list of blogs based on the simMtxs generated by xxxBlogSimGenerator(s)
 * different simMtx's have different weights according to the accuracy of their recommendation history
 * this sounds like the boosting method in machine learning and that's why it is called SelfAda(ptive)
 */
public class SelfAdaReco {

	private ArrayList<BlogSimMtx> simMtxList;//different sim matrixs
	private ArrayList<Double> weightsList;//weights vector
	private ArrayList<Double> probList;
	
	public SelfAdaReco(ArrayList<BlogSimMtx> simMtx) {
		this.simMtxList=simMtx;
		this.weightsList=new ArrayList<Double>(simMtx.size());
		Double w=new Double(1/simMtx.size());
		for (int i = 0; i < simMtx.size(); i++) {
			weightsList.add(i, w);
		}
		genProbList();
	}

	public SelfAdaReco(ArrayList<BlogSimMtx> simMtx, ArrayList<Double> weights) {
		this.simMtxList=simMtx;
		this.weightsList=weights;
		genProbList();
	}
/*
 * set weightList by user
 */
	public void setWeights(ArrayList<Double> weights) {
		this.weightsList=weights;
	}
	/*
	 * set weightList by user
	 */

	public void setWeights(double[] weights) {
		this.weightsList=new ArrayList<Double>(weights.length);
		for (int i = 0; i < weights.length; i++) {
			this.weightsList.add(i, new Double(weights[i]));
		}
		genProbList();
	}
/*
 * adds new similarity matrix and its weight in weightList
 * @param sim the new similarity matrix
 * @param w user-set weight of <i>sim</i> in weightList
 */
	public void addSimMtx(BlogSimMtx sim, double w) {
		simMtxList.add(sim);
		weightsList.add(new Double(w));

		double sum=0;
		for (Double d : this.weightsList) {
			sum=sum+d.doubleValue();
		}
		for (Double d : this.weightsList) {
			d=new Double(d.doubleValue()/sum);
		}
		genProbList();
	}

	/*
	 * adds new similarity matrix
	 * its weight in weightList is assigned as the average of weightList
	 * @param sim the new similarity matrix
	 */

	public void addSimMtx(BlogSimMtx sim) {
		simMtxList.add(sim);
		double sum=0;
		for (Double d : weightsList) {
			sum+=d.doubleValue();
		}
		Double weight=new Double(sum/weightsList.size());
		weightsList.add(weight);
		sum+=weight.doubleValue();
		for (Double d : this.weightsList) {
			d=new Double(d.doubleValue()/sum);
		}
		genProbList();
	}
/*
 * recommends serveral blogs to the visitor according to the blog he is visiting
 * @param ID ID of the blog which the visitor is at
 * @return 	recommended blogs and the respectiv simMtx that generated them
 */
	public Map<String, Integer> recoBlogs(String ID, int num){
		Map<String, Integer> blogList=new HashMap<String, Integer>(Math.min(simMtxList.get(0).getBlogNum(), num));
		int[] count=new int[weightsList.size()];
		BlogSimMtx temp;
		for (int i = 0; i < num; i++) {
			temp=selectSimMtx();
			int ind=simMtxList.indexOf(temp);
			String tempID=temp.getNthMostBlogs(ID, count[ind]+1);
			count[ind]++;
			//repicated
			if (!blogList.containsKey(tempID))
				blogList.put(tempID, ind);
			else
				i--;
		}
		return blogList;
	}

	/*
	 * if the <i>ind</i> simMtx's recomended blog is hit
	 * the weight of this simMtx in weightsList should boosted by 0.1
	 * @param ind the index of the successful simMtx in simMtxList
	 */
	public void feedBack(Integer ind) {
		weightsList.add(ind, weightsList.get(ind).doubleValue()*1.1);
		genProbList();
	}

	private void genProbList() {
		this.probList=new ArrayList<Double>(weightsList.size());
		double ac=0;
		for (int i = 0; i < probList.size(); i++) {
			ac=ac+weightsList.get(i).doubleValue();
			Double d=new Double(ac);
			probList.set(i, d);
		}
	}
/*
 * select one simMtx according to probList
 * 
 */
	private BlogSimMtx selectSimMtx() {
		double r=Math.random();
		int index=0;
		while (r>=probList.get(index+1)) {
			index++;
			if (index==simMtxList.size())
				break;
		}
		return simMtxList.get(index);
	}
}