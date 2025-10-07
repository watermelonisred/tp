package seedu.address.testutil;

import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nusnetid;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Slot;
import seedu.address.model.person.Telegram;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@u.nus.edu";
    public static final String DEFAULT_NUSNETID = "E1234567";
    public static final String DEFAULT_TELEGRAM = "@amyy";
    public static final String DEFAULT_SLOT = "T01";

    private Name name;
    private Phone phone;
    private Email email;
    private Nusnetid nusnetid;
    private Telegram telegram;
    private Slot slot;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        nusnetid = new Nusnetid(DEFAULT_NUSNETID);
        telegram = new Telegram(DEFAULT_TELEGRAM);
        slot = new Slot(DEFAULT_SLOT);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        nusnetid = personToCopy.getNusnetid();
        telegram = personToCopy.getTelegram();
        slot = personToCopy.getSlot();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }


    /**
     * Sets the {@code NUSnetid} of the {@code Person} that we are building.
     */
    public PersonBuilder withNusnetid(String nusnetid) {
        this.nusnetid = new Nusnetid(nusnetid);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Telegram} of the {@code Person} that we are building.
     */
    public PersonBuilder withTelegram(String telegram) {
        this.telegram = new Telegram(telegram);
        return this;
    }

    /**
     * Sets the {@code Slot} of the {@code Person} that we are building.
     */
    public PersonBuilder withSlot(String slot) {
        this.slot = new Slot(slot);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, nusnetid, telegram, slot);
    }

}
