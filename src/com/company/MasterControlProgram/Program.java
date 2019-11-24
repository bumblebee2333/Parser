package com.company.MasterControlProgram;

import com.company.AnalyzeTable.table;

import java.util.Map;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class Program {
    private String[][] ForeCast = table.Forecast;//预测分析表
    private Stack<Character> stack = new Stack<>();
    private String inputString = null;
    Map<Character, Integer> map_NotEnd = table.map_NotEnd;
    Map<Character, Integer> map_End = table.map_End;
    private AtomicInteger ati = new AtomicInteger();

    public Program(String string){
        this.inputString = string;
    }

    public void run(){
        String[] l = {"步骤","符号栈","输入串","所用产生式"};
        System.out.printf("%s%8s%18s%14s",l[0],l[1],l[2],l[3]);
        char[] string = inputString.toCharArray();
        int i=0;
        stack.push('#');
        stack.push('A');//放入文法的开始符号A
        StringBuilder ss = new StringBuilder();
        for(Character c:stack){
            ss.append(c);
        }
        System.out.printf("\n%2d       %-14s%10s%18s\n",ati.getAndIncrement(),ss.toString(),inputString.substring(i,inputString.length())+"#"," ");
        while (true){
            char ch = stack.pop();
            if(isEmpty(stack) && i>=inputString.length()){
                System.out.println("成功走出！！！");
                break;
            }
            if(ch=='#'){
                System.out.println("成功走出！！！");
                break;
            }
            if(map_NotEnd.containsKey(ch)){
                int row = map_NotEnd.get(ch);
                char letter = string[i];
                int column = map_End.get(letter);
                String production = ForeCast[row][column];
                if(production.equals("ε")){

                }else {
                    char[] temp = production.toCharArray();
                    for(int m=temp.length-1;m>=0;m--){
                        stack.push(temp[m]);
                    }
                }
                StringBuilder sb = new StringBuilder();
                for(Character c:stack){
                    sb.append(c);
                }
                System.out.printf("%2d       %-14s%10s%18s\n",ati.getAndIncrement(),sb.toString(),inputString.substring(i,inputString.length())+"#",ch+"->"+production);
            }else if(map_End.containsKey(ch)){
                ++i;
                StringBuilder sss = new StringBuilder();
                for(Character c:stack){
                    sss.append(c);
                }
                System.out.printf("%2d       %-14s%10s%18s\n",ati.getAndIncrement(),sss.toString(),inputString.substring(i,inputString.length())+"#"," ");
            }
        }
    }

    public boolean isEmpty(Stack stack){
        if(stack.isEmpty())
            return false;
        else
            return true;
    }
}
