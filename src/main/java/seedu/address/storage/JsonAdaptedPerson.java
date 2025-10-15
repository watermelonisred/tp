package seedu.address.storage;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.event.Consultation;
import seedu.address.model.person.Email;
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
    private final String consultation_start;
    private final String consultation_end;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String nusnetid,
            @JsonProperty("slot") String slot, @JsonProperty("telegram") String telegram,
            @JsonProperty("consultation_start") String consultation_start,
            @JsonProperty("consultation_end") String consultation_end) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.nusnetid = nusnetid;
        this.slot = slot;
        this.telegram = telegram;
        this.consultation_start = consultation_start;
        this.consultation_end = consultation_end;
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
        consultation_start = source.getConsultation().map(Consultation::getFromInString).orElse("");
        consultation_end = source.getConsultation().map(Consultation::getToInString).orElse("");
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

        if (consultation_start == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    "consultation start time"));
        } else if (consultation_end == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    "consultation end time"));
        }

        if (consultation_start.isEmpty() || consultation_end.isEmpty()) {
            return new Person(modelName, modelPhone, modelEmail, modelNusnetid, modelTelegram, modelSlot);
        }

        LocalDateTime from = ParserUtil.parseDateTime(consultation_start);
        LocalDateTime to = ParserUtil.parseDateTime(consultation_end);

        if (!Consultation.isValidConsultation(from, to)) {
            throw new IllegalValueException(Consultation.MESSAGE_CONSTRAINTS);
        }

        final Consultation modelConsultation = new Consultation(modelNusnetid, from, to);

        return new Person(modelName, modelPhone, modelEmail, modelNusnetid,
                modelTelegram, modelSlot, modelConsultation);
    }

}
