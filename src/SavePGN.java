package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

class SavePGN {
    private static final Path SAVE_DIRECTORY = Paths.get("../Games/");

    public static void saveGame() {
        String filename = generateFileName();
        try {
            Files.createFile(SAVE_DIRECTORY.resolve(filename));
            System.out.println("Game save to Games/");
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    private static String generateFileName() {
        return LocalDateTime.now() + ".pgn";
    }

}
