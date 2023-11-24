package services;

public interface GeneralManager<O> {
    void add(O o);
    void showAll();
    O findById(int id);
    void deleteById(int id);
    void update(int id);
}
