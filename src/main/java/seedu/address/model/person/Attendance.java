package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
/**
 * Represents a record of attendance for a specific week.
 * Guarantees: week is between 2 and 13, status is either "Present", "Absent", or "Excused".
 */
public class Attendance {

    private final int week;
    private final AttendanceStatus attendanceStatus;

    /**
     * Constructs an {@code AttendanceSheet} object with the specified week and status.
     *
     * @param week             the week number (2 to 13)
     * @param attendanceStatus the attendance status ("Present", "Absent", or "Excused")
     * @throws IllegalArgumentException if {@code week} is not between 2 and 13,
     *                                  or {@code status} is invalid
     */

    public Attendance(int week, AttendanceStatus attendanceStatus) {
        requireNonNull(attendanceStatus);
        requireNonNull(week);
        if (week < 2 || week > 13) {
            throw new IllegalArgumentException("Week must be between 2 and 13.");
        }

        this.week = week;
        this.attendanceStatus = attendanceStatus;
    }

    public int getWeek() {
        return week;
    }

    public AttendanceStatus getAttendanceStatus() {
        return attendanceStatus;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Attendance)) {
            return false;
        }

        Attendance otherAttendance = (Attendance) other;
        return week == otherAttendance.week
                && attendanceStatus == otherAttendance.attendanceStatus;
    }

    @Override
    public int hashCode() {
        return 31 * week + attendanceStatus.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Week %d: %s", week, attendanceStatus.getStatus());
    }
}

