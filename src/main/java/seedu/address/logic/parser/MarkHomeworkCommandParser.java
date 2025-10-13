package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.MarkHomeworkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@link MarkHomeworkCommand} object.
 * <p>
 * The expected input format is:
 * <pre>{@code
 * i/<nusnetId> a/<assignmentId> status/<complete|incomplete|late>
 * }</pre>
 * Assignment IDs must be integers between 0 and 2, and the status must be one of
 * "complete", "incomplete", or "late".
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * mark i/E1234567 a/1 status/complete    // marks assignment 0 for student E1234567 as complete
 * }</pre>
 */
public class MarkHomeworkCommandParser implements Parser<MarkHomeworkCommand> {
    private static final Pattern MARK_COMMAND_FORMAT = Pattern.compile(
            "i/(?<nusnetId>\\S+)\\s+a/(?<assignmentId>\\d+)\\s+status/(?<status>\\S+)", Pattern.CASE_INSENSITIVE);

    /**
     * Parses the given {@code String} of arguments in the context of the MarkHomeworkCommand
     * and returns a {@link MarkHomeworkCommand} object for execution.
     *
     * @param args the input arguments string
     * @return a {@link MarkHomeworkCommand} representing the parsed input
     * @throws ParseException if the input does not conform to the expected format,
     *                        or if the assignment ID is not a valid integer
     */
    @Override
    public MarkHomeworkCommand parse(String args) throws ParseException {
        final Matcher matcher = MARK_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkHomeworkCommand.MESSAGE_USAGE));
        }

        String nusnetId = matcher.group("nusnetId");
        int assignmentId;
        try {
            assignmentId = Integer.parseInt(matcher.group("assignmentId"));
            if (assignmentId < 1 || assignmentId > 3) {
                throw new ParseException("Assignment id must be between 1 and 3.");
            }
        } catch (NumberFormatException e) {
            throw new ParseException("Assignment id must be an integer between 1 and 3.");
        }
        String status = matcher.group("status").toLowerCase();
        if (!status.equals("complete") && !status.equals("incomplete") && !status.equals("late")) {
            throw new ParseException("Status must be one of: complete, incomplete, late.");
        }
        return new MarkHomeworkCommand(nusnetId, assignmentId, status);
    }
}
