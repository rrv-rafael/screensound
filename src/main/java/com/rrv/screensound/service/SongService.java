package com.rrv.screensound.service;

import com.rrv.screensound.entity.Artist;
import com.rrv.screensound.entity.Song;
import com.rrv.screensound.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongService {
    private final ArtistService artistService;
    private final SongRepository songRepository;

    public Song registerSong(String name, String nameArtist) {
        Artist artist = artistService.findByName(nameArtist);

        Song song = Song.builder()
                .name(name)
                .artist(artist)
                .build();

        return songRepository.save(song);
    }
}
