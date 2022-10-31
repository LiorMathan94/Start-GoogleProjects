package PhoneBook;

public class Main {
    public static void main(String[] args) {
        testPhoneBook();
    }

    public static void testPhoneBook() {
        PhoneBook phoneBook = new PhoneBook("My PhoneBook");

        Contact contact1 = new Contact(new Name(Name.Prefix.MR, "Peter", "Griffin"), new PhoneNumber("050", "1111111"));
        Contact contact2 = new Contact(new Name(Name.Prefix.MRS, "Lois", "Griffin"), new PhoneNumber("054", "2222222"));
        Contact contact3 = new Contact(new Name(Name.Prefix.MISS, "Meg", "Griffin"), new PhoneNumber("057", "3333333"));

        phoneBook.addContact(contact1);
        phoneBook.addContact(contact2);
        phoneBook.addContact(contact3);

        System.out.println(phoneBook.toString());

        Contact contact1Clone = contact1.clone();
        System.out.println(contact1.equals(contact1Clone));
        System.out.println(contact1.compareTo(contact1Clone));
    }
}

