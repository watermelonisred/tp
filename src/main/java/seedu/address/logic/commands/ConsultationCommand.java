package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUSNETID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Consultation;

/**
 * Adds a consultation slot to the address book.
 */
public class ConsultationCommand extends Command {

    public static final String COMMAND_WORD = "add_consult";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a consultation slot to the address book. "
            + "Parameters: "
            + PREFIX_NUSNETID + "NUSNETID "
            + PREFIX_FROM + "CONSULTATION START TIME "
            + PREFIX_TO + "CONSULTATION END TIME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NUSNETID + "E1234567 "
            + PREFIX_FROM + "20251010 1400 "
            + PREFIX_TO + "20251010 1600";

    public static final String MESSAGE_SUCCESS = "New consultation added: %1$s";
    public static final String MESSAGE_DUPLICATE_CONSULTATION = "Consultation already exists";
    public static final String MESSAGE_STUDENT_DOES_NOT_EXIST = "Student does not exist";

    private final Consultation toAdd;

    /**
     * Creates an ConsultationCommand to add the specified {@code Consultation}
     */
    public ConsultationCommand(Consultation consultation) {
        requireNonNull(consultation);
        toAdd = consultation;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasConsultation(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONSULTATION);
        }

        if (!model.hasPerson(toAdd.getNusnetid())) {
            throw new CommandException(MESSAGE_STUDENT_DOES_NOT_EXIST);
        } else {
            model.updatePersonWithConsultation(toAdd.getNusnetid(), toAdd);
        }

        model.addConsultation(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ConsultationCommand)) {
            return false;
        }

        ConsultationCommand otherConsultationCommand = (ConsultationCommand) other;
        return toAdd.equals(otherConsultationCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
