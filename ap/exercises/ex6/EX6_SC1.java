package ap.exercises.ex6;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class EX6_SC1 {
    private Set<String> urls = new HashSet<>();

    public void extractImagesFromHtml(File htmlFile, File outputFile) throws IOException {
        if (outputFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    urls.add(line.trim());
                }
            }
        }
        Document document = Jsoup.parse(htmlFile, "UTF-8");
        Elements images = document.select("img[src]");

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile, true))) {
            for (Element image : images) {
                String src = image.absUrl("src");
                if (!urls.contains(src)) {
                    urls.add(src);
                    writer.println(src);
                }
            }
        }
    }
}


