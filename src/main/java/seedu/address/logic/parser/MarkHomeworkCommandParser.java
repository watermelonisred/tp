package seedu.address.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.MarkHomeworkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class MarkHomeworkCommandParser implements Parser<MarkHomeworkCommand> {
    private static final Pattern MARK_COMMAND_FORMAT = Pattern.compile(
            "i/(?<NusnetId>\\S+)\\s+a/(?<assignmentId>\\d+)\\s+status/(?<status>\\S+)", Pattern.CASE_INSENSITIVE);

    @Override
    public MarkHomeworkCommand parse(String args) throws ParseException {
        final Matcher matcher = MARK_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkHomeworkCommand.MESSAGE_USAGE));
        }

        String NusnetId = matcher.group("NusnetId");
        int assignmentId;
        try {
            assignmentId = Integer.parseInt(matcher.group("assignmentId"));
        } catch (NumberFormatException e) {
            throw new ParseException("Assignment id must be an integer between 0 and 2.");
        }
        String status = matcher.group("status").toLowerCase();

        return new MarkHomeworkCommand(NusnetId, assignmentId, status);
    }
}
