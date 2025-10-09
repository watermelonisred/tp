package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class Homework {

    private final int id;
    private final String status;

    public static final String STATUS_COMPLETE = "complete";
    public static final String STATUS_INCOMPLETE = "incomplete";
    public static final String STATUS_LATE = "late";

    public Homework(int id, String status) {
        requireNonNull(status);
        // homework id is form 1 to 3 (assume only 3 homeworks)
        if (id < 1 || id > 3) {
            throw new IllegalArgumentException("Assignment ID must be between 0 and 2.");
        }
        if (!isValidStatus(status)) {
            throw new IllegalArgumentException("Please enter complete/incomplete/late only.");
        }
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Homework withStatus(String newStatus) {
        return new Homework(this.id, newStatus);
    }

    public static boolean isValidStatus(String status) {
        return status.equals(STATUS_COMPLETE)
                || status.equals(STATUS_INCOMPLETE)
                || status.equals(STATUS_LATE);
    }

    @Override
    public String toString() {
        return String.format("Assignment %d: %s", id, status);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Homework)) return false;
        Homework o = (Homework) other;
        return id == o.id && status.equals(o.status);
    }

    @Override
    public int hashCode() {
        return id * 31 + status.hashCode();
    }
}
