package com.rrv.screensound.service;

import com.rrv.screensound.entity.Artist;
import com.rrv.screensound.enums.ArtistType;
import com.rrv.screensound.repository.ArtistRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;

    public Artist registerArtist(String name, int type) {
        ArtistType artistType = ArtistType.values()[type - 1];

        Artist artist = Artist.builder()
                .name(name)
                .type(artistType)
                .build();

        return artistRepository.save(artist);
    }

    public Artist findByName(String name) {
        return artistRepository.findArtistByNameIgnoreCase(name)
                .orElseThrow(() -> new EntityNotFoundException("\nArtista não encontrado!"));
    }
}
