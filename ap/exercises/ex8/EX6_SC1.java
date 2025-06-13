package ap.exercises.ex8;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.*;

public class EX6_SC1 {
    private final Set<String> existingUrls = new HashSet<>();

    public void extractImagesFromHtml(File[] htmlFiles, File outputFile, int threadCount) throws Exception {
        if (outputFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    existingUrls.add(line.trim());
                }
            }
        }
        List<String> newUrls = Collections.synchronizedList(new ArrayList<>());
        List<Thread> threads = new ArrayList<>();

        for (File file : htmlFiles) {
            Thread t = new Thread(() -> {
                try {
                    String baseUrl = guessBaseUrl(file);
                    Document doc = Jsoup.parse(file, "UTF-8", baseUrl);
                    Elements imgs = doc.select("img[src]");
                    for (Element img : imgs) {
                        String src = img.absUrl("src");
                        if (!src.isEmpty() && !existingUrls.contains(src)) {
                            synchronized (existingUrls) {
                                if (!existingUrls.contains(src)) {
                                    existingUrls.add(src);
                                    newUrls.add(src);
                                }
                            }
                        }
                    }
                } catch (Exception ignore) {}
            });

            if (threadCount <= 0) t.run();
            else {
                t.start();
                threads.add(t);
                if (threads.size() >= threadCount) {
                    for (Thread th : threads) th.join();
                    threads.clear();
                }
            }
        }
        for (Thread th : threads) th.join();

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile, true))) {
            for (String u : newUrls) writer.println(u);
        }
    }

    private String guessBaseUrl(File file) {
        try {
            String p = file.getCanonicalPath().replace("\\", "/");
            int i = p.indexOf("downloads/");
            if (i<0) return "https://www.znu.ac.ir/";
            String rel = p.substring(i + "downloads/".length());
            String[] parts = rel.split("/");
            String host = parts[0];
            StringBuilder sb = new StringBuilder("https://" + host);
            for (int j = 1; j < parts.length - 1; j++) sb.append("/").append(parts[j]);
            sb.append("/");
            return sb.toString();
        } catch (IOException e) {
            return "https://www.znu.ac.ir/";
        }
    }
}