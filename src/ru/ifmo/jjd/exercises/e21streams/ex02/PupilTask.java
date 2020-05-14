package ru.ifmo.jjd.exercises.e21streams.ex02;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.ifmo.jjd.utils.ConsoleHelper.printf;
import static ru.ifmo.jjd.utils.ConsoleHelper.println;
import static ru.ifmo.jjd.utils.RandomHelper.randomInt;

public class PupilTask {
    public static void main(String[] args) {
        List<Pupil> pupils = Pupil.Generator.generate(randomInt(30, 50));
        println("Working with the following list of pupils:");
        println(pupils);
        println("\nGrouped by gender:");
        println(groupByGender(pupils));
        printf("\nAverage age of all pupils: %.2f years\n", getAverageAge(pupils));
        println("\nYoungest pupil: " + getYoungest(pupils));
        println("\nOldest pupil: " + getOldest(pupils));
        println("\nGrouped by birth year:");
        println(groupByBirthYear(pupils));
        println("\nPupils with unique names:");
        printHavingUniqueNames(pupils);
        println("\nSorted by gender, birth date, name (desc): ");
        println(sorted(pupils));
        int lower = randomInt(6, 12), upper = lower + randomInt(1, 6);
        println("\nPupils who are " + lower + " to " + upper + " years old");
        println(filterByAge(pupils, lower, upper));
        String name = Stream.of("Mike", "Tom", "Alex", "John", "Peter", "Jack", "Charlie", "Max", "Jenifer", "Linda",
                "Elizabeth", "Samantha", "Sophia").findAny().get();
        println("\nPupils called " + name);
        println(filterByName(pupils, name));
        println("\nHave summarized ages by gender:");
        println(sumAgesByGender(pupils));
    }

    public static Map<Pupil.Gender, ArrayList<Pupil>> groupByGender(List<Pupil> pupils) {
        return pupils.stream()
                .collect(Collectors.groupingBy(Pupil::getGender, Collectors.toCollection(ArrayList::new)));
    }

    public static double getAverageAge(List<Pupil> pupils) {
        return pupils.stream()
                .collect(Collectors.averagingDouble(p -> Period.between(p.getBirthday(), LocalDate.now()).getYears()));
    }

    public static Pupil getYoungest(List<Pupil> pupils) {
        return pupils.stream()
                .max(Comparator.comparing(Pupil::getBirthday))
                .orElseThrow();
    }

    public static Pupil getOldest(List<Pupil> pupils) {
        return pupils.stream()
                .min(Comparator.comparing(Pupil::getBirthday))
                .orElseThrow();
    }

    public static Map<Integer, List<Pupil>> groupByBirthYear(List<Pupil> pupils) {
        return pupils.stream()
                .collect(Collectors.groupingBy(p -> p.getBirthday().getYear()));
    }

    public static void printHavingUniqueNames(List<Pupil> pupils) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMM yy", Locale.ENGLISH);
        pupils.stream()
                .collect(Collectors.toMap(Pupil::getName, Pupil::getBirthday, (pupil, pupil2) -> pupil))
                .forEach((name, date) -> System.out.println(name + ": " + date.format(format)));
    }

    public static List<Pupil> sorted(List<Pupil> pupils) {
        return pupils.stream()
                .sorted(Comparator.comparing(Pupil::getGender)
                        .thenComparing(Pupil::getBirthday)
                        .thenComparing(Comparator.comparing(Pupil::getName).reversed()))
                .collect(Collectors.toList());
    }

    public static List<Pupil> filterByAge(List<Pupil> pupils, int lower, int upper) {
        if (lower >= upper) throw new IllegalArgumentException(lower + " must be less than " + upper);
        return pupils.stream()
                .filter(pupil -> {
                    int age = Period.between(pupil.getBirthday(), LocalDate.now()).getYears();
                    return age >= lower && age <= upper;
                })
                .collect(Collectors.toList());
    }

    public static List<Pupil> filterByName(List<Pupil> pupils, String name) {
        return name == null ? Collections.emptyList() : pupils.stream()
                .filter(pupil -> pupil.getName().equalsIgnoreCase(name.trim()))
                .collect(Collectors.toList());
    }

    public static Map<Pupil.Gender, Integer> sumAgesByGender(List<Pupil> pupils) {
        return pupils.stream()
                .collect(Collectors.groupingBy(Pupil::getGender,
                        Collectors.summingInt(pupil -> Period.between(pupil.getBirthday(), LocalDate.now())
                                .getYears())));
    }
}
/*
 * Working with the following list of pupils:
 * {Pupil{number=1, name='Tom', gender=male, birthday='9 Jan 11'}
 *  Pupil{number=2, name='Jenifer', gender=female, birthday='23 Nov 11'}
 *  Pupil{number=3, name='Linda', gender=female, birthday='15 Feb 14'}
 *  Pupil{number=4, name='Charlie', gender=male, birthday='11 Jun 04'}
 *  Pupil{number=5, name='Charlie', gender=male, birthday='10 Jun 07'}
 *  Pupil{number=6, name='Jenifer', gender=female, birthday='30 Nov 11'}
 *  Pupil{number=7, name='Linda', gender=female, birthday='30 Oct 11'}
 *  Pupil{number=8, name='Tom', gender=male, birthday='2 May 13'}
 *  Pupil{number=9, name='Sophia', gender=female, birthday='15 Jun 12'}
 *  Pupil{number=10, name='Elizabeth', gender=female, birthday='24 Dec 07'}
 *  Pupil{number=11, name='Charlie', gender=male, birthday='8 Apr 07'}
 *  Pupil{number=12, name='Elizabeth', gender=female, birthday='16 Aug 12'}
 *  Pupil{number=13, name='Charlie', gender=male, birthday='11 Jan 09'}
 *  Pupil{number=14, name='Linda', gender=female, birthday='11 Apr 14'}
 *  Pupil{number=15, name='Samantha', gender=female, birthday='3 Apr 08'}
 *  Pupil{number=16, name='Elizabeth', gender=female, birthday='25 May 08'}
 *  Pupil{number=17, name='Peter', gender=male, birthday='29 Jun 08'}
 *  Pupil{number=18, name='Elizabeth', gender=female, birthday='28 May 11'}
 *  Pupil{number=19, name='Sophia', gender=female, birthday='1 Nov 13'}
 *  Pupil{number=20, name='Max', gender=male, birthday='1 Jun 11'}
 *  Pupil{number=21, name='Jack', gender=male, birthday='9 Feb 10'}
 *  Pupil{number=22, name='Alex', gender=male, birthday='16 Jan 11'}
 *  Pupil{number=23, name='Jack', gender=male, birthday='5 Dec 08'}
 *  Pupil{number=24, name='Tom', gender=male, birthday='1 Sep 08'}
 *  Pupil{number=25, name='Sophia', gender=female, birthday='20 Nov 05'}
 *  Pupil{number=26, name='Elizabeth', gender=female, birthday='16 Apr 12'}
 *  Pupil{number=27, name='Peter', gender=male, birthday='7 Apr 07'}
 *  Pupil{number=28, name='Mike', gender=male, birthday='24 Sep 10'}
 *  Pupil{number=29, name='Jack', gender=male, birthday='5 Aug 09'}
 *  Pupil{number=30, name='Alex', gender=male, birthday='19 Jun 11'}}
 *
 * Grouped by gender:
 * {FEMALE=[Pupil{number=2, name='Jenifer', gender=female, birthday='23 Nov 11'},
 *      Pupil{number=3, name='Linda', gender=female, birthday='15 Feb 14'},
 *      Pupil{number=6, name='Jenifer', gender=female, birthday='30 Nov 11'},
 *      Pupil{number=7, name='Linda', gender=female, birthday='30 Oct 11'},
 *      Pupil{number=9, name='Sophia', gender=female, birthday='15 Jun 12'},
 *      Pupil{number=10, name='Elizabeth', gender=female, birthday='24 Dec 07'},
 *      Pupil{number=12, name='Elizabeth', gender=female, birthday='16 Aug 12'},
 *      Pupil{number=14, name='Linda', gender=female, birthday='11 Apr 14'},
 *      Pupil{number=15, name='Samantha', gender=female, birthday='3 Apr 08'},
 *      Pupil{number=16, name='Elizabeth', gender=female, birthday='25 May 08'},
 *      Pupil{number=18, name='Elizabeth', gender=female, birthday='28 May 11'},
 *      Pupil{number=19, name='Sophia', gender=female, birthday='1 Nov 13'},
 *      Pupil{number=25, name='Sophia', gender=female, birthday='20 Nov 05'},
 *      Pupil{number=26, name='Elizabeth', gender=female, birthday='16 Apr 12'}]
 *  MALE=[Pupil{number=1, name='Tom', gender=male, birthday='9 Jan 11'},
 *      Pupil{number=4, name='Charlie', gender=male, birthday='11 Jun 04'},
 *      Pupil{number=5, name='Charlie', gender=male, birthday='10 Jun 07'},
 *      Pupil{number=8, name='Tom', gender=male, birthday='2 May 13'},
 *      Pupil{number=11, name='Charlie', gender=male, birthday='8 Apr 07'},
 *      Pupil{number=13, name='Charlie', gender=male, birthday='11 Jan 09'},
 *      Pupil{number=17, name='Peter', gender=male, birthday='29 Jun 08'},
 *      Pupil{number=20, name='Max', gender=male, birthday='1 Jun 11'},
 *      Pupil{number=21, name='Jack', gender=male, birthday='9 Feb 10'},
 *      Pupil{number=22, name='Alex', gender=male, birthday='16 Jan 11'},
 *      Pupil{number=23, name='Jack', gender=male, birthday='5 Dec 08'},
 *      Pupil{number=24, name='Tom', gender=male, birthday='1 Sep 08'},
 *      Pupil{number=27, name='Peter', gender=male, birthday='7 Apr 07'},
 *      Pupil{number=28, name='Mike', gender=male, birthday='24 Sep 10'},
 *      Pupil{number=29, name='Jack', gender=male, birthday='5 Aug 09'},
 *      Pupil{number=30, name='Alex', gender=male, birthday='19 Jun 11'}]}
 *
 * Average age of all pupils: 9.60 years
 *
 * Youngest pupil: Pupil{number=14, name='Linda', gender=female, birthday='11 Apr 14'}
 *
 * Oldest pupil: Pupil{number=4, name='Charlie', gender=male, birthday='11 Jun 04'}
 *
 * Grouped by birth year:
 * {2004=[Pupil{number=4, name='Charlie', gender=male, birthday='11 Jun 04'}]
 *  2005=[Pupil{number=25, name='Sophia', gender=female, birthday='20 Nov 05'}]
 *  2007=[Pupil{number=5, name='Charlie', gender=male, birthday='10 Jun 07'},
 *      Pupil{number=10, name='Elizabeth', gender=female, birthday='24 Dec 07'},
 *      Pupil{number=11, name='Charlie', gender=male, birthday='8 Apr 07'},
 *      Pupil{number=27, name='Peter', gender=male, birthday='7 Apr 07'}]
 *  2008=[Pupil{number=15, name='Samantha', gender=female, birthday='3 Apr 08'},
 *      Pupil{number=16, name='Elizabeth', gender=female, birthday='25 May 08'},
 *      Pupil{number=17, name='Peter', gender=male, birthday='29 Jun 08'},
 *      Pupil{number=23, name='Jack', gender=male, birthday='5 Dec 08'},
 *      Pupil{number=24, name='Tom', gender=male, birthday='1 Sep 08'}]
 *  2009=[Pupil{number=13, name='Charlie', gender=male, birthday='11 Jan 09'},
 *      Pupil{number=29, name='Jack', gender=male, birthday='5 Aug 09'}]
 *  2010=[Pupil{number=21, name='Jack', gender=male, birthday='9 Feb 10'},
 *      Pupil{number=28, name='Mike', gender=male, birthday='24 Sep 10'}]
 *  2011=[Pupil{number=1, name='Tom', gender=male, birthday='9 Jan 11'},
 *      Pupil{number=2, name='Jenifer', gender=female, birthday='23 Nov 11'},
 *      Pupil{number=6, name='Jenifer', gender=female, birthday='30 Nov 11'},
 *      Pupil{number=7, name='Linda', gender=female, birthday='30 Oct 11'},
 *      Pupil{number=18, name='Elizabeth', gender=female, birthday='28 May 11'},
 *      Pupil{number=20, name='Max', gender=male, birthday='1 Jun 11'},
 *      Pupil{number=22, name='Alex', gender=male, birthday='16 Jan 11'},
 *      Pupil{number=30, name='Alex', gender=male, birthday='19 Jun 11'}]
 *  2012=[Pupil{number=9, name='Sophia', gender=female, birthday='15 Jun 12'},
 *      Pupil{number=12, name='Elizabeth', gender=female, birthday='16 Aug 12'},
 *      Pupil{number=26, name='Elizabeth', gender=female, birthday='16 Apr 12'}]
 *  2013=[Pupil{number=8, name='Tom', gender=male, birthday='2 May 13'},
 *      Pupil{number=19, name='Sophia', gender=female, birthday='1 Nov 13'}]
 *  2014=[Pupil{number=3, name='Linda', gender=female, birthday='15 Feb 14'},
 *      Pupil{number=14, name='Linda', gender=female, birthday='11 Apr 14'}]}
 *
 * Pupils with unique names:
 * Alex: 16 Jan 11
 * Mike: 24 Sep 10
 * Elizabeth: 24 Dec 07
 * Tom: 9 Jan 11
 * Max: 1 Jun 11
 * Charlie: 11 Jun 04
 * Jenifer: 23 Nov 11
 * Samantha: 3 Apr 08
 * Peter: 29 Jun 08
 * Jack: 9 Feb 10
 * Sophia: 15 Jun 12
 * Linda: 15 Feb 14
 *
 * Sorted by gender, birth date, name (desc):
 * {Pupil{number=4, name='Charlie', gender=male, birthday='11 Jun 04'}
 *  Pupil{number=27, name='Peter', gender=male, birthday='7 Apr 07'}
 *  Pupil{number=11, name='Charlie', gender=male, birthday='8 Apr 07'}
 *  Pupil{number=5, name='Charlie', gender=male, birthday='10 Jun 07'}
 *  Pupil{number=17, name='Peter', gender=male, birthday='29 Jun 08'}
 *  Pupil{number=24, name='Tom', gender=male, birthday='1 Sep 08'}
 *  Pupil{number=23, name='Jack', gender=male, birthday='5 Dec 08'}
 *  Pupil{number=13, name='Charlie', gender=male, birthday='11 Jan 09'}
 *  Pupil{number=29, name='Jack', gender=male, birthday='5 Aug 09'}
 *  Pupil{number=21, name='Jack', gender=male, birthday='9 Feb 10'}
 *  Pupil{number=28, name='Mike', gender=male, birthday='24 Sep 10'}
 *  Pupil{number=1, name='Tom', gender=male, birthday='9 Jan 11'}
 *  Pupil{number=22, name='Alex', gender=male, birthday='16 Jan 11'}
 *  Pupil{number=20, name='Max', gender=male, birthday='1 Jun 11'}
 *  Pupil{number=30, name='Alex', gender=male, birthday='19 Jun 11'}
 *  Pupil{number=8, name='Tom', gender=male, birthday='2 May 13'}
 *  Pupil{number=25, name='Sophia', gender=female, birthday='20 Nov 05'}
 *  Pupil{number=10, name='Elizabeth', gender=female, birthday='24 Dec 07'}
 *  Pupil{number=15, name='Samantha', gender=female, birthday='3 Apr 08'}
 *  Pupil{number=16, name='Elizabeth', gender=female, birthday='25 May 08'}
 *  Pupil{number=18, name='Elizabeth', gender=female, birthday='28 May 11'}
 *  Pupil{number=7, name='Linda', gender=female, birthday='30 Oct 11'}
 *  Pupil{number=2, name='Jenifer', gender=female, birthday='23 Nov 11'}
 *  Pupil{number=6, name='Jenifer', gender=female, birthday='30 Nov 11'}
 *  Pupil{number=26, name='Elizabeth', gender=female, birthday='16 Apr 12'}
 *  Pupil{number=9, name='Sophia', gender=female, birthday='15 Jun 12'}
 *  Pupil{number=12, name='Elizabeth', gender=female, birthday='16 Aug 12'}
 *  Pupil{number=19, name='Sophia', gender=female, birthday='1 Nov 13'}
 *  Pupil{number=3, name='Linda', gender=female, birthday='15 Feb 14'}
 *  Pupil{number=14, name='Linda', gender=female, birthday='11 Apr 14'}}
 *
 * Pupils who are 10 to 14 years old
 * {Pupil{number=5, name='Charlie', gender=male, birthday='10 Jun 07'}
 *  Pupil{number=10, name='Elizabeth', gender=female, birthday='24 Dec 07'}
 *  Pupil{number=11, name='Charlie', gender=male, birthday='8 Apr 07'}
 *  Pupil{number=13, name='Charlie', gender=male, birthday='11 Jan 09'}
 *  Pupil{number=15, name='Samantha', gender=female, birthday='3 Apr 08'}
 *  Pupil{number=16, name='Elizabeth', gender=female, birthday='25 May 08'}
 *  Pupil{number=17, name='Peter', gender=male, birthday='29 Jun 08'}
 *  Pupil{number=21, name='Jack', gender=male, birthday='9 Feb 10'}
 *  Pupil{number=23, name='Jack', gender=male, birthday='5 Dec 08'}
 *  Pupil{number=24, name='Tom', gender=male, birthday='1 Sep 08'}
 *  Pupil{number=25, name='Sophia', gender=female, birthday='20 Nov 05'}
 *  Pupil{number=27, name='Peter', gender=male, birthday='7 Apr 07'}
 *  Pupil{number=29, name='Jack', gender=male, birthday='5 Aug 09'}}
 *
 * Pupils called Mike
 * {Pupil{number=28, name='Mike', gender=male, birthday='24 Sep 10'}}
 *
 * Have summarized ages by gender:
 * {FEMALE=121
 *  MALE=167}
 * */












