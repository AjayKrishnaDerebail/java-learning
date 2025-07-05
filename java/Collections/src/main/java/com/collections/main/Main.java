package com.collections.main;

import com.collections.set.hashsetdemo.Contact;
import com.collections.set.hashsetdemo.EmailContact;
import com.collections.set.hashsetdemo.PhoneContact;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Main class demonstrating various HashSet operations including union, intersection,
 * difference, and symmetric difference with detailed Javadoc comments.
 */
public class Main {

    /**
     * Main method to demonstrate HashSet operations.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Initialize three sets of contacts
        Set<Contact> personalContacts = createPersonalContacts();
        Set<Contact> workContacts = createWorkContacts();
        Set<Contact> familyContacts = createFamilyContacts();

        // Display initial sets
        printHashSet("=== Personal Contacts ===", personalContacts);
        printHashSet("=== Work Contacts ===", workContacts);
        printHashSet("=== Family Contacts ===", familyContacts);

        // 1. Union: All unique contacts from both sets
        Set<Contact> unionSet = new HashSet<>(personalContacts);
        unionSet.addAll(workContacts);
        printHashSet("\n=== Union of Personal and Work Contacts ===", unionSet);

        // 2. Intersection: Common contacts in both sets
        Set<Contact> intersectionSet = new HashSet<>(personalContacts);
        intersectionSet.retainAll(familyContacts);
        printHashSet("\n=== Intersection of Personal and Family Contacts ===", intersectionSet);

        // 3. Difference: Contacts in personal but not in work
        Set<Contact> differenceSet = new HashSet<>(personalContacts);
        differenceSet.removeAll(workContacts);
        printHashSet("\n=== Personal Contacts not in Work Contacts ===", differenceSet);

        // 4. Symmetric Difference: Contacts in either set but not in both
        Set<Contact> symmetricDiff = new HashSet<>(personalContacts);
        symmetricDiff.addAll(workContacts);
        Set<Contact> tmp = new HashSet<>(personalContacts);
        tmp.retainAll(workContacts);
        symmetricDiff.removeAll(tmp);
        printHashSet("\n=== Symmetric Difference (Personal △ Work) ===", symmetricDiff);

        // 5. Check if one set is a subset of another
        boolean isSubset = workContacts.containsAll(familyContacts);
        System.out.println("\n=== Is Family Contacts a subset of Work Contacts? ===\n" + isSubset);

        // 6. Check if sets are disjoint (no common elements)
        boolean isDisjoint = Collections.disjoint(personalContacts, workContacts);
        System.out.println("\n=== Are Personal and Work Contacts disjoint? ===\n" + isDisjoint);
    }

    /**
     * Creates a set of personal contacts.
     * 
     * @return Set of personal contacts
     */
    private static Set<Contact> createPersonalContacts() {
        return new HashSet<>(Arrays.asList(
            new EmailContact("John Doe", "123-456-7890", "john@personal.com"),
            new PhoneContact("Jane Smith", "234-567-8901", "123 Home St"),
            new EmailContact("Alice Johnson", "345-678-9012", "alice@personal.com"),
            new PhoneContact("Bob Wilson", "456-789-0123", "456 Home Ave")
        ));
    }

    /**
     * Creates a set of work contacts.
     * 
     * @return Set of work contacts
     */
    private static Set<Contact> createWorkContacts() {
        return new HashSet<>(Arrays.asList(
            new EmailContact("John Doe", "123-456-7890", "john@work.com"),
            new PhoneContact("Sarah Connor", "567-890-1234", "789 Work Blvd"),
            new EmailContact("Mike Tyson", "678-901-2345", "mike@work.com"),
            new PhoneContact("Emma Watson", "789-012-3456", "101 Work St")
        ));
    }

    /**
     * Creates a set of family contacts.
     * 
     * @return Set of family contacts
     */
    private static Set<Contact> createFamilyContacts() {
        return new HashSet<>(Arrays.asList(
            new PhoneContact("John Doe", "123-456-7890", "123 Family Rd"),
            new EmailContact("Mom", "111-222-3333", "mom@family.com"),
            new PhoneContact("Dad", "444-555-6666", "123 Family Rd"),
            new EmailContact("Sister", "777-888-9999", "sister@family.com")
        ));
    }

    /**
     * Prints the contents of a HashSet with a descriptive message.
     * 
     * @param message The message to display before the set contents
     * @param set The set to be printed
     * @param <T> The type of elements in the set
     */
    private static <T> void printHashSet(String message, Set<T> set) {
        System.out.println(message);
        if (set.isEmpty()) {
            System.out.println("  <empty>\n");
            return;
        }
        int index = 1;
        for (T item : set) {
            System.out.println("  " + index++ + ". " + item);
        }
        System.out.println("  Total: " + set.size() + " contacts\n");
    }
}