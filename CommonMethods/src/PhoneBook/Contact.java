package PhoneBook;

import java.util.Objects;

public class Contact implements Cloneable, Comparable<Contact> {
    private Name name;
    private PhoneNumber phoneNumber;

    public Contact(Name name, PhoneNumber phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return name.equals(contact.name) && phoneNumber.equals(contact.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name=" + name +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    @Override
    protected Contact clone() {
        try {
            Contact clone = (Contact) super.clone();
            clone.name = this.name.clone();
            clone.phoneNumber = this.phoneNumber.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int compareTo(Contact o) {
        int result = name.compareTo(o.name);
        if (result == 0) {
            return phoneNumber.compareTo(o.phoneNumber);
        }

        return result;
    }
}
