package PhoneBook;

import java.util.Objects;

public class PhoneNumber implements Cloneable, Comparable<PhoneNumber> {
    private final String areaCode;
    private final String number;

    public PhoneNumber(String areaCode, String number) {
        this.areaCode = areaCode;
        this.number = number;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneNumber)) return false;

        PhoneNumber phoneNumber = (PhoneNumber) o;
        return Objects.equals(areaCode, phoneNumber.areaCode) && Objects.equals(number, phoneNumber.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(areaCode, number);
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "areaCode='" + areaCode + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    protected PhoneNumber clone() {
        try {
            PhoneNumber clone = (PhoneNumber) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int compareTo(PhoneNumber o) {
        int result = areaCode.compareTo(o.areaCode);
        if (result == 0) {
            return number.compareTo(o.number);
        }

        return result;
    }
}
