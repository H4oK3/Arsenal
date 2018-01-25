// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import com.google.gson.internal.LinkedTreeMap;
import java.util.ArrayList;
import com.google.gson.stream.JsonReader;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.TypeAdapter;

public final class ObjectTypeAdapter extends TypeAdapter<Object>
{
    public static final TypeAdapterFactory FACTORY;
    private final Gson gson;
    
    static {
        FACTORY = new TypeAdapterFactory() {
            @Override
            public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
                if (typeToken.getRawType() == Object.class) {
                    return (TypeAdapter<T>)new ObjectTypeAdapter(gson, null);
                }
                return null;
            }
        };
    }
    
    private ObjectTypeAdapter(final Gson gson) {
        this.gson = gson;
    }
    
    @Override
    public Object read(final JsonReader jsonReader) throws IOException {
        switch (jsonReader.peek()) {
            default: {
                throw new IllegalStateException();
            }
            case BEGIN_ARRAY: {
                final ArrayList<Object> list = new ArrayList<Object>();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    list.add(this.read(jsonReader));
                }
                jsonReader.endArray();
                return list;
            }
            case BEGIN_OBJECT: {
                final LinkedTreeMap<String, Object> linkedTreeMap = new LinkedTreeMap<String, Object>();
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    linkedTreeMap.put(jsonReader.nextName(), this.read(jsonReader));
                }
                jsonReader.endObject();
                return linkedTreeMap;
            }
            case STRING: {
                return jsonReader.nextString();
            }
            case NUMBER: {
                return jsonReader.nextDouble();
            }
            case BOOLEAN: {
                return jsonReader.nextBoolean();
            }
            case NULL: {
                jsonReader.nextNull();
                return null;
            }
        }
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Object o) throws IOException {
        if (o == null) {
            jsonWriter.nullValue();
            return;
        }
        final TypeAdapter<?> adapter = this.gson.getAdapter(o.getClass());
        if (adapter instanceof ObjectTypeAdapter) {
            jsonWriter.beginObject();
            jsonWriter.endObject();
            return;
        }
        adapter.write(jsonWriter, o);
    }
}
