package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.DeleteHomeworkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@link DeleteHomeworkCommand} object.
 * <p>
 * Expected formats:
 * <ul>
 *     <li>{@code i/<nusnetId> a/<assignmentId>} – delete homework for one student</li>
 *     <li>{@code all a/<assignmentId>} – delete homework for all students</li>
 * </ul>
 * Assignment IDs must be integers between 1 and 3.
 */
public class DeleteHomeworkCommandParser implements Parser<DeleteHomeworkCommand> {

    private static final Pattern DELETEHW_PATTERN = Pattern.compile(
            "^(?:i/(?<nusnetId>\\S+)\\s+a/(?<assignmentId>\\d+)|all\\s+a/(?<assignmentIdAll>\\d+))$",
            Pattern.CASE_INSENSITIVE
    );

    @Override
    public DeleteHomeworkCommand parse(String args) throws ParseException {
        final Matcher matcher = DELETEHW_PATTERN.matcher(args.trim());

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteHomeworkCommand.MESSAGE_USAGE));
        }

        String nusnetId = matcher.group("nusnetId");
        int assignmentId;

        // Case 1: delete for specific student
        if (nusnetId != null) {
            try {
                assignmentId = Integer.parseInt(matcher.group("assignmentId"));
            } catch (NumberFormatException e) {
                throw new ParseException("Assignment id must be an integer between 1 and 3.");
            }
        } else {
            // Case 2: delete for all students
            nusnetId = "all";
            try {
                assignmentId = Integer.parseInt(matcher.group("assignmentIdAll"));
            } catch (NumberFormatException e) {
                throw new ParseException("Assignment id must be an integer between 1 and 3.");
            }
        }

        // Validate assignment ID
        if (assignmentId < 1 || assignmentId > 3) {
            throw new ParseException("Assignment id must be between 1 and 3.");
        }

        return new DeleteHomeworkCommand(nusnetId, assignmentId);
    }
}
