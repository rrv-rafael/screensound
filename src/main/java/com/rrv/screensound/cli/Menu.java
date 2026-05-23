package com.rrv.screensound.cli;

import com.rrv.screensound.entity.Artist;
import com.rrv.screensound.entity.Song;
import com.rrv.screensound.service.ArtistService;
import com.rrv.screensound.service.SongService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class Menu implements CommandLineRunner {
    private final ArtistService artistService;
    private final SongService songService;

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
        int option;

        do {
            System.out.print(MENU);
            option = Integer.parseInt(SCANNER.nextLine());

            switch (option) {
                case 1 -> collectArtistData();
                case 2 -> collectSongData();
                case 3 -> listAllSongs();
                case 0 -> System.out.println("Encerrando a aplicação...\n");
                default -> System.out.println("Opção inválida!");
            }
        } while (option != 0);
    }

    private void collectArtistData() {
        String registerAnother = "";

        do {
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
                    System.out.println("\nInformações inválidas!");

                    System.out.print("\nDeseja tentar novamente? [s/n]: ");
                    registerAnother = SCANNER.nextLine();
                } else {
                    Artist artist = artistService.registerArtist(name, type);

                    if (artist.getId() != null) {
                        System.out.println("\nArtista cadastrado com sucesso!");

                        System.out.print("\nCadastrar outro artista? [s/n]: ");
                        registerAnother = SCANNER.nextLine();
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("\nTipo inválido, por favor digite um número entre 1 e 3.");

                System.out.print("\nDeseja tentar novamente? [s/n]: ");
                registerAnother = SCANNER.nextLine();
            }
        } while (registerAnother.equalsIgnoreCase("s"));
    }

    private void collectSongData() {
        System.out.print("Digite o nome da música: ");
        var name = SCANNER.nextLine();

        System.out.print("Digite o nome completo do artista desta música: ");
        var nameArtist = SCANNER.nextLine();

        if (name.isBlank() || nameArtist.isBlank()) {
            System.out.println("\nInformações inválidas!\n");

            return;
        }

        try {
            Song song = songService.registerSong(name, nameArtist);

            if (song.getId() != null) {
                System.out.println("\nMúsica cadastrada com sucesso!\n");
            }
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void listAllSongs() {
        List<Song> songs = songService.findAllSongs();

        if (songs.isEmpty()) {
            System.out.println("\nNenhuma música cadastrada.\n");

            return;
        }

        songs.forEach(s -> System.out.println("Música: " + s.getName() + " - Artista: " + s.getArtist().getName()));
    }
}
