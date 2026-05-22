package com.rrv.screensound.repository;

import com.rrv.screensound.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findArtistByNameIgnoreCase(String name);
}
