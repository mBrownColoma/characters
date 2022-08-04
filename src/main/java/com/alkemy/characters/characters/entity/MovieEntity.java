package com.alkemy.characters.characters.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movie")
@Getter
@Setter

public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String image;


    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate creationDate;

    @Column(name = "associated_characters")
    private String associatedCharacters;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "gender_id", insertable = false, updatable = false)
    private GenderEntity gender;

    @Column(name = "gender_id", nullable = false)
    private Long genderId;
    private Long classification;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })

    @JoinTable(
            name = "characters_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private Set<GenderEntity> characters = new HashSet<>();

//    @Override
//    public boolean equals(Object obj){
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        final MovieEntity other = (MovieEntity) obj;
//        return other.id == this.id;
//    }
}