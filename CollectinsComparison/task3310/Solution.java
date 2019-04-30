package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(),10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
        testStrategy(new FileStorageStrategy(), 40);
        testStrategy(new OurHashBiMapStorageStrategy(), 10000);
        testStrategy(new HashBiMapStorageStrategy(), 10000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 10000);

    }


    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> ids=new HashSet<>();
        for(String str:strings){
            ids.add(shortener.getId(str));
        }
        return ids;
    }
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> strings=new HashSet<>();
        for(Long key:keys){
            strings.add(shortener.getString(key));
        }
        return strings;
    }
    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> testStrigs = new HashSet<>();
        Date start1,finish1,start2,finish2;
        for(int i = 0; i<elementsNumber;i++){
            testStrigs.add(Helper.generateRandomString());
        }
        Shortener shortener=new Shortener(strategy);
        start1 = new Date();
        Set<Long> testIds = getIds(shortener,testStrigs);
        finish1 = new Date();
        Helper.printMessage((finish1.getTime()-start1.getTime())+"");

        start2 = new Date();
        testStrigs=getStrings(shortener,testIds);
        finish2 = new Date();
        Helper.printMessage((finish2.getTime()-start2.getTime())+"");
        if(testStrigs.size()==elementsNumber){
            Helper.printMessage("Тест пройден.");
        }else{
            Helper.printMessage("Тест не пройден.");
        }
    }
}
