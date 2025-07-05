package com.collections.set.hashsetdemo;

public class EmailContact extends Contact {
    private String email;

    public EmailContact(String name, String phoneNumber, String email) {
        super(name, phoneNumber);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "EmailContact{" +
                "name='" + getName() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    /**
     * Compares this email contact to the specified object. The result is {@code true} if and only if
     * the argument is not {@code null} and is an {@code EmailContact} object that has the same
     * phone number and email address as this object.
     *
     * @param o the object to compare this {@code EmailContact} against
     * @return {@code true} if the given object represents an {@code EmailContact}
     *         with the same phone number and email as this contact, {@code false} otherwise
     * @see Contact#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailContact)) return false;
        if (!super.equals(o)) return false;
        EmailContact that = (EmailContact) o;
        return email.equals(that.email);
    }

    /**
     * Returns a hash code value for this email contact. The hash code is computed by combining
     * the hash code of the parent class with the hash code of the email address.
     *
     * @return a hash code value for this email contact
     * @see Contact#hashCode()
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}
