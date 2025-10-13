package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's slot number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSlot(String)}
 */
public class Slot {


    public static final String MESSAGE_CONSTRAINTS =
            "Slot numbers should start with T or B (case-insensitive) and be followed by at least 1 digit.";
    public static final String VALIDATION_REGEX = "[TtBb]\\d{1,}";

    public final String value;

    /**
     * Constructs a {@code Slot}.
     *
     * @param slot A valid slot number.
     */
    public Slot(String slot) {
        requireNonNull(slot);
        checkArgument(isValidSlot(slot), MESSAGE_CONSTRAINTS);
        char prefix = Character.toUpperCase(slot.charAt(0));
        value = prefix + slot.substring(1);
    }

    /**
     * Returns true if a given string is a valid slot number.
     */
    public static boolean isValidSlot(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Slot)) {
            return false;
        }

        Slot otherSlot = (Slot) other;
        return value.equals(otherSlot.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
