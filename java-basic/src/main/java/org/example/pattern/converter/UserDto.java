package org.example.pattern.converter;

import lombok.*;

/**
 * @author: zyh
 * @date: 2022/8/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private boolean active;
    private String email;
}
