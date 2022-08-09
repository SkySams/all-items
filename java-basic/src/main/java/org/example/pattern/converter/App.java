package org.example.pattern.converter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: zyh
 * @date: 2022/8/9
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        Converter<UserDto, User> userConverter = new UserConverter();
        UserDto dtoUser = new UserDto("John", "Doe", true, "whatever[at]wherever.com");
        User user = userConverter.convertFromDto(dtoUser);
        System.out.println(user);
    }

}
