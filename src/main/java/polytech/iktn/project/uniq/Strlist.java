package polytech.iktn.project.uniq;

import java.io.*;
import java.util.*;

public class Strlist {
    /***************************************************
     1 Метод для получения данных из файла или консоли
     ***************************************************/
    static ArrayList<String> getData(String inFile) {
        ArrayList<String> data = new ArrayList<>();
        try {
            // Читаем данные из файла
            if (!inFile.equals("")) {
                File file = new File(inFile);
                FileReader fr = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fr);
                String line = bufferedReader.readLine();
                while (line != null) {
                    data.add(line);// Добавляем прочитанную строку в результирующий список
                    line = bufferedReader.readLine();
                }
                fr.close();
            } else {
                // Читаем данные с консоли
                Scanner in = new Scanner(System.in);
                System.out.print("Введите строку или Ctrl+D для завершения ввода: ");
                String line = in.nextLine();
                while (line != null) {
                    data.add(line); // Добавляем прочитанную строку в результирующий список
                    System.out.print("Введите строку или Ctrl+D: ");
                    line = in.nextLine();
                }
                in.close(); // Закрываем консоль
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла");
            System.exit(1);
        } catch (NoSuchElementException e) { // Исключение при нажатии Ctrl+D
            System.out.println("<EOF>"); // Выводим сообщение "Конец файла" и выходим из метода
        }

        return data;
    }

    /******************************
     2 Метод для обработки данных
     ******************************/
    public ArrayList<String> refactorData(ArrayList<String> inputStr,
                                          boolean caseInsensitiveMode, boolean uniqeMode,
                                          boolean countMode, int numIgnor) {
        ArrayList<String> data = new ArrayList<>();

        int i = 1;
        int n = 0; // Счётчик повторов строк для префикса

        if (inputStr.size() == 0) System.exit(0); // Данных нет, выходим

        String a = inputStr.get(0);
        if (inputStr.size() == 1) { data.add(a); return data; }  // Если строка 1 её и возвращаем

        while (i < inputStr.size()) {
            String b = inputStr.get(i);
            // Если строки/участки совпадают увеличиваем счётчик префикса
            if (a.regionMatches(caseInsensitiveMode, numIgnor,
                    b, numIgnor, Math.max(a.length() - numIgnor,
                            b.length() - numIgnor))) n++;
            else { // Строки разные, сохраняем результат
                if (uniqeMode && (n == 0)) data.add(a);
                else {
                    if (!uniqeMode && countMode && (n != 0))
                        data.add(++n + " " + a); // Если нужно добавляем префикс 2 и более
                    else if (!uniqeMode) data.add(a); // Добавляем строку без префикса 1
                }
                a = b;
                n = 0;
            }
            // Если достигли конца данных сохраняем последний результат
            if ((i + 1) == inputStr.size()) {
                if (uniqeMode && (n == 0)) data.add(a);
                else {
                    if (!uniqeMode && countMode && (n != 0))
                        data.add(++n + " " + a); // Если нужно добавляем префикс 2 и более
                    else if (!uniqeMode) data.add(a); // Добавляем строку без префикса 1
                }
                break;
            }
            i++;
        }
        return data;
    }

    /*******************************************************
    3 Метод записи результата в файл или вывода на консоль
     *******************************************************/
    public void type(ArrayList<String> outputStr, String outputName) {
        try {
            // Запись результата в файл
            if (!outputName.equals("")) {
                FileWriter writer = new FileWriter(outputName);

                for (String s : outputStr) {
                    writer.write(s); // Записываем строку
                    writer.append(System.lineSeparator()); // Добовляем перевод строки
                }
                writer.flush(); // Сбрасываем данные на диск
                writer.close();
            }
            // Вывод результата на консоль
            else {
                for (String s : outputStr) {
                    System.out.println(s);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл");
            System.exit(1);
        }
    }
}
