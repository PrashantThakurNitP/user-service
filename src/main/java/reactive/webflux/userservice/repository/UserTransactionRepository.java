package reactive.webflux.userservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactive.webflux.userservice.entity.UserTransaction;
@Repository
public interface UserTransactionRepository extends ReactiveCrudRepository<UserTransaction, Integer> {

}
