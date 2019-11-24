package com.company.AnalyzeTable;

import com.company.FirstAndFollow.NotEndChar;

import java.util.HashMap;
import java.util.Map;

public class table {

    /*
   构造预测分析表
    */
    private static int MAX = 20;
    public static char[] a = {'A', 'B', 'C', 'D', 'E'};
    public static char[] b = {'a', 'b', 'c', 'd', 'g', 'f', '#'};
    private String[][] M = new String[a.length][b.length];
    private static int[] a1 = new int[a.length];
    public static Map<Character, Integer> map_NotEnd = new HashMap<>();
    public static Map<Character, Integer> map_End = new HashMap<>();
    int t1, t2;
    public static String[][] Forecast = {{"BCc", "BCc", "BCc", "BCc", "gDB", "error", "BCc"}, {"error", "bCDE", "ε", "ε", "ε", "ε", "ε"}, {"error", "DaB", "ca", "DaB", "DaB", "error", "DaB"}, {"error", "error", "ε", "dD", "ε", "error", "ε"}, {"error", "error", "c", "error", "gAf", "error", "error"}};

    public void Analyze__table() {
        for (int i = 0; i < a.length; i++) {
            map_NotEnd.put(a[i], i);
        }
        for (int i = 0; i < a.length; i++) {
            map_End.put(b[i], i);
        }

    }

    public String FindExpression(NotEndChar notEndChar, char c){
        for (int i = 0; i <a.length ; i++) {
            if (String.valueOf(a[i]).equals(notEndChar.getName())){
                t1=i;//在map中找到非终结符的下标
            }
            if (b[i]==c){
                t2=i;//终结符下标
            }

        }
        return Forecast[t1][t2];
    }
}
