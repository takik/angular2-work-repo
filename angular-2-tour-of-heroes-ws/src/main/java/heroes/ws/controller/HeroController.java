package heroes.ws.controller;

import heroes.ws.model.Hero;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
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

	//http://websystique.com/springmvc/spring-mvc-4-restful-web-services-crud-example-resttemplate/
	
	private static final Logger _LOG = org.slf4j.LoggerFactory
			.getLogger(HeroController.class);

	private List<Hero> heroes =  new ArrayList<Hero>();

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<Hero>> getAllHeroes() {
		_LOG.info("==== in getAllHeroes ====");

		return new ResponseEntity<List<Hero>>(heroes, HttpStatus.OK);
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hero> getHero(@PathVariable("id") int id) {
		_LOG.info("Updating User {} ", id);

		return new ResponseEntity<Hero>(heroes.get(id - 1), HttpStatus.OK);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Void> createHero(@RequestBody Hero hero,
			UriComponentsBuilder ucBuilder) {
		_LOG.info("Creating User {}", hero.getName());

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/hero/getOne/{id}")
				.buildAndExpand(hero.getId()).toUri());

		heroes.add(hero);

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Hero> updateHero(@PathVariable("id") int id,
			@RequestBody Hero hero) {
		_LOG.info("Updating User {}", id);
		Hero aHero = getHero(id).getBody();
		aHero.setName(hero.getName());
		return new ResponseEntity<Hero>(aHero, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Hero> deleteUser(@PathVariable("id") int id) {
		_LOG.info("Fetching & Deleting User with id {} ", id);

		/*
		 * User user = userService.findById(id); if (user == null) {
		 * System.out.println("Unable to delete. User with id " + id +
		 * " not found"); return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		 * }
		 */

		// userService.deleteUserById(id);

		heroes.remove(id);
		return new ResponseEntity<Hero>(HttpStatus.NO_CONTENT);
	}

}
