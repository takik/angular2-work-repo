package heroes.ws.service;

import heroes.ws.jpa.entity.Hero;

import java.util.List;

public interface IHeroService {
	
	public List<Hero> findAll();
	
	public Hero getOne(Long id);
	
	public Hero saveOrUpdate(final Hero hero);
	
	public boolean delete(final Hero hero);
	
}
