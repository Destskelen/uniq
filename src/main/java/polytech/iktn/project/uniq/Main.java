package polytech.iktn.project.uniq;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int index;
        String inputName = ""; // Переменная имя входного файла/пустая строка - ввод с консоли
        String outputName = ""; // Переменная имя выходного файла/пустая - вывод на консоль
        boolean caseInsensitiveMode = false; // Флаг не учитывать регистр символов
        int numIgnor = 0; // Число игнорируемых символов
        boolean uniqeMode = false; // Флаг только уникальные строки
        boolean countMode = false; // Флаг вывод с префиксом

        loop: for (index = 0; index < args.length; index++) {
            String opt = args[index];
            switch (opt) {
                case "-i":
                    caseInsensitiveMode = true; // Включаем регистро-независимый режим работы
                    break;
                case "-u":
                    uniqeMode = true; // Включаем режим работы с уникальными строками
                    break;
                case "-c":
                    countMode = true; // Включаем режим вывода префикса строк
                    break;
                case "-s":
                    index++;
                    try {
                        numIgnor = Integer.parseInt(args[index]); // Устанавливаем число игнорируемых символов
                    } catch (NumberFormatException e) {
                        System.err.println("Ошибка в аргументе s");
                        System.exit(1);
                    }
                    //System.out.println(numIgnor);
                    break;
                case "-o":
                    index++;
                    outputName = args[index]; // Устанавливаем имя выходного файла
                    //System.out.println(outputName);
                    break;
                default:
                    if (!opt.isEmpty() && opt.charAt(0) == '-') {
                        error("Unknown option: '" + opt + "'");
                    }
                    break loop;
            }
        }
        /*if (index >= args.length) {
            error("Missing argument(s)");
        }*/
        if (index < args.length) inputName = args[index]; // Устанавливаем имя входного файла
        //System.out.println(inputName);

        Strlist strList = new Strlist();
        // Получаем данные
        ArrayList<String> inputStr = Strlist.getData(inputName);
        // Обрабатываем данные
        ArrayList<String> outputStr = strList.refactorData(inputStr, caseInsensitiveMode,
                uniqeMode, countMode, numIgnor);
        // Выводим данные
        strList.type(outputStr, outputName);
    }

    /***************************************
     * Выводим сообщение об ошибке и справку
     ***************************************/
    private static void error(String message) {
        if (message != null) {
            System.err.println(message);
        }
        System.err.println("usage: uniq [-i] [-u] [-c] [-s num] [-o ofile] [file]");
        System.exit(1);
    }
}