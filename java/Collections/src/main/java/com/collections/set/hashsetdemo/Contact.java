package com.collections.set.hashsetdemo;

public class Contact {
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Compares this contact to the specified object. The result is {@code true} if and only if
     * the argument is not {@code null} and is a {@code Contact} object that has the same
     * phone number as this object.
     *
     * @param o the object to compare this {@code Contact} against
     * @return {@code true} if the given object represents a {@code Contact}
     *         with the same phone number as this contact, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return phoneNumber.equals(contact.phoneNumber);
    }

    /**
     * Returns a hash code value for this contact. The hash code is computed using
     * the phone number of the contact.
     *
     * @return a hash code value for this contact
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        return result;
    }
}
