package it.aulab.progetto_finale_gianmarco_nacci.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
    private Integer numberOfArticles;
}
