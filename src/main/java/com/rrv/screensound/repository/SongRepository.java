package com.rrv.screensound.repository;

import com.rrv.screensound.entity.Artist;
import com.rrv.screensound.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findAllByArtist(Artist artist);
}
