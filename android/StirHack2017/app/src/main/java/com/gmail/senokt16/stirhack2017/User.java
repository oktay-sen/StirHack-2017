package com.gmail.senokt16.stirhack2017;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class User implements Serializable{
    public String username;
    public String name;
    public String university;
    public List<Language> languages;

    @Override
    public String toString() {
        String result = username + "|" + name + "|" + university;
        for (Language l : languages) {
            result += "|" + l.name + "|" + l.level;
        }
        return result;
    }

    public static User fromString(String str) {
        try {
            String[] tokens = str.split("|");
            User result = new User();
            result.username = tokens[0] == null?null:tokens[0];
            result.name = tokens[1] == null?null:tokens[1];
            result.university = tokens[2] == null?null:tokens[2];
            result.languages = new ArrayList<>();
            for (int i=3; i < tokens.length; i += 2) {
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
