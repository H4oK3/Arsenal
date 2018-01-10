// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.util.LongSparseArray;
import android.util.Log;
import android.os.Build$VERSION;
import android.content.res.Resources;
import java.lang.reflect.Field;

class n
{
    private static Field a;
    private static boolean b;
    private static Class c;
    private static boolean d;
    private static Field e;
    private static boolean f;
    private static Field g;
    private static boolean h;
    
    static boolean a(final Resources resources) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 24) {
            return d(resources);
        }
        if (sdk_INT >= 23) {
            return c(resources);
        }
        return sdk_INT >= 21 && b(resources);
    }
    
    private static boolean a(final Object o) {
        Label_0018: {
            if (n.d) {
                break Label_0018;
            }
            while (true) {
                try {
                    n.c = Class.forName("android.content.res.ThemedResourceCache");
                    n.d = true;
                    if (n.c == null) {
                        return false;
                    }
                }
                catch (ClassNotFoundException ex) {
                    Log.e("ResourcesFlusher", "Could not find ThemedResourceCache class", (Throwable)ex);
                    continue;
                }
                break;
            }
        }
        while (true) {
            if (!n.f) {
                while (true) {
                    try {
                        (n.e = n.c.getDeclaredField("mUnthemedEntries")).setAccessible(true);
                        n.f = true;
                        if (n.e == null) {
                            return false;
                        }
                    }
                    catch (NoSuchFieldException ex2) {
                        Log.e("ResourcesFlusher", "Could not retrieve ThemedResourceCache#mUnthemedEntries field", (Throwable)ex2);
                        continue;
                    }
                    break;
                }
                while (true) {
                    try {
                        final LongSparseArray longSparseArray = (LongSparseArray)n.e.get(o);
                        if (longSparseArray != null) {
                            longSparseArray.clear();
                            return true;
                        }
                    }
                    catch (IllegalAccessException ex3) {
                        Log.e("ResourcesFlusher", "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", (Throwable)ex3);
                        final LongSparseArray longSparseArray = null;
                        continue;
                    }
                    break;
                }
                return false;
            }
            continue;
        }
    }
    
    private static boolean b(final Resources p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       android/support/v7/app/n.b:Z
        //     3: ifne            27
        //     6: ldc             Landroid/content/res/Resources;.class
        //     8: ldc             "mDrawableCache"
        //    10: invokevirtual   java/lang/Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
        //    13: putstatic       android/support/v7/app/n.a:Ljava/lang/reflect/Field;
        //    16: getstatic       android/support/v7/app/n.a:Ljava/lang/reflect/Field;
        //    19: iconst_1       
        //    20: invokevirtual   java/lang/reflect/Field.setAccessible:(Z)V
        //    23: iconst_1       
        //    24: putstatic       android/support/v7/app/n.b:Z
        //    27: getstatic       android/support/v7/app/n.a:Ljava/lang/reflect/Field;
        //    30: ifnull          84
        //    33: getstatic       android/support/v7/app/n.a:Ljava/lang/reflect/Field;
        //    36: aload_0        
        //    37: invokevirtual   java/lang/reflect/Field.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    40: checkcast       Ljava/util/Map;
        //    43: astore_0       
        //    44: aload_0        
        //    45: ifnull          84
        //    48: aload_0        
        //    49: invokeinterface java/util/Map.clear:()V
        //    54: iconst_1       
        //    55: ireturn        
        //    56: astore_1       
        //    57: ldc             "ResourcesFlusher"
        //    59: ldc             "Could not retrieve Resources#mDrawableCache field"
        //    61: aload_1        
        //    62: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    65: pop            
        //    66: goto            23
        //    69: astore_0       
        //    70: ldc             "ResourcesFlusher"
        //    72: ldc             "Could not retrieve value from Resources#mDrawableCache"
        //    74: aload_0        
        //    75: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    78: pop            
        //    79: aconst_null    
        //    80: astore_0       
        //    81: goto            44
        //    84: iconst_0       
        //    85: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  6      23     56     69     Ljava/lang/NoSuchFieldException;
        //  33     44     69     84     Ljava/lang/IllegalAccessException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0044:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static boolean c(final Resources p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_1       
        //     1: istore_1       
        //     2: getstatic       android/support/v7/app/n.b:Z
        //     5: ifne            29
        //     8: ldc             Landroid/content/res/Resources;.class
        //    10: ldc             "mDrawableCache"
        //    12: invokevirtual   java/lang/Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
        //    15: putstatic       android/support/v7/app/n.a:Ljava/lang/reflect/Field;
        //    18: getstatic       android/support/v7/app/n.a:Ljava/lang/reflect/Field;
        //    21: iconst_1       
        //    22: invokevirtual   java/lang/reflect/Field.setAccessible:(Z)V
        //    25: iconst_1       
        //    26: putstatic       android/support/v7/app/n.b:Z
        //    29: getstatic       android/support/v7/app/n.a:Ljava/lang/reflect/Field;
        //    32: ifnull          72
        //    35: getstatic       android/support/v7/app/n.a:Ljava/lang/reflect/Field;
        //    38: aload_0        
        //    39: invokevirtual   java/lang/reflect/Field.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    42: astore_0       
        //    43: aload_0        
        //    44: ifnonnull       77
        //    47: iconst_0       
        //    48: ireturn        
        //    49: astore_2       
        //    50: ldc             "ResourcesFlusher"
        //    52: ldc             "Could not retrieve Resources#mDrawableCache field"
        //    54: aload_2        
        //    55: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    58: pop            
        //    59: goto            25
        //    62: astore_0       
        //    63: ldc             "ResourcesFlusher"
        //    65: ldc             "Could not retrieve value from Resources#mDrawableCache"
        //    67: aload_0        
        //    68: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    71: pop            
        //    72: aconst_null    
        //    73: astore_0       
        //    74: goto            43
        //    77: aload_0        
        //    78: ifnull          90
        //    81: aload_0        
        //    82: invokestatic    android/support/v7/app/n.a:(Ljava/lang/Object;)Z
        //    85: ifeq            90
        //    88: iload_1        
        //    89: ireturn        
        //    90: iconst_0       
        //    91: istore_1       
        //    92: goto            88
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  8      25     49     62     Ljava/lang/NoSuchFieldException;
        //  35     43     62     72     Ljava/lang/IllegalAccessException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0043:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static boolean d(final Resources p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_1       
        //     1: istore_1       
        //     2: getstatic       android/support/v7/app/n.h:Z
        //     5: ifne            29
        //     8: ldc             Landroid/content/res/Resources;.class
        //    10: ldc             "mResourcesImpl"
        //    12: invokevirtual   java/lang/Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
        //    15: putstatic       android/support/v7/app/n.g:Ljava/lang/reflect/Field;
        //    18: getstatic       android/support/v7/app/n.g:Ljava/lang/reflect/Field;
        //    21: iconst_1       
        //    22: invokevirtual   java/lang/reflect/Field.setAccessible:(Z)V
        //    25: iconst_1       
        //    26: putstatic       android/support/v7/app/n.h:Z
        //    29: getstatic       android/support/v7/app/n.g:Ljava/lang/reflect/Field;
        //    32: ifnonnull       50
        //    35: iconst_0       
        //    36: ireturn        
        //    37: astore_2       
        //    38: ldc             "ResourcesFlusher"
        //    40: ldc             "Could not retrieve Resources#mResourcesImpl field"
        //    42: aload_2        
        //    43: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    46: pop            
        //    47: goto            25
        //    50: getstatic       android/support/v7/app/n.g:Ljava/lang/reflect/Field;
        //    53: aload_0        
        //    54: invokevirtual   java/lang/reflect/Field.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    57: astore_0       
        //    58: aload_0        
        //    59: ifnull          35
        //    62: getstatic       android/support/v7/app/n.b:Z
        //    65: ifne            91
        //    68: aload_0        
        //    69: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    72: ldc             "mDrawableCache"
        //    74: invokevirtual   java/lang/Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
        //    77: putstatic       android/support/v7/app/n.a:Ljava/lang/reflect/Field;
        //    80: getstatic       android/support/v7/app/n.a:Ljava/lang/reflect/Field;
        //    83: iconst_1       
        //    84: invokevirtual   java/lang/reflect/Field.setAccessible:(Z)V
        //    87: iconst_1       
        //    88: putstatic       android/support/v7/app/n.b:Z
        //    91: getstatic       android/support/v7/app/n.a:Ljava/lang/reflect/Field;
        //    94: ifnull          156
        //    97: getstatic       android/support/v7/app/n.a:Ljava/lang/reflect/Field;
        //   100: aload_0        
        //   101: invokevirtual   java/lang/reflect/Field.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   104: astore_0       
        //   105: aload_0        
        //   106: ifnull          161
        //   109: aload_0        
        //   110: invokestatic    android/support/v7/app/n.a:(Ljava/lang/Object;)Z
        //   113: ifeq            161
        //   116: iload_1        
        //   117: ireturn        
        //   118: astore_0       
        //   119: ldc             "ResourcesFlusher"
        //   121: ldc             "Could not retrieve value from Resources#mResourcesImpl"
        //   123: aload_0        
        //   124: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   127: pop            
        //   128: aconst_null    
        //   129: astore_0       
        //   130: goto            58
        //   133: astore_2       
        //   134: ldc             "ResourcesFlusher"
        //   136: ldc             "Could not retrieve ResourcesImpl#mDrawableCache field"
        //   138: aload_2        
        //   139: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   142: pop            
        //   143: goto            87
        //   146: astore_0       
        //   147: ldc             "ResourcesFlusher"
        //   149: ldc             "Could not retrieve value from ResourcesImpl#mDrawableCache"
        //   151: aload_0        
        //   152: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   155: pop            
        //   156: aconst_null    
        //   157: astore_0       
        //   158: goto            105
        //   161: iconst_0       
        //   162: istore_1       
        //   163: goto            116
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  8      25     37     50     Ljava/lang/NoSuchFieldException;
        //  50     58     118    133    Ljava/lang/IllegalAccessException;
        //  68     87     133    146    Ljava/lang/NoSuchFieldException;
        //  97     105    146    156    Ljava/lang/IllegalAccessException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 83, Size: 83
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
