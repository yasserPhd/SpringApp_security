package brain.security.web;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import brain.security.*;
import brain.security.Dao.AthleteRepository;
import brain.security.Dao.CoachRepository;
import brain.security.Dao.PaimentRepository;
import brain.security.Dao.UserRepository;
import brain.security.Model.Food;
import brain.security.Model.UserSecurity;
import brain.security.Model.UserStatus;
import brain.security.entities.Athlete;
import brain.security.entities.Coach;
import brain.security.entities.Paiment;
import brain.security.service.FoodService;
import brain.security.service.ReadFood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

//@Controller
@RestController
@RequestMapping("api")
public class AthleteCtrl {
	@Autowired
	private AthleteRepository athleteRepository;
	@Autowired
	private PaimentRepository paimentRepository;
	@Autowired
	private CoachRepository coachRepository;
	@Autowired
	private  UserRepository userRepository;
	RestTemplate restTemplate = new RestTemplate();
	/*@Value("${tasks3}")
	private String ApiUrl2;*/
	
	//private ReadFood rf;
	
	@GetMapping("/home")
    public String home() {
        return ("<h1>Welcome</h1>");
    }
	
	@GetMapping("/user")
    public String user() {
        return ("<h1>Welcome user</h1>");
    }
	
	@GetMapping(produces = "application/json")
	@RequestMapping({ "/validateLogin" })
	public UserStatus validateLogin() {
		return new UserStatus("User successfully authenticated");
	}
	@RequestMapping(value="/getAllClientNames", method = RequestMethod.GET)
	// @RequestMapping(value = "/getProduct/{id}",method=RequestMethod.GET)
	public List<String> getCoachesNames()   {
		List<String> ls= athleteRepository.getAllNames();
		return ls;
	}
	@RequestMapping(value="/getAllPayersNames", method = RequestMethod.GET)
	// @RequestMapping(value = "/getProduct/{id}",method=RequestMethod.GET)
	public List<String> getClientsNames()   {
		List<String> ls= paimentRepository.getAllNames();
		return ls;
	}
	
	@RequestMapping(value="/getAllPayments", method = RequestMethod.GET)
	// @RequestMapping(value = "/getProduct/{id}",method=RequestMethod.GET)
	public List<Paiment> getPayments()   {
		List<Paiment> ls= paimentRepository.findAll();
		return ls;
	}
	@PostMapping("/setPayment")
	List<Paiment> newPayment(@RequestBody Paiment p) {
		paimentRepository.save(p);
		List<Paiment> ls= paimentRepository.findAll();
	    return ls;
	  }
	
	@PostMapping("/addUser")
	UserSecurity newUser(@RequestBody UserSecurity u) {
		
	    return userRepository.save(u);
		
			
	  }
	
	@GetMapping(value="/getAllUsers")
	// @RequestMapping(value = "/getProduct/{id}",method=RequestMethod.GET)
	public List<UserSecurity> getAllUsers()   {
		List<UserSecurity> ls= userRepository.findAll();
		return ls;
	}
	@DeleteMapping("/deleteUser/{id}")
	void deleteUser(@PathVariable int id)
	{
		  userRepository.deleteById(id);
	}
	
	@PostMapping("/addAthlete")
	Athlete newAthlete(@RequestBody Athlete a) {
		;
		//List<Athlete> ls= athleteRepository.findAll();
	    return athleteRepository.save(a);
	  }
	@RequestMapping(value="/GetBenifice",method=RequestMethod.GET)
	public double GetBenifice() {
		double s=0 ;
		List<Paiment> la = paimentRepository.findAll() ;
		for(int i=0; i < la.size(); i++)
		{
			if(la.get(i).getDate_debut().getMonth() == Calendar.getInstance().get(Calendar.MONTH))
		     s = s + la.get(i).getMontant();
		}
		//double benifice=s;
		 
		
		// System.out.println("benifice"+ benifice);
		 return s;
	}
	
	/*@Value("${tasks3}")
	private String ApiUrl;*/
	/*
	@RequestMapping(value="/getProduct", method = RequestMethod.GET)
	// @RequestMapping(value = "/getProduct/{id}",method=RequestMethod.GET)
	public List<Food> getProduct()   {
		 
		
		
		
		   // final String uri = "https://chomp.p.rapidapi.com/request.php?ingredient=milk";
		//final String uri = "https://jsonplaceholder.typicode.com/todos/1";
		//final String uri = "https://world.openfoodfacts.org/api/v0/product/737628064502.json";
		    final String uri = ApiUrl2;
		    final String uri2 = "https://esha-nutrition-demo.azurewebsites.net/openapi3.json";
		    RestTemplate restTemplate = new RestTemplate();
		    Food[] forNow = restTemplate.getForObject(uri2, Food[].class);
		    //Collection<Food> readValues =  new ObjectMapper().reader().forNow);
		    	
		   List<Food> foodList= Arrays.asList(forNow);
		    System.out.println(foodList.get(0).getCountry());
		    return foodList;
		}*/
	
	
	@RequestMapping(value="/getAllFood", method = RequestMethod.GET)
	//@RequestMapping("/getNutFact")
	/*public String getNutFact(@PathVariable("name") String name)    {*/
	//@RequestParam(name = "name") String name
	public List<Food>  getAllFood() throws JsonProcessingException, IOException, InterruptedException  {
	
		FoodService f= new FoodService();
		List<Food> foods=f.fetchFoodData();
		return foods;
	}
	
	
	@PutMapping("/athletes/update/{athleteId}")
	Athlete editAthlete(
			@RequestBody Athlete a, @PathVariable long athleteId )
	{
		//Coach c=coachRepository.findById(coachId);
		Athlete UpdA=athleteRepository.findById(athleteId).get();
		 if (UpdA == null) {
	            return null;
	        }
		 UpdA.setNom(a.getNom());
		 UpdA.setDate_naissance(a.getDate_naissance());
		 UpdA.setPhoto(a.getPhoto());
		 UpdA.setCoach(a.getCoach());
		//System.out.println("coach attributed is"+c.getNom());
		
		
		//System.out.println("now coach"+c.getNom()+"has"+c.getAthletes().size()+"athletes");
		
		return athleteRepository.saveAndFlush(UpdA);
	}	
	@GetMapping("/search/athletes/{athleteId}")
	Athlete getAthlete(
			 @PathVariable long athleteId )
	{
		//Coach c=coachRepository.findById(coachId);
		Athlete a=athleteRepository.findById(athleteId).get();
		
		//System.out.println("coach attributed is"+c.getNom());
		
		
		//System.out.println("now coach"+c.getNom()+"has"+c.getAthletes().size()+"athletes");
		
		return a;
	}	
	
	
	@GetMapping("/search/coaches")
	List<Coach> getCoaches( )
	{
		//Coach c=coachRepository.findById(coachId);
		List<Coach> lc=coachRepository.findAll();
		
		//System.out.println("coach attributed is"+c.getNom());
		
		
		//System.out.println("now coach"+c.getNom()+"has"+c.getAthletes().size()+"athletes");
		
		return lc;
	}	
	@PutMapping("/athletes/{athleteId}")
	Athlete addCoachToAthlete(
			@RequestBody Coach c, @PathVariable long athleteId )
	{
		//Coach c=coachRepository.findById(coachId);
		Athlete a=athleteRepository.findById(athleteId).get();
		//System.out.println("coach attributed is"+c.getNom());
		
		a.setCoach(c);
		//System.out.println("now coach"+c.getNom()+"has"+c.getAthletes().size()+"athletes");
		
		return athleteRepository.save(a);
	}	
	
	@PutMapping("/coaches/addAthlete/{coachId}")
	Athlete addAthleteToCoach(
	  @PathVariable long coachId,@RequestBody Athlete a )
	{
		Coach c=coachRepository.findById(coachId).get();
		//Athlete a=athleteRepository.findById(athleteId);
		//System.out.println("coach attributed is"+c.getNom());
		
		a.setCoach(c);
		//System.out.println("now coach"+c.getNom()+"has"+c.getAthletes().size()+"athletes");
		
		return athleteRepository.save(a);
	}
	
	@GetMapping("/{coachId}/EditAthlete/{athleteId}")
	Athlete EditAthleteToCoach(
	  @PathVariable long coachId,@PathVariable long athleteId )
	{
		Coach c=coachRepository.findById(coachId).get();
		Athlete a=athleteRepository.findById(athleteId).get();
		//System.out.println("coach attributed is"+c.getNom());
		
		a.setCoach(c);
		//System.out.println("now coach"+c.getNom()+"has"+c.getAthletes().size()+"athletes");
		
		return athleteRepository.save(a);
	}	
	
	
	@RequestMapping(value="/getNutFact", method = RequestMethod.GET)
	//@RequestMapping("/getNutFact")
	/*public String getNutFact(@PathVariable("name") String name)    {*/
	//@RequestParam(name = "name") String name
	public List<Food>  getNutFact(@RequestParam(name = "name") String name) throws JsonProcessingException, IOException, InterruptedException  {
		/*RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object[]> response =
				  restTemplate.getForEntity("https://retoolapi.dev/AgFTsW/data",Object[].class);
		Object[] foods = response.getBody();*/
		
	
		
		
		
		/*List<JSONObject> jo= new ArrayList<JSONObject>();
		for(int i=0; i< foods.length;i++)
		{
			JSONObject obj = new JSONObject();
			obj.put("food", foods[i]);
			jo.add(obj);
		}*/
		
		//List<Object>searchList=Arrays.asList(foods);
		//System.out.println(searchList
		
		/*ObjectMapper mapper = new ObjectMapper();
		 Arrays.stream(foods)
				  .map(object -> mapper.convertValue(object, JSON.class))
				  .map(User::getName)
				  .collect(Collectors.toList());*/
		FoodService f= new FoodService();
		List<Food> foods=f.fetchFoodData();
		List<Food> rst=f.getFoodElem(name,foods );
	        //for(int i=0; i< rst.size(); i++)
		//System.out.println("elem"+rst.get(i).getFoodItem());
		return rst ;
		//System.out.println(foods.);
		/*HttpResponse<String> response = Unirest.get("https://catfact.ninja/fact")
				.asString();*/
		/*ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity("https://catfact.ninja/fact", Object[].class);
		Object[] objects = responseEntity.getBody();
		ObjectMapper mapper = new ObjectMapper();*/
		
		//System.out.println("objects are here"+objects);
		//RestTemplate restTemplate = new RestTemplate();

		// Send request with GET method and default Headers.
		//String result = restTemplate.getForObject(ApiUrl2, String.class);
		
		//String foodlist = restTemplate.getForObject("https://retoolapi.dev/AgFTsW/data", String.class);
		/*ResponseEntity<Food[]> response =
				  restTemplate.getForEntity("https://retoolapi.dev/AgFTsW/data",Food[].class);
				Food[] foods = response.getBody();
				System.out.println("name is"+foods[0].getId());*/
		        /*ResponseEntity<List<Food>> rateResponse =
		        restTemplate.exchange("https://retoolapi.dev/AgFTsW/data",
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Food>>() {
		            });
		        List<Food> foods = rateResponse.getBody();*/
		/*ResponseEntity<List<Rate>> rateResponse =
		        restTemplate.exchange("https://bitpay.com/api/rates",
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Rate>>() {
		            });
		List<Rate> rates = rateResponse.getBody();*/
		
		/*ResponseEntity<String> rateResponse =
		        restTemplate.exchange("https://retoolapi.dev/AgFTsW/data",
		                    HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
		            });*/
		//String foods = rateResponse.getBody();
		//List<Food> foods = restTemplate.getForObject("https://retoolapi.dev/AgFTsW/data", List<Food>.class);
		//ObjectMapper mapper = new ObjectMapper();
		//List<Object> participantJsonList = mapper.readValue(foods, new TypeReference<List<Food>>) ;
		
		
		/*Food[] forNow = restTemplate.getForObject("https://retoolapi.dev/AgFTsW/data", Food[].class);
		List<Food> foodList= Arrays.asList(forNow);*/
		//List<Food> forNow = restTemplate.getForObject(ApiUrl2, Food.class);
		/*for(int i=0; i <5 ; i++)
		//System.out.println("calories in "+foodlist[i].getFoodItem()+"="+   foodlist[i].getCals_per100grams());
		System.out.println("name is"+name);*/
		//Food[] p = restTemplate.getForObject("https://retoolapi.dev/aZ06hr/data", Food[].class);
		
		//Food[] p = restTemplate.getForObject("https://raw.githubusercontent.com/yasserPhd/knn-classification/main/nutrients_csvfile.csv", Food[].class);
		//List<Food> foodList= Arrays.asList(p);
		/*String URL = "https://raw.githubusercontent.com/yasserPhd/knn-classification/main/nutrients_csvfile.csv";
		BufferedReader br = new BufferedReader(new FileReader(URL));
		String line=null;
		String[] personCsv=null;
		while ((line = br.readLine()) != null) {  
		       // split on comma(',')  
		        personCsv = line.split(",");
		//List<Food> foodList= ReadFood.readBooksFromCSV(URL);
		System.out.println("food is"+personCsv[0]);*/
		
		
		//List<Food> foodList=fs.fetchFoodData();
      // String cal=f.getFoodCalorie(name, foodList);
	//	Food f=fs.getFoodElem(name, foodList);
	}
	
}
	
	

