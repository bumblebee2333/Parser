package com.company.FirstAndFollow;

import java.util.HashSet;

public class NotEndChar {
    private String name;
    private HashSet<String> first=new HashSet<String>();
    private HashSet<String> follow=new HashSet<String>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashSet<String> getFirst() {
        return first;
    }

    public void setFirst(HashSet<String> first) {
        this.first = first;
    }

    public HashSet<String> getFollow() {
        return follow;
    }

    public void setFollow(HashSet<String> follow) {
        this.follow = follow;
    }

    @Override
    public String toString() {
        return "handler.NotEndChar{" +
                "name='" + name + '\'' +
                ", first=" + first +
                ", follow=" + follow +
                '}';
    }
}
