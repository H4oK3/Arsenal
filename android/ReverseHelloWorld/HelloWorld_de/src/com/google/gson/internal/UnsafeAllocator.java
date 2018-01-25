// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal;

import java.lang.reflect.Field;
import java.io.ObjectStreamClass;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;

public abstract class UnsafeAllocator
{
    public static UnsafeAllocator create() {
        try {
            final Class<?> forName = Class.forName("sun.misc.Unsafe");
            final Field declaredField = forName.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            return new UnsafeAllocator() {
                final /* synthetic */ Method val$allocateInstance = forName.getMethod("allocateInstance", Class.class);
                final /* synthetic */ Object val$unsafe = declaredField.get(null);
                
                @Override
                public <T> T newInstance(final Class<T> clazz) throws Exception {
                    return (T)this.val$allocateInstance.invoke(this.val$unsafe, clazz);
                }
            };
        }
        catch (Exception ex) {
            try {
                final Method declaredMethod = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                declaredMethod.setAccessible(true);
                return new UnsafeAllocator() {
                    @Override
                    public <T> T newInstance(final Class<T> clazz) throws Exception {
                        return (T)declaredMethod.invoke(null, clazz, Object.class);
                    }
                };
            }
            catch (Exception ex2) {
                try {
                    final Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                    declaredMethod2.setAccessible(true);
                    final int intValue = (int)declaredMethod2.invoke(null, Object.class);
                    final Method declaredMethod3 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                    declaredMethod3.setAccessible(true);
                    return new UnsafeAllocator() {
                        @Override
                        public <T> T newInstance(final Class<T> clazz) throws Exception {
                            return (T)declaredMethod3.invoke(null, clazz, intValue);
                        }
                    };
                }
                catch (Exception ex3) {
                    return new UnsafeAllocator() {
                        @Override
                        public <T> T newInstance(final Class<T> clazz) {
                            throw new UnsupportedOperationException("Cannot allocate " + clazz);
                        }
                    };
                }
            }
        }
    }
    
    public abstract <T> T newInstance(final Class<T> p0) throws Exception;
}
