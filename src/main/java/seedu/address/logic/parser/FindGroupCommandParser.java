package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import java.util.stream.Stream;

import seedu.address.logic.commands.FindGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.GroupId;

/**
 * Parses input arguments and creates a new FindGroupCommand object
 */
public class FindGroupCommandParser implements Parser<FindGroupCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindGroupCommand
     * and returns a FindGroupCommand object for execution.
     *
     * @param args the input arguments string.
     * @return a FindGroupCommand object.
     */
    @Override
    public FindGroupCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_GROUP);
        if (!arePrefixesPresent(argumentMultimap, PREFIX_GROUP)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindGroupCommand.MESSAGE_USAGE));
        }
        argumentMultimap.verifyNoDuplicatePrefixesFor(PREFIX_GROUP);
        GroupId groupId = ParserUtil.parseGroupId(argumentMultimap.getValue(PREFIX_GROUP).get());
        return new FindGroupCommand(groupId);
    }
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
