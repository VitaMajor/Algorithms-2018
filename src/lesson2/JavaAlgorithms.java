package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     *
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     *
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     *
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     *
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     *
     * 1 2 3
     * 8   4
     * 7 6 5
     *
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     *
     * 1 2 3
     * 8   4
     * 7 6 х
     *
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     *
     * 1 х 3
     * 8   4
     * 7 6 Х
     *
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     *
     * 1 Х 3
     * х   4
     * 7 6 Х
     *
     * 1 Х 3
     * Х   4
     * х 6 Х
     *
     * х Х 3
     * Х   4
     * Х 6 Х
     *
     * Х Х 3
     * Х   х
     * Х 6 Х
     *
     * Х Х 3
     * Х   Х
     * Х х Х
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     *
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    // Трудоемкость: T = O(m * n)
    // Ресурсоемкость: R = O(n)
    static public String longestCommonSubstring(String firs, String second) {

        int maxLength = 0;
        String maxLengthStr = "";
        int firsLength = firs.length();
        int secondLength = second.length();
        int[][] table = new int[firsLength + 1][secondLength + 1];

        for (int i = 1; i <= firsLength; i++) {
            for (int j = 1; j <= secondLength; j++) {
                if (firs.charAt(i - 1) == second.charAt(j-1)) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                    if (table[i][j] > maxLength)
                        maxLength = table[i][j];
                }
            }
        }

        for (int i = 1; i <= firsLength - maxLength; i++) {
            for (int j = 1; j <= secondLength - maxLength; j++) {
                if (firs.substring(i - 1,i + maxLength - 1).equals(second.substring(j - 1, j + maxLength - 1))) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                    if (table[i][j] > maxLengthStr.length()) {
                        maxLengthStr = firs.substring(i - 1, i + maxLength - 1);
                        break;
                    }
                }
            }
        }
        return maxLengthStr;
    }

    /**
     * Число простых чисел в интервале
     * Простая
     *
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     *
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        throw new NotImplementedError();
    }

    /**
     * Балда
     * Сложная
     *
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     *
     * И Т Ы Н
     * К Р А Н
     * А К В А
     *
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     *
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     *
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     *
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    // Трудоемкость: T = O(n * m * k)
    // Ресурсоемкость: R = O(n)
    static public Set<String> baldaSearcher(String inputName, Set<String> words) {
        Set<String> str = new LinkedHashSet<>();
        StringBuilder sb = new StringBuilder();
        File InputFile = new File(inputName);

        try {
            BufferedReader in = new BufferedReader(new FileReader(InputFile.getAbsoluteFile()));
        try {
            String s;
            while ((s = in.readLine()) != null) {
                sb.append(s);
                sb.append("\n");
            }
        } finally {
            in.close();
        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String[] lines = sb.toString().split("\n");
        String[] letters = lines[0].split(" ");
        String[][] field = new String[lines.length][letters.length];
        boolean[][] wasHere = new boolean[lines.length][letters.length];

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < letters.length; j++) {
                field[i][j] = String.valueOf(lines[i].charAt(2 * (j)));
                wasHere[i][j] = false;
            }
        }

        for (String word: words) {
            for (int i = 0; i < lines.length; i++) {
                for (int j = 0; j < letters.length; j++) {
                    if (field[i][j].equals(String.valueOf(word.charAt(0)))) {
                        if (look(field, i, j,lines.length - 1,letters.length - 1, word, wasHere)) {
                            str.add(word);
                        }
                        refresh(lines.length, letters.length, wasHere);
                    }
                }
            }
        }
        return str;
    }

    private static void refresh (int maxLine, int maxColumn, boolean[][] wasHere){
        for (int i = 0; i < maxLine; i++) {
            for (int j = 0; j < maxColumn; j++) {
                wasHere[i][j] = false;
            }
        }
    }

    private static boolean look (String[][] field, int line, int column, int maxLine, int maxColumn, String word, boolean[][] wasHere) {

        wasHere[line][column] = true;

        if (word.length() == 1){
            return true;
        }
        if (line > 0) {
            if (field[line - 1][column].equals(String.valueOf(word.charAt(1))) && !wasHere[line - 1][column]) {
                if (look(field,line - 1, column, maxLine, maxColumn, word.substring(1, word.length()), wasHere)){
                    return true;
                }
            }
        }
        if (column > 0) {
            if (field[line][column - 1].equals(String.valueOf(word.charAt(1))) && !wasHere[line][column - 1]) {
                if (look(field, line,column - 1, maxLine, maxColumn, word.substring(1, word.length()), wasHere)){
                    return true;
                }
            }
        }
        if (line < maxLine) {
            if (field[line + 1][column].equals(String.valueOf(word.charAt(1))) && !wasHere[line + 1][column]) {
                if (look(field,line + 1, column, maxLine, maxColumn, word.substring(1, word.length()), wasHere)){
                    return true;
                }
            }
        }
        if (column < maxColumn) {
            if (field[line][column + 1].equals(String.valueOf(word.charAt(1))) && !wasHere[line][column + 1]) {
                if (look(field, line,column + 1, maxLine, maxColumn, word.substring(1, word.length()), wasHere)){
                    return true;
                }
            }
        }
        return false;
    }
}
