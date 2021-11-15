package reactive.webflux.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import reactive.webflux.userservice.dto.UserDto;
import reactive.webflux.userservice.service.UserService;
import reactor.core.publisher.Mono;

@RequestMapping
public class UserController {
	@Autowired
	private UserService service;
	
	@GetMapping("{id}")
	public Mono<ResponseEntity<UserDto>>getUseryId(@PathVariable int id){
		return this.service.getUserById(id)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
		
	}
	@PostMapping
	public Mono<UserDto> createUser(@RequestBody Mono<UserDto> userDtoMono){
		return this.service.createUser(userDtoMono);
	}
	@PutMapping("{id}")
	public Mono<ResponseEntity<UserDto>> updateUser(@PathVariable int id, @RequestBody Mono<UserDto> userDtoMono){
		return this.service.updateUser(id, userDtoMono)
				.map(ResponseEntity::ok)//only if you are getting response call ok
				//else
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	@DeleteMapping("{id}")
	public Mono<Void> deleteUser(@PathVariable int id){
		return this.service.deleteUser(id);
	}
	
}