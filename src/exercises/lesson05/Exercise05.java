package exercises.lesson05;

import static utils.ConsoleHelper.*;
import static utils.StringHelper.*;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Даны два слова и словарь (массив слов).
 * Необходимо найти алгоритм превращения в другое, меняя за один шаг одну букву, причем каждое новое слово должно
 * быть в словаре (массиве слов).
 * Например,даны слова "hit" и "cog" и словарь(массив слов) ["hot", "dot", "dog", "log", "lot"].
 * Один из вариантов цепочки: "hit" -> "hot" -> "dot" -> "dog" -> "cog".
 * */
public class Exercise05 {
    private static ArrayList<String> dictionary = new ArrayList<>();
    private static ArrayList<ArrayList<String>> graph = new ArrayList<>();
    private static ArrayList<String> path = new ArrayList<>();

    public static void main(String[] args) {
        getUserInput();
        buildGraph();
        println();
        println("Ищем путь от \"" + dictionary.get(0) + "\" до \"" + dictionary.get(dictionary.size() - 1)
                + "\" испрользуя словарь:");
        printDictionary();
        if (graph.get(0).size() == 1 || graph.get(graph.size() - 1).size() == 1) {
            println("Задача решения не имеет.");         // начальное или конечное слово недостижимы из словаря
            return;
        }
        path.add(dictionary.get(0));
        if (buildPath()) {
            printPath();
        } else {
            println("Задача решения не имеет.");
        }
    }

    private static void getUserInput() {
        print("""
                Программа ищет путь, по которому можно преобразовать одно слово в другое,
                каждый раз заменяя только одну букву и используя только слова из заданного словаря.

                Программа ожидает последовательный ввод слов словаря, и начального и конечного слова.
                Чтобы завершить ввод словаря, оставьте ввод пустым.
                Все небуквенные символы будут проигнорированы.
                Введите "random", чтобы использовать случайно сгенерированные значения.
                Оставьте ввод изначально пустым, чтобы использовать значения по умолчанию.
                """);
        String input;
        while (true) {
            if (dictionary.size() >= 20) {                      // вычислительная сложность алгоритма buildPath()
                println("Словарь сформирован: " + dictionary);  // будет O(n^n)
                break;                                          // поэтому нельзя допускать слишком большой словарь.
            }
            input = readLine("Введите слово для словаря: ").toLowerCase().replaceAll("[^a-zа-я]+", "");
            if (input.length() == 0) {
                if (dictionary.isEmpty()) {
                    Collections.addAll(dictionary, "лужа", "кора", "лоза", "горе", "коза", "кожа", "роза",
                            "гора", "карп", "серп", "ложа", "луза", "кара", "окно", "сено", "руно", "море");
                    return;
                } else {
                    println("Словарь сформирован: " + dictionary);
                    break;
                }
            } else if (input.replaceAll("\\W+", "").equalsIgnoreCase("random")) {
                dictionary.clear();
                dictionary.add(randomWord(4));
                while (dictionary.size() < 20) {
                    String candidate = randomWord(4);
                    for (String word : dictionary) {
                        if (isNear(candidate, word)) {
                            offer(dictionary, candidate);
                            break;
                        }
                    }
                }
                return;
            } else {
                int success = offer(dictionary, input);
                if (success < 0) {
                    println("Все слова должны быть одинаковой длины");
                } else if (success == 0) {
                    println("Такое слово уже есть в словаре");
                }
            }
        }
        while (true) {
            input = readLine("Введите начальное слово: ")
                    .toLowerCase().replaceAll("[^a-zа-я]+", "");
            if (input.length() == 0) {
                continue;
            }
            int success = offerFirst(dictionary, input);
            if (success < 0) {
                println("Все слова должны быть одинаковой длины");
            } else if (success == 0) {
                println("Такое слово уже есть в словаре");
            } else {
                break;
            }
        }
        while (true) {
            input = readLine("Введите конечное слово: ")
                    .toLowerCase().replaceAll("[^a-zа-я]+", "");
            if (input.length() == 0) {
                continue;
            }
            int success = offer(dictionary, input);
            if (success < 0) {
                println("Все слова должны быть одинаковой длины");
            } else if (success == 0) {
                println("Такое слово уже есть в словаре");
            } else {
                break;
            }
        }
    }

    private static boolean isNear(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        } else {
            s1 = s1.trim().toLowerCase().replaceAll("[^a-zа-я]+", "");
            s2 = s2.trim().toLowerCase().replaceAll("[^a-zа-я]+", "");
        }
        if (s1.length() == 0 || s2.length() == 0 || s1.equals(s2) || s1.length() != s2.length()) {
            return false;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int count = 0;
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i] && ++count > 1) break;
        }
        return count == 1;
    }

    private static int offer(ArrayList<String> list, String s) {
        if (list.isEmpty()) {
            list.add(s);
            return 1;
        } else if (list.contains(s)) {                          // значения должны быть уникальны
            return 0;
        } else if (s.length() != list.get(0).length()) {        // задача имеет смысл только для слов одинаковой длины
            return -1;
        } else {
            list.add(s);
            return 1;
        }
    }

    private static int offerFirst(ArrayList<String> list, String s) {   // для алгоритма нужно иметь возможность
        if (list.isEmpty()) {                                           // поместить начальное слово в начало словаря
            list.add(s);
            return 1;
        } else if (list.contains(s)) {
            return 0;
        } else if (s.length() != list.get(0).length()) {
            return -1;
        } else {
            list.add(0, s);
            return 1;
        }
    }

    private static void buildGraph() {
        for (String word : dictionary) {
            graph.add(new ArrayList<>());
            graph.get(graph.size() - 1).add(word);
        }
        for (ArrayList<String> node : graph) {
            for (String word : dictionary) {
                if (isNear(node.get(0), word)) {
                    node.add(word);
                }
            }
        }
    }

    private static boolean buildPath() {
        String currentNode = path.get(path.size() - 1);
        if (currentNode.equals(dictionary.get(dictionary.size() - 1))) {
            return true;
        }
        for (String nextNode : graph.get(dictionary.indexOf(currentNode))) {
            if (path.contains(nextNode)) continue;
            path.add(nextNode);
            if (buildPath()) {
                return true;
            } else {
                path.remove(nextNode);
            }
        }
        return false;
    }

    private static void printPath() {
        print(path.get(0));
        for (int i = 1; i < path.size(); i++) {
            print(" -> ");
            print(path.get(i));
        }
        println();
    }

    private static void printDictionary() {
        print("[");
        print(dictionary.get(1));
        for (int i = 2; i < dictionary.size() - 1; i++) {
            print(", ");
            print(dictionary.get(i));
        }
        println("]");
    }
}
/*
 * output:
 * Программа ищет путь, по которому можно преобразовать одно слово в другое,
 * каждый раз заменяя только одну букву и используя только слова из заданного словаря.
 * Программа ожидает последовательный ввод слов словаря, и начального и конечного слова.
 * Чтобы завершить ввод словаря, оставьте ввод пустым.
 * Все небуквенные символы будут проигнорированы.
 * Введите "random", чтобы использовать случайно сгенерированные значения.
 * Оставьте ввод изначально пустым, чтобы использовать значения по умолчанию.
 * Введите слово для словаря:
 *
 * Ищем пусть от "лужа" до "море" испрользуя словарь:
 * [кора, лоза, горе, коза, кожа, роза, гора, карп, серп, ложа, луза, кара, окно, сено, руно]
 * лужа -> ложа -> лоза -> коза -> кора -> гора -> горе -> море
 * */
