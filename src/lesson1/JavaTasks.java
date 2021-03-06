package lesson1;

import kotlin.NotImplementedError;

import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     *
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС,
     * каждый на отдельной строке. Пример:
     *
     * 13:15:19
     * 07:26:57
     * 10:00:03
     * 19:56:14
     * 13:15:19
     * 00:40:31
     *
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС. Одинаковые моменты времени выводить друг за другом. Пример:
     *
     * 00:40:31
     * 07:26:57
     * 10:00:03
     * 13:15:19
     * 13:15:19
     * 19:56:14
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) { throw new NotImplementedError();}

    /**
     * Сортировка адресов
     *
     * Средняя
     *
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     *
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     *
     * Людей в городе может быть до миллиона.
     *
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     *
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     *
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     *
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     *
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     *
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    // Трудоемкость: T = O(log(n))
    // Ресурсоемкость: R = O(n)
    static public void sortTemperatures(String inputName, String outputName) {
        try {
            StringBuilder sb = new StringBuilder();
            File InputFile = new File(inputName);
            File outputFile = new File(outputName);
            BufferedReader in = new BufferedReader(new FileReader(InputFile.getAbsoluteFile()));
            int end = 0;
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                    end++;
                }
            } finally {
                in.close();
            }

            ArrayList<Float> floatList = new ArrayList<>();

            for (String part : sb.toString().split("\n")) {
                Float fl = Float.parseFloat(part);
                if (fl >= -273.0 && fl <= 500.0) {
                    floatList.add(fl);
                } else {
                    throw  new IllegalArgumentException("Число вне диапазона");
                }
            }

            sort(0, end - 1, floatList);

            PrintWriter out = new PrintWriter(outputFile.getAbsoluteFile());

            try {
                for (float f: floatList) {
                    out.print(f);
                    out.print("\n");
                }
            } finally {
                out.close();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void sort(int start, int end, List<Float> floats){
        if (start >= end){
            return;
        }
        int i = start;
        int j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (floats.get(i) <= floats.get(cur))) {
                i++;
            }
            while (j > cur && (floats.get(cur) <= floats.get(j))) {
                j--;
            }
            if (i < j) {
                float temp = floats.get(i);
                floats.set(i,floats.get(j));
                floats.set(j,temp);
                if (i == cur) {
                    cur = j;
                } else if (j == cur) {
                    cur = i;
                }
            }
        }
        sort(start,cur, floats);
        sort(cur + 1, end, floats);
    }

    /**
     * Сортировка последовательности
     *
     * Средняя
     * (Задача взята с сайта acmp.ru)
     *
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     *
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     *
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     *
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    // Трудоемкость: T = O(n)
    // Ресурсоемкость: R = O(n)
    static public void sortSequence(String inputName, String outputName) {
        try {
            StringBuilder sb = new StringBuilder();
            File InputFile = new File(inputName);
            File outputFile = new File(outputName);
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

            Map<Integer, Integer> hashMap = new HashMap<>();
            String st;
            int maxSize = 0;
            int maxPoint = 0;

            for (String part : sb.toString().split("\n")) {
                Integer point = Integer.parseInt(part);
                Integer count = hashMap.get(point);

                if (count == null) {
                    hashMap.put(point, 1);
                } else {
                    hashMap.put(point, hashMap.get(point) + 1);
                    if (hashMap.get(point) > maxSize) {
                        maxSize = hashMap.get(point);
                        maxPoint = point;
                    }
                    else if (hashMap.get(point) >= maxSize && (maxPoint > point || maxPoint == 0)) {
                        maxSize = hashMap.get(point);
                        maxPoint = point;
                    }
                }
            }

            PrintWriter out = new PrintWriter(outputFile.getAbsoluteFile());

            try {
                for (String part : sb.toString().split("\n")) {
                    Integer point = Integer.parseInt(part);
                    if (point != maxPoint) {
                        out.print(part);
                        out.print("\n");
                    }
                }
                for (int i = 1; i <= maxSize; i++) {
                    out.print(maxPoint);
                    out.print("\n");
                }
            } finally {
                out.close();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Соединить два отсортированных массива в один
     *
     * Простая
     *
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     *
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     *
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
