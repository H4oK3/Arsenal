// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.util.Log;
import android.graphics.drawable.Drawable;
import java.lang.reflect.Method;

class DrawableCompatJellybeanMr1
{
    private static final String TAG = "DrawableCompatJellybeanMr1";
    private static Method sGetLayoutDirectionMethod;
    private static boolean sGetLayoutDirectionMethodFetched;
    private static Method sSetLayoutDirectionMethod;
    private static boolean sSetLayoutDirectionMethodFetched;
    
    public static int getLayoutDirection(final Drawable drawable) {
        while (true) {
            if (!DrawableCompatJellybeanMr1.sGetLayoutDirectionMethodFetched) {
                while (true) {
                    try {
                        (DrawableCompatJellybeanMr1.sGetLayoutDirectionMethod = Drawable.class.getDeclaredMethod("getLayoutDirection", (Class<?>[])new Class[0])).setAccessible(true);
                        DrawableCompatJellybeanMr1.sGetLayoutDirectionMethodFetched = true;
                        if (DrawableCompatJellybeanMr1.sGetLayoutDirectionMethod != null) {
                            final Method method = DrawableCompatJellybeanMr1.sGetLayoutDirectionMethod;
                            final Drawable drawable2 = drawable;
                            final int n = 0;
                            final Object[] array = new Object[n];
                            final Object o = method.invoke(drawable2, array);
                            final Integer n2 = (Integer)o;
                            final int intValue = n2;
                            return intValue;
                        }
                        return -1;
                    }
                    catch (NoSuchMethodException ex) {
                        Log.i("DrawableCompatJellybeanMr1", "Failed to retrieve getLayoutDirection() method", (Throwable)ex);
                        continue;
                    }
                    break;
                }
                try {
                    final Method method = DrawableCompatJellybeanMr1.sGetLayoutDirectionMethod;
                    final Drawable drawable2 = drawable;
                    final int n = 0;
                    final Object[] array = new Object[n];
                    final Object o = method.invoke(drawable2, array);
                    final Integer n2 = (Integer)o;
                    final int intValue2;
                    final int intValue = intValue2 = n2;
                    return intValue2;
                }
                catch (Exception ex2) {
                    Log.i("DrawableCompatJellybeanMr1", "Failed to invoke getLayoutDirection() via reflection", (Throwable)ex2);
                    DrawableCompatJellybeanMr1.sGetLayoutDirectionMethod = null;
                }
                return -1;
            }
            continue;
        }
    }
    
    public static void setLayoutDirection(final Drawable p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sSetLayoutDirectionMethodFetched:Z
        //     3: ifne            37
        //     6: ldc             Landroid/graphics/drawable/Drawable;.class
        //     8: ldc             "setLayoutDirection"
        //    10: iconst_1       
        //    11: anewarray       Ljava/lang/Class;
        //    14: dup            
        //    15: iconst_0       
        //    16: getstatic       java/lang/Integer.TYPE:Ljava/lang/Class;
        //    19: aastore        
        //    20: invokevirtual   java/lang/Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    23: putstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sSetLayoutDirectionMethod:Ljava/lang/reflect/Method;
        //    26: getstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sSetLayoutDirectionMethod:Ljava/lang/reflect/Method;
        //    29: iconst_1       
        //    30: invokevirtual   java/lang/reflect/Method.setAccessible:(Z)V
        //    33: iconst_1       
        //    34: putstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sSetLayoutDirectionMethodFetched:Z
        //    37: getstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sSetLayoutDirectionMethod:Ljava/lang/reflect/Method;
        //    40: ifnull          62
        //    43: getstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sSetLayoutDirectionMethod:Ljava/lang/reflect/Method;
        //    46: aload_0        
        //    47: iconst_1       
        //    48: anewarray       Ljava/lang/Object;
        //    51: dup            
        //    52: iconst_0       
        //    53: iload_1        
        //    54: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    57: aastore        
        //    58: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    61: pop            
        //    62: return         
        //    63: astore_2       
        //    64: ldc             "DrawableCompatJellybeanMr1"
        //    66: ldc             "Failed to retrieve setLayoutDirection(int) method"
        //    68: aload_2        
        //    69: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    72: pop            
        //    73: goto            33
        //    76: astore_0       
        //    77: ldc             "DrawableCompatJellybeanMr1"
        //    79: ldc             "Failed to invoke setLayoutDirection(int) via reflection"
        //    81: aload_0        
        //    82: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    85: pop            
        //    86: aconst_null    
        //    87: putstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sSetLayoutDirectionMethod:Ljava/lang/reflect/Method;
        //    90: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  6      33     63     76     Ljava/lang/NoSuchMethodException;
        //  43     62     76     91     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0062:
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
}
