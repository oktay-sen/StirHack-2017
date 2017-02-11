package com.gmail.senokt16.stirhack2017;

import java.util.ArrayList;
import java.util.List;


public class User {
    public String name;
    public String university;
    public List<Language> languages;

    @Override
    public String toString() {
        String result = name + "|" + university;
        for (Language l : languages) {
            result += "|" + l.name + "|" + l.level;
        }
        return result;
    }

    public static User fromString(String str) {
        try {
            String[] tokens = str.split("|");
            User result = new User();
            result.name = tokens[0] == null?null:tokens[0];
            result.university = tokens[0] == null?null:tokens[0];
            result.languages = new ArrayList<>();
            for (int i=2; i < tokens.length; i += 2) {
                Language l = new Language();
                l.name = tokens[i] == null?null:tokens[i];
                l.level = tokens[i+1] == null?null:Integer.parseInt(tokens[i+1]);
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
