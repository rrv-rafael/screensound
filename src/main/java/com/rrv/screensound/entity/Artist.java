package com.rrv.screensound.entity;

import com.rrv.screensound.enums.ArtistType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
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

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ArtistType type;

    @OneToMany(mappedBy = "artist")
    @Builder.Default
    private List<Song> songs = new ArrayList<>();
}
