package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.AttendanceStatus;

/**
 * Jackson-friendly version of {@link Attendance}.
 * <p>
 * This class is used to serialize and deserialize {@link Attendance} objects to/from JSON.
 * It stores the week number and attendance status in a format compatible with Jackson.
 * </p>
 */
public class JsonAdaptedAttendance {

    private final int week;
    private final String status;

    /**
     * Constructs a {@code JsonAdaptedAttendance} with the given week and status.
     * This constructor is used by Jackson during deserialization.
     *
     * @param week the week number (2-12)
     * @param status the attendance status ("present", "absent", or "late")
     */
    @JsonCreator
    public JsonAdaptedAttendance(@JsonProperty("week") int week,
                                 @JsonProperty("status") String status) {
        this.week = week;
        this.status = status;
    }

    /**
     * Converts an {@link Attendance} object into a {@code JsonAdaptedAttendance}.
     * This constructor is used during serialization.
     *
     * @param source the {@code Attendance} object to convert
     */
    public JsonAdaptedAttendance(Attendance source) {
        this.week = source.getWeek();
        this.status = source.getAttendanceStatus().getStatus();
    }

    public String getStatus() {
        return this.status;
    }
    public int getWeek() {
        return this.week;
    }
    /**
     * Converts this Jackson-friendly adapted attendance object into the model's {@link Attendance} object.
     *
     * @return the corresponding {@link Attendance} object
     * @throws IllegalValueException if the week or status is invalid
     */
    public Attendance toModelType() throws IllegalValueException {
        try {
            AttendanceStatus attendanceStatus = AttendanceStatus.fromString(status);
            return new Attendance(week, attendanceStatus);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(e.getMessage());
        }
    }
}
