// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal;

public final class $Gson$Preconditions
{
    public static void checkArgument(final boolean b) {
        if (!b) {
            throw new IllegalArgumentException();
        }
    }
    
    public static <T> T checkNotNull(final T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        return t;
    }
}
