package kz.bitlab.springboot.techboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_musics")
@Getter
@Setter
public class MusicModel {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(name = "id") // column name
    private Long id;

    @Column(name = "music_name")
    private String name;

    @Column(name = "duration")
    private int duration;

    @ManyToOne
    private AuthorModel authorModel;

    @PrePersist
    public void checkForNegativeDuration() {
        if (this.duration <= 0) {
            this.duration = 180;
        }
    }
}