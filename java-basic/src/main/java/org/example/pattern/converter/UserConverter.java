package org.example.pattern.converter;

import java.util.function.Function;

/**
 * @author: zyh
 * @date: 2022/8/9
 */
public class UserConverter extends Converter<UserDto,User>{

    public UserConverter() {
        super(UserConverter::convertToEntity, UserConverter::convertToDto);
    }

    public UserConverter(Function<UserDto, User> fromDto, Function<User, UserDto> fromEntity) {
        super(fromDto, fromEntity);
    }

    private static UserDto convertToDto(User user) {
        return new UserDto(user.getFirstName(), user.getLastName(), user.isActive(), user.getUserId());
    }

    private static User convertToEntity(UserDto dto) {
        return new User(dto.getFirstName(), dto.getLastName(), dto.isActive(), dto.getEmail());
    }
}
