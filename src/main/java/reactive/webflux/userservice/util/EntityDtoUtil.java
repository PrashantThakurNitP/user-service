package reactive.webflux.userservice.util;

import org.springframework.beans.BeanUtils;

import reactive.webflux.userservice.dto.UserDto;
import reactive.webflux.userservice.entity.User;

public class EntityDtoUtil {
	
	public static UserDto toDto(User user) {
		UserDto dto =new UserDto();
		BeanUtils.copyProperties(user, dto);
		return dto;
	}
	
	public static User toEntity(UserDto dto) {
		User user = new User();
		BeanUtils.copyProperties(dto,user);
		return user;
	}

}
