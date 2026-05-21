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
            
            1 - Cadastrar artistas
            2 - Cadastrar músicas
            3 - Listar músicas
            4 - Buscar músicas por artistas
            5 - Pesquisar dados sobre um artista
            
            0 - Sair
            
            Digite a opção desejada:
            """;

    @Override
    public void run(String @NonNull ... args) throws Exception {
        showMenu();
    }

    private void showMenu() {
        var option = -1;

        while (option != 0) {
            System.out.println(MENU);
            option = Integer.parseInt(SCANNER.nextLine());

            switch (option) {
                case 1 -> registerArtist();
                case 0 -> exitApplication();
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private void registerArtist() {
        System.out.print("Digite o nome do artista: ");
        var name = SCANNER.nextLine();

        System.out.print("""
                Selecione o tipo:
                1 - Solo
                2 - Dupla
                3 - Banda
                """);
        var type = Integer.parseInt(SCANNER.nextLine());

        ArtistType artistType = ArtistType.values()[type - 1];

        Artist artist = Artist.builder()
                .name(name)
                .type(artistType)
                .build();

        artistRepository.save(artist);
    }

    private void exitApplication() {
        System.out.println("Encerrando a aplicação...");
    }
}
