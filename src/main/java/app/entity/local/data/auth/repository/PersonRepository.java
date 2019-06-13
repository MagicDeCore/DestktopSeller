package app.entity.local.data.auth.repository;

import app.entity.local.data.auth.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
    Person findByName(String name);
    Person findByLast(boolean last);
    List<Person> findAllByLast(boolean last);

}
