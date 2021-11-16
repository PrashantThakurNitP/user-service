package reactive.webflux.userservice.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserTransaction {
	
	@Id
	private Integer id;
	private Integer userId;//foreign key
	private Integer amount;
	private LocalDateTime transactionDate;

}
