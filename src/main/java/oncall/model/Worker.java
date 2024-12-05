package oncall.model;

public class Worker {
    private final String name;

    public Worker(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException();
        }
    }
}
