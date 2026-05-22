package com.rrv.screensound.repository;

import com.rrv.screensound.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
