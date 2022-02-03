package brain.security.Model;

/*import org.opendevup.service.ArrayList;
import org.opendevup.service.Book;
import org.opendevup.service.BufferedReader;
import org.opendevup.service.Food;
import org.opendevup.service.IOException;
import org.opendevup.service.List;
import org.opendevup.service.Path;*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
/*@JsonPropertyOrder({ "Food", "Measure", "Grams", "Calories","Protein"
	,"Fat","Sat_Fat","Fiber","Carbs","Category"})*/
public class Food {
	
	private int id;
	private String FoodItem;
	private String per100grams;
	private String FoodCategory;
	private String KJ_per100grams;
	private String Cals_per100grams;
	public Food() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Food(int id, String foodItem, String per100grams, String foodCategory, String kJ_per100grams,
			String cals_per100grams) {
		super();
		this.id = id;
		FoodItem = foodItem;
		this.per100grams = per100grams;
		FoodCategory = foodCategory;
		KJ_per100grams = kJ_per100grams;
		Cals_per100grams = cals_per100grams;
	}
	@Override
	public String toString() {
		return "Food [id=" + id + ", FoodItem=" + FoodItem + ", per100grams=" + per100grams + ", FoodCategory="
				+ FoodCategory + ", KJ_per100grams=" + KJ_per100grams + ", Cals_per100grams=" + Cals_per100grams + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFoodItem() {
		return FoodItem;
	}
	public void setFoodItem(String foodItem) {
		FoodItem = foodItem;
	}
	public String getPer100grams() {
		return per100grams;
	}
	public void setPer100grams(String per100grams) {
		this.per100grams = per100grams;
	}
	public String getFoodCategory() {
		return FoodCategory;
	}
	public void setFoodCategory(String foodCategory) {
		FoodCategory = foodCategory;
	}
	public String getKJ_per100grams() {
		return KJ_per100grams;
	}
	public void setKJ_per100grams(String kJ_per100grams) {
		KJ_per100grams = kJ_per100grams;
	}
	public String getCals_per100grams() {
		return Cals_per100grams;
	}
	public void setCals_per100grams(String cals_per100grams) {
		Cals_per100grams = cals_per100grams;
	}
	
	
	
	
	
	
   
}
