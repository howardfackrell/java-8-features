package com.hlf.java8.lamdas;

import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LamdasTest {

    class Person {
        String first;
        String last;
        public Person(String first, String last) {
            this.first = first;
            this.last = last;
        }
    }

    @Test
    public void testMap() {
        List<Person> people = Arrays.asList(new Person("John", "Smith"), new Person("Jane", "Doe"));

        List<String> firstNames = people.stream().map(
                (person) ->  person.first
        ).collect(Collectors.toList());

        assertEquals(firstNames.size(), 2);
        assertEquals(firstNames.get(0), "John");
    }

    @Test
    public void testGroupBy() {
        List<Person> people = Arrays.asList(
                new Person("John", "Smith"),
                new Person("Jane", "Smith"),
                new Person("Jane", "Doe")
        );

        Map<String, List<Person>> peopleByLastName =
                people.stream().collect(Collectors.groupingBy( (person) -> person.last));
        assertEquals(2, peopleByLastName.size());
        assertEquals(2, peopleByLastName.get("Smith").size());
    }

    @Test
    public void testStackTraceAggregate() {
        try {
            throw new RuntimeException("This is an error");
        } catch (RuntimeException e) {
            StringWriter error = new StringWriter();
            error.append("Hello there\n");
            e.printStackTrace(new PrintWriter(error));
            System.out.println(error);
        }
        assertTrue(true);
    }

    @FunctionalInterface
    interface Procedure {
        void doit();
    }

    Procedure walking = new Procedure() {
        @Override
        public void doit() {
            System.out.println("walking");
        }
    };

    public static void bar(String something) {
        System.out.println("barring");
    }

    Map<String, Procedure> commands = new HashMap<String, Procedure>() {
        {
            put("run", () -> System.out.println("running"));
            put("give", () -> System.out.println("giving"));
            put("take", () -> System.out.println("taking"));
            put("walk", walking);
            put("bar", System.out::println);
        }

        @Override
        public Procedure get(Object var1) {
            System.out.println("using my custom get method");
            return super.get(var1);
        }
    };




    @Test
    public void testGenerator() {
        commands.getOrDefault("walk", () -> System.out.println("I dont know how to do that")).doit();
        commands.getOrDefault("bar", () -> System.out.println("I dont know how to do that")).doit();
        String otherCommand = "swim";
        commands.getOrDefault(otherCommand, () -> System.out.println("I dont know how to " + otherCommand)).doit();

        String message = MessageFormat.format("Hello, /{0}/{1}/{2}", "path", "to", "file");
        System.out.println(message);



    }
}
//public class foo {
//    public void bar() {
//        dothings();
//    }
//    public void yolo() {
//        dootherthings();
//    }
//    Procedure barProcedure = new Procedure() {
//        @Override
//        public void run() {
//            bar();
//        }
//    }
//}