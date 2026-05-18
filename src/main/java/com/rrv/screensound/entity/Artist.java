package com.rrv.screensound.entity;

import com.rrv.screensound.enums.ArtistType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artist")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@ToString(exclude = "songs")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private ArtistType type;

    @OneToMany(mappedBy = "artist")
    @Builder.Default
    private List<Song> songs = new ArrayList<>();
}
