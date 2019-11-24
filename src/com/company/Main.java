package com.company;

import com.company.AnalyzeTable.table;
import com.company.MasterControlProgram.Program;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        table t = new table();
        t.Analyze__table();
        Scanner in = new Scanner(System.in);
        System.out.println("请输入你的输入串：");
        String s = in.nextLine();
        Program program = new Program(s);
        program.run();
    }
}
