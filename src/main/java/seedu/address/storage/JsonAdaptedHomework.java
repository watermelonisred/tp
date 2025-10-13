package seedu.address.storage;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Homework;

/**
 * Jackson-friendly version of {@link Homework}.
 * <p>
 * This class is used to serialize and deserialize {@link Homework} objects to/from JSON.
 * It stores the homework ID and status in a format compatible with Jackson.
 * </p>
 */
public class JsonAdaptedHomework {

    private final int id;
    private final String status;

    /**
     * Constructs a {@code JsonAdaptedHomework} with the given ID and status.
     * This constructor is used by Jackson during deserialization.
     *
     * @param id the homework ID
     * @param status the homework status
     */
    @JsonCreator
    public JsonAdaptedHomework(@JsonProperty("id") int id,
                               @JsonProperty("status") String status) {
        this.id = id;
        this.status = status;
    }

    /**
     * Converts a {@link Homework} object into a {@code JsonAdaptedHomework}.
     * This constructor is used during serialization.
     *
     * @param source the {@code Homework} object to convert
     */
    public JsonAdaptedHomework(Homework source) {
        this.id = source.getId();
        this.status = source.getStatus();
    }

    /**
     * Converts this Jackson-friendly adapted homework object into the model's {@link Homework} object.
     *
     * @return the corresponding {@link Homework} object
     * @throws IllegalValueException if the ID or status is invalid
     */
    public Homework toModelType() throws IllegalValueException {
        try {
            return new Homework(id, status);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(e.getMessage());
        }
    }
}
