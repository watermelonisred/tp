package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Nusnetid nusnetid;
    private final Telegram telegram;
    private final Slot slot;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Nusnetid nusnetid, Telegram telegram, Slot slot) {
        requireAllNonNull(name, phone, email, nusnetid, telegram, slot);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.nusnetid = nusnetid;
        this.telegram = telegram;
        this.slot = slot;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Nusnetid getNusnetid() {
        return nusnetid;
    }

    public Telegram getTelegram() {
        return telegram;
    }

    public Slot getSlot() {
        return slot;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && nusnetid.equals(otherPerson.nusnetid)
                && telegram.equals(otherPerson.telegram)
                && slot.equals(otherPerson.slot);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, nusnetid, telegram, slot);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("NUSnetid", nusnetid)
                .add("telegram", telegram)
                .add("slot", slot)
                .toString();
    }

}
