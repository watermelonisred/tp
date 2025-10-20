package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.Group;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Consultation;
import seedu.address.model.person.GroupId;
import seedu.address.model.person.Person;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_CONSULTATION =
            "Consultations list contains duplicate consultation(s).";
    public static final String MESSAGE_DUPLICATE_GROUP = "Groups list contains duplicate group(s).";
    public static final String MESSAGE_GROUP_REFERENCES_UNKNOWN_PERSON =
            "Group references a person that does not exist in persons list.";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedConsultation> consultations = new ArrayList<>();
    private final List<JsonAdaptedGroup> groups = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("consultations") List<JsonAdaptedConsultation> consultations,
                                       @JsonProperty("groups") List<JsonAdaptedGroup> groups) {
        if (persons != null) {
            this.persons.addAll(persons);
        }
        if (consultations != null) {
            this.consultations.addAll(consultations);
        }
        if (groups != null) {
            this.groups.addAll(groups);
        }
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        consultations.addAll(source.getConsultationList().stream()
                .map(JsonAdaptedConsultation::new).collect(Collectors.toList()));
        groups.addAll(source.getGroupList().stream().map(JsonAdaptedGroup::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        for (JsonAdaptedConsultation jsonAdaptedConsultation : consultations) {
            Consultation consultation = jsonAdaptedConsultation.toModelType();
            if (addressBook.hasConsultation(consultation)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CONSULTATION);
            }
            addressBook.addConsultation(consultation);
        }

        List<Group> modelGroups = new ArrayList<>();
        for (JsonAdaptedGroup jsonAdaptedGroup : groups) {
            // validate and build Group object
            GroupId modelGroupId = jsonAdaptedGroup.toModelGroupId();
            Group modelGroup = new Group(modelGroupId);
            // For each stored nusnetid, find the corresponding Person in addressBook and add
            for (String nusId : jsonAdaptedGroup.getStudentNusnetids()) {
                boolean found = false;
                for (Person p : addressBook.getPersonList()) {
                    if (p.getNusnetid().value.equals(nusId)) {
                        modelGroup.addStudent(p);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    throw new IllegalValueException(MESSAGE_GROUP_REFERENCES_UNKNOWN_PERSON);
                }
            }
            modelGroups.add(modelGroup);
        }

        // check for duplicate groups
        long distinctCount = modelGroups.stream().distinct().count();
        if (distinctCount != modelGroups.size()) {
            throw new IllegalValueException(MESSAGE_DUPLICATE_GROUP);
        }

        addressBook.setGroups(modelGroups);
        return addressBook;
    }

}
