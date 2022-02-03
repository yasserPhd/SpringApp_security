package brain.security.service;

import java.util.Optional;

import brain.security.entities.Athlete;
public interface AthleteService {
	Optional<Athlete> findById(Long id);
}
