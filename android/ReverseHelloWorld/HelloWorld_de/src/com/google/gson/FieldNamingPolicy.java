// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;

public enum FieldNamingPolicy implements FieldNamingStrategy
{
    IDENTITY {
        @Override
        public String translateName(final Field field) {
            return field.getName();
        }
    }, 
    LOWER_CASE_WITH_DASHES {
        @Override
        public String translateName(final Field field) {
            return separateCamelCase(field.getName(), "-").toLowerCase();
        }
    }, 
    LOWER_CASE_WITH_UNDERSCORES {
        @Override
        public String translateName(final Field field) {
            return separateCamelCase(field.getName(), "_").toLowerCase();
        }
    }, 
    UPPER_CAMEL_CASE {
        @Override
        public String translateName(final Field field) {
            return upperCaseFirstLetter(field.getName());
        }
    }, 
    UPPER_CAMEL_CASE_WITH_SPACES {
        @Override
        public String translateName(final Field field) {
            return upperCaseFirstLetter(separateCamelCase(field.getName(), " "));
        }
    };
    
    private static String modifyString(final char c, final String s, final int n) {
        if (n < s.length()) {
            return c + s.substring(n);
        }
        return String.valueOf(c);
    }
    
    private static String separateCamelCase(final String s, final String s2) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (Character.isUpperCase(char1) && sb.length() != 0) {
                sb.append(s2);
            }
            sb.append(char1);
        }
        return sb.toString();
    }
    
    private static String upperCaseFirstLetter(final String s) {
        final StringBuilder sb = new StringBuilder();
        int n;
        char c;
        for (n = 0, c = s.charAt(0); n < s.length() - 1 && !Character.isLetter(c); ++n, c = s.charAt(n)) {
            sb.append(c);
        }
        String string;
        if (n == s.length()) {
            string = sb.toString();
        }
        else {
            string = s;
            if (!Character.isUpperCase(c)) {
                return sb.append(modifyString(Character.toUpperCase(c), s, n + 1)).toString();
            }
        }
        return string;
    }
}
