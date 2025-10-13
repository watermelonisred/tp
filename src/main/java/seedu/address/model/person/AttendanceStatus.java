package seedu.address.model.person;

/**
 * Represents the attendance status of a person.
 * It can be either "Present", "Absent", or "Late".
 */
public enum AttendanceStatus {
    PRESENT("Present"),
    ABSENT("Absent"),
    LATE("late");

    private final String status;

    AttendanceStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    /**
     * Converts a string to the corresponding AttendanceStatus enum.
     *
     * @param status the attendance status as a string
     * @return the corresponding AttendanceStatus enum
     * @throws IllegalArgumentException if the status is invalid
     */
    public static AttendanceStatus fromString(String status) {
        for (AttendanceStatus attendanceStatus : AttendanceStatus.values()) {
            if (attendanceStatus.getStatus().equalsIgnoreCase(status)) {
                return attendanceStatus;
            }
        }
        throw new IllegalArgumentException("Invalid attendance status: " + status);
    }
}
