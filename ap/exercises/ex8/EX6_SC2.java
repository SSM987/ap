package ap.exercises.ex8;
import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.*;

public class EX6_SC2 {
    private final String baseDomain;

    public EX6_SC2(String baseDomain) {
        this.baseDomain = baseDomain;
    }

    public boolean isSameDomain(String url) {
        try {
            return new URL(url).getHost().endsWith(baseDomain);
        } catch (Exception e) {
            return false;
        }
    }

    public File getSavePath(String urlStr) throws Exception {
        URL u = new URL(urlStr);
        String host = u.getHost();
        String path = u.getPath();
        String fn = path.endsWith("/") || path.isEmpty() ? "index.html" : Paths.get(path).getFileName().toString();
        String folder = "downloads/" + host + "/";
        String[] parts = path.split("/");
        for (int i=1; i<parts.length-1;i++) folder += parts[i] + "/";
        Files.createDirectories(Paths.get(folder));
        return new File(folder + fn);
    }

    public void saveHtmlList(List<String> urls, int threadCount) throws Exception {
        List<Thread> threads = new ArrayList<>();
        for (String url : urls) {
            Thread t = new Thread(() -> {
                try {
                    if (!isSameDomain(url)) return;
                    File f = getSavePath(url);
                    try (InputStream in = new URL(url).openStream()) {
                        Files.copy(in, f.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (Exception ignore) {}
            });
            if (threadCount <= 0) t.run();
            else {
                t.start();
                threads.add(t);
                if (threads.size() >= threadCount) {
                    for (Thread th:threads) th.join();
                    threads.clear();
                }
            }
        }
        for (Thread th:threads) th.join();
    }
}