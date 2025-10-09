package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Homework;

public class JsonAdaptedHomework {

    private final int id;
    private final String status;

    @JsonCreator
    public JsonAdaptedHomework(@JsonProperty("id") int id,
                               @JsonProperty("status") String status) {
        this.id = id;
        this.status = status;
    }

    public JsonAdaptedHomework(Homework source) {
        this.id = source.getId();
        this.status = source.getStatus();
    }

    public Homework toModelType() throws IllegalValueException {
        try {
            return new Homework(id, status);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(e.getMessage());
        }
    }
}
