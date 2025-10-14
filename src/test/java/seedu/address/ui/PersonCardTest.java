package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.scene.control.Label;
import seedu.address.model.person.Email;
import seedu.address.model.person.Homework;
import seedu.address.model.person.HomeworkTracker;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nusnetid;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Slot;
import seedu.address.model.person.Telegram;

public class PersonCardTest {
    private static boolean jfxInitialized = false;

    private Person person;
    private PersonCard personCard;

    @BeforeAll
    public static void initJfx() {
        if (!jfxInitialized) {
            Platform.startup(() -> {
                // JavaFX toolkit initialized
            });
            jfxInitialized = true;
        }
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        // Sample person with some homework
        person = new Person(
                new Name("Alice"),
                new Phone("12345678"),
                new Email("alice@u.nus.edu"),
                new Nusnetid("E1234567"),
                new Telegram("@alice"),
                new Slot("T01"),
                new HomeworkTracker(Map.of(
                        1, new Homework(1, "complete"),
                        2, new Homework(2, "incomplete"),
                        3, new Homework(3, "late")
                ))
        );


        // Use CountDownLatch to wait for JavaFX thread
        java.util.concurrent.CountDownLatch latch = new java.util.concurrent.CountDownLatch(1);

        Platform.runLater(() -> {
            personCard = new PersonCard(person, 1);
            latch.countDown(); // signal that creation is done
        });

        latch.await(); // wait until PersonCard is fully initialized
    }

    @Test
    public void display_personDetails_correctly() {
        // ID and name
        assertEquals("1. ", personCard.getIdLabel().getText());
        assertEquals("Alice", personCard.getNameLabel().getText());

        var hwContainer = personCard.getHomeworkContainer();
        assertEquals(3, hwContainer.getChildren().size());

        Label hw1 = (Label) hwContainer.getChildren().get(0);
        assertEquals("HW1", hw1.getText());
        assertTrue(hw1.getStyle().contains("#b2fab4")); // green

        Label hw2 = (Label) hwContainer.getChildren().get(1);
        assertEquals("HW2", hw2.getText());
        assertTrue(hw2.getStyle().contains("#ffcccb")); // red

        Label hw3 = (Label) hwContainer.getChildren().get(2);
        assertEquals("HW3", hw3.getText());
        assertTrue(hw3.getStyle().contains("#fff59d")); // yellow
    }

    @Test
    public void display_noHomeworkPlaceholder_whenEmpty() {
        Person emptyPerson = new Person(
                new Name("Bob"),
                new Phone("12345678"),
                new Email("bob@u.nus.edu"),
                new Nusnetid("E7654321"),
                new Telegram("@bob"),
                new Slot("T02"),
                new HomeworkTracker(Map.of()) // empty
        );

        PersonCard emptyCard = new PersonCard(emptyPerson, 2);
        var hwContainer = emptyCard.getHomeworkContainer();

        assertEquals(1, hwContainer.getChildren().size());
        Label placeholder = (Label) hwContainer.getChildren().get(0);
        assertEquals("No homework", placeholder.getText());
    }
}
