package com.sda.auctionsservice.bonus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Playground {

    public Playground(String finalString) {
        this.finalString = finalString;
    }

    // Access modifiers in Java
    //4 access modifiers
    public void foo() {}
    protected void foo1() {}
    void foo2() {}
    private void foo3() {}

    public static void main(String[] args) {
        System.out.println("hello");
        var playground = new Playground("playground");
        playground.doSomething(1);

    }

    //Where can we use final

    final String finalString;

    void doSomething(final Integer arg) {
        //1 local variable
        final int finalInt = 3;
        //2 final on class - no extending possible on final class
        //
        final List<Integer> finalList = new ArrayList<>();

        //finalList = new ArrayList<>(); - not compile due to final

        finalList.add(arg);
        System.out.println(finalList);

        final List<Integer> finalFinalList = List.of(arg);

        //finalFinalList.add(2); // This violates SOLID Liscov substitution principle

        String s = "s";

        s = s + "a";

        System.out.println(s);

        //mane few methods on Object class
        //toString(), equals(), hashcode()

        //contract between hashcode and equals
        Playground a = new Playground("Object");
        Playground b = new Playground("Object");

        System.out.println("a.equals(b) = " + a.equals(b));

        System.out.println("a == b = " + (a == b));

        a.equals(b); // returns true -> a.hashcode() == b.hashcode()
        // a.hashCode() == b.hashCode() does not mean that objects are equal


        List<Integer> collect = List.of(1, 2, 3, 4, 5, 6)
                .stream()// stream creation operation
                .map(item -> item * 2)//intermidiet operation
                .collect(Collectors.toList());// terminal operation

        System.out.println("collect = " + collect);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Playground that = (Playground) o;

        return finalString.equals(that.finalString);
    }

    @Override
    public int hashCode() {
        return finalString.hashCode();
    }
}
