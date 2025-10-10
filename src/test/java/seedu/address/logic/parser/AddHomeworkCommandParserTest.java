package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NUSNETID_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddHomeworkCommand;

public class AddHomeworkCommandParserTest {

    private AddHomeworkCommandParser parser = new AddHomeworkCommandParser();

    @Test
    public void parse_validArgs_success() {
        // Single student
        assertParseSuccess(parser, " i/" + VALID_NUSNETID_AMY + " a/" + VALID_ASSIGNMENT_1,
                new AddHomeworkCommand(VALID_NUSNETID_AMY, 1));
    }

    @Test
    public void parse_validArgsAllStudents_success() {
        // All students
        assertParseSuccess(parser, " i/all a/2",
                new AddHomeworkCommand("all", 2));
    }

    @Test
    public void parse_invalidAssignmentId_failure() {
        assertParseFailure(parser, " i/" + VALID_NUSNETID_AMY + " a/10",
                "Assignment id must be between 1 and 3.");
    }

    @Test
    public void parse_missingFields_failure() {
        assertParseFailure(parser, " a/1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddHomeworkCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " i/" + VALID_NUSNETID_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddHomeworkCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidNusnetId_failure() {
        // parser only checks format, so invalid characters or empty NUSNET ID
        assertParseFailure(parser, " i/ a/1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddHomeworkCommand.MESSAGE_USAGE));
    }
}
