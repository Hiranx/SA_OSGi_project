package foodandBeverageproducer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodandBeverageModel {
	
	private int id;
	private String PackageID;
	private String PackageName;
	private float TotalBill;
  
	private Map<Integer, Integer> foodOrders = new HashMap<>();
    
    
	 public void addFood(int foodCode, int quantity) {
	        foodOrders.put(foodCode, foodOrders.getOrDefault(foodCode, 0) + quantity);
	    }

	    public Map<Integer, Integer> getFoods() {
	        return foodOrders;
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
