package com.rrv.screensound.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "song")
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

    private String name;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;
}
