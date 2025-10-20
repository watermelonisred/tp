package seedu.address.model;

import java.util.List;
import javafx.collections.ObservableList;
import seedu.address.model.event.Consultation;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    List<Person> getUniquePersonList();

    /**
     * Returns an unmodifiable view of the consultations list.
     * This list will not contain any duplicate consultations.
     */
    ObservableList<Consultation> getConsultationList();

}
