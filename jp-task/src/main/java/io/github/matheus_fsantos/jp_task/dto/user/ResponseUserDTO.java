package io.github.matheus_fsantos.jp_task.dto.user;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDTO  implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;
    private String id;
    private String email;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private String createdAt;
    private String updatedAt;
}