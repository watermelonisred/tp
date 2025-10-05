package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SlotTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Slot(null));
    }

    @Test
    public void constructor_invalidSlot_throwsIllegalArgumentException() {
        String invalidSlot = "";
        assertThrows(IllegalArgumentException.class, () -> new Slot(invalidSlot));
    }

    @Test
    public void isValidSlot() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Slot.isValidSlot(null));

        // invalid phone numbers
        assertFalse(Slot.isValidSlot("")); // empty string
        assertFalse(Slot.isValidSlot(" ")); // spaces only
        assertFalse(Slot.isValidSlot("91")); //no T
        assertFalse(Slot.isValidSlot("T"));//onlyT
        // valid phone numbers
        assertTrue(Slot.isValidSlot("T1")); // exactly 1 numbers
        assertTrue(Slot.isValidSlot("T124293842033123")); // long phone numbers
    }

    @Test
    public void equals() {
        Slot slot = new Slot("T999");

        // same values -> returns true
        assertTrue(slot.equals(new Slot("T999")));

        // same object -> returns true
        assertTrue(slot.equals(slot));

        // null -> returns false
        assertFalse(slot.equals(null));

        // different types -> returns false
        assertFalse(slot.equals(5.0f));

        // different values -> returns false
        assertFalse(slot.equals(new Slot("T995")));
    }

}