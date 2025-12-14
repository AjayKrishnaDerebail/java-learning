package com.main.section.section7;

import com.main.database.StudentDatabase;
import com.main.model.Student;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsumerAndPredicateChallenge {

  static Predicate<Student> honourStudent = s -> s.gpa() >= 4.0;

  static Predicate<Student> onProbation = s -> s.gpa() < 3.0;

  static Predicate<Student> extraCurricularTooMuch = s -> s.hobbies().size() >= 4;

  static Predicate<Student> wellRounded = s -> s.gpa() > 3.5 && s.hobbies().size() >= 2;

  static Predicate<Student> p3 = s -> s.firstName().startsWith("M");

  static Predicate<Student> p4 = s -> s.gpa() >= 4.0 && s.hobbies().size() >= 2;

  static Predicate<Student> p5 = s -> s.hobbies().size() < 2;

  static Consumer<Student> printHonorBadge = s -> log.info("\uD83C\uDF96 Honor Student: {}",
      s.firstName());

  static Consumer<Student> probationWarning = s -> log.info("⚠ Academic Probation: {}",
      s.firstName());

  static Consumer<Student> extraCurricularTooMuchWarning = s -> log.info(
      "⚠ Extra Curricular Too Much: {}", s.firstName());

  static Consumer<Student> wellRoundedWarning = s -> log.info(
      "⭐ Well Rounded: {}", s.firstName());

  static List<Rule<Student>> rules = List.of(
      new Rule<>(honourStudent, printHonorBadge),
      new Rule<>(onProbation, probationWarning),
      new Rule<>(extraCurricularTooMuch, extraCurricularTooMuchWarning),
      new Rule<>(wellRounded, wellRoundedWarning)
  );

  public static void purePredicateChallenge() {
    StudentDatabase.getAllStudents().forEach(s -> {
      if (p4.or(p3).and(p5.negate()).test(s)) {
        log.info("{}", s);
      }
    });
  }

  public static void purePredicateChallengeUsingStream() {
    StudentDatabase.getAllStudents()
        .stream()
        .filter(p4.or(p3).and(p5.negate()))   // Predicate decides
        .forEach(s -> log.info("{}", s));     // Consumer acts

  }

  public static void ConsumerAndPredicateChatGptChallenge(){

    StudentDatabase.getAllStudents().forEach( s ->{
      if(honourStudent.test(s))
        printHonorBadge.accept(s);

      if(onProbation.test(s))
        probationWarning.accept(s);

      if(extraCurricularTooMuch.test(s))
        extraCurricularTooMuchWarning.accept(s);

      if(wellRounded.test(s))
        wellRoundedWarning.accept(s);
    });
  }

  public static void ruleBasedExecution(){
    StudentDatabase.getAllStudents().forEach(s -> rules.forEach(rule -> {
      if (rule.condition().test(s))
        rule.action().accept(s);
    }));
  }
}