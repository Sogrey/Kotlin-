package org.sogrey.kotlin;

/**
 * Created by Sogrey on 2017/6/21.
 */
public class demo_2_2 {

    public static void printlnLogs(String... logs) {
        for (String str : logs) {
            System.out.println(str);
        }
    }

    public static void main(String args[]) {
        printlnLogs("log1","log2","log3");
    }
}
