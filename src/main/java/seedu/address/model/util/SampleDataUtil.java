package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nusnetid;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Slot;
import seedu.address.model.person.Telegram;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeo@u.nus.edu"),
                new Nusnetid("E1234567"), new Telegram("@alex"), new Slot("T01")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@u.nus.edu"),
                new Nusnetid("E1234568"), new Telegram("@bernice_yu"), new Slot("T02")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@u.nus.edu"),
                new Nusnetid("E1234569"), new Telegram("@oliv"), new Slot("T01")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@u.nus.edu"),
                new Nusnetid("E1234560"), new Telegram("@davidddd"), new Slot("T15")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@u.nus.edu"),
                new Nusnetid("E1234562"),  new Telegram("@ibraccccc"), new Slot("T12")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@u.nus.edu"),
                new Nusnetid("E1234563"),  new Telegram("@Royy"), new Slot("T09"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }
}
