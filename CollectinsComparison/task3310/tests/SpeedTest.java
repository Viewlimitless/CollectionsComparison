package com.javarush.task.task33.task3310.tests;

/*Напишем еще один тест, который проверит, что получить идентификатор для строки используя стратегию HashBiMapStorageStrategy можно быстрее, чем используя стратегию HashMapStorageStrategy.
15.1. Создай класс SpeedTest в пакете tests.
15.2. Добавь в класс метод long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids). Он должен возвращать время в миллисекундах необходимое для получения идентификаторов для всех строк из strings. Идентификаторы должны быть записаны в ids.
15.3. Добавь в класс метод long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings). Он должен возвращать время в миллисекундах необходимое для получения строк для всех идентификаторов из ids. Строки должны быть записаны в strings.
15.4. Добавь в класс SpeedTest тест testHashMapStorage(). Он должен:
15.4.1. Создавать два объекта типа Shortener, один на базе HashMapStorageStrategy, второй на базе HashBiMapStorageStrategy. Назовем их shortener1 и shortener2.
15.4.2. Генерировать с помощью Helper 10000 строк и помещать их в сет со строками, назовем его origStrings.
15.4.3. Получать время получения идентификаторов для origStrings (вызывать метод getTimeToGetIds для shortener1, а затем для shortener2).
15.4.4. Проверять с помощью junit, что время, полученное в предыдущем пункте для shortener1 больше, чем для shortener2.
15.4.5. Получать время получения строк (вызывать метод getTimeToGetStrings для shortener1 и shortener2).
15.4.6. Проверять с помощью junit, что время, полученное в предыдущем пункте для shortener1 примерно равно времени для shortener2. Используй метод assertEquals(float expected, float actual, float delta). В качестве delta можно использовать 30, этого вполне достаточно для наших экспериментов.


Требования:
1. Метод getTimeToGetStrings должен возвращать время в миллисекундах необходимое для получения всех строк для множества идентификаторов ids.
2. Метод getTimeToGetIds должен возвращать время в миллисекундах необходимое для получения всех идентификаторов для множества строк strings.
3. В методе testHashMapStorage должно быть выполнено сравнение времени получения множества ключей и множества значений для стратегий HashMapStorageStrategy и HashBiMapStorageStrategy.
*/

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Long start = System.currentTimeMillis();
        for (String str : strings) {
            ids.add(shortener.getId(str));
        }
        Long finish = System.currentTimeMillis();
        return finish - start;
    }
    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Long start = System.currentTimeMillis();
        for (Long id : ids) {
            strings.add(shortener.getString(id));
        }
        Long finish = System.currentTimeMillis();
        return finish - start;

    }
    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }
        Set<Long> ids = new HashSet<>();
        Long s1TTgetIds = getTimeToGetIds(shortener1, origStrings, ids);
        Long s2TTgetIds = getTimeToGetIds(shortener2, origStrings, new HashSet<Long>());
        Assert.assertTrue(s1TTgetIds > s2TTgetIds);

        Long s1TTgetStrings = getTimeToGetStrings(shortener1, ids, new HashSet<String>());
        Long s2TTgetStrings = getTimeToGetStrings(shortener2, ids, new HashSet<String>());
        Assert.assertEquals( s1TTgetStrings, s2TTgetStrings, 30);
    }


}
