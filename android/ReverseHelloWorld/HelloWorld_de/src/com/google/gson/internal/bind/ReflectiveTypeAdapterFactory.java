// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import java.util.Iterator;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.$Gson$Types;
import java.util.LinkedHashMap;
import java.util.Map;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import com.google.gson.stream.JsonReader;
import com.google.gson.TypeAdapter;
import java.lang.reflect.Type;
import com.google.gson.internal.Primitives;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Field;
import com.google.gson.Gson;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.TypeAdapterFactory;

public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory
{
    private final ConstructorConstructor constructorConstructor;
    private final Excluder excluder;
    private final FieldNamingStrategy fieldNamingPolicy;
    
    public ReflectiveTypeAdapterFactory(final ConstructorConstructor constructorConstructor, final FieldNamingStrategy fieldNamingPolicy, final Excluder excluder) {
        this.constructorConstructor = constructorConstructor;
        this.fieldNamingPolicy = fieldNamingPolicy;
        this.excluder = excluder;
    }
    
    private BoundField createBoundField(final Gson gson, final Field field, final String s, final TypeToken<?> typeToken, final boolean b, final boolean b2) {
        return (BoundField)new BoundField(s, b, b2) {
            final TypeAdapter<?> typeAdapter = gson.getAdapter(typeToken);
            final /* synthetic */ boolean val$isPrimitive = Primitives.isPrimitive(typeToken.getRawType());
            
            @Override
            void read(final JsonReader jsonReader, final Object o) throws IOException, IllegalAccessException {
                final Object read = this.typeAdapter.read(jsonReader);
                if (read != null || !this.val$isPrimitive) {
                    field.set(o, read);
                }
            }
            
            @Override
            void write(final JsonWriter jsonWriter, Object value) throws IOException, IllegalAccessException {
                value = field.get(value);
                new TypeAdapterRuntimeTypeWrapper<Object>(gson, this.typeAdapter, typeToken.getType()).write(jsonWriter, value);
            }
        };
    }
    
    private Map<String, BoundField> getBoundFields(final Gson gson, TypeToken<?> value, Class<?> rawType) {
        final LinkedHashMap<String, BoundField> linkedHashMap = new LinkedHashMap<String, BoundField>();
        if (!rawType.isInterface()) {
            final Type type = value.getType();
            while (rawType != Object.class) {
                final Field[] declaredFields = rawType.getDeclaredFields();
                for (int length = declaredFields.length, i = 0; i < length; ++i) {
                    final Field field = declaredFields[i];
                    final boolean excludeField = this.excludeField(field, true);
                    final boolean excludeField2 = this.excludeField(field, false);
                    if (excludeField || excludeField2) {
                        field.setAccessible(true);
                        final BoundField boundField = this.createBoundField(gson, field, this.getFieldName(field), TypeToken.get($Gson$Types.resolve(value.getType(), rawType, field.getGenericType())), excludeField, excludeField2);
                        final BoundField boundField2 = linkedHashMap.put(boundField.name, boundField);
                        if (boundField2 != null) {
                            throw new IllegalArgumentException(type + " declares multiple JSON fields named " + boundField2.name);
                        }
                    }
                }
                value = TypeToken.get($Gson$Types.resolve(value.getType(), rawType, rawType.getGenericSuperclass()));
                rawType = value.getRawType();
            }
        }
        return linkedHashMap;
    }
    
    private String getFieldName(final Field field) {
        final SerializedName serializedName = field.getAnnotation(SerializedName.class);
        if (serializedName == null) {
            return this.fieldNamingPolicy.translateName(field);
        }
        return serializedName.value();
    }
    
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        final Class<? super T> rawType = typeToken.getRawType();
        if (!Object.class.isAssignableFrom(rawType)) {
            return null;
        }
        return new Adapter<T>((ObjectConstructor)this.constructorConstructor.get(typeToken), (Map)this.getBoundFields(gson, typeToken, rawType));
    }
    
    public boolean excludeField(final Field field, final boolean b) {
        return !this.excluder.excludeClass(field.getType(), b) && !this.excluder.excludeField(field, b);
    }
    
    public static final class Adapter<T> extends TypeAdapter<T>
    {
        private final Map<String, BoundField> boundFields;
        private final ObjectConstructor<T> constructor;
        
        private Adapter(final ObjectConstructor<T> constructor, final Map<String, BoundField> boundFields) {
            this.constructor = constructor;
            this.boundFields = boundFields;
        }
        
        @Override
        public T read(final JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            final T construct = this.constructor.construct();
            try {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    final BoundField boundField = this.boundFields.get(jsonReader.nextName());
                    if (boundField != null && boundField.deserialized) {
                        goto Label_0084;
                    }
                    jsonReader.skipValue();
                }
            }
            catch (IllegalStateException ex) {
                throw new JsonSyntaxException(ex);
            }
            catch (IllegalAccessException ex2) {
                throw new AssertionError((Object)ex2);
            }
            jsonReader.endObject();
            return construct;
        }
        
        @Override
        public void write(final JsonWriter jsonWriter, final T t) throws IOException {
            if (t == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            try {
                for (final BoundField boundField : this.boundFields.values()) {
                    if (boundField.serialized) {
                        jsonWriter.name(boundField.name);
                        boundField.write(jsonWriter, t);
                    }
                }
            }
            catch (IllegalAccessException ex) {
                throw new AssertionError();
            }
            jsonWriter.endObject();
        }
    }
    
    abstract static class BoundField
    {
        final boolean deserialized;
        final String name;
        final boolean serialized;
        
        protected BoundField(final String name, final boolean serialized, final boolean deserialized) {
            this.name = name;
            this.serialized = serialized;
            this.deserialized = deserialized;
        }
        
        abstract void read(final JsonReader p0, final Object p1) throws IOException, IllegalAccessException;
        
        abstract void write(final JsonWriter p0, final Object p1) throws IOException, IllegalAccessException;
    }
}
