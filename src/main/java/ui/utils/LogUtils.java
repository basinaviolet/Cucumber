package ui.utils;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class LogUtils {

    public static void printLog(Logger logger, LogEntries entries) {
        logger.info("{} log entries found", entries.getAll().size());
        for (LogEntry entry : entries) {
            logger.info("{} {} {}",
                    new Date(entry.getTimestamp()), entry.getLevel(), entry.getMessage()
            );
        }
    }

    /*
     * Getting attributes of elements of element collection
     */
    public static List<String> getAttribute(ElementsCollection collectionOfItems, String attribute) {
        List<String> newList = new ArrayList<>();

        collectionOfItems
                .forEach(i -> newList.add(i.getAttribute(attribute)));
        //     for (String str : newList) System.out.println("newList str:" + str);
        return newList.stream().filter(i -> i != null).collect(Collectors.toList());
    }

    /*
     * Check to string lists. return:
     * - true if the number of items in pointsForCheck is equal to the number of matching items
     * - false if the number of items in pointsForCheck is not equal to the number of matching items
     */
    public static boolean compareItemsCheck(List<String> pointsFromPage, List<String> pointsForCheck) {
        int check = 0;
        for (String strForCheck : pointsForCheck) {
            boolean checkItem = false;
            for (String iInternal : pointsFromPage) {
                if (iInternal.lastIndexOf(strForCheck) >= 0) {
                    checkItem = true;
//                    System.out.println("page:" + iInternal + " pointsForCheck:" + strForCheck);
                }
            }
            if (checkItem) check++;
        }
//        System.out.println("check:" + check + " pointsForCheck:" + pointsForCheck.size());
        return check == pointsForCheck.size();
    }

    public static void prnList(List<String> list) {
        for (String str : list)
            System.out.println("str:" + str);
    }

    /*
     * Getting random number between 1 and countOfItems
     */
    public static int chooseView(int countOfItems) {
        return  (int) (Math.random() * countOfItems + 1);
    }

}
