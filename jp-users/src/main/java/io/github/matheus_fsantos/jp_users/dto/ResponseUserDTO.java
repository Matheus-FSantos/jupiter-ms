package io.github.matheus_fsantos.jp_users.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.matheus_fsantos.jp_users.model.Role;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Relation(collectionRelation = "users", itemRelation = "user")
public class ResponseUserDTO extends RepresentationModel<ResponseUserDTO>  implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;
    private UUID id;
    private String email;
    private String password;
    private Role role;
    private String firstName;
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    LocalDateTime updatedAt;

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password.substring(0, 3) + "*".repeat(password.length() - 3);
    }

    @JsonProperty("role")
    public String getRole() {
        return role.toString().substring(0, 1).toUpperCase() + role.toString().substring(1).toLowerCase();
    }
}
