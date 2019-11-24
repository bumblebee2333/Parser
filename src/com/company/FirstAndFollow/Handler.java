package com.company.FirstAndFollow;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;

public class Handler {
    private File file;
    private static HashSet<NotEndChar> notEndChars=new HashSet<NotEndChar>();
    private static HashSet<String> endChars=new HashSet<String>();
    private static HashSet<String> leftChars=new HashSet<String>();
    private static ArrayList<Generation> generations=new ArrayList<Generation>();

    /**
     *
     * @param file 文法文件
     */
    public Handler(File file){
        this.file=file;
    }

    private static HashSet<String> removeNull(HashSet<String> s){
        HashSet<String> hashSet=new HashSet<String>();
        for (String s1 : s) {
            if(!s1.equals("ε")){
                hashSet.add(s1);
            }
        }
        return hashSet;
    }
    private static boolean isEndChar(String c){
        for (NotEndChar notEndChar : notEndChars) {
            if(notEndChar.getName().equals(c)){
                return false;
            }
        }
        return true;
    }
    private static HashSet<String> findFirstSet(String c){
        for (NotEndChar notEndChar : notEndChars) {
            if(notEndChar.getName().equals(c)){
                return notEndChar.getFirst();
            }
        }
        return null;
    }
    private static void followSET(NotEndChar c){
        int i=0;
        for (Generation generation : generations) {
            if(generation.getLeft().equals(c.getName())&&i==0){

                c.getFollow().add("#");

            }
            else{
                String rightString=generation.getRight();
                int k=0;

                while(k<rightString.length()){
                    if(c.getName().equals(""+rightString.charAt(k))){
                        if(!generation.getLeft().equals(""+rightString.charAt(k))&&k==rightString.length()-1&&!isEndChar(""+rightString.charAt(k))){
                            NotEndChar notEndChar=new NotEndChar();
                            notEndChar.setName(generation.getLeft());
                            followSET(notEndChar);
                            c.getFollow().addAll(notEndChar.getFollow());
                            break;
                        }
                        else if(k==rightString.length()-1){
                            break;
                        }
                        if(isEndChar(""+rightString.charAt(k+1))){
                            c.getFollow().add(""+rightString.charAt(k+1));
                        }
                        else{
                            HashSet<String> res=findFirstSet(""+rightString.charAt(k+1));

                            if(res.contains("ε")) {
                                int l=k+1;
                                int flag=0;
                                while(l<rightString.length()){
                                    if(!isEndChar(""+rightString.charAt(l))){

                                        if(findFirstSet(""+rightString.charAt(l)).contains("ε")){
                                            c.getFollow().addAll(removeNull(findFirstSet(""+rightString.charAt(l))));
                                        }
                                        else {
                                            c.getFollow().addAll(removeNull(findFirstSet(""+rightString.charAt(l))));
                                            flag=1;
                                            break;
                                        }
                                    }
                                    else {
                                        c.getFollow().add(""+rightString.charAt(l));
                                        flag=1;
                                        break;
                                    }
                                    l++;
                                }
                                if(l==rightString.length()&&flag==0){
                                    NotEndChar notEndChar=new NotEndChar();
                                    notEndChar.setName(generation.getLeft());
                                    followSET(notEndChar);
                                    c.getFollow().addAll(notEndChar.getFollow());
                                }
                            }
                            else{
                                c.getFollow().addAll(res);
                            }
                        }
                    }
                    k++;
                }
            }
            i++;
        }
    }
    private static void firstSET(NotEndChar c){
        for (Generation generation : generations) {
            if(generation.getLeft().equals(c.getName())){
                char cs=generation.getRight().charAt(0);
                if(isEndChar(""+cs)){
                    c.getFirst().add(""+cs);
                }else {

                    int flag=0;
                    while (flag<generation.getRight().length()){
                        if(isEndChar(""+generation.getRight().charAt(flag))){
                            c.getFirst().add(""+generation.getRight().charAt(flag));
                            break;
                        }
                        else {
                            NotEndChar notEndChar=new NotEndChar();
                            notEndChar.setName(""+generation.getRight().charAt(flag));
                            firstSET(notEndChar);
                            if(notEndChar.getFirst().contains("ε")){
                                c.getFirst().addAll(notEndChar.getFirst());
                                flag++;
                                continue;
                            }else{
                                c.getFirst().addAll(notEndChar.getFirst());
                                break;
                            }
                        }

                    }
                }
            }
        }
    }

    /**
     *
     * @return HashSet<handler.NotEndChar>
     * @throws Exception
     */

    public  HashSet<NotEndChar> getNotEndChars()throws Exception{
        char[] filechar=new char[10000];
        FileReader fileReader=new FileReader(this.file);
        fileReader.read(filechar);
        System.out.println(filechar);
        String text=new String(filechar);
        System.out.println(text);
        text=text.replace("\r","");
        String[] strings=text.split("\n");
        for (String string : strings) {
            Generation generation=new Generation();
            generation.setLeft(string.split("->")[0]);
            generation.setRight(string.split("->")[1]);
            generations.add(generation);
            if(!leftChars.contains(generation.getLeft())){
                leftChars.add(generation.getLeft());
                NotEndChar notEndChar=new NotEndChar();
                notEndChar.setName(generation.getLeft());
                notEndChars.add(notEndChar);
            }

        }
        for (NotEndChar notEndChar : notEndChars) {
            firstSET(notEndChar);
            endChars.addAll(notEndChar.getFirst());
        }
        for(NotEndChar notEndChar:notEndChars){
            followSET(notEndChar);
            endChars.addAll(notEndChar.getFollow());
        }

        fileReader.close();
        return notEndChars;
    }
    public HashSet<String> getEndChars()throws Exception{
        endChars=removeNull(endChars);
        return endChars;
    }
    public ArrayList<Generation> getGenerations(){
        return generations;
    }
}
