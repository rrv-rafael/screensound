package com.rrv.screensound.service;

import com.rrv.screensound.entity.Artist;
import com.rrv.screensound.entity.Song;
import com.rrv.screensound.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService {
    private final ArtistService artistService;
    private final SongRepository songRepository;

    public Song registerSong(String name, String artistName) {
        Artist artist = artistService.findByName(artistName);

        Song song = Song.builder()
                .name(name)
                .artist(artist)
                .build();

        return songRepository.save(song);
    }

    public List<Song> findAllSongs() {
        return songRepository.findAll();
    }

    public List<Song> findAllSongsByArtist(String artistName) {
        Artist artist = artistService.findByName(artistName);

        return songRepository.findAllByArtist(artist);
    }
}
