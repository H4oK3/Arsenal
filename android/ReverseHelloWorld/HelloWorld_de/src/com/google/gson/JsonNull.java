// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson;

public final class JsonNull extends JsonElement
{
    public static final JsonNull INSTANCE;
    
    static {
        INSTANCE = new JsonNull();
    }
    
    @Override
    JsonNull deepCopy() {
        return JsonNull.INSTANCE;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || o instanceof JsonNull;
    }
    
    @Override
    public int hashCode() {
        return JsonNull.class.hashCode();
    }
}
