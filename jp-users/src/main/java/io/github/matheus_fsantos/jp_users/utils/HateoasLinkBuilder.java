package io.github.matheus_fsantos.jp_users.utils;

import io.github.matheus_fsantos.jp_users.controller.UserController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class HateoasLinkBuilder {
    public <T extends RepresentationModel<T>> T addSelfLink(T dto, UUID id) {
        Link link = WebMvcLinkBuilder.linkTo(UserController.class).slash(id).withSelfRel();
        return dto.add(link);
    }
}
