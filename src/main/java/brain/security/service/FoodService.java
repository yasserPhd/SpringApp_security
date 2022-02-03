package brain.security.service;

import brain.security.Model.Food;
//import brain.security.entities.*;
/*import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;*/
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;

/*import sun.net.www.http.HttpClient;*/

import javax.annotation.PostConstruct;

/*import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;*/
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {

   // private static String URL = "https://raw.githubusercontent.com/yasserPhd/knn-classification/main/nutrients_csvfile.csv";
	private static String URL = "https://retoolapi.dev/AgFTsW/data";
    private List<Food> allFoods = new ArrayList<>();

    public List<Food> getAllFoods() {
        return allFoods;
    }

    /*@PostConstruct
    @Scheduled(cron = "* * * * * *")*/
    public List<Food> fetchFoodData() 
    {
    	RestTemplate  restTemplate= new RestTemplate();
		ResponseEntity<Object[]> responseEntity =
    			   restTemplate.getForEntity(URL, Object[].class);
		Object[] foodelem = responseEntity.getBody();
		List<Food> lf=new ArrayList<Food>();
		//lf=null;
		
		for(int i=0; i< foodelem.length; i++ )
		{
			Food f=new Food();
			String[] arr=foodelem[i].toString().split(",");
			    String[] idArr= arr[0].split("=");
			    f.setId(Integer.parseInt(idArr[1]));
				String[] FoodItemArr= arr[1].split("=");
				f.setFoodItem(FoodItemArr[1]);
				f.setFoodItem(FoodItemArr[1]);
				String[] per100Arr= arr[2].split("=");
				f.setPer100grams(per100Arr[1]);
				String[] FoodCategoryArr= arr[3].split("=");
				f.setFoodCategory(FoodCategoryArr[1]);
				String[] KJ_per100grArr= arr[4].split("=");
				f.setKJ_per100grams(KJ_per100grArr[1]);
				String[] Cals_per100gr= arr[5].split("=");
				f.setCals_per100grams(Cals_per100gr[1].substring(0, Cals_per100gr[1].length()-1));
				//System.out.println(f.toString());
				lf.add(f);
				
				//System.out.println(FoodItemArr[1]);
				
			}
		for(int i=0; i< lf.size(); i++ )
		{
			System.out.println("element"+i+"="+lf.get(i).toString());
		}
			//System.out.println(foodelem[i]);
			//lf.add(foodelem)
		
		//System.out.println(foodelem.toString());
    	return lf;
    	
    	
    	}
    public String getFoodCalorie(String foodname, List<Food> lf) 
    {
    	String cal="-10";
    	for(Food e: lf)
    	{
    		if(e.getFoodItem().equals(foodname))
    		{
    			cal=e.getCals_per100grams();
    		}
    	}
    	return cal;
    }
    public List<Food> getFoodElem(String foodname, List<Food> lf) 
    {
    	//Food f="-10";
    	List<Food> l=new ArrayList<Food>();
    	for(Food e: lf)
    	{
    		if(e.getFoodItem().matches(".*"+foodname+".*"))
    		{
    			 l.add(e);
    		}
    		
    	}
    	return l;
    	
    }
    
    }


