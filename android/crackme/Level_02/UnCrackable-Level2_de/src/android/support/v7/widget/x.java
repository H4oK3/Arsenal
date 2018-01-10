// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.g.ae;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow$OnDismissListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;
import android.database.DataSetObserver;
import android.widget.ThemedSpinnerAdapter;
import android.support.v7.b.a.b;
import android.widget.ListAdapter;
import android.widget.Adapter;
import android.view.MotionEvent;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.os.Build$VERSION;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.View$MeasureSpec;
import android.graphics.drawable.Drawable;
import android.content.res.Resources$Theme;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.widget.SpinnerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.support.v4.g.ab;
import android.widget.Spinner;

public class x extends Spinner implements ab
{
    private static final int[] d;
    b a;
    int b;
    final Rect c;
    private g e;
    private Context f;
    private ag g;
    private SpinnerAdapter h;
    private boolean i;
    
    static {
        d = new int[] { 16843505 };
    }
    
    public x(final Context context, final AttributeSet set) {
        this(context, set, android.support.v7.a.a.a.spinnerStyle);
    }
    
    public x(final Context context, final AttributeSet set, final int n) {
        this(context, set, n, -1);
    }
    
    public x(final Context context, final AttributeSet set, final int n, final int n2) {
        this(context, set, n, n2, null);
    }
    
    public x(final Context p0, final AttributeSet p1, final int p2, final int p3, final Resources$Theme p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: aload_2        
        //     3: iload_3        
        //     4: invokespecial   android/widget/Spinner.<init>:(Landroid/content/Context;Landroid/util/AttributeSet;I)V
        //     7: aload_0        
        //     8: new             Landroid/graphics/Rect;
        //    11: dup            
        //    12: invokespecial   android/graphics/Rect.<init>:()V
        //    15: putfield        android/support/v7/widget/x.c:Landroid/graphics/Rect;
        //    18: aload_1        
        //    19: aload_2        
        //    20: getstatic       android/support/v7/a/a$j.Spinner:[I
        //    23: iload_3        
        //    24: iconst_0       
        //    25: invokestatic    android/support/v7/widget/av.a:(Landroid/content/Context;Landroid/util/AttributeSet;[III)Landroid/support/v7/widget/av;
        //    28: astore          10
        //    30: aload_0        
        //    31: new             Landroid/support/v7/widget/g;
        //    34: dup            
        //    35: aload_0        
        //    36: invokespecial   android/support/v7/widget/g.<init>:(Landroid/view/View;)V
        //    39: putfield        android/support/v7/widget/x.e:Landroid/support/v7/widget/g;
        //    42: aload           5
        //    44: ifnull          329
        //    47: aload_0        
        //    48: new             Landroid/support/v7/view/d;
        //    51: dup            
        //    52: aload_1        
        //    53: aload           5
        //    55: invokespecial   android/support/v7/view/d.<init>:(Landroid/content/Context;Landroid/content/res/Resources$Theme;)V
        //    58: putfield        android/support/v7/widget/x.f:Landroid/content/Context;
        //    61: aload_0        
        //    62: getfield        android/support/v7/widget/x.f:Landroid/content/Context;
        //    65: ifnull          249
        //    68: iload           4
        //    70: istore          7
        //    72: iload           4
        //    74: iconst_m1      
        //    75: if_icmpne       146
        //    78: getstatic       android/os/Build$VERSION.SDK_INT:I
        //    81: bipush          11
        //    83: if_icmplt       444
        //    86: aload_1        
        //    87: aload_2        
        //    88: getstatic       android/support/v7/widget/x.d:[I
        //    91: iload_3        
        //    92: iconst_0       
        //    93: invokevirtual   android/content/Context.obtainStyledAttributes:(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;
        //    96: astore          8
        //    98: iload           4
        //   100: istore          6
        //   102: aload           8
        //   104: astore          5
        //   106: aload           8
        //   108: iconst_0       
        //   109: invokevirtual   android/content/res/TypedArray.hasValue:(I)Z
        //   112: ifeq            128
        //   115: aload           8
        //   117: astore          5
        //   119: aload           8
        //   121: iconst_0       
        //   122: iconst_0       
        //   123: invokevirtual   android/content/res/TypedArray.getInt:(II)I
        //   126: istore          6
        //   128: iload           6
        //   130: istore          7
        //   132: aload           8
        //   134: ifnull          146
        //   137: aload           8
        //   139: invokevirtual   android/content/res/TypedArray.recycle:()V
        //   142: iload           6
        //   144: istore          7
        //   146: iload           7
        //   148: iconst_1       
        //   149: if_icmpne       249
        //   152: new             Landroid/support/v7/widget/x$b;
        //   155: dup            
        //   156: aload_0        
        //   157: aload_0        
        //   158: getfield        android/support/v7/widget/x.f:Landroid/content/Context;
        //   161: aload_2        
        //   162: iload_3        
        //   163: invokespecial   android/support/v7/widget/x$b.<init>:(Landroid/support/v7/widget/x;Landroid/content/Context;Landroid/util/AttributeSet;I)V
        //   166: astore          5
        //   168: aload_0        
        //   169: getfield        android/support/v7/widget/x.f:Landroid/content/Context;
        //   172: aload_2        
        //   173: getstatic       android/support/v7/a/a$j.Spinner:[I
        //   176: iload_3        
        //   177: iconst_0       
        //   178: invokestatic    android/support/v7/widget/av.a:(Landroid/content/Context;Landroid/util/AttributeSet;[III)Landroid/support/v7/widget/av;
        //   181: astore          8
        //   183: aload_0        
        //   184: aload           8
        //   186: getstatic       android/support/v7/a/a$j.Spinner_android_dropDownWidth:I
        //   189: bipush          -2
        //   191: invokevirtual   android/support/v7/widget/av.f:(II)I
        //   194: putfield        android/support/v7/widget/x.b:I
        //   197: aload           5
        //   199: aload           8
        //   201: getstatic       android/support/v7/a/a$j.Spinner_android_popupBackground:I
        //   204: invokevirtual   android/support/v7/widget/av.a:(I)Landroid/graphics/drawable/Drawable;
        //   207: invokevirtual   android/support/v7/widget/x$b.a:(Landroid/graphics/drawable/Drawable;)V
        //   210: aload           5
        //   212: aload           10
        //   214: getstatic       android/support/v7/a/a$j.Spinner_android_prompt:I
        //   217: invokevirtual   android/support/v7/widget/av.d:(I)Ljava/lang/String;
        //   220: invokevirtual   android/support/v7/widget/x$b.a:(Ljava/lang/CharSequence;)V
        //   223: aload           8
        //   225: invokevirtual   android/support/v7/widget/av.a:()V
        //   228: aload_0        
        //   229: aload           5
        //   231: putfield        android/support/v7/widget/x.a:Landroid/support/v7/widget/x$b;
        //   234: aload_0        
        //   235: new             Landroid/support/v7/widget/x$1;
        //   238: dup            
        //   239: aload_0        
        //   240: aload_0        
        //   241: aload           5
        //   243: invokespecial   android/support/v7/widget/x$1.<init>:(Landroid/support/v7/widget/x;Landroid/view/View;Landroid/support/v7/widget/x$b;)V
        //   246: putfield        android/support/v7/widget/x.g:Landroid/support/v7/widget/ag;
        //   249: aload           10
        //   251: getstatic       android/support/v7/a/a$j.Spinner_android_entries:I
        //   254: invokevirtual   android/support/v7/widget/av.f:(I)[Ljava/lang/CharSequence;
        //   257: astore          5
        //   259: aload           5
        //   261: ifnull          289
        //   264: new             Landroid/widget/ArrayAdapter;
        //   267: dup            
        //   268: aload_1        
        //   269: ldc             17367048
        //   271: aload           5
        //   273: invokespecial   android/widget/ArrayAdapter.<init>:(Landroid/content/Context;I[Ljava/lang/Object;)V
        //   276: astore_1       
        //   277: aload_1        
        //   278: getstatic       android/support/v7/a/a$g.support_simple_spinner_dropdown_item:I
        //   281: invokevirtual   android/widget/ArrayAdapter.setDropDownViewResource:(I)V
        //   284: aload_0        
        //   285: aload_1        
        //   286: invokevirtual   android/support/v7/widget/x.setAdapter:(Landroid/widget/SpinnerAdapter;)V
        //   289: aload           10
        //   291: invokevirtual   android/support/v7/widget/av.a:()V
        //   294: aload_0        
        //   295: iconst_1       
        //   296: putfield        android/support/v7/widget/x.i:Z
        //   299: aload_0        
        //   300: getfield        android/support/v7/widget/x.h:Landroid/widget/SpinnerAdapter;
        //   303: ifnull          319
        //   306: aload_0        
        //   307: aload_0        
        //   308: getfield        android/support/v7/widget/x.h:Landroid/widget/SpinnerAdapter;
        //   311: invokevirtual   android/support/v7/widget/x.setAdapter:(Landroid/widget/SpinnerAdapter;)V
        //   314: aload_0        
        //   315: aconst_null    
        //   316: putfield        android/support/v7/widget/x.h:Landroid/widget/SpinnerAdapter;
        //   319: aload_0        
        //   320: getfield        android/support/v7/widget/x.e:Landroid/support/v7/widget/g;
        //   323: aload_2        
        //   324: iload_3        
        //   325: invokevirtual   android/support/v7/widget/g.a:(Landroid/util/AttributeSet;I)V
        //   328: return         
        //   329: aload           10
        //   331: getstatic       android/support/v7/a/a$j.Spinner_popupTheme:I
        //   334: iconst_0       
        //   335: invokevirtual   android/support/v7/widget/av.g:(II)I
        //   338: istore          6
        //   340: iload           6
        //   342: ifeq            362
        //   345: aload_0        
        //   346: new             Landroid/support/v7/view/d;
        //   349: dup            
        //   350: aload_1        
        //   351: iload           6
        //   353: invokespecial   android/support/v7/view/d.<init>:(Landroid/content/Context;I)V
        //   356: putfield        android/support/v7/widget/x.f:Landroid/content/Context;
        //   359: goto            61
        //   362: getstatic       android/os/Build$VERSION.SDK_INT:I
        //   365: bipush          23
        //   367: if_icmpge       382
        //   370: aload_1        
        //   371: astore          5
        //   373: aload_0        
        //   374: aload           5
        //   376: putfield        android/support/v7/widget/x.f:Landroid/content/Context;
        //   379: goto            61
        //   382: aconst_null    
        //   383: astore          5
        //   385: goto            373
        //   388: astore          9
        //   390: aconst_null    
        //   391: astore          8
        //   393: aload           8
        //   395: astore          5
        //   397: ldc             "AppCompatSpinner"
        //   399: ldc             "Could not read android:spinnerMode"
        //   401: aload           9
        //   403: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   406: pop            
        //   407: iload           4
        //   409: istore          7
        //   411: aload           8
        //   413: ifnull          146
        //   416: aload           8
        //   418: invokevirtual   android/content/res/TypedArray.recycle:()V
        //   421: iload           4
        //   423: istore          7
        //   425: goto            146
        //   428: astore_1       
        //   429: aconst_null    
        //   430: astore          5
        //   432: aload           5
        //   434: ifnull          442
        //   437: aload           5
        //   439: invokevirtual   android/content/res/TypedArray.recycle:()V
        //   442: aload_1        
        //   443: athrow         
        //   444: iconst_1       
        //   445: istore          7
        //   447: goto            146
        //   450: astore_1       
        //   451: goto            432
        //   454: astore          9
        //   456: goto            393
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  86     98     388    393    Ljava/lang/Exception;
        //  86     98     428    432    Any
        //  106    115    454    459    Ljava/lang/Exception;
        //  106    115    450    454    Any
        //  119    128    454    459    Ljava/lang/Exception;
        //  119    128    450    454    Any
        //  397    407    450    454    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0128:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
    
    int a(final SpinnerAdapter spinnerAdapter, final Drawable drawable) {
        if (spinnerAdapter == null) {
            return 0;
        }
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 0);
        final int measureSpec2 = View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 0);
        final int max = Math.max(0, this.getSelectedItemPosition());
        final int min = Math.min(spinnerAdapter.getCount(), max + 15);
        int i = Math.max(0, max - (15 - (min - max)));
        View view = null;
        int max2 = 0;
        int n = 0;
        while (i < min) {
            final int itemViewType = spinnerAdapter.getItemViewType(i);
            if (itemViewType != n) {
                view = null;
                n = itemViewType;
            }
            view = spinnerAdapter.getView(i, view, (ViewGroup)this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup$LayoutParams(-2, -2));
            }
            view.measure(measureSpec, measureSpec2);
            max2 = Math.max(max2, view.getMeasuredWidth());
            ++i;
        }
        if (drawable != null) {
            drawable.getPadding(this.c);
            return this.c.left + this.c.right + max2;
        }
        return max2;
    }
    
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.e != null) {
            this.e.c();
        }
    }
    
    public int getDropDownHorizontalOffset() {
        if (this.a != null) {
            return this.a.j();
        }
        if (Build$VERSION.SDK_INT >= 16) {
            return super.getDropDownHorizontalOffset();
        }
        return 0;
    }
    
    public int getDropDownVerticalOffset() {
        if (this.a != null) {
            return this.a.k();
        }
        if (Build$VERSION.SDK_INT >= 16) {
            return super.getDropDownVerticalOffset();
        }
        return 0;
    }
    
    public int getDropDownWidth() {
        if (this.a != null) {
            return this.b;
        }
        if (Build$VERSION.SDK_INT >= 16) {
            return super.getDropDownWidth();
        }
        return 0;
    }
    
    public Drawable getPopupBackground() {
        if (this.a != null) {
            return this.a.h();
        }
        if (Build$VERSION.SDK_INT >= 16) {
            return super.getPopupBackground();
        }
        return null;
    }
    
    public Context getPopupContext() {
        if (this.a != null) {
            return this.f;
        }
        if (Build$VERSION.SDK_INT >= 23) {
            return super.getPopupContext();
        }
        return null;
    }
    
    public CharSequence getPrompt() {
        if (this.a != null) {
            return this.a.b();
        }
        return super.getPrompt();
    }
    
    public ColorStateList getSupportBackgroundTintList() {
        if (this.e != null) {
            return this.e.a();
        }
        return null;
    }
    
    public PorterDuff$Mode getSupportBackgroundTintMode() {
        if (this.e != null) {
            return this.e.b();
        }
        return null;
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.a != null && this.a.d()) {
            this.a.c();
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
        if (this.a != null && View$MeasureSpec.getMode(n) == Integer.MIN_VALUE) {
            this.setMeasuredDimension(Math.min(Math.max(this.getMeasuredWidth(), this.a(this.getAdapter(), this.getBackground())), View$MeasureSpec.getSize(n)), this.getMeasuredHeight());
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return (this.g != null && this.g.onTouch((View)this, motionEvent)) || super.onTouchEvent(motionEvent);
    }
    
    public boolean performClick() {
        if (this.a != null) {
            if (!this.a.d()) {
                this.a.a();
            }
            return true;
        }
        return super.performClick();
    }
    
    public void setAdapter(final SpinnerAdapter spinnerAdapter) {
        if (!this.i) {
            this.h = spinnerAdapter;
        }
        else {
            super.setAdapter(spinnerAdapter);
            if (this.a != null) {
                Context context;
                if (this.f == null) {
                    context = this.getContext();
                }
                else {
                    context = this.f;
                }
                this.a.a((ListAdapter)new a(spinnerAdapter, context.getTheme()));
            }
        }
    }
    
    public void setBackgroundDrawable(final Drawable backgroundDrawable) {
        super.setBackgroundDrawable(backgroundDrawable);
        if (this.e != null) {
            this.e.a(backgroundDrawable);
        }
    }
    
    public void setBackgroundResource(final int backgroundResource) {
        super.setBackgroundResource(backgroundResource);
        if (this.e != null) {
            this.e.a(backgroundResource);
        }
    }
    
    public void setDropDownHorizontalOffset(final int dropDownHorizontalOffset) {
        if (this.a != null) {
            this.a.c(dropDownHorizontalOffset);
        }
        else if (Build$VERSION.SDK_INT >= 16) {
            super.setDropDownHorizontalOffset(dropDownHorizontalOffset);
        }
    }
    
    public void setDropDownVerticalOffset(final int dropDownVerticalOffset) {
        if (this.a != null) {
            this.a.d(dropDownVerticalOffset);
        }
        else if (Build$VERSION.SDK_INT >= 16) {
            super.setDropDownVerticalOffset(dropDownVerticalOffset);
        }
    }
    
    public void setDropDownWidth(final int n) {
        if (this.a != null) {
            this.b = n;
        }
        else if (Build$VERSION.SDK_INT >= 16) {
            super.setDropDownWidth(n);
        }
    }
    
    public void setPopupBackgroundDrawable(final Drawable popupBackgroundDrawable) {
        if (this.a != null) {
            this.a.a(popupBackgroundDrawable);
        }
        else if (Build$VERSION.SDK_INT >= 16) {
            super.setPopupBackgroundDrawable(popupBackgroundDrawable);
        }
    }
    
    public void setPopupBackgroundResource(final int n) {
        this.setPopupBackgroundDrawable(android.support.v7.b.a.b.b(this.getPopupContext(), n));
    }
    
    public void setPrompt(final CharSequence prompt) {
        if (this.a != null) {
            this.a.a(prompt);
            return;
        }
        super.setPrompt(prompt);
    }
    
    public void setSupportBackgroundTintList(final ColorStateList list) {
        if (this.e != null) {
            this.e.a(list);
        }
    }
    
    public void setSupportBackgroundTintMode(final PorterDuff$Mode porterDuff$Mode) {
        if (this.e != null) {
            this.e.a(porterDuff$Mode);
        }
    }
    
    private static class a implements ListAdapter, SpinnerAdapter
    {
        private SpinnerAdapter a;
        private ListAdapter b;
        
        public a(final SpinnerAdapter a, final Resources$Theme dropDownViewTheme) {
            this.a = a;
            if (a instanceof ListAdapter) {
                this.b = (ListAdapter)a;
            }
            if (dropDownViewTheme != null) {
                if (Build$VERSION.SDK_INT >= 23 && a instanceof ThemedSpinnerAdapter) {
                    final ThemedSpinnerAdapter themedSpinnerAdapter = (ThemedSpinnerAdapter)a;
                    if (themedSpinnerAdapter.getDropDownViewTheme() != dropDownViewTheme) {
                        themedSpinnerAdapter.setDropDownViewTheme(dropDownViewTheme);
                    }
                }
                else if (a instanceof ar) {
                    final ar ar = (ar)a;
                    if (ar.a() == null) {
                        ar.a(dropDownViewTheme);
                    }
                }
            }
        }
        
        public boolean areAllItemsEnabled() {
            final ListAdapter b = this.b;
            return b == null || b.areAllItemsEnabled();
        }
        
        public int getCount() {
            if (this.a == null) {
                return 0;
            }
            return this.a.getCount();
        }
        
        public View getDropDownView(final int n, final View view, final ViewGroup viewGroup) {
            if (this.a == null) {
                return null;
            }
            return this.a.getDropDownView(n, view, viewGroup);
        }
        
        public Object getItem(final int n) {
            if (this.a == null) {
                return null;
            }
            return this.a.getItem(n);
        }
        
        public long getItemId(final int n) {
            if (this.a == null) {
                return -1L;
            }
            return this.a.getItemId(n);
        }
        
        public int getItemViewType(final int n) {
            return 0;
        }
        
        public View getView(final int n, final View view, final ViewGroup viewGroup) {
            return this.getDropDownView(n, view, viewGroup);
        }
        
        public int getViewTypeCount() {
            return 1;
        }
        
        public boolean hasStableIds() {
            return this.a != null && this.a.hasStableIds();
        }
        
        public boolean isEmpty() {
            return this.getCount() == 0;
        }
        
        public boolean isEnabled(final int n) {
            final ListAdapter b = this.b;
            return b == null || b.isEnabled(n);
        }
        
        public void registerDataSetObserver(final DataSetObserver dataSetObserver) {
            if (this.a != null) {
                this.a.registerDataSetObserver(dataSetObserver);
            }
        }
        
        public void unregisterDataSetObserver(final DataSetObserver dataSetObserver) {
            if (this.a != null) {
                this.a.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }
    
    private class b extends ai
    {
        ListAdapter a;
        private CharSequence h;
        private final Rect i;
        
        public b(final Context context, final AttributeSet set, final int n) {
            super(context, set, n);
            this.i = new Rect();
            this.b((View)x.this);
            this.a(true);
            this.a(0);
            this.a((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
                public void onItemClick(final AdapterView<?> adapterView, final View view, final int selection, final long n) {
                    x.this.setSelection(selection);
                    if (x.this.getOnItemClickListener() != null) {
                        x.this.performItemClick(view, selection, android.support.v7.widget.x.b.this.a.getItemId(selection));
                    }
                    android.support.v7.widget.x.b.this.c();
                }
            });
        }
        
        @Override
        public void a() {
            final boolean d = this.d();
            this.f();
            this.h(2);
            super.a();
            this.e().setChoiceMode(1);
            this.i(x.this.getSelectedItemPosition());
            if (!d) {
                final ViewTreeObserver viewTreeObserver = x.this.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener = (ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
                        public void onGlobalLayout() {
                            if (!android.support.v7.widget.x.b.this.a((View)x.this)) {
                                android.support.v7.widget.x.b.this.c();
                                return;
                            }
                            android.support.v7.widget.x.b.this.f();
                            android.support.v7.widget.x.b.this.a();
                        }
                    };
                    viewTreeObserver.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)viewTreeObserver$OnGlobalLayoutListener);
                    this.a((PopupWindow$OnDismissListener)new PopupWindow$OnDismissListener() {
                        public void onDismiss() {
                            final ViewTreeObserver viewTreeObserver = x.this.getViewTreeObserver();
                            if (viewTreeObserver != null) {
                                viewTreeObserver.removeGlobalOnLayoutListener(viewTreeObserver$OnGlobalLayoutListener);
                            }
                        }
                    });
                }
            }
        }
        
        @Override
        public void a(final ListAdapter a) {
            super.a(a);
            this.a = a;
        }
        
        public void a(final CharSequence h) {
            this.h = h;
        }
        
        boolean a(final View view) {
            return ae.r(view) && view.getGlobalVisibleRect(this.i);
        }
        
        public CharSequence b() {
            return this.h;
        }
        
        void f() {
            final Drawable h = this.h();
            int right;
            if (h != null) {
                h.getPadding(x.this.c);
                if (ay.a((View)x.this)) {
                    right = x.this.c.right;
                }
                else {
                    right = -x.this.c.left;
                }
            }
            else {
                final Rect c = x.this.c;
                x.this.c.right = 0;
                c.left = 0;
                right = 0;
            }
            final int paddingLeft = x.this.getPaddingLeft();
            final int paddingRight = x.this.getPaddingRight();
            final int width = x.this.getWidth();
            if (x.this.b == -2) {
                int a = x.this.a((SpinnerAdapter)this.a, this.h());
                final int n = x.this.getContext().getResources().getDisplayMetrics().widthPixels - x.this.c.left - x.this.c.right;
                if (a > n) {
                    a = n;
                }
                this.g(Math.max(a, width - paddingLeft - paddingRight));
            }
            else if (x.this.b == -1) {
                this.g(width - paddingLeft - paddingRight);
            }
            else {
                this.g(x.this.b);
            }
            int n2;
            if (ay.a((View)x.this)) {
                n2 = width - paddingRight - this.l() + right;
            }
            else {
                n2 = right + paddingLeft;
            }
            this.c(n2);
        }
    }
}
