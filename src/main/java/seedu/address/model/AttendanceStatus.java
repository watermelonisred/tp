package seedu.address.model;

/**
 * Represents the attendance status of a student in a tutorial session.
 */
public enum AttendanceStatus {
    PRESENT,
    ABSENT,
    LATE;


    /**
     * Returns the string representation of the attendance status.
     */
    @Override
    public String toString() {
        return switch (this) {
        case PRESENT -> "Present";
        case ABSENT -> "Absent";
        case LATE -> "Late";
        default -> "";
        };
    }
}
