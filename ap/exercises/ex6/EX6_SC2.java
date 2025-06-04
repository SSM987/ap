package ap.exercises.ex6;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EX6_SC2 {

    private String baseDomain;

    public EX6_SC2(String baseDomain) {
        this.baseDomain = baseDomain;
    }

    public boolean isSameDomain(String url) throws Exception {
        URL url1 = new URL(url);
        return url1.getHost().endsWith(baseDomain);
    }

    public File getSavePath(String url2) throws Exception {
        URL url3 = new URL(url2);
        String h = url3.getHost();
        String p = url3.getPath();

        String[] pathPart = p.split("/");
        String fileName = pathPart[pathPart.length - 1];
        if (fileName.isEmpty()) {
            fileName = "index.html";
        }

        String folderPath = "downloads/";
        if (!h.equals(baseDomain)) {
            folderPath = folderPath + "_" + h.replace("." + baseDomain, "") + "/";
        }

        for (int i = 1; i < pathPart.length - 1; i++)
            folderPath = folderPath + pathPart[i] + "/";

        Files.createDirectories(Paths.get(folderPath));
        return new File(folderPath + fileName);
    }

    public void saveHtml(String urlStr) throws Exception {

            if (!isSameDomain(urlStr)) return;

        File file = getSavePath(urlStr);
        URL url = new URL(urlStr);

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                PrintWriter writer = new PrintWriter(new FileWriter(file))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.println(line);
            }
        }
    }
}

