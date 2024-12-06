package oncall.model;

import oncall.util.CustomException;
import oncall.util.ErrorMessage;

public class Worker {
    private final String name;

    public Worker(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name.length() > 5) {
            throw new CustomException(ErrorMessage.INVALID_INPUT);
        }
    }
}
