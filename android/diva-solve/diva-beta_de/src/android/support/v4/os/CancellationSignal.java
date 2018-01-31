// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.os;

import android.os.Build$VERSION;

public final class CancellationSignal
{
    private boolean mCancelInProgress;
    private Object mCancellationSignalObj;
    private boolean mIsCanceled;
    private OnCancelListener mOnCancelListener;
    
    private void waitForCancelFinishedLocked() {
        while (this.mCancelInProgress) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void cancel() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        android/support/v4/os/CancellationSignal.mIsCanceled:Z
        //     6: ifeq            12
        //     9: aload_0        
        //    10: monitorexit    
        //    11: return         
        //    12: aload_0        
        //    13: iconst_1       
        //    14: putfield        android/support/v4/os/CancellationSignal.mIsCanceled:Z
        //    17: aload_0        
        //    18: iconst_1       
        //    19: putfield        android/support/v4/os/CancellationSignal.mCancelInProgress:Z
        //    22: aload_0        
        //    23: getfield        android/support/v4/os/CancellationSignal.mOnCancelListener:Landroid/support/v4/os/CancellationSignal$OnCancelListener;
        //    26: astore_1       
        //    27: aload_0        
        //    28: getfield        android/support/v4/os/CancellationSignal.mCancellationSignalObj:Ljava/lang/Object;
        //    31: astore_2       
        //    32: aload_0        
        //    33: monitorexit    
        //    34: aload_1        
        //    35: ifnull          44
        //    38: aload_1        
        //    39: invokeinterface android/support/v4/os/CancellationSignal$OnCancelListener.onCancel:()V
        //    44: aload_2        
        //    45: ifnull          52
        //    48: aload_2        
        //    49: invokestatic    android/support/v4/os/CancellationSignalCompatJellybean.cancel:(Ljava/lang/Object;)V
        //    52: aload_0        
        //    53: monitorenter   
        //    54: aload_0        
        //    55: iconst_0       
        //    56: putfield        android/support/v4/os/CancellationSignal.mCancelInProgress:Z
        //    59: aload_0        
        //    60: invokevirtual   java/lang/Object.notifyAll:()V
        //    63: aload_0        
        //    64: monitorexit    
        //    65: return         
        //    66: astore_1       
        //    67: aload_0        
        //    68: monitorexit    
        //    69: aload_1        
        //    70: athrow         
        //    71: astore_1       
        //    72: aload_0        
        //    73: monitorexit    
        //    74: aload_1        
        //    75: athrow         
        //    76: astore_1       
        //    77: aload_0        
        //    78: monitorenter   
        //    79: aload_0        
        //    80: iconst_0       
        //    81: putfield        android/support/v4/os/CancellationSignal.mCancelInProgress:Z
        //    84: aload_0        
        //    85: invokevirtual   java/lang/Object.notifyAll:()V
        //    88: aload_0        
        //    89: monitorexit    
        //    90: aload_1        
        //    91: athrow         
        //    92: astore_1       
        //    93: aload_0        
        //    94: monitorexit    
        //    95: aload_1        
        //    96: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  2      11     71     76     Any
        //  12     34     71     76     Any
        //  38     44     76     97     Any
        //  48     52     76     97     Any
        //  54     65     66     71     Any
        //  67     69     66     71     Any
        //  72     74     71     76     Any
        //  79     90     92     97     Any
        //  93     95     92     97     Any
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
    
    public Object getCancellationSignalObject() {
        if (Build$VERSION.SDK_INT < 16) {
            return null;
        }
        synchronized (this) {
            if (this.mCancellationSignalObj == null) {
                this.mCancellationSignalObj = CancellationSignalCompatJellybean.create();
                if (this.mIsCanceled) {
                    CancellationSignalCompatJellybean.cancel(this.mCancellationSignalObj);
                }
            }
            return this.mCancellationSignalObj;
        }
    }
    
    public boolean isCanceled() {
        synchronized (this) {
            return this.mIsCanceled;
        }
    }
    
    public void setOnCancelListener(final OnCancelListener mOnCancelListener) {
        synchronized (this) {
            this.waitForCancelFinishedLocked();
            if (this.mOnCancelListener == mOnCancelListener) {
                return;
            }
            this.mOnCancelListener = mOnCancelListener;
            if (!this.mIsCanceled || mOnCancelListener == null) {
                return;
            }
        }
        // monitorexit(this)
        final OnCancelListener onCancelListener;
        onCancelListener.onCancel();
    }
    
    public void throwIfCanceled() {
        if (this.isCanceled()) {
            throw new OperationCanceledException();
        }
    }
    
    public interface OnCancelListener
    {
        void onCancel();
    }
}
