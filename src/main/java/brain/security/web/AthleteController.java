package brain.security.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
//import org.opendevup.doa.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import brain.security.Dao.AthleteRepository;
import brain.security.Dao.CoachRepository;
import brain.security.Dao.PaimentRepository;
import brain.security.entities.Athlete;
import brain.security.entities.Coach;
import brain.security.entities.Paiment;
import brain.security.service.Helper;
import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;




//import org.opendevup.entities.Etudiant;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping(value="/athlete")
public class AthleteController {

	
	@Autowired
	private AthleteRepository athleteRepository;
	@Autowired
	private CoachRepository coachRepository;
	@Autowired
	private Helper h ;
	@Autowired
	private PaimentRepository pr;
	/*@Autowired
	private PaimentRepository pr;*/
	
	/*@GetMapping("/getFoods")
    public String home(Model model) {
        List<Food> allStats = foodService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }*/


	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String Index(Model model, @RequestParam(name="page",defaultValue="0") int page ) {  
		Page<Athlete> etds = athleteRepository.findAll( PageRequest.of(page, 10, Sort.unsorted())); 
 
		int NbPage = etds.getTotalPages();
		int[] pages = new int[NbPage];
		for (int i = 0; i < NbPage; i++) {
			pages[i] = i;
		}
		model.addAttribute("pages",pages);
		model.addAttribute("pageEtudiants",etds);
		model.addAttribute("pageCourante",page);
		model.addAttribute("ListEtudiants",etds);
		return "etudiant";
	}
	
	

	@RequestMapping(value="/FormAdd",method=RequestMethod.GET)
	public String addClient( ) {  
		
		return "FormAdd";
	}
	@RequestMapping(value="/FormAddCo",method=RequestMethod.GET)
	public String addCoach( ) {  
		
		return "coachAdd";
	}	
	
	@RequestMapping(value="/addFood",method=RequestMethod.GET)
	public String addFood( ) {  
		
		return "food";
	}
	
	@RequestMapping(value="/example",method=RequestMethod.GET)
	public String example(Model model, @RequestParam(name="page",defaultValue="0") int page ) {  
		Page<Athlete> etds = athleteRepository.findAll( PageRequest.of(page, 10, Sort.unsorted())); 
 
		int NbPage = etds.getTotalPages();
		int[] pages = new int[NbPage];
		for (int i = 0; i < NbPage; i++) {
			pages[i] = i;
		}
		model.addAttribute("pages",pages);
		model.addAttribute("pageEtudiants",etds);
		model.addAttribute("pageCourante",page);
		model.addAttribute("ListEtudiants",etds);
		return "example";
	}
	@RequestMapping(value="/listCoach",method=RequestMethod.GET)
	public String listCoach(Model model, @RequestParam(name="page",defaultValue="0") int page ) {  
		Page<Coach> etds = coachRepository.findAll( PageRequest.of(page, 10, Sort.unsorted())); 
 
		int NbPage = etds.getTotalPages();
		int[] pages = new int[NbPage];
		for (int i = 0; i < NbPage; i++) {
			pages[i] = i;
		}
		model.addAttribute("pages",pages);
		model.addAttribute("pageCoaches",etds);
		model.addAttribute("pageCourante",page);
		model.addAttribute("ListCoaches",etds);
		return "listCoach";
	}
	
	
	
	
	@RequestMapping(value="/getPay",method=RequestMethod.GET)
	public String getPaiment(Model model, @RequestParam(name="page",defaultValue="0") int page ) {  
		Page<Paiment> etds = pr.findAll( PageRequest.of(page, 10, Sort.unsorted())); 

		int NbPage = etds.getTotalPages();
		int[] pages = new int[NbPage];
		for (int i = 0; i < NbPage; i++) {
			pages[i] = i;
		}
		model.addAttribute("pages",pages);
		model.addAttribute("pagePaiment",etds);
		model.addAttribute("pageCourante",page);
		model.addAttribute("ListPaiments",etds);
		return "Paiments";
	}
	@RequestMapping(value="/contact",method=RequestMethod.GET)
	public String contact( ) {  
		
		return "contact";
	}
	@RequestMapping(value="/searchName",method=RequestMethod.GET)
	public String chercherNom(Model model, @RequestParam(name="page",defaultValue="0") int page, 
			@RequestParam(name="motSrch",defaultValue="") String motCle) {  
		Page<Athlete> etds = athleteRepository.findByMc("%"+motCle+"%", PageRequest.of(page, 5, Sort.unsorted())); 
		model.addAttribute("pageEtudiants",etds);
		return "example";
	}
	@RequestMapping(value="/searchNames",method=RequestMethod.GET)
	public String chercherNoms(Model model) {
		
		List<String> ls = null ;
		
		ls =  athleteRepository.getAllNames();
		model.addAttribute("listAthletes",ls);
		/*List<Athlete> etds = athleteRepository.findAll();
		List<String> ls = null ;
		for(int i=0; i < etds.size(); i++)
		 ls.add(etds.get(i).getNom());
		model.addAttribute("listAthletes",ls);*/
		return "payAdd";
	}
	
	
	@RequestMapping(value="/addCoach",method=RequestMethod.GET)
	public String ajouterCoach(Model model, @RequestParam(name="nom",defaultValue="") String nom,@RequestParam(name="date",defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,@RequestParam(name="email",defaultValue="") String email,  @RequestParam(name="photo",defaultValue="") String photo) throws ParseException { 
		DateFormat d = new SimpleDateFormat("yyyy-MM-dd"); 
	
		Coach e = new Coach(nom,date,email, photo);
		coachRepository.save(e) ;
		System.out.println("coach added successfully"+e);
		 List<Coach> etds = coachRepository.findAll() ;
		 model.addAttribute("pageCoach",etds);
		/* model.addAttribute("date_ent", sd.toGMTString());*/
		// return "redirect:index";
		 return "redirect:listCoach";
	}
	
	@RequestMapping(value="/addClient",method=RequestMethod.GET)
	public String ajouterClient(Model model, /*@RequestParam(name="id") long id,*/ @RequestParam(name="nom",defaultValue="") String nom,@RequestParam(name="date",defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,@RequestParam(name="email",defaultValue="") String email,  @RequestParam(name="photo",defaultValue="") String photo) throws ParseException { 
		DateFormat d = new SimpleDateFormat("yyyy-MM-dd"); 
	
		Athlete e = new Athlete(nom,date,email, photo);
		athleteRepository.save(e) ;
		 List<Athlete> etds = athleteRepository.findAll() ;
		 model.addAttribute("pageEtudiants",etds);
		/* model.addAttribute("date_ent", sd.toGMTString());*/
		// return "redirect:index";
		 return "redirect:example";
	}

	@RequestMapping(value="/addPay",method=RequestMethod.POST)
	public String ajouterPaiment(Model model, /*@RequestParam(name="id") long id,*/ @RequestParam(name="nom",defaultValue="") String nom, @RequestParam(name="Montant",defaultValue="0") int Montant, @RequestParam(name="date_debut", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") Date sd, @RequestParam(name="date_f", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") Date df) throws ParseException
	{ 
		DateFormat d = new SimpleDateFormat("dd/MM/yyyy"); 
	   /* String d1= d.format(sd);
	    String d2=d.format(df);
	    sd=d.parse(d1);
	    df=d.parse(d2);*/
		Paiment e = new Paiment(nom,Montant, sd,df);
		pr.save(e) ;
		 List<Paiment> lp =pr.findAll() ;
		 model.addAttribute("ListPaiment",lp);
		/* model.addAttribute("date_ent", sd.toGMTString());*/
		// return "redirect:index";
		 return "etudiant";
	}
	@RequestMapping(value="/GetSalary",method=RequestMethod.GET)
	public String GetSalary(Model model) {
		double s=0 ;
		List<Paiment> la = pr.findAll() ;
		for(int i=0; i < la.size(); i++)
		{
	if(la.get(i).getDate_debut().getMonth() == Calendar.getInstance().get(Calendar.MONTH))
		s = s + la.get(i).getMontant();
		}
		double salaire=s/3;
		 model.addAttribute("salaire",salaire);
		 System.out.println(salaire);
		 return "salaire";
	}
	
	@RequestMapping(value="/GetBenifice",method=RequestMethod.GET)
	public String GetBenifice(Model model) {
		double s=0 ;
		List<Paiment> la = pr.findAll() ;
		for(int i=0; i < la.size(); i++)
		{
			if(la.get(i).getDate_debut().getMonth() == Calendar.getInstance().get(Calendar.MONTH))
		s = s + la.get(i).getMontant();
		}
		double benifice=s-s/3;
		 
		 model.addAttribute("benifice",benifice);
		 System.out.println("benifice"+ benifice);
		 return "benifice";
	}
	/*@RequestMapping(value="/GetImg",method=RequestMethod.GET)
	URL getImage( @RequestParam(name="photo")  byte[] photo) throws MalformedURLException
	{
		String urlString = new String(photo);
		URL yourUrl = new URL(urlString);
		System.out.println("this is url"+ yourUrl);
		
		return yourUrl;
	}*/
	/*@RequestMapping(value="/form",method=RequestMethod.GET)
	public String formEtudiant(Model model) {
		model.addAttribute("etudiant", new Etudiant());
		return "formEtudiant";
	}*/
	
	@RequestMapping(value="/supp")
	public String supp(Long id) {
		athleteRepository.deleteById(id);
		return "redirect:example";
	}
	@RequestMapping(value="/suppCoach")
	public String suppCoach(Long id) {
		coachRepository.deleteById(id);
		return "redirect:example";
	}
	@RequestMapping(value="/suppPay")
	public String suppPay(Long id) {
		pr.deleteById(id);
		return "redirect:index";
	}
	@RequestMapping(value="/editer")
	public String editer(Long id, Model model) {
		Athlete athlete = athleteRepository.getOne(id);
		model.addAttribute("athlete", athlete);
		return "editEtudiant";
		//return "redirect:index";
	}
	
	@RequestMapping(value="/UpdateAthlete",method=RequestMethod.POST)
	public String update( Athlete et, BindingResult bindingResult, @RequestParam(name="picture")MultipartFile file) throws Exception 
	{
		if (bindingResult.hasErrors()) {
			return "editEtudiant";
		}
		else
		{
		if (file.isEmpty()) {
			java.util.Optional<Athlete> a = athleteRepository.findById(et.getId());
			et.setPhoto(athleteRepository.getOne(et.getId()).getPhoto());
					
			//System.out.println("la photo =" +a.getPhoto());
			
			//System.out.println(et.getPhoto());
			athleteRepository.save(et);
			
			
		}
			//file.transferTo(new File(""+ et.getId()));
			if (!(file.isEmpty())) { et.setPhoto(file.getOriginalFilename());
			athleteRepository.save(et);
			
			}
			return "redirect:index";
			
		}
		
	}

	
		 /*return restTemplate.getForObject(ApiUrl, String.class);
		 }*/
	
	/*@RequestMapping(value="/form",method=RequestMethod.GET)
	public String formEtudiant(Model model) {
		model.addAttribute("etudiant", new Etudiant());
		return "formEtudiant";
	}
	
	@RequestMapping(value="/SaveEtudiant",method=RequestMethod.POST)
	public String save(@Valid Etudiant et, BindingResult bindingResult, @RequestParam(name="picture")MultipartFile file) throws Exception {
		if (bindingResult.hasErrors()) {
			System.out.println("denndcndjk");
			return "formEtudiant";
		}
		if (!(file.isEmpty())) { et.setPhoto(file.getOriginalFilename());}
		etudiantRepository.save(et);
		
		if (!(file.isEmpty())) {
			et.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(imageDir + et.getId()));
		}
		
		
		
		
		return "redirect:index";
	}
	
	@RequestMapping(value="/getPhoto", produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(Long id) throws Exception {
		File f = new File(imageDir + id);
		return IOUtils.toByteArray(new FileInputStream(f));
		
	}
	
	@RequestMapping(value="/supp")
	public String supp(Long id) {
		etudiantRepository.delete(id);
		return "redirect:index";
	}
	
	@RequestMapping(value="/editer")
	public String editer(Long id, Model model) {
		Etudiant etudiant = etudiantRepository.getOne(id);
		model.addAttribute("etudiant", etudiant);
		return "editEtudiant";
	}
	
	
	@RequestMapping(value="/UpdateEtudiant",method=RequestMethod.POST)
	public String update(@Valid Etudiant et, BindingResult bindingResult, @RequestParam(name="picture")MultipartFile file) throws Exception {
		if (bindingResult.hasErrors()) {
			return "editEtudiant";
		}
		if (!(file.isEmpty())) { et.setPhoto(file.getOriginalFilename());}
		etudiantRepository.save(et);
		
		if (!(file.isEmpty())) {
			et.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(imageDir + et.getId()));
		}
			
		return "redirect:index";
	}
	
	*/
}
