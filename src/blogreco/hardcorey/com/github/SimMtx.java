package blogreco.hardcorey.com.github;

import java.util.ArrayList;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.ArrayRealVector;

public class SimMtx {

	private Array2DRowRealMatrix simMtx;
	private ArrayList<String> itemList; //user/blog list

	public SimMtx() {}
	
	public SimMtx(Array2DRowRealMatrix m,ArrayList<String> itemList) {
		this.itemList=itemList;
		this.simMtx=m;
	}
	public ArrayList<String> getItemList() {
		return itemList;
	}
	public Array2DRowRealMatrix getSimMtx() {
		return simMtx;
	}
	public void setItemList(ArrayList<String> itemList) {
		this.itemList = itemList;
	}
	public void setSimMtx(Array2DRowRealMatrix simMtx) {
		this.simMtx = simMtx;
	}
	
	/*
	 * get a list of the most similar items of the given item
	 * @param ID target item ID
	 * @parma num how many similar items will be returned
	 */
	public ArrayList<String> getMostSimItems(String ID,int num){

		int i=itemList.indexOf(ID);
		ArrayRealVector simVector=(ArrayRealVector)this.simMtx.getRowVector(i);
		int listLength=Math.min(num, simVector.getDimension()-1);
		ArrayList<String> itemListSortedBySim=new ArrayList<String>(listLength);
		int index;

		for (int j=0;j<listLength;j++) {
			index=simVector.getMaxIndex();
			itemListSortedBySim.set(j, itemList.get(index));
			simVector.setEntry(index, 0);
		}
		return itemListSortedBySim;
	}
	
	/*
	 * get the Nth most similar blog of the given blog
	 */
		public String getNthMostSimItem(String ID,int N){

			int ind=itemList.indexOf(ID);
			double[] simRow=this.simMtx.getRow(ind);
			String[] items=(String[]) this.itemList.toArray();
			int i=0;

			while (!(i==N)) {
				for (int j=i+1;j<simRow.length;j++) {
					if (simRow[i]>simRow[j]) {
						double temp=simRow[i];
						simRow[i]=simRow[j];
						simRow[j]=temp;
						String t=items[i];
						items[i]=items[j];
						items[j]=t;
					}
				}
				i++;
			}
			return items[N];
		}
		
		public double getSim(String IDa, String IDb) {
			int inda=this.itemList.indexOf(IDa);
			int indb=this.itemList.indexOf(IDb);
			return this.simMtx.getEntry(inda,indb);
		}
}