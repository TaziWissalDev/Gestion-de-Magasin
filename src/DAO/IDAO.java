package DAO;

import java.util.List;

public interface IDAO<T> {
    public void add(T o);

    public void delete(long id);

    public T getOne(long id);

    public List<T> getAll();

    public void update(T object);
}
