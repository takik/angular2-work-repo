package heroes.ws.jpa.repository;

import heroes.ws.jpa.entity.Hero;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository<Hero, Long> {
}
