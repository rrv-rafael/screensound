package com.rrv.screensound.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@ToString(exclude = "artist")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;
}
