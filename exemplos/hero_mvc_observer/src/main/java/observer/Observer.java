package observer;

public interface Observer<T> {
    void changed(T subject);
}
