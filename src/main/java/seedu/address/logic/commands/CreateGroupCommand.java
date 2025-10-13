package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import seedu.address.model.Group;
import seedu.address.model.Model;
import seedu.address.model.person.Slot;

/**
 * Creates a group with a given name.
 */
public class CreateGroupCommand extends Command {
    public static final String COMMAND_WORD = "create_group";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a group with the specified name. "
            + "Parameters: "
            + COMMAND_WORD + "GROUP_NAME\""
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUP + "CS2103/T Buddies";
    private final String groupName;
    /**
     * Creates a CreatGroupCommand to add the specified {@code groupName}
     */
    public CreateGroupCommand(String groupName) {
        requireNonNull(groupName);
        this.groupName = groupName;
    }
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        // Create a new group with the given name (using Slot as identifier)
        Group newGroup = new Group(new Slot(groupName));
        model.addGroup(newGroup);
        return new CommandResult(String.format("New group %s created", groupName));
    }
}
