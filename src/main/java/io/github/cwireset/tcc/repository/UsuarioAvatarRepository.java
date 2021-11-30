package io.github.cwireset.tcc.repository;

import io.github.cwireset.tcc.domain.Avatar;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "avatar", url = "https://some-random-api.ml/img")
public interface UsuarioAvatarRepository {

    @GetMapping("/dog")
    Avatar retornarAvatar();
}
