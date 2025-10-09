package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.model.person.Nusnetid;
import seedu.address.model.person.Person;
import seedu.address.model.person.Slot;

/**
 * Represents a Tutorial session which contains a Slot and an AttendanceSheet.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Group {
    private final Slot slot;
    private final AttendanceSheet attendanceSheet;
    private ArrayList<Person> students = new ArrayList<>();
    /**
     * Every field must be present and not null.
     */
    public Group(Slot slot, AttendanceSheet attendanceSheet) {
        requireNonNull(slot);
        requireNonNull(attendanceSheet);
        this.slot = slot;
        this.attendanceSheet = attendanceSheet;
    }
    /**
     * Constructs a {@code Tutorial} with an empty AttendanceSheet.
     *
     * @param slot A valid slot number.
     */
    public Group(Slot slot) {
        requireNonNull(slot);
        this.slot = slot;
        this.attendanceSheet = new AttendanceSheet();
    }
    /**
     * Get the attendance sheet of this tutorial.
     */
    public AttendanceSheet getAttendanceSheet() {
        return attendanceSheet;
    }
    /**
     * Returns the list of students in this tutorial.
     */
    public ArrayList<Person> getStudents() {
        return new ArrayList<>(students); // Return a copy for safety
    }
    /**
     * adds a student to this tutorial.
     */
    public void addStudent(Person student) {
        requireNonNull(student);
        if (hasStudent(student.getNusnetid())) {
            throw new IllegalArgumentException("Student already exists in this tutorial.");
        }
        students.add(student);
    }
    /**
     * removes a student from this tutorial.
     */
    public void removeStudent(Nusnetid nusnetid) {
        requireNonNull(nusnetid);
        students.removeIf(person -> person.getNusnetid().equals(nusnetid));
    }
    /**
     * Checks if a student with the given NUSNET ID exists in this tutorial.
     *
     * @param nusnetid The NUSNET ID to check.
     * @return true if student exists in this tutorial, false otherwise.
     */
    public boolean hasStudent(Nusnetid nusnetid) {
        requireNonNull(nusnetid);
        return students.stream()
                .anyMatch(person -> person.getNusnetid().equals(nusnetid));
    }
    /**
     * Adds a student to this tutorial.
     *
     * @param nusnetid The student to add.
     * @throws IllegalArgumentException if the student already exists in this tutorial.
     */
    public void markAttendance(int week, Nusnetid nusnetid, AttendanceStatus status) {
        requireNonNull(nusnetid);
        attendanceSheet.markAttendance(week, nusnetid, status);
    }
}
