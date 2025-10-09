package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Homework;
import seedu.address.model.person.HomeworkTracker;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nusnetid;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Slot;
import seedu.address.model.person.Telegram;

import java.util.HashMap;
import java.util.Map;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_NUSNETID = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_SLOT = "00";
    private static final String INVALID_TELEGRAM = "5dfr";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_NUSNETID = BENSON.getNusnetid().toString();
    private static final String VALID_SLOT = BENSON.getSlot().toString();
    private static final String VALID_TELEGRAM = BENSON.getTelegram().toString();

    private static final HomeworkTracker VALID_HOMEWORK_TRACKER;
    static {
        VALID_HOMEWORK_TRACKER = new HomeworkTracker()
                .addHomework(1)
                .addHomework(2);
    }

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    private static Map<Integer, JsonAdaptedHomework> convertToJsonMap(HomeworkTracker tracker) {
        Map<Integer, JsonAdaptedHomework> map = new HashMap<>();
        tracker.asMap().forEach((id, hw) -> map.put(id, new JsonAdaptedHomework(hw)));
        return map;
    }

    @Test
    public void toModelType_invalidHomework_throwsIllegalValueException() {
        Map<Integer, JsonAdaptedHomework> invalidHomework = new HashMap<>();
        invalidHomework.put(0, new JsonAdaptedHomework(0, "finished")); // invalid status

        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NUSNETID,
                VALID_TELEGRAM, VALID_SLOT, invalidHomework
        );

        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NUSNETID,
                        VALID_TELEGRAM, VALID_SLOT, convertToJsonMap(VALID_HOMEWORK_TRACKER));
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_NUSNETID,
                VALID_TELEGRAM, VALID_SLOT, convertToJsonMap(VALID_HOMEWORK_TRACKER));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_NUSNETID,
                        VALID_TELEGRAM, VALID_SLOT, convertToJsonMap(VALID_HOMEWORK_TRACKER));
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_NUSNETID,
                VALID_TELEGRAM, VALID_SLOT, convertToJsonMap(VALID_HOMEWORK_TRACKER));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_NUSNETID,
                        VALID_TELEGRAM, VALID_SLOT, convertToJsonMap(VALID_HOMEWORK_TRACKER));
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_NUSNETID,
                VALID_TELEGRAM, VALID_SLOT, convertToJsonMap(VALID_HOMEWORK_TRACKER));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidNusnetid_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_NUSNETID,
                        VALID_TELEGRAM, VALID_SLOT, convertToJsonMap(VALID_HOMEWORK_TRACKER));
        String expectedMessage = Nusnetid.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullNusnetid_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_TELEGRAM, VALID_SLOT, convertToJsonMap(VALID_HOMEWORK_TRACKER));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Nusnetid.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidSlot_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NUSNETID,
                        VALID_TELEGRAM, INVALID_SLOT, convertToJsonMap(VALID_HOMEWORK_TRACKER));
        String expectedMessage = Slot.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullSlot_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NUSNETID,
                VALID_TELEGRAM, null, convertToJsonMap(VALID_HOMEWORK_TRACKER));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Slot.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTelegram_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NUSNETID,
                        INVALID_TELEGRAM, VALID_SLOT, convertToJsonMap(VALID_HOMEWORK_TRACKER));
        String expectedMessage = Telegram.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullTelegram_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NUSNETID,
                null, VALID_SLOT, convertToJsonMap(VALID_HOMEWORK_TRACKER));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Telegram.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
