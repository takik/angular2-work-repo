package heroes.ws.controller;

import heroes.ws.jpa.entity.Hero;
import heroes.ws.service.IHeroService;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
public class HeroController {

	// http://websystique.com/springmvc/spring-mvc-4-restful-web-services-crud-example-resttemplate/

	@Autowired
	private IHeroService heroService;

	private static final Logger _LOG = org.slf4j.LoggerFactory
			.getLogger(HeroController.class);

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<Hero>> getAllHeroes() {

		List<Hero> heroes = heroService.findAll();

		if (heroes.isEmpty()) {
			return new ResponseEntity<List<Hero>>(HttpStatus.NO_CONTENT);// You
																			// many
																			// decide
																			// to
																			// return
																			// HttpStatus.NOT_FOUND
		}

		_LOG.info("getAllHeroes returns {} heroes", heroes.size());

		return new ResponseEntity<List<Hero>>(heroes, HttpStatus.OK);
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hero> getHero(@PathVariable("id") Long id) {
		Hero hero = heroService.getOne(id);
		if (hero == null) {
			_LOG.info("Hero with id {} not found", id);
			return new ResponseEntity<Hero>(HttpStatus.NOT_FOUND);
		}
		_LOG.info("Select a Hero {} ", id);

		return new ResponseEntity<Hero>(hero, HttpStatus.OK);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Void> createHero(@RequestBody Hero hero,
			UriComponentsBuilder ucBuilder) {
		_LOG.info("Creating User {}", hero.getName());

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/hero/getOne/{id}")
				.buildAndExpand(hero.getId()).toUri());

		heroService.saveOrUpdate(hero);

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Hero> updateHero(@PathVariable("id") Long id,
			@RequestBody Hero hero) {
		Hero aHero = heroService.getOne(id);

		if (aHero == null) {
			_LOG.info("Hero with id {} not found", id);
			return new ResponseEntity<Hero>(HttpStatus.NOT_FOUND);
		}
		aHero.setName(hero.getName());
		heroService.saveOrUpdate(aHero);
		return new ResponseEntity<Hero>(aHero, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Hero> deleteUser(@PathVariable("id") Long id) {
		_LOG.info("Fetching & Deleting User with id {} ", id);

		Hero hero = heroService.getOne(id);
		if (hero == null) {
			_LOG.error("Unable to delete. User with id {} not found",id);
			return new ResponseEntity<Hero>(HttpStatus.NOT_FOUND);
		}

		// userService.deleteUserById(id);
		heroService.delete(hero);

		return new ResponseEntity<Hero>(HttpStatus.NO_CONTENT);
	}

}
