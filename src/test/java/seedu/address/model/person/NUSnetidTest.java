package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NUSnetidTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NUSnetid(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidNUSnetid = "";
        assertThrows(IllegalArgumentException.class, () -> new NUSnetid(invalidNUSnetid));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> NUSnetid.isValidNUSnetid(null));

        // invalid addresses
        assertFalse(NUSnetid.isValidNUSnetid("")); // empty string
        assertFalse(NUSnetid.isValidNUSnetid(" ")); // spaces only
        assertFalse(NUSnetid.isValidNUSnetid("e1234567"));//starts with "e"
        assertFalse(NUSnetid.isValidNUSnetid("E123456"));//only 6 digits
        assertFalse(NUSnetid.isValidNUSnetid("E12345678"));//8 digits
        // valid addresses
        assertTrue(NUSnetid.isValidNUSnetid("E1234567"));
    }

    @Test
    public void equals() {
        NUSnetid nusnetid = new NUSnetid("E1234567");

        // same values -> returns true
        assertTrue(nusnetid.equals(new NUSnetid("E1234567")));

        // same object -> returns true
        assertTrue(nusnetid.equals(nusnetid));

        // null -> returns false
        assertFalse(nusnetid.equals(null));

        // different types -> returns false
        assertFalse(nusnetid.equals(5.0f));

        // different values -> returns false
        assertFalse(nusnetid.equals(new NUSnetid("E1234568")));
    }
}
