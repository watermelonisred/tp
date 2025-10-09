package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class AddHomeworkCommand extends Command {

    public static final String COMMAND_WORD = "addhw";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a homework to a student or all students.\n"
            + "Parameters: i/NETID a/ASSIGNMENT_ID or all a/ASSIGNMENT_ID\n"
            + "Example: " + COMMAND_WORD + " i/E1234567 a/1\n"
            + "Example (all): " + COMMAND_WORD + " all a/1";;

    public static final String MESSAGE_SUCCESS_ONE = "Added assignment %d for %s (default incomplete).";
    public static final String MESSAGE_SUCCESS_ALL = "Added assignment %d for all students (default incomplete).";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "Student not found.";

    private final String NusnetId;  // can be "all" for all students
    private final int assignmentId;

    public AddHomeworkCommand(String NusnetId, int assignmentId) {
        this.NusnetId = NusnetId;
        this.assignmentId = assignmentId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (NusnetId.equalsIgnoreCase("all")) {
            // add homework for every student
            for (Person p : model.getFilteredPersonList()) {
                model.setPerson(p, p.withAddedHomework(assignmentId));
            }
            return new CommandResult(String.format(MESSAGE_SUCCESS_ALL, assignmentId));
        } else {
            Person target = model.getFilteredPersonList().stream()
                    .filter(p -> p.getNusnetid().value.equalsIgnoreCase(NusnetId)) // replace getNetId() with your implementation
                    .findFirst()
                    .orElse(null);

            if (target == null) {
                throw new CommandException(MESSAGE_STUDENT_NOT_FOUND);
            }

            Person updated = target.withAddedHomework(assignmentId);
            model.setPerson(target, updated);

            return new CommandResult(String.format(MESSAGE_SUCCESS_ONE, assignmentId, target.getName()));
        }
    }
}
