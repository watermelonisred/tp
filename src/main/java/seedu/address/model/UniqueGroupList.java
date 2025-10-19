package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of groups that enforces uniqueness between its elements and does not allow nulls.
 * A group is considered unique by comparing using {@code Group#isSameGroup(Group)}.
 * As such, adding and updating of groups uses Group#isSameGroup(Group) for equality
 * to ensure that the group being added or updated is unique in terms of identity
 * in the UniqueGroupList. However, the removal of a group uses Group#equals(Object
 */
public class UniqueGroupList implements Iterable<Group> {
    private final ObservableList<Group> internalList = FXCollections.observableArrayList();
    private final ObservableList<Group> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    /**
     * Returns true if the list contains an equivalent group as the given argument.
     * @param toCheck group to check
     * @return boolean
     */
    public boolean contains(Group toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameGroup);
    }
    /**
     * Sets the groups to the groups in the given list.
     * @param groups updated list of groups
     */
    public void setGroups(List<Group> groups) {
        requireNonNull(groups);
        long distinctCount = groups.stream().distinct().count();
        if (distinctCount != groups.size()) {
            throw new IllegalArgumentException("Groups list contains duplicate groups");
        }
        internalList.setAll(groups);
    }
    @Override
    public Iterator<Group> iterator() {
        return internalList.iterator();
    }
    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Group> asUnmodifiableObservableList() {
        return this.internalUnmodifiableList;
    }
}
