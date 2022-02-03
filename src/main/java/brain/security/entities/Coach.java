package brain.security.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Coach {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/*@Column(name="coach_id")*/
	private long id ;
	private String nom;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_naissance;
	private String email;
	@JsonIgnore
    @OneToMany(mappedBy = "coach",cascade = CascadeType.ALL)
	/*@JoinColumn(name="coach_id", referencedColumnName="id")*/
    private Set<Athlete> athletes;
	/*private int Montant ;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_debut;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_fin;*/
	@Lob
	private String photo;
	/*public Date getDate_debut() {
		return date_debut;
	}*/

	/*public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}*/

	
	public Coach() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setAthletes(Set<Athlete> athletes) {
		this.athletes = athletes;
	}
	
	/*public void AddAthlete( Athlete a)
	{
		this.athletes.add(a);
		System.out.println("athlete"+a.getNom()+"was added");
	}*/
	public Set<Athlete> getAthletes() {
		return athletes;
	}
	public Coach(String nom, Date date_naissance, String email, String photo) {
		super();
		this.nom = nom;
		this.date_naissance = date_naissance;
		this.email = email;
		/*this.Montant = Montant;
		this.date_debut = date_debut;
		this.date_fin = date_fin;*/
		this.photo = photo;
	}

	/*public Date getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}*/

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	/*public void setMontant(int Montant) {
		this.Montant = Montant;
	}
	public int getMontant() {
		return Montant;
	}*/
	public  String getPhoto()  {
		//String encoded = Base64.encodeBase64String(photo) ;
		/*String urlString = new String(photo);
		URL yourUrl = new URL(urlString);
		System.out.println("this is url"+ yourUrl);*/
		//String photoStr = Base64.encodeBase64String(photo);
		return  photo;
	}
	/*public String generateBase64Image()
	{
	    return Base64.getEncoder().encodeToString(this.photo);
	}*/

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	

}
