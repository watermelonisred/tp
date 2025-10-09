package seedu.address.storage;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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


/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String nusnetid;
    private final String slot;
    private final String telegram;
    private final Map<String, JsonAdaptedHomework> homework;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String nusnetid,
            @JsonProperty("slot") String slot, @JsonProperty("telegram") String telegram,
                             @JsonProperty("homework") Map<String, JsonAdaptedHomework> homework) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.nusnetid = nusnetid;
        this.slot = slot;
        this.telegram = telegram;
        this.homework = homework == null ? homework : new HashMap<>();
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        nusnetid = source.getNusnetid().value;
        slot = source.getSlot().value;
        telegram = source.getTelegram().value;
        homework = new HashMap<>();
        source.getHomeworkTracker().asMap().forEach((id, hw) -> homework.put(String.valueOf(id),
                new JsonAdaptedHomework(hw))
        );
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidSlot(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (nusnetid == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Nusnetid.class.getSimpleName()));
        }
        if (!Nusnetid.isValidNusnetid(nusnetid)) {
            throw new IllegalValueException(Nusnetid.MESSAGE_CONSTRAINTS);
        }
        final Nusnetid modelNusnetid = new Nusnetid(nusnetid);

        if (slot == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Slot.class.getSimpleName()));
        }
        if (!Slot.isValidSlot(slot)) {
            throw new IllegalValueException(Slot.MESSAGE_CONSTRAINTS);
        }
        final Slot modelSlot = new Slot(slot);

        if (telegram == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Telegram.class.getSimpleName()));
        }
        if (!Telegram.isValidTelegram(telegram)) {
            throw new IllegalValueException(Telegram.MESSAGE_CONSTRAINTS);
        }
        final Telegram modelTelegram = new Telegram(telegram);

        Map<Integer, Homework> homeworkMap = new HashMap<>();
        for (Map.Entry<String, JsonAdaptedHomework> entry : homework.entrySet()) {
            homeworkMap.put(Integer.parseInt(entry.getKey()), entry.getValue().toModelType());
        }

        HomeworkTracker modelHomeworkTracker = new HomeworkTracker(homeworkMap);

        return new Person(modelName, modelPhone, modelEmail, modelNusnetid, modelTelegram, modelSlot,
                modelHomeworkTracker);
    }

}
