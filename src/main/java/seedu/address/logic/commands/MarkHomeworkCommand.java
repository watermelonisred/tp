package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.HomeworkTracker;

/**
 * Marks a student's assignment completion status.
 */
public class MarkHomeworkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a student's assignment completeness.\n"
            + "Parameters: i/<NusnetId> a/<assignmentId> status/<complete|incomplete|late>\n"
            + "Example: " + COMMAND_WORD + " i/E1234567 a/0 status/complete";

    public static final String MESSAGE_SUCCESS = "Assignment %d for %s marked %s.";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "Student not found.";
    public static final String MESSAGE_INVALID_ASSIGNMENT = "Assignment not found.";
    public static final String MESSAGE_INVALID_STATUS = "Please enter complete/incomplete/late only.";

    private final String NusnetId;
    private final int assignmentId;
    private final String status;

    public MarkHomeworkCommand(String NusnetId, int assignmentId, String status) {
        this.NusnetId = NusnetId;
        this.assignmentId = assignmentId;
        this.status = status;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> list = model.getFilteredPersonList();

        // find by NusnetId — adapt depending on your Person fields.
        Person target = list.stream()
                .filter(p -> {
                    // prefer using a dedicated NusnetId field if you have one:
                    try {
                        // If you have getNetId(), uncomment next line and remove the fallback
                        // return p.getNetId().value.equalsIgnoreCase(netId);
                    } catch (Exception e) {}
                    // fallback: match by email or name
                    return p.getNusnetid().value.equalsIgnoreCase(NusnetId);
                })
                .findFirst()
                .orElse(null);

        if (target == null) {
            throw new CommandException(MESSAGE_STUDENT_NOT_FOUND);
        }

        if (!HomeworkTracker.isValidAssignmentId(assignmentId)) {
            throw new CommandException(MESSAGE_INVALID_ASSIGNMENT);
        }

        if (!HomeworkTracker.isValidStatus(status)) {
            throw new CommandException(MESSAGE_INVALID_STATUS);
        }

        Person updated = target.withUpdatedHomework(assignmentId, status);
        model.setPerson(target, updated);

        return new CommandResult(String.format(MESSAGE_SUCCESS, assignmentId, updated.getName().fullName, status));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MarkHomeworkCommand
                && NusnetId.equals(((MarkHomeworkCommand) other).NusnetId)
                && assignmentId == ((MarkHomeworkCommand) other).assignmentId
                && status.equals(((MarkHomeworkCommand) other).status));
    }
}
