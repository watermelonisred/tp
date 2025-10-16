package seedu.address.model.person;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Represents an attendance sheet for a class.
 * It contains a list of Attendance records for each student.
 */
public class AttendanceSheet {
    private final ArrayList<Attendance> attendanceList;
    public AttendanceSheet() {
        this.attendanceList = new ArrayList<>();
    }
    public ArrayList<Attendance> getAttendanceList() {
        return attendanceList;
    }
    /**
     * Marks the attendance for a specific week.
     *
     * @param week   the week number (2 to 13)
     * @param status the attendance status ("Present", "Absent", or "Excused")
     * @throws IllegalArgumentException if {@code week} is not between 2 and 13,
     *                                  or {@code status} is invalid
     */
    public void markAttendance(int week, AttendanceStatus status) {
        Attendance attendance = new Attendance(week, status);
        for (int i = 0; i < attendanceList.size(); i++) {
            if (attendanceList.get(i).getWeek() == week) {
                attendanceList.set(i, attendance);
                return;
            }
        }
        attendanceList.add(attendance);
    }
    public Optional<Attendance> getAttendanceForWeek(int week) {
        return attendanceList.stream()
                .filter(attendance -> attendance.getWeek() == week)
                .findFirst();
    }
}
