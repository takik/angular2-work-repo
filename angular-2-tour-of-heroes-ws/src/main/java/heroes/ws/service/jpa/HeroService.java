package heroes.ws.service.jpa;

import heroes.ws.jpa.entity.Hero;
import heroes.ws.service.IHeroService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HeroService implements IHeroService {

	@Autowired
	private JpaRepository<Hero, Long> heroRepository;

	@Transactional(readOnly = true)
	public List<Hero> findAll() {
		return heroRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Hero getOne(Long id) {
		return heroRepository.findOne(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Hero saveOrUpdate(Hero hero) {
		return heroRepository.save(hero);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean delete(Hero hero) {
		heroRepository.delete(hero);
		return true;
	}

}
