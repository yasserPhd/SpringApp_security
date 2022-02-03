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
/*@CrossOrigin(origins ="http://localhost:4200")*/
@RepositoryRestResource
public interface AthleteRepository extends JpaRepository<Athlete, Long> 
{
	/*public List<String> findByNom();
	@Query("select e.nom from Athlete e")*/
	@Query("select e.nom from Athlete e")
	List<String> getAllNames();
	public Optional<Athlete> findById(Long id);
	public List<Athlete> findByNom(String n, Pageable p);
	@RestResource(path= "/KeywordPage")
	@Query("select e from Athlete e where e.nom like %:mc%")
	public Page<Athlete> findByMc(@Param("mc")String mc, Pageable pageable);
}
