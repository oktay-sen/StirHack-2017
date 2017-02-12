package com.gmail.senokt16.stirhack2017;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class User implements Serializable{
    public String username;
    public String name;
    public String university;
    public List<Language> languages;

    @Override
    public String toString() {
        String result = username + "`" + name + "`" + university;
        if (languages != null && languages.size() > 0)
            result += "`" + languages.get(0).name + "|" + languages.get(0).level;
        for (int i=1; i < languages.size(); i += 2) {
            result += "|" + languages.get(i).name + "|" + languages.get(i).level;
        }
        return result;
    }

    public static User fromString(String str) {
        try {
            String[] tokens = str.split("`");
            User result = new User();
            result.username = tokens[0] == null?null:tokens[0];
            result.name = tokens[1] == null?null:tokens[1];
            result.university = tokens[2] == null?null:tokens[2];
            String[] langs = tokens[3].split("|");
            result.languages = new ArrayList<>();
            for (int i=0; i < langs.length; i += 2) {
                Language l = new Language();
                l.name = langs[i];
                l.level = Integer.parseInt(langs[i+1]);
                result.languages.add(l);
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
