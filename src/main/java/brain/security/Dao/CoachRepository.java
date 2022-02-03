package brain.security.Dao;
import brain.security.entities.*;
import java.util.List;
import java.util.Optional;

//import org.opendevup.entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins ="*")
@RepositoryRestResource(path= "coaches")
public interface CoachRepository extends JpaRepository<Coach, Long> 
{
	/*public List<String> findByNom();
	@Query("select e.nom from Athlete e")*/
	  @RestResource(path= "getAllCoachesNames")
	@Query("select e.nom from Athlete e")
	List<String> getAllNames();
	public Optional<Coach> findById(Long id);
	public List<Coach> findByNom(String n, Pageable p); 
	@RestResource(path= "CoachKeywordPage")
	@Query("select e from Coach e where e.nom like %:mc%")
	public Page<Coach> findByMc(@Param("mc")String mc, Pageable pageable);
}

