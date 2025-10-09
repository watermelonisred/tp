package seedu.address.logic.parser;

import seedu.address.logic.commands.AddHomeworkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class AddHomeworkCommandParser implements Parser<AddHomeworkCommand> {

    private static final Pattern ADDHW_PATTERN = Pattern.compile(
            "(i/(?<NusnetId>\\S+)|all)\\s+a/(?<assignmentId>\\d+)", Pattern.CASE_INSENSITIVE
    );

    @Override
    public AddHomeworkCommand parse(String args) throws ParseException {
        final Matcher matcher = ADDHW_PATTERN.matcher(args.trim());

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddHomeworkCommand.MESSAGE_USAGE));
        }

        String NusnetId = matcher.group("NusnetId");
        if (NusnetId == null) {
            NusnetId = "all";
        }

        int assignmentId;
        try {
            assignmentId = Integer.parseInt(matcher.group("assignmentId"));
        } catch (NumberFormatException e) {
            throw new ParseException("Assignment id must be an integer between 1 and 3.");
        }

        if (assignmentId < 1 || assignmentId > 3) {
            throw new ParseException("Assignment id must be between 1 and 3.");
        }

        return new AddHomeworkCommand(NusnetId, assignmentId);
    }
}
