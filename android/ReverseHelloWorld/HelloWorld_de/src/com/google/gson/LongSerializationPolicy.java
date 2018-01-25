// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson;

public enum LongSerializationPolicy
{
    DEFAULT {
        @Override
        public JsonElement serialize(final Long n) {
            return new JsonPrimitive(n);
        }
    }, 
    STRING {
        @Override
        public JsonElement serialize(final Long n) {
            return new JsonPrimitive(String.valueOf(n));
        }
    };
    
    public abstract JsonElement serialize(final Long p0);
}
