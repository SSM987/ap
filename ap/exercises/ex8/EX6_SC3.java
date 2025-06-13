package ap.exercises.ex8;
import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.*;

public class EX6_SC3 {
    public void download(List<String> urls, int delaySec, int threadCount) throws Exception {
        List<Thread> threads = new ArrayList<>();
        for (String url : urls) {
            Thread t = new Thread(() -> {
                try {
                    String folder="others";
                    if (url.endsWith(".jpg") || url.endsWith(".png") || url.endsWith(".gif")) folder="images";
                    else if (url.endsWith(".mp3")||url.endsWith(".wav")) folder="songs";
                    else if (url.endsWith(".html")||url.endsWith(".htm")) folder="html";
                    Path dir = Paths.get(folder);
                    Files.createDirectories(dir);
                    URL u = new URL(url);
                    String fn = Paths.get(u.getPath()).getFileName().toString();
                    try (InputStream in = u.openStream()) {
                        Files.copy(in, dir.resolve(fn), StandardCopyOption.REPLACE_EXISTING);
                    }
                    Thread.sleep(delaySec*1000L);
                } catch (Exception ignore) {}
            });
            if (threadCount<=0) t.run();
            else {
                t.start();
                threads.add(t);
                if (threads.size()>=threadCount) {
                    for (Thread th:threads) th.join();
                    threads.clear();
                }
            }
        }
        for (Thread th:threads) th.join();
    }
}