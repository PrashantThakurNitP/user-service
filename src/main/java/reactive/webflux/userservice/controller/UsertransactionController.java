package reactive.webflux.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactive.webflux.userservice.dto.TransactionRequestDto;
import reactive.webflux.userservice.dto.TransactionResponseDto;
import reactive.webflux.userservice.entity.UserTransaction;
import reactive.webflux.userservice.service.TransactionService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user/transaction")
public class UserTransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping
	public Mono<TransactionResponseDto> createTransaction(@RequestBody Mono<TransactionRequestDto> requestDtoMono){
		return requestDtoMono.flatMap(this.transactionService::createTransaction);//return mono hence convert to flatmap
	}
	@GetMapping
	public Flux<UserTransaction>getByUserId(@RequestParam("userid") int userId){
		return this.transactionService.getByUserId(userId);
	}

}
