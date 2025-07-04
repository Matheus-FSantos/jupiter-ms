package io.github.matheus_fsantos.jp_task.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.matheus_fsantos.jp_task.dto.ResponseTaskDTO;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Relation(collectionRelation = "users", itemRelation = "user")
public class ResponseUserDTO  extends RepresentationModel<ResponseUserDTO> implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;
    private String id;
    private String email;
    private String password;
    private String role;
    private String firstName;
    private String fullName;
    private String lastName;
    private String createdAt;
    private String updatedAt;
}