package pl.sda.christmasgifts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.christmasgifts.entity.Person;
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
