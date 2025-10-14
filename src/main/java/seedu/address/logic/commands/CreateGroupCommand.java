package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import seedu.address.model.Group;
import seedu.address.model.Model;
import seedu.address.model.person.Slot;

/**
 * Creates a group with a given slot (used as group identifier).
 */
public class CreateGroupCommand extends Command {
    public static final String COMMAND_WORD = "create_group";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a new group identified by the given slot. "
            + "Parameters: " + PREFIX_GROUP + "SLOT "
            + "Example: " + COMMAND_WORD + " " + PREFIX_GROUP + "T12";

    private final Slot groupSlot;

    /**
     * Creates a CreateGroupCommand to add the specified {@code groupSlot}
     */
    public CreateGroupCommand(Slot groupSlot) {
        requireNonNull(groupSlot);
        this.groupSlot = groupSlot;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        // Check for duplicate group slot
        boolean duplicate = model.getGroupList().stream()
            .anyMatch(g -> g.getSlot().equals(groupSlot));
        if (duplicate) {
            return new CommandResult(String.format("Group %s already exists!", groupSlot));
        }
        // Create a new group with the given slot
        Group newGroup = new Group(groupSlot);
        model.addGroup(newGroup);
        return new CommandResult(String.format("New group %s created", groupSlot));
    }
}
