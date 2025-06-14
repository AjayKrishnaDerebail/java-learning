package com.java.lambda.functionalInterface;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("SimplifyStreamApiCallChains")
public class StreamApiDemo {

  public static void main(String[] args) {
    /*
     * STREAM CREATION
     * A stream can be created from a collection or an array.
     * Streams do not hold data; they operate on the underlying data source.
     */
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

    /*
     * FILTER
     * Used to filter elements based on a predicate (boolean condition).
     * Example: Filter out even numbers.
     */
    List<Integer> evens = numbers.stream().filter(n -> n % 2 == 0) // Keeps only even numbers
        .toList();
    System.out.println("Filtered evens: " + evens); // [2, 4, 6]

    /*
     * MAP
     * Used to transform each element in the stream.
     * Example: Multiply each number by 2.
     */
    List<Integer> doubled = numbers.stream().map(n -> n * 2).collect(Collectors.toList());
    System.out.println("Doubled: " + doubled); // [2, 4, 6, 8, 10, 12]

    /*
     * FLATMAP
     * Used to flatten a stream of collections into a single stream.
     * Example: Flatten list of lists into a single list.
     */
    List<List<String>> nested = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("c", "d"));
    List<String> flat = nested.stream().flatMap(Collection::stream).collect(Collectors.toList());
    System.out.println("Flattened: " + flat); // [a, b, c, d]

    /*
     * SORTED
     * Sorts elements in natural or custom order.
     * Example: Descending order.
     */
    List<Integer> sortedDesc = numbers.stream().sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());
    System.out.println("Sorted Descending: " + sortedDesc); // [6, 5, 4, 3, 2, 1]

    /*
     * DISTINCT
     * Removes duplicate elements.
     */
    List<Integer> withDuplicates = Arrays.asList(1, 2, 2, 3, 3, 4);
    List<Integer> distinct = withDuplicates.stream().distinct().collect(Collectors.toList());
    System.out.println("Distinct: " + distinct); // [1, 2, 3, 4]

    /*
     * LIMIT and SKIP
     * LIMIT returns the first N elements; SKIP skips the first N.
     */
    List<Integer> limited = numbers.stream().limit(3).collect(Collectors.toList());
    List<Integer> skipped = numbers.stream().skip(3).collect(Collectors.toList());
    System.out.println("Limited: " + limited); // [1, 2, 3]
    System.out.println("Skipped: " + skipped); // [4, 5, 6]

    /*
     * FOREACH
     * A terminal operation that performs an action on each element.
     */
    System.out.print("ForEach: ");
    numbers.stream().forEach(n -> System.out.print(n + " ")); // prints: 1 2 3 4 5 6
    System.out.println();

    /*
     * REDUCE
     * Aggregates stream elements into a single result.
     * Example: Sum of numbers.
     */
    int sum = numbers.stream().reduce(0, Integer::sum);
    System.out.println("Sum using reduce: " + sum); // 21

    /*
     * COLLECT
     * Gathers the stream output into a collection like List, Set, or Map.
     * Shown above in all examples like collect(Collectors.toList())
     */

    /*
     * COUNT, MATCH, FIND
     * Terminal operations to count or search the stream.
     */
    long count = numbers.size();
    // long count = numbers.stream().count();
    boolean anyEven = numbers.stream().anyMatch(n -> n % 2 == 0);
    Optional<Integer> first = numbers.stream().findFirst();
    System.out.println("Count: " + count); // 6
    System.out.println("Any even?: " + anyEven); // true
    System.out.println("First element: " + first.orElse(-1)); // 1

    /*
     * ADVANCED COLLECTORS
     * Grouping, partitioning, mapping
     */
    List<String> items = Arrays.asList("apple", "banana", "apricot", "blueberry", "avocado");

    // GROUPING BY
    Map<Character, List<String>> grouped = items.stream()
        .collect(Collectors.groupingBy(s -> s.charAt(0)));
    System.out.println("Grouped by first letter: " + grouped);
    // Output: {a=[apple, apricot, avocado], b=[banana, blueberry]}

    // PARTITIONING BY
    List<Integer> values = Arrays.asList(1, 2, 3, 4, 5);
    Map<Boolean, List<Integer>> partitioned = values.stream()
        .collect(Collectors.partitioningBy(n -> n % 2 == 0));
    System.out.println("Partitioned evens/odds: " + partitioned);
    // Output: {false=[1, 3, 5], true=[2, 4]}

    // MAPPING inside groupingBy
    Map<Character, List<Integer>> lengthsGrouped = items.stream().collect(
        Collectors.groupingBy(s -> s.charAt(0),
            Collectors.mapping(String::length, Collectors.toList())));
    System.out.println("Grouped lengths by letter: " + lengthsGrouped);
    // Output: {a=[5, 7, 7], b=[6, 9]}

    /*
     * PARALLEL STREAM
     * Processes elements concurrently using multiple threads.
     * Output order is not guaranteed.
     */
    System.out.println("Parallel Stream Output (order not guaranteed):");
    numbers.parallelStream()
        .forEach(n -> System.out.println(Thread.currentThread().getName() + ": " + n));
  }
}
