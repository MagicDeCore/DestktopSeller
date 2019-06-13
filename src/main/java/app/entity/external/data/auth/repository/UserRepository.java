package app.entity.external.data.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import app.entity.external.data.auth.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByName(String name);
}
