package ex7;
import java.util.List;

interface StorageStrategy<T> {
    List<T> load() throws Exception;
    void save(List<T> items) throws Exception;
}

