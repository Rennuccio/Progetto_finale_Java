package it.aulab.progetto_finale_gianmarco_nacci.dtos;

import java.time.LocalDate;

import it.aulab.progetto_finale_gianmarco_nacci.models.Category;
import it.aulab.progetto_finale_gianmarco_nacci.models.Image;
import it.aulab.progetto_finale_gianmarco_nacci.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String subtitle;
    private String body;
    private LocalDate publishDate;
    private Boolean isAccepted; 
    private User user;
    private Category category;
    private Image image;
}
