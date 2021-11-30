package io.github.cwireset.tcc.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Avatar {
    private String link;
}
