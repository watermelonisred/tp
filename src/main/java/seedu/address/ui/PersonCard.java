package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private HBox phoneBox;
    @FXML
    private Label email;
    @FXML
    private HBox emailBox;
    @FXML
    private Label telegram;
    @FXML
    private HBox telegramBox;
    @FXML
    private Label nusnetid;
    @FXML
    private HBox nusnetidBox;
    @FXML
    private Label slot;
    @FXML
    private FlowPane homeworkContainer;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        if (person.getPhone().isPresent()) {
            phone.setText(person.getPhone().get().value);
            phoneBox.setVisible(true);
            phoneBox.setManaged(true);
        } else {
            phoneBox.setVisible(false);
            phoneBox.setManaged(false);
        }
        if (person.getEmail().isPresent()) {
            email.setText(person.getEmail().get().value);
            emailBox.setVisible(true);
            emailBox.setManaged(true);
        } else {
            emailBox.setVisible(false);
            emailBox.setManaged(false);
        }
        nusnetid.setText(person.getNusnetid().value);
        telegram.setText(person.getTelegram().value);
        slot.setText(person.getSlot().value);
        showHomework();
    }

    private void showHomework() {
        if (person.getHomeworkTracker() == null) {
            return;
        }

        if (person.getHomeworkTracker().asMap().isEmpty()) {
            Label placeholder = new Label("No homework");
            placeholder.setStyle("-fx-background-color: #d3d3d3; -fx-text-fill: black; "
                    + "-fx-padding: 3 6; -fx-background-radius: 6; -fx-font-size: 11px;");
            homeworkContainer.getChildren().add(placeholder);
            return;
        }

        person.getHomeworkTracker().asMap().forEach((id, hw) -> {
            Label hwLabel = new Label("HW" + hw.getId());
            hwLabel.setStyle("-fx-padding: 3 8; -fx-background-radius: 6; -fx-font-size: 11px;");

            switch (hw.getStatus().toLowerCase()) {
            case "complete":
                hwLabel.setStyle(hwLabel.getStyle()
                        + "-fx-background-color: #b2fab4; -fx-text-fill: #2e7d32;"); // 🟢 soft green
                break;
            case "late":
                hwLabel.setStyle(hwLabel.getStyle()
                        + "-fx-background-color: #fff59d; -fx-text-fill: #996c00;"); // 🟡 soft yellow
                break;
            default: // incomplete
                hwLabel.setStyle(hwLabel.getStyle()
                        + "-fx-background-color: #ffcccb; -fx-text-fill: #b71c1c;"); // 🔴 light red
                break;
            }

            homeworkContainer.getChildren().add(hwLabel);
        });
    }
}
