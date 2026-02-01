String name = "Ajay";

void main() {
    final var nameMain = List.of("Dilip");
    IO.println(nameMain);
    IO.println(name + ", hello and welcome!");

    final var multiLineString = """
        This is a
          multiline String
        This is awesome !!!
        """;

  IO.print(multiLineString);
}