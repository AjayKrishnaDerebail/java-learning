import com.java_9_to_25.newSwitch.NewSwitch;
import com.java_9_to_25.recorddemo.RecordDemo;

import java.time.Month;
import java.util.List;

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

  IO.println(NewSwitch.getDays(Month.FEBRUARY,1900));

  RecordDemo.run();

}