package reactive.webflux.userservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactive.webflux.userservice.entity.User;
@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Integer> {

}
