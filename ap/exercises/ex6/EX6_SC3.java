package ap.exercises.ex6;
import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.List;

public class EX6_SC3 {

    public void download(List<String> url, int delay) throws Exception {
        for (String url1 : url) {
            if (url1.endsWith(".jpg") || url1.endsWith(".png") || url1.endsWith(".gif")) {
                downloadFile(url1, "images");
            } else if (url1.endsWith(".mp3") || url1.endsWith(".wav")) {
                downloadFile(url1, "songs");
            } else if (url1.endsWith(".html") || url1.endsWith(".htm")) {
                downloadFile(url1, "html");
            }

            Thread.sleep(delay * 1000L);
        }
    }

    public void downloadFile(String url2, String folder) throws IOException {
        URL url3 = new URL(url2);
        String fileName = Paths.get(url3.getPath()).getFileName().toString();
        Path dir = Paths.get(folder);
        Files.createDirectories(dir);
        Path file = dir.resolve(fileName);
        try (InputStream inputStream = url3.openStream()) {
            Files.copy(inputStream, file, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
