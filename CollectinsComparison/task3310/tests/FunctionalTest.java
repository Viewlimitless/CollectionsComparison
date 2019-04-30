package com.javarush.task.task33.task3310.tests;
/*
 * Мы много раз тестировали наши стратегии с помощью метода testStrategy() класса Solution.
 * Пришло время написать настоящие юнит тесты с использованием junit.
 * 14.1. Прочитай что такое юнит тесты.
 * 14.2. Скачай и подключи библиотеку Junit 4.12. Разберись как ей пользоваться.
 * Библиотека Junit зависит от библиотеки hamcrest-core. Подключи и ее. Используй версию 1.3.
 * 14.3. Добавь класс FunctionalTest в пакет tests. В этом классе мы проверим функциональность наших стратегий.
 * 14.4. Добавь в класс FunctionalTest метод testStorage(Shortener shortener). Он должен:
 * 14.4.1. Создавать три строки. Текст 1 и 3 строк должен быть одинаковым.
 * 14.4.2. Получать и сохранять идентификаторы для всех трех строк с помощью shortener.
 * 14.4.3. Проверять, что идентификатор для 2 строки не равен идентификатору для 1 и 3 строк.
 *
 * Подсказка: метод Assert.assertNotEquals.
 *
 * 14.4.4. Проверять, что идентификаторы для 1 и 3 строк равны.
 *
 * Подсказка: метод Assert.assertEquals.
 *
 * 14.4.5. Получать три строки по трем идентификаторам с помощью shortener.
 * 14.4.6. Проверять, что строки, полученные в предыдущем пункте, эквивалентны оригинальным.
 *
 * Подсказка: метод Assert.assertEquals.
 *
 * 14.5. Добавь в класс FunctionalTest тесты:
 * 14.5.1. testHashMapStorageStrategy()
 * 14.5.2. testOurHashMapStorageStrategy()
 * 14.5.3. testFileStorageStrategy()
 * 14.5.4. testHashBiMapStorageStrategy()
 * 14.5.5. testDualHashBidiMapStorageStrategy()
 * 14.5.6. testOurHashBiMapStorageStrategy()
 * Каждый тест должен иметь аннотацию @Test, создавать подходящую стратегию, создавать объект класса Shortener
 * на базе этой стратегии и вызывать метод testStorage для него.
 * Запусти и проверь, что все тесты проходят.
 */

import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class FunctionalTest extends Assert {




    public void testStorage(Shortener shortener) {
        String string1 = "Nelly";
        String string2 = "NDKJFDOIfUISD";
        String string3 = "Nelly";
        Long id1 = shortener.getId(string1);
        Long id2 = shortener.getId(string2);
        Long id3 = shortener.getId(string3);

        Assert.assertNotEquals(id2, id1);
        Assert.assertEquals(id1, id3);

        String s1 = shortener.getString(id1);
        String s2 = shortener.getString(id2);
        String s3 = shortener.getString(id3);

        Assert.assertEquals(s1, string1);
        Assert.assertEquals(s2, string2);
        Assert.assertEquals(s3, string3);
    }


    @Test
    public void testHashMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        Shortener shortener = new Shortener(new OurHashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        Shortener shortener = new Shortener(new FileStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashBiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        Shortener shortener = new Shortener(new DualHashBidiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new OurHashBiMapStorageStrategy());
        testStorage(shortener);
    }
}