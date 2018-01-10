// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.annotation.TargetApi;

@TargetApi(21)
class ar
{
    public static void a(final ViewParent viewParent, final View view) {
        try {
            viewParent.onStopNestedScroll(view);
        }
        catch (AbstractMethodError abstractMethodError) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface " + "method onStopNestedScroll", (Throwable)abstractMethodError);
        }
    }
    
    public static void a(final ViewParent viewParent, final View view, final int n, final int n2, final int n3, final int n4) {
        try {
            viewParent.onNestedScroll(view, n, n2, n3, n4);
        }
        catch (AbstractMethodError abstractMethodError) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface " + "method onNestedScroll", (Throwable)abstractMethodError);
        }
    }
    
    public static void a(final ViewParent viewParent, final View view, final int n, final int n2, final int[] array) {
        try {
            viewParent.onNestedPreScroll(view, n, n2, array);
        }
        catch (AbstractMethodError abstractMethodError) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface " + "method onNestedPreScroll", (Throwable)abstractMethodError);
        }
    }
    
    public static boolean a(final ViewParent viewParent, final View view, final float n, final float n2) {
        try {
            return viewParent.onNestedPreFling(view, n, n2);
        }
        catch (AbstractMethodError abstractMethodError) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface " + "method onNestedPreFling", (Throwable)abstractMethodError);
            return false;
        }
    }
    
    public static boolean a(final ViewParent viewParent, final View view, final float n, final float n2, final boolean b) {
        try {
            return viewParent.onNestedFling(view, n, n2, b);
        }
        catch (AbstractMethodError abstractMethodError) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface " + "method onNestedFling", (Throwable)abstractMethodError);
            return false;
        }
    }
    
    public static boolean a(final ViewParent viewParent, final View view, final View view2, final int n) {
        try {
            return viewParent.onStartNestedScroll(view, view2, n);
        }
        catch (AbstractMethodError abstractMethodError) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface " + "method onStartNestedScroll", (Throwable)abstractMethodError);
            return false;
        }
    }
    
    public static void b(final ViewParent viewParent, final View view, final View view2, final int n) {
        try {
            viewParent.onNestedScrollAccepted(view, view2, n);
        }
        catch (AbstractMethodError abstractMethodError) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface " + "method onNestedScrollAccepted", (Throwable)abstractMethodError);
        }
    }
}
