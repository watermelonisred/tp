package seedu.address.logic.commands;

/**
 * Adds a person to a group.
 */
public class AddToGroupCommand {
    public static final String COMMAND_WORD = "add_to_group";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a existing student to a group.\n"
            + "Parameters: g/GROUPID i/NETID \n"
            + "Example: " + COMMAND_WORD + " g/T01 i/E1234567 \n";

    public static final String MESSAGE_SUCCESS = "Student %s added to Group %s.";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "Student not found.";
    public static final String MESSAGE_GROUP_NOT_FOUND = "Group not found.";

    private final String nusnetId;
    private final String groupId;
    /**
     * Creates an {@code AddToGroupCommand} to add a person to a group.
     *
     * @param nusnetId the nusnetId ID of the target student
     * @param groupId the ID of the group to add the student to
     */
    public AddToGroupCommand(String nusnetId, String groupId) {
        this.nusnetId = nusnetId;
        this.groupId = groupId;
    }
}
