package ap.exercises.ex8;
import java.io.*;
import java.util.*;

public class EX8 {
    public static void main(String[] args) throws Exception {
        int threadCount = readThreads();
        EX6_SC2 s2 = new EX6_SC2("znu.ac.ir");
        List<String> urls = Arrays.asList(
                "https://www.znu.ac.ir",
                "https://www.znu.ac.ir/education",
                "https://www.znu.ac.ir/research"
        );
        s2.saveHtmlList(urls, threadCount);

        List<File> htmls = new ArrayList<>();
        walkFiles(new File("downloads"), htmls, ".html", ".htm");

        EX6_SC1 s1 = new EX6_SC1();
        File linkFile = new File("image_links.txt");
        s1.extractImagesFromHtml(htmls.toArray(new File[0]), linkFile, threadCount);

        List<String> imgs = readLines(linkFile);
        EX6_SC3 s3 = new EX6_SC3();
        s3.download(imgs, 1, threadCount);
    }

    static int readThreads() throws Exception {
        Properties p = new Properties();
        try (FileInputStream fs = new FileInputStream("config2.properties")) {
            p.load(fs);
        }
        return Integer.parseInt(p.getProperty("thread-count","0"));
    }

    static void walkFiles(File f, List<File> out, String... exts) {
        File[] arr = f.listFiles();
        if (arr==null) return;
        for (File e:arr) {
            if (e.isDirectory()) walkFiles(e,out,exts);
            else {
                for (String ext:exts) if (e.getName().endsWith(ext)) { out.add(e); break; }
            }
        }
    }

    static List<String> readLines(File f) throws Exception {
        List<String> r=new ArrayList<>();
        if (!f.exists()) return r;
        try (BufferedReader br=new BufferedReader(new FileReader(f))) {
            String l;
            while ((l=br.readLine())!=null) r.add(l.trim());
        }
        return r;
    }
}