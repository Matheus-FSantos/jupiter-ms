package io.github.matheus_fsantos.jp_users.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class HateoasLinkBuilder {

    @Value("${hateoas.server-url}")
    private String SERVER_URL;

    @Value("${hateoas.server-port}")
    private String SERVER_PORT;

    public <T extends RepresentationModel<T>> T addSelfLink(T dto, UUID id) {
        Link link = Link.of(String.format("%s:%s/api/users/%s", SERVER_URL, SERVER_PORT, id)).withSelfRel();
        return dto.add(link);
    }
}
