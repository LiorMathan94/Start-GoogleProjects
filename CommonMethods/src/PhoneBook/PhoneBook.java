package PhoneBook;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private final String name;
    private List<Contact> contacts;

    public PhoneBook(String name) {
        this.name = name;
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    @Override
    public String toString() {
        return "PhoneBook{" +
                "name='" + name + '\'' +
                ", contacts=" + contacts +
                '}';
    }
}
