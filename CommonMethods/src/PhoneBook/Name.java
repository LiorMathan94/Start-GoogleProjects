package PhoneBook;

import java.util.Objects;

public class Name implements Cloneable, Comparable<Name> {
    private Prefix prefix;
    private final String firstName;
    private String lastName;

    public Name(Prefix prefix, String firstName, String lastName) {
        this.prefix = prefix;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Prefix getPrefix() {
        return prefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPrefix(Prefix prefix) {
        this.prefix = prefix;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Name)) return false;
        Name name = (Name) o;
        return Objects.equals(prefix, name.prefix) && Objects.equals(firstName, name.firstName)
                && Objects.equals(lastName, name.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prefix, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Name{" +
                "prefix=" + prefix +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    protected Name clone() {
        try {
            Name clone = (Name) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int compareTo(Name o) {
        int result = firstName.compareTo(o.firstName);
        if (result == 0) {
            return lastName.compareTo(o.lastName);
        }

        return result;
    }

    enum Prefix {
        MR,
        MS,
        MISS,
        MRS,
        JR
    }
}
