package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Optional<Phone> phone;
    private final Optional<Email> email;
    private final Nusnetid nusnetid;
    private final Telegram telegram;
    private final Slot slot;
    private final HomeworkTracker homeworkTracker;
    private final AttendanceSheet attendanceSheet;

    /**
     * Some field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Nusnetid nusnetid, Telegram telegram, Slot slot,
                  HomeworkTracker homeworkTracker) {
        requireAllNonNull(name, nusnetid, telegram, slot, homeworkTracker);
        this.name = name;
        this.phone = Optional.ofNullable(phone);
        this.email = Optional.ofNullable(email);
        this.nusnetid = nusnetid;
        this.telegram = telegram;
        this.slot = slot;
        this.homeworkTracker = homeworkTracker;
        this.attendanceSheet = new AttendanceSheet();
    }
    /**
     * Some field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Nusnetid nusnetid, Telegram telegram, Slot slot,
                  HomeworkTracker homeworkTracker, AttendanceSheet attendanceSheet) {
        requireAllNonNull(name, nusnetid, telegram, slot, homeworkTracker);
        this.name = name;
        this.phone = Optional.ofNullable(phone);
        this.email = Optional.ofNullable(email);
        this.nusnetid = nusnetid;
        this.telegram = telegram;
        this.slot = slot;
        this.homeworkTracker = homeworkTracker;
        this.attendanceSheet = attendanceSheet;
    }
    /**
     * Some field must be present and not null.
     * Different from the other constructor as this one takes in Optional phone and email.
     */
    public Person(Name name, Optional<Phone> phone, Optional<Email> email,
                  Nusnetid nusnetid, Telegram telegram, Slot slot,
                  HomeworkTracker homeworkTracker, AttendanceSheet attendanceSheet) {
        requireAllNonNull(name, phone, email, nusnetid, telegram, slot, homeworkTracker);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.nusnetid = nusnetid;
        this.telegram = telegram;
        this.slot = slot;
        this.homeworkTracker = homeworkTracker;
        this.attendanceSheet = attendanceSheet;
    }


    public Name getName() {
        return name;
    }

    public Optional<Phone> getPhone() {
        return phone;
    }

    public Optional<Email> getEmail() {
        return email;
    }

    public Nusnetid getNusnetid() {
        return nusnetid;
    }

    public Telegram getTelegram() {
        return telegram;
    }

    public Slot getSlot() {
        return slot;
    }
    public AttendanceSheet getAttendanceSheet() {
        return attendanceSheet;
    }

    /**
     * Returns the {@link HomeworkTracker} associated with this student.
     *
     * @return the {@code HomeworkTracker} object containing this student's homework statuses
     */
    public HomeworkTracker getHomeworkTracker() {
        return homeworkTracker;
    }

    /**
     * Returns a new {@code Person} instance with a new homework added to the homework tracker.
     * <p>
     * The new homework is added with the specified assignment ID. The original {@code Person} object
     * remains unchanged because {@link HomeworkTracker} follows an immutable design.
     * </p>
     *
     * @param assignmentId the ID of the assignment to add (usually 1–3)
     * @return a new {@code Person} object with the updated {@link HomeworkTracker}
     */
    // In Person class
    public Person withAddedHomework(int assignmentId) {
        requireNonNull(homeworkTracker);

        if (homeworkTracker.contains(assignmentId)) {
            throw new IllegalArgumentException("Duplicate assignment");
        }

        HomeworkTracker updatedTracker = homeworkTracker.addHomework(assignmentId);
        return new Person(name, phone, email, nusnetid, telegram, slot, updatedTracker);
    }


    /** Returns a new Person with updated homework status for the given assignment. */
    public Person withUpdatedHomework(int assignmentId, String status) {
        HomeworkTracker updated = this.homeworkTracker.updateStatus(assignmentId, status);
        return new Person(this.name, this.phone, this.email, this.nusnetid, this.telegram, this.slot, updated);
    }


    /**
     * Returns true if both persons have the same nusnetid.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }
        return otherPerson != null
                && otherPerson.getNusnetid().equals(getNusnetid());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && nusnetid.equals(otherPerson.nusnetid)
                && telegram.equals(otherPerson.telegram)
                && slot.equals(otherPerson.slot);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, nusnetid, telegram, slot);
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this)
                .add("name", name)
                .add("NUSnetid", nusnetid)
                .add("telegram", telegram)
                .add("slot", slot);
        if (phone.isPresent()) {
            builder.add("phone", phone.get());
        }
        if (email.isPresent()) {
            builder.add("email", email.get());
        }
        return builder.toString();
    }

}
