package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Map;

import seedu.address.model.person.Nusnetid;

/**
 * Represents an Attendance Sheet for a tutorial session.
 * It maintains a mapping of weeks to students' attendance status.
 * Guarantees: week is between 3 and 13 (inclusive), attendanceStatus is valid
 */
public class AttendanceSheet {
    private final int min = 3;
    private final int max = 13;
    private final Map<Integer, Map<Nusnetid, AttendanceStatus>> attendanceMap;

    public AttendanceSheet() {
        this.attendanceMap = new HashMap<>();
    }

    /**
     * Marks the attendance of a student for a specific week.
     * @param week The week number (between 3 and 13 inclusive).
     * @param nusnetid The Nusnetid of the student.
     * @param attendanceStatus The attendance status of the student.
     * @throws IllegalArgumentException if the week is not between 3 and 13 inclusive.
     * @throws NullPointerException if nusnetid or attendanceStatus is null.
     */
    public void markAttendance(int week, Nusnetid nusnetid, AttendanceStatus attendanceStatus) {
        requireNonNull(nusnetid);
        requireNonNull(attendanceStatus);
        if (!isValidWeek(week)) {
            throw new IllegalArgumentException("Invalid Week");
        }
        // computeIfAbsent is used to initialize the inner map if it does **not** exist
        this.attendanceMap.computeIfAbsent(week, k -> new HashMap<>()).put(nusnetid, attendanceStatus);

    }

    public boolean isValidWeek(int week) {
        return week >= min && week <= max;
    }


}
