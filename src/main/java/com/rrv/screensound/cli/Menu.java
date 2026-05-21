package com.rrv.screensound.cli;

import com.rrv.screensound.entity.Artist;
import com.rrv.screensound.enums.ArtistType;
import com.rrv.screensound.repository.ArtistRepository;
import com.rrv.screensound.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class Menu implements CommandLineRunner {
    private final ArtistService artistService;
    private final ArtistRepository artistRepository;

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String MENU = """
            *** Screen Sound Music ***
            
            Digite a opção desejada:
            
            1 - Cadastrar artistas
            2 - Cadastrar músicas
            3 - Listar músicas
            4 - Buscar músicas por artistas
            5 - Pesquisar dados sobre um artista
            
            0 - Sair
            
            >>>\s""";

    @Override
    public void run(String @NonNull ... args) throws Exception {
        showMenu();
    }

    private void showMenu() {
        var option = -1;

        while (option != 0) {
            System.out.print(MENU);
            option = Integer.parseInt(SCANNER.nextLine());

            switch (option) {
                case 1 -> registerArtist();
                case 0 -> exitApplication();
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void registerArtist() {
        System.out.print("\nDigite o nome do artista: ");
        var name = SCANNER.nextLine();

        System.out.print("""
                \nDigite o tipo do artista:
                
                1 - Solo
                2 - Dupla
                3 - Banda
                
                >>>\s""");

        try {
            var type = Integer.parseInt(SCANNER.nextLine());

            if (name.isBlank() || type > 3 || type < 1) {
                System.out.println("\nInformações inválidas!\n");

                return;
            }

            Artist artist = artistRepository.save(buildArtist(name, type));

            if (artist.getId() != null) {
                System.out.println("\nArtista cadastrado com sucesso!\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nTipo inválido, por favor digite um número entre 1 e 3.\n");
        }
    }

    private Artist buildArtist(String name, int type) {
        ArtistType artistType = ArtistType.values()[type - 1];

        return Artist.builder()
                .name(name)
                .type(artistType)
                .build();
    }

    private void exitApplication() {
        System.out.println("Encerrando a aplicação...\n");
    }
}
