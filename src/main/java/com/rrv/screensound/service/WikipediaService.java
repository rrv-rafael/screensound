package com.rrv.screensound.service;

import com.rrv.screensound.dto.WikipediaSearchResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
public class WikipediaService {
    private final RestClient wikipediaRestClient;

    public WikipediaService(@Qualifier("wikipediaRestClient") RestClient wikipediaRestClient) {
        this.wikipediaRestClient = wikipediaRestClient;
    }

    public String getArtistDescription(String artistName) {
        try {
            WikipediaSearchResponse wikipediaSearchResponse = wikipediaRestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/w/rest.php/v1/search/page")
                            .queryParam("q", artistName)
                            .queryParam("limit", 1)
                            .build())
                    .retrieve()
                    .body(WikipediaSearchResponse.class);

            if (wikipediaSearchResponse == null || wikipediaSearchResponse.pages().isEmpty()) {
                return "Não foram encontrados dados do artista informado.";
            }

            String excerpt = wikipediaSearchResponse.pages().getFirst().excerpt();

            return removeHtmlTags(excerpt);
        } catch (RestClientException exception) {
            return "Não foi possível buscar a descrição do artista no momento.";
        }
    }

    private String removeHtmlTags(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        return text
                .replaceAll("<[^>]*>", "")
                .replaceAll("\\s+", " ")
                .trim();
    }
}
