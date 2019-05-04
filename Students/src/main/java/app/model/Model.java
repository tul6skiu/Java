package app.model;

import java.util.List;
import java.util.Optional;

public interface Model<T> {

    Optional<T> find(Integer id);
    void save(T model);
    void update(T model);
    void delet(Integer id);

    List<T> findAll();
}
