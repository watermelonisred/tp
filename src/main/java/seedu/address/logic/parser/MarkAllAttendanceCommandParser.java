package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.MarkAllAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@link MarkAllAttendanceCommand} object.
 * <p>
 * The expected input format is:
 * <pre>{@code
 * g/<group> w/<week> <present|absent|excused>
 * }</pre>
 * Week must be an integer between 2 and 13, and the status must be one of
 * "present", "absent", or "excused".
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * mark_all_attendance g/T02 w/3 present  // marks week 3 attendance for all students in group T02 as present
 * }</pre>
 */
public class MarkAllAttendanceCommandParser implements Parser<MarkAllAttendanceCommand> {
    private static final Pattern MARK_ALL_COMMAND_FORMAT = Pattern.compile(
            "g/(?<groupId>\\S+)\\s+w/(?<week>\\d+)\\s+(?<status>\\S+)",
            Pattern.CASE_INSENSITIVE);

    @Override
    public MarkAllAttendanceCommand parse(String args) throws ParseException {
        final Matcher matcher = MARK_ALL_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkAllAttendanceCommand.MESSAGE_USAGE));
        }

        int week;
        try {
            week = Integer.parseInt(matcher.group("week"));
            if (week < 2 || week > 13) {
                throw new ParseException(MarkAllAttendanceCommand.MESSAGE_INVALID_WEEK);
            }
        } catch (NumberFormatException e) {
            throw new ParseException(MarkAllAttendanceCommand.MESSAGE_INVALID_WEEK);
        }

        String groupId = matcher.group("groupId");
        String status = matcher.group("status").toLowerCase();

        if (!status.equals("present") && !status.equals("absent") && !status.equals("excused")) {
            throw new ParseException(MarkAllAttendanceCommand.MESSAGE_INVALID_STATUS);
        }

        return new MarkAllAttendanceCommand(groupId, week, status);
    }
}
