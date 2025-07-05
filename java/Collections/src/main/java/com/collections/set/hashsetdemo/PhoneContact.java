package com.collections.set.hashsetdemo;

public class PhoneContact extends Contact {
    private String address;

    public PhoneContact(String name, String phoneNumber, String address) {
        super(name, phoneNumber);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "PhoneContact{" +
                "name='" + getName() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    /**
     * Compares this phone contact to the specified object. The result is {@code true} if and only if
     * the argument is not {@code null} and is a {@code PhoneContact} object that has the same
     * phone number and physical address as this object.
     *
     * @param o the object to compare this {@code PhoneContact} against
     * @return {@code true} if the given object represents a {@code PhoneContact}
     *         with the same phone number and address as this contact, {@code false} otherwise
     * @see Contact#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneContact)) return false;
        if (!super.equals(o)) return false;
        PhoneContact that = (PhoneContact) o;
        return address.equals(that.address);
    }

    /**
     * Returns a hash code value for this phone contact. The hash code is computed by combining
     * the hash code of the parent class with the hash code of the physical address.
     *
     * @return a hash code value for this phone contact
     * @see Contact#hashCode()
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }
}
