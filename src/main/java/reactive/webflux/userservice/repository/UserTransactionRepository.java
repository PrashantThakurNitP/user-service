package reactive.webflux.userservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactive.webflux.userservice.entity.UserTransaction;

public interface UserTransactionRepository extends ReactiveCrudRepository<UserTransaction, Integer> {

}
