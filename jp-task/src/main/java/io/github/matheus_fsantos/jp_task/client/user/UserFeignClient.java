package io.github.matheus_fsantos.jp_task.client.user;

import io.github.matheus_fsantos.jp_task.dto.user.ResponseUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Component
@FeignClient(name="jp-users", path="/api/users")
public interface UserFeignClient {

    @GetMapping(path = "/{id}")
    ResponseEntity<ResponseUserDTO> findById(@PathVariable UUID id);
}
