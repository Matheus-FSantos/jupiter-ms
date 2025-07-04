package io.github.matheus_fsantos.jp_users.utils;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class HateoasLinkBuilder {
    public <T extends RepresentationModel<T>> T addSelfLink(T dto, UUID id) {
        Link link = Link.of(String.format("http://localhost:8080/api/users/%s", id)).withSelfRel();
        return dto.add(link);
    }
}
