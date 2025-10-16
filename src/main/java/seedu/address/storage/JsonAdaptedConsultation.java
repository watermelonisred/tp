package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Consultation;
import seedu.address.model.person.Nusnetid;

/**
 * Jackson-friendly version of {@link Consultation}.
 */
class JsonAdaptedConsultation {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Consultation's %s field is missing!";
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");

    private final String nusnetId;
    private final String from;
    private final String to;

    /**
     * Constructs a {@code JsonAdaptedConsultation} with the given consultation details.
     */
    @JsonCreator
    public JsonAdaptedConsultation(@JsonProperty("nusnetId") String nusnetId, @JsonProperty("from") String from,
                             @JsonProperty("to") String to) {
        this.nusnetId = nusnetId;
        this.from = from;
        this.to = to;
    }

    /**
     * Converts a given {@code Consultation} into this class for Jackson use.
     */
    public JsonAdaptedConsultation(Consultation source) {
        nusnetId = source.getNusnetid().value;
        from = source.getFrom().format(DATE_TIME_FORMAT);
        to = source.getTo().format(DATE_TIME_FORMAT);
    }

    /**
     * Converts this Jackson-friendly adapted consultation object into the model's {@code Consultation} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted consultation.
     */
    public Consultation toModelType() throws IllegalValueException {

        // converts nusnetId string to Nusnetid object
        if (nusnetId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Nusnetid.class.getSimpleName()));
        }
        if (!Nusnetid.isValidNusnetid(nusnetId)) {
            throw new IllegalValueException(Nusnetid.MESSAGE_CONSTRAINTS);
        }
        final Nusnetid modelNusnetid = new Nusnetid(nusnetId);

        // converts from string to LocalDateTime object
        if (from == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "start time"));
        }
        final LocalDateTime modelFrom;
        try {
            modelFrom = ParserUtil.parseDateTime(from);;
        } catch (ParseException e) {
            throw new IllegalValueException(e.getMessage());
        }

        // converts to string to LocalDateTime object
        if (to == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "end time"));
        }
        final LocalDateTime modelTo;
        try {
            modelTo = ParserUtil.parseDateTime(to);;
        } catch (ParseException e) {
            throw new IllegalValueException(e.getMessage());
        }

        // creates and returns Consultation object
        try {
            return new Consultation(modelNusnetid, modelFrom, modelTo);
        } catch (IllegalArgumentException e) { // handles error of end time being before or equal to start time
            throw new IllegalValueException(Consultation.MESSAGE_CONSTRAINTS);
        }
    }
}
