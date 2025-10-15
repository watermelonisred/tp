package seedu.address.ui;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.AttendanceStatus;
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
    private HBox slotBox;
    @FXML
    private HBox attendanceContainer;
    @FXML
    private VBox homeworkContainer;

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
        showAttendance();
        showHomework();
    }
    private void showAttendance() {
        if (person.getAttendanceSheet() == null) {
            return;
        }
        for (int week = 2; week <= 13; week++) {
            Label weekBox = new Label(String.valueOf(week));
            weekBox.setMinWidth(35);
            weekBox.setMinHeight(35);
            weekBox.setMaxWidth(35);
            weekBox.setMaxHeight(35);
            weekBox.setAlignment(javafx.geometry.Pos.CENTER);
            String baseStyle = "-fx-text-fill: white; -fx-font-weight: bold; "
                    + "-fx-background-radius: 5; -fx-border-radius: 5; "
                    + "-fx-border-color: #cccccc; -fx-border-width: 1;";
            Optional<Attendance> attendanceOpt = person.getAttendanceSheet().getAttendanceForWeek(week);
            if (attendanceOpt.isPresent()) {
                Attendance attendance = attendanceOpt.get();
                AttendanceStatus status = attendance.getAttendanceStatus();
                String backgroundColor;
                switch (status) {
                case PRESENT:
                    backgroundColor = "-fx-background-color: #4CAF50;";
                    break;
                case ABSENT:
                    backgroundColor = "-fx-background-color: #F44336;";
                    break;
                case EXCUSED:
                    backgroundColor = "-fx-background-color: #FFC107; -fx-text-fill: #333333;";
                    break;
                default:
                    backgroundColor = "-fx-background-color: #9E9E9E;";
                }
                weekBox.setStyle(baseStyle + backgroundColor);
            } else {
                weekBox.setStyle(baseStyle + "-fx-background-color: grey;");
            }
            attendanceContainer.getChildren().add(weekBox);
        }
    }

    private void showHomework() {
        if (person.getHomeworkTracker() == null) {
            return;
        }

        // If no homework exists yet
        if (person.getHomeworkTracker().asMap().isEmpty()) {
            Label placeholder = new Label("No homework yet");
            placeholder.setStyle("-fx-background-color: grey; -fx-text-fill: white; -fx-padding: 5 10 5 10; "
                    + "-fx-background-radius: 5;");
            homeworkContainer.getChildren().add(placeholder);
            return;
        }

        person.getHomeworkTracker().asMap().forEach((id, hw) -> {
            Label hwLabel = new Label("HW " + hw.getId() + ": Incomplete");
            hwLabel.setStyle("-fx-text-fill: white; -fx-padding: 5 10 5 10; -fx-background-radius: 5;");

            switch (hw.getStatus().toLowerCase()) {
            case "complete":
                hwLabel = new Label("HW " + hw.getId() + ": Complete");
                hwLabel.setStyle("-fx-text-fill: white; -fx-padding: 5 10 5 10; -fx-background-radius: 5;"
                        + "-fx-background-color: green;");
                break;
            case "late":
                hwLabel = new Label("HW " + hw.getId() + ": Late");
                hwLabel.setStyle("-fx-text-fill: white; -fx-padding: 5 10 5 10; -fx-background-radius: 5;"
                        + "-fx-background-color: yellow; -fx-text-fill: grey;");
                break;
            default: // incomplete
                hwLabel.setStyle(hwLabel.getStyle() + "-fx-background-color: red;");
                break;
            }
            homeworkContainer.getChildren().add(hwLabel);
        });
    }
}
