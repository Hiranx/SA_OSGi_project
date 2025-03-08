package foodandBeverageproducer;

import java.util.ArrayList;
import java.util.List;

public class FoodandBeverageModel {
	
	private int id;
	private String PackageID;
	private String PackageName;
	private float TotalBill;
  
	private List<Integer> Addfoods= new ArrayList<>();
    
    
    public void addFood(int foodCode) {
    	Addfoods.add(foodCode);
    }

    public List<Integer> getFoods() {
        return Addfoods;
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPackageID() {
		return PackageID;
	}
	public void setPackageID(String packageID) {
		PackageID = packageID;
	}
	public String getPackageName() {
		return PackageName;
	}
	public void setPackageName(String packageName) {
		PackageName = packageName;
	}
	  public float getTotalBill() {
			return TotalBill;
		}

		public void setTotalBill(float totalBill) {
			TotalBill = totalBill;
		}
	
	
	

}
