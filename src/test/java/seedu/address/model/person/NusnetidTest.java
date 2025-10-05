package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NusnetidTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Nusnetid(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidNUSnetid = "";
        assertThrows(IllegalArgumentException.class, () -> new Nusnetid(invalidNUSnetid));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Nusnetid.isValidNusnetid(null));

        // invalid addresses
        assertFalse(Nusnetid.isValidNusnetid("")); // empty string
        assertFalse(Nusnetid.isValidNusnetid(" ")); // spaces only
        assertFalse(Nusnetid.isValidNusnetid("e1234567"));//starts with "e"
        assertFalse(Nusnetid.isValidNusnetid("E123456"));//only 6 digits
        assertFalse(Nusnetid.isValidNusnetid("E12345678"));//8 digits
        // valid addresses
        assertTrue(Nusnetid.isValidNusnetid("E1234567"));
    }

    @Test
    public void equals() {
        Nusnetid nusnetid = new Nusnetid("E1234567");

        // same values -> returns true
        assertTrue(nusnetid.equals(new Nusnetid("E1234567")));

        // same object -> returns true
        assertTrue(nusnetid.equals(nusnetid));

        // null -> returns false
        assertFalse(nusnetid.equals(null));

        // different types -> returns false
        assertFalse(nusnetid.equals(5.0f));

        // different values -> returns false
        assertFalse(nusnetid.equals(new Nusnetid("E1234568")));
    }
}
