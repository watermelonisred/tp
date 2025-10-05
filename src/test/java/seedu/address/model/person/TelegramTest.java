package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Telegram(null));
    }

    @Test
    public void constructor_invalidTelegram_throwsIllegalArgumentException() {
        String invalidTelegram = "";
        assertThrows(IllegalArgumentException.class, () -> new Telegram(invalidTelegram));
    }

    @Test
    public void isValidTelegram() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Telegram.isValidTelegram(null));

        // invalid phone numbers
        assertFalse(Telegram.isValidTelegram("")); // empty string
        assertFalse(Telegram.isValidTelegram(" ")); // spaces only
        assertFalse(Telegram.isValidTelegram("91")); // no@
        assertFalse(Telegram.isValidTelegram("@")); //only@

        // valid phone numbers
        assertTrue(Telegram.isValidTelegram("@1")); // exactly 1 numbers
        assertTrue(Telegram.isValidTelegram("@124293842033123")); // long phone numbers
    }

    @Test
    public void equals() {
        Telegram telegram = new Telegram("@999");

        // same values -> returns true
        assertTrue(telegram.equals(new Telegram("@999")));

        // same object -> returns true
        assertTrue(telegram.equals(telegram));

        // null -> returns false
        assertFalse(telegram.equals(null));

        // different types -> returns false
        assertFalse(telegram.equals(5.0f));

        // different values -> returns false
        assertFalse(telegram.equals(new Telegram("@995")));
    }
}
