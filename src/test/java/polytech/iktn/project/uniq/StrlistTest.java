package polytech.iktn.project.uniq;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StrlistTest {

    @Test
    // Тест на простой поиск
    public void test01() {
        ArrayList<String> inStr = new ArrayList<>();
        ArrayList<String> expStr = new ArrayList<>();
        ArrayList<String> actualStr;

        inStr.add("String a");
        inStr.add("String a");
        inStr.add("String b");
        inStr.add("String c");

        expStr.add("String a");
        expStr.add("String b");
        expStr.add("String c");

        Strlist strList = new Strlist();

        actualStr = strList.refactorData(inStr, false,
                false, false, 0);
        assertEquals(expStr, actualStr);
    }
    @Test
    // Тест на поиск без учёта регистра символов
    public void test02() {
        ArrayList<String> inStr = new ArrayList<>();
        ArrayList<String> expStr = new ArrayList<>();
        ArrayList<String> actualStr;

        inStr.add("String a");
        inStr.add("STRING A");
        inStr.add("String b");
        inStr.add("String c");

        expStr.add("String a");
        expStr.add("String b");
        expStr.add("String c");

        Strlist strList = new Strlist();

        actualStr = strList.refactorData(inStr, true,
                false, false, 0);
        assertEquals(expStr, actualStr);
    }
    @Test
    // Тест на поиск с игнорированием первых N символов
    public void test03() {
        ArrayList<String> inStr = new ArrayList<>();
        ArrayList<String> expStr = new ArrayList<>();
        ArrayList<String> actualStr;

        inStr.add("String a");
        inStr.add("STRING a");
        inStr.add("String b");
        inStr.add("String c");

        expStr.add("String a");
        expStr.add("String b");
        expStr.add("String c");

        Strlist strList = new Strlist();

        actualStr = strList.refactorData(inStr, false,
                false, false, 6);
        assertEquals(expStr, actualStr);
    }
    @Test
    // Тест на вывод уникальных строк
    public void test04() {
        ArrayList<String> inStr = new ArrayList<>();
        ArrayList<String> expStr = new ArrayList<>();
        ArrayList<String> actualStr;

        inStr.add("String a");
        inStr.add("String a");
        inStr.add("String b");
        inStr.add("String c");

        expStr.add("String b");
        expStr.add("String c");

        Strlist strList = new Strlist();

        actualStr = strList.refactorData(inStr, false,
                true, false, 0);
        assertEquals(expStr, actualStr);
    }
    @Test
    // Тест на добавление префикса повторов
    public void test05() {
        ArrayList<String> inStr = new ArrayList<>();
        ArrayList<String> expStr = new ArrayList<>();
        ArrayList<String> actualStr;

        inStr.add("String a");
        inStr.add("String a");
        inStr.add("String b");
        inStr.add("String c");

        expStr.add("2 String a");
        expStr.add("String b");
        expStr.add("String c");

        Strlist strList = new Strlist();

        actualStr = strList.refactorData(inStr, false,
                false, true, 0);
        assertEquals(expStr, actualStr);
    }
    @Test
    // Тест на чтение из файла
    public void test06() {
        ArrayList<String> expStr = new ArrayList<>();
        ArrayList<String> actualStr;

        expStr.add("String 1");
        expStr.add("String 2");
        expStr.add("String 3");

        Strlist strList = new Strlist();

        actualStr = strList.getData("test.txt");
        assertEquals(expStr, actualStr);
    }

    @Test
    // Тест на запись в файл
    public void test07() {
        ArrayList<String> fileStr = new ArrayList<>();

        fileStr.add("String 1");
        fileStr.add("String 2");
        fileStr.add("String 3");

        Strlist strList = new Strlist();

        strList.type(fileStr,"file.txt");
        File file = new File("file.txt");
        assertTrue(file.exists());
        file.delete();
    }
}