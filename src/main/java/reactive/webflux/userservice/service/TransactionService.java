package reactive.webflux.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactive.webflux.userservice.dto.TransactionRequestDto;
import reactive.webflux.userservice.dto.TransactionResponseDto;
import reactive.webflux.userservice.dto.TransactionStatus;
import reactive.webflux.userservice.repository.UserRepository;
import reactive.webflux.userservice.repository.UserTransactionRepository;
import reactive.webflux.userservice.util.EntityDtoUtil;
import reactor.core.publisher.Mono;

@Service
public class TransactionService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserTransactionRepository transactionRepository;
	
	public Mono<TransactionResponseDto>createTransaction(final TransactionRequestDto requestDto){
		return this.userRepository.updateUserBalance(requestDto.getUserId(), requestDto.getAmount())
		//try to update if succesful then return true otherwise false
		//it is wrapper type , we use filter to convert to primitive type
			.filter(Boolean::booleanValue)
			//if true below 3 line will be executed else last line will be executed
			.map(b->EntityDtoUtil.toEntity(requestDto))
			.flatMap(this.transactionRepository::save)
			.map(ut->EntityDtoUtil.toDto(requestDto,TransactionStatus.APPROVED))
			.defaultIfEmpty(EntityDtoUtil.toDto(requestDto,TransactionStatus.DECLINED));
	}

}
