package manager;

import java.util.List;

public interface DBManager<T> {
    List<T> get();
}
