package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddHomeworkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@link AddHomeworkCommand} object.
 * <p>
 * The expected format of the input is either:
 * <ul>
 *     <li>{@code i/<nusnetId> a/<assignmentId>} to add homework to a specific student</li>
 *     <li>{@code all a/<assignmentId>} to add homework to all students</li>
 * </ul>
 * Assignment IDs must be integers between 1 and 3.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * addhw i/E1234567 a/1    // adds assignment 1 to student E1234567
 * addhw all a/2           // adds assignment 2 to all students
 * }</pre>
 */
public class AddHomeworkCommandParser implements Parser<AddHomeworkCommand> {

    private static final Pattern ADDHW_PATTERN = Pattern.compile(
            "^(?:i/(?<nusnetId>\\S+)\\s+a/(?<assignmentId>\\d+)|all\\s+a/(?<assignmentIdAll>\\d+))$",
            Pattern.CASE_INSENSITIVE
    );

    /**
     * Parses the given {@code String} of arguments in the context of the AddHomeworkCommand
     * and returns an {@link AddHomeworkCommand} object for execution.
     *
     * @param args the input arguments string
     * @return an {@link AddHomeworkCommand} representing the parsed input
     * @throws ParseException if the input does not conform to the expected format,
     *                        or if the assignment ID is invalid
     */
    @Override
    public AddHomeworkCommand parse(String args) throws ParseException {
        final Matcher matcher = ADDHW_PATTERN.matcher(args.trim());

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddHomeworkCommand.MESSAGE_USAGE));
        }

        String nusnetId = matcher.group("nusnetId");

        int assignmentId;
        if (nusnetId != null) {
            // Case 1: i/<netid> matched
            // nusnetId is already captured
            try {
                assignmentId = Integer.parseInt(matcher.group("assignmentId"));
            } catch (NumberFormatException e) {
                throw new ParseException("Assignment id must be an integer between 1 and 3.");
            }
        } else {
            // Case 2: all matched
            nusnetId = "all";
            try {
                assignmentId = Integer.parseInt(matcher.group("assignmentIdAll"));
            } catch (NumberFormatException e) {
                throw new ParseException("Assignment id must be an integer between 1 and 3.");
            }
        }

        if (assignmentId < 1 || assignmentId > 3) {
            throw new ParseException("Assignment id must be between 1 and 3.");
        }

        return new AddHomeworkCommand(nusnetId, assignmentId);
    }
}
