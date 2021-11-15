package reactive.webflux.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactive.webflux.userservice.dto.UserDto;
import reactive.webflux.userservice.util.EntityDtoUtil;
import reactive.webflux.userservice.repository.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public Flux<UserDto>all(){
		return this.userRepository.findAll()
		.map(EntityDtoUtil::toDto);
	}
	public Mono<UserDto>getUserById(final int userId){
		return this.userRepository.findById(userId)
				.map(EntityDtoUtil::toDto);
	}
	
	public Mono<UserDto>createUser(Mono<UserDto>userDtoMono){
		return userDtoMono
				.map(EntityDtoUtil::toEntity)//convert from dto to entity
				.flatMap(this.userRepository::save)//save will return mono so we use flatMap
				.map(EntityDtoUtil::toDto);
	}
	
	public Mono<UserDto>updateUser(int id, Mono<UserDto>userDtoMono){
		return this.userRepository.findById(id)//check if user present
		.flatMap(u->userDtoMono.map(EntityDtoUtil::toEntity)//u is user if present
				//convert argument to entity class
				.doOnNext(e->e.setId(id)))//set id
		//we have updated entity
		.flatMap(this.userRepository::save)
		.map(EntityDtoUtil::toDto);
	}
	
	public Mono<Void> deleteUser(int id){
		return this.userRepository.deleteById(id);
	}
}
