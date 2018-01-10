// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.math.BigDecimal;
import android.content.ComponentName;
import android.content.pm.ResolveInfo;
import java.util.Collections;
import android.os.AsyncTask;
import android.support.v4.e.a;
import java.util.Collection;
import java.util.ArrayList;
import android.text.TextUtils;
import java.util.HashMap;
import android.content.Intent;
import java.util.List;
import android.content.Context;
import java.util.Map;
import android.database.DataSetObservable;

class e extends DataSetObservable
{
    static final String a;
    private static final Object e;
    private static final Map<String, e> f;
    final Context b;
    final String c;
    boolean d;
    private final Object g;
    private final List<a> h;
    private final List<c> i;
    private Intent j;
    private b k;
    private int l;
    private boolean m;
    private boolean n;
    private boolean o;
    private d p;
    
    static {
        a = e.class.getSimpleName();
        e = new Object();
        f = new HashMap<String, e>();
    }
    
    private boolean a(final c c) {
        final boolean add = this.i.add(c);
        if (add) {
            this.n = true;
            this.h();
            this.c();
            this.e();
            this.notifyChanged();
        }
        return add;
    }
    
    private void c() {
        if (!this.m) {
            throw new IllegalStateException("No preceding call to #readHistoricalData");
        }
        if (this.n) {
            this.n = false;
            if (!TextUtils.isEmpty((CharSequence)this.c)) {
                android.support.v4.e.a.a((android.os.AsyncTask<Object, Object, Object>)new e(), new ArrayList(this.i), this.c);
            }
        }
    }
    
    private void d() {
        final boolean f = this.f();
        final boolean g = this.g();
        this.h();
        if (f | g) {
            this.e();
            this.notifyChanged();
        }
    }
    
    private boolean e() {
        if (this.k != null && this.j != null && !this.h.isEmpty() && !this.i.isEmpty()) {
            this.k.a(this.j, this.h, Collections.unmodifiableList((List<? extends c>)this.i));
            return true;
        }
        return false;
    }
    
    private boolean f() {
        boolean b = false;
        if (this.o) {
            b = b;
            if (this.j != null) {
                this.o = false;
                this.h.clear();
                final List queryIntentActivities = this.b.getPackageManager().queryIntentActivities(this.j, 0);
                for (int size = queryIntentActivities.size(), i = 0; i < size; ++i) {
                    this.h.add(new a(queryIntentActivities.get(i)));
                }
                b = true;
            }
        }
        return b;
    }
    
    private boolean g() {
        if (this.d && this.n && !TextUtils.isEmpty((CharSequence)this.c)) {
            this.d = false;
            this.m = true;
            this.i();
            return true;
        }
        return false;
    }
    
    private void h() {
        final int n = this.i.size() - this.l;
        if (n > 0) {
            this.n = true;
            for (int i = 0; i < n; ++i) {
                final c c = this.i.remove(0);
            }
        }
    }
    
    private void i() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        android/support/v7/widget/e.b:Landroid/content/Context;
        //     4: aload_0        
        //     5: getfield        android/support/v7/widget/e.c:Ljava/lang/String;
        //     8: invokevirtual   android/content/Context.openFileInput:(Ljava/lang/String;)Ljava/io/FileInputStream;
        //    11: astore_2       
        //    12: invokestatic    android/util/Xml.newPullParser:()Lorg/xmlpull/v1/XmlPullParser;
        //    15: astore_3       
        //    16: aload_3        
        //    17: aload_2        
        //    18: ldc             "UTF-8"
        //    20: invokeinterface org/xmlpull/v1/XmlPullParser.setInput:(Ljava/io/InputStream;Ljava/lang/String;)V
        //    25: iconst_0       
        //    26: istore_1       
        //    27: iload_1        
        //    28: iconst_1       
        //    29: if_icmpeq       47
        //    32: iload_1        
        //    33: iconst_2       
        //    34: if_icmpeq       47
        //    37: aload_3        
        //    38: invokeinterface org/xmlpull/v1/XmlPullParser.next:()I
        //    43: istore_1       
        //    44: goto            27
        //    47: ldc             "historical-records"
        //    49: aload_3        
        //    50: invokeinterface org/xmlpull/v1/XmlPullParser.getName:()Ljava/lang/String;
        //    55: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    58: ifne            111
        //    61: new             Lorg/xmlpull/v1/XmlPullParserException;
        //    64: dup            
        //    65: ldc             "Share records file does not start with historical-records tag."
        //    67: invokespecial   org/xmlpull/v1/XmlPullParserException.<init>:(Ljava/lang/String;)V
        //    70: athrow         
        //    71: astore_3       
        //    72: getstatic       android/support/v7/widget/e.a:Ljava/lang/String;
        //    75: new             Ljava/lang/StringBuilder;
        //    78: dup            
        //    79: invokespecial   java/lang/StringBuilder.<init>:()V
        //    82: ldc             "Error reading historical recrod file: "
        //    84: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    87: aload_0        
        //    88: getfield        android/support/v7/widget/e.c:Ljava/lang/String;
        //    91: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    94: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    97: aload_3        
        //    98: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   101: pop            
        //   102: aload_2        
        //   103: ifnull          110
        //   106: aload_2        
        //   107: invokevirtual   java/io/FileInputStream.close:()V
        //   110: return         
        //   111: aload_0        
        //   112: getfield        android/support/v7/widget/e.i:Ljava/util/List;
        //   115: astore          4
        //   117: aload           4
        //   119: invokeinterface java/util/List.clear:()V
        //   124: aload_3        
        //   125: invokeinterface org/xmlpull/v1/XmlPullParser.next:()I
        //   130: istore_1       
        //   131: iload_1        
        //   132: iconst_1       
        //   133: if_icmpne       147
        //   136: aload_2        
        //   137: ifnull          110
        //   140: aload_2        
        //   141: invokevirtual   java/io/FileInputStream.close:()V
        //   144: return         
        //   145: astore_2       
        //   146: return         
        //   147: iload_1        
        //   148: iconst_3       
        //   149: if_icmpeq       124
        //   152: iload_1        
        //   153: iconst_4       
        //   154: if_icmpeq       124
        //   157: ldc             "historical-record"
        //   159: aload_3        
        //   160: invokeinterface org/xmlpull/v1/XmlPullParser.getName:()Ljava/lang/String;
        //   165: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   168: ifne            223
        //   171: new             Lorg/xmlpull/v1/XmlPullParserException;
        //   174: dup            
        //   175: ldc             "Share records file not well-formed."
        //   177: invokespecial   org/xmlpull/v1/XmlPullParserException.<init>:(Ljava/lang/String;)V
        //   180: athrow         
        //   181: astore_3       
        //   182: getstatic       android/support/v7/widget/e.a:Ljava/lang/String;
        //   185: new             Ljava/lang/StringBuilder;
        //   188: dup            
        //   189: invokespecial   java/lang/StringBuilder.<init>:()V
        //   192: ldc             "Error reading historical recrod file: "
        //   194: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   197: aload_0        
        //   198: getfield        android/support/v7/widget/e.c:Ljava/lang/String;
        //   201: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   204: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   207: aload_3        
        //   208: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   211: pop            
        //   212: aload_2        
        //   213: ifnull          110
        //   216: aload_2        
        //   217: invokevirtual   java/io/FileInputStream.close:()V
        //   220: return         
        //   221: astore_2       
        //   222: return         
        //   223: aload           4
        //   225: new             Landroid/support/v7/widget/e$c;
        //   228: dup            
        //   229: aload_3        
        //   230: aconst_null    
        //   231: ldc             "activity"
        //   233: invokeinterface org/xmlpull/v1/XmlPullParser.getAttributeValue:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   238: aload_3        
        //   239: aconst_null    
        //   240: ldc             "time"
        //   242: invokeinterface org/xmlpull/v1/XmlPullParser.getAttributeValue:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   247: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
        //   250: aload_3        
        //   251: aconst_null    
        //   252: ldc_w           "weight"
        //   255: invokeinterface org/xmlpull/v1/XmlPullParser.getAttributeValue:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   260: invokestatic    java/lang/Float.parseFloat:(Ljava/lang/String;)F
        //   263: invokespecial   android/support/v7/widget/e$c.<init>:(Ljava/lang/String;JF)V
        //   266: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   271: pop            
        //   272: goto            124
        //   275: astore_3       
        //   276: aload_2        
        //   277: ifnull          284
        //   280: aload_2        
        //   281: invokevirtual   java/io/FileInputStream.close:()V
        //   284: aload_3        
        //   285: athrow         
        //   286: astore_2       
        //   287: return         
        //   288: astore_2       
        //   289: goto            284
        //   292: astore_2       
        //   293: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                   
        //  -----  -----  -----  -----  ---------------------------------------
        //  0      12     292    294    Ljava/io/FileNotFoundException;
        //  12     25     71     110    Lorg/xmlpull/v1/XmlPullParserException;
        //  12     25     181    223    Ljava/io/IOException;
        //  12     25     275    286    Any
        //  37     44     71     110    Lorg/xmlpull/v1/XmlPullParserException;
        //  37     44     181    223    Ljava/io/IOException;
        //  37     44     275    286    Any
        //  47     71     71     110    Lorg/xmlpull/v1/XmlPullParserException;
        //  47     71     181    223    Ljava/io/IOException;
        //  47     71     275    286    Any
        //  72     102    275    286    Any
        //  106    110    286    288    Ljava/io/IOException;
        //  111    124    71     110    Lorg/xmlpull/v1/XmlPullParserException;
        //  111    124    181    223    Ljava/io/IOException;
        //  111    124    275    286    Any
        //  124    131    71     110    Lorg/xmlpull/v1/XmlPullParserException;
        //  124    131    181    223    Ljava/io/IOException;
        //  124    131    275    286    Any
        //  140    144    145    147    Ljava/io/IOException;
        //  157    181    71     110    Lorg/xmlpull/v1/XmlPullParserException;
        //  157    181    181    223    Ljava/io/IOException;
        //  157    181    275    286    Any
        //  182    212    275    286    Any
        //  216    220    221    223    Ljava/io/IOException;
        //  223    272    71     110    Lorg/xmlpull/v1/XmlPullParserException;
        //  223    272    181    223    Ljava/io/IOException;
        //  223    272    275    286    Any
        //  280    284    288    292    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 142, Size: 142
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3417)
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
    
    public int a() {
        synchronized (this.g) {
            this.d();
            return this.h.size();
        }
    }
    
    public int a(final ResolveInfo resolveInfo) {
        while (true) {
            while (true) {
                int n;
                synchronized (this.g) {
                    this.d();
                    final List<a> h = this.h;
                    final int size = h.size();
                    n = 0;
                    if (n >= size) {
                        return -1;
                    }
                    if (h.get(n).a == resolveInfo) {
                        return n;
                    }
                }
                ++n;
                continue;
            }
        }
    }
    
    public ResolveInfo a(final int n) {
        synchronized (this.g) {
            this.d();
            return this.h.get(n).a;
        }
    }
    
    public Intent b(final int n) {
        synchronized (this.g) {
            if (this.j == null) {
                return null;
            }
            this.d();
            final a a = this.h.get(n);
            final ComponentName component = new ComponentName(a.a.activityInfo.packageName, a.a.activityInfo.name);
            final Intent intent = new Intent(this.j);
            intent.setComponent(component);
            if (this.p != null && this.p.a(this, new Intent(intent))) {
                return null;
            }
            this.a(new c(component, System.currentTimeMillis(), 1.0f));
            return intent;
        }
    }
    
    public ResolveInfo b() {
        synchronized (this.g) {
            this.d();
            if (!this.h.isEmpty()) {
                return this.h.get(0).a;
            }
            return null;
        }
    }
    
    public void c(final int n) {
        while (true) {
            while (true) {
                synchronized (this.g) {
                    this.d();
                    final a a = this.h.get(n);
                    final a a2 = this.h.get(0);
                    if (a2 != null) {
                        final float n2 = a2.b - a.b + 5.0f;
                        this.a(new c(new ComponentName(a.a.activityInfo.packageName, a.a.activityInfo.name), System.currentTimeMillis(), n2));
                        return;
                    }
                }
                final float n2 = 1.0f;
                continue;
            }
        }
    }
    
    public final class a implements Comparable<a>
    {
        public final ResolveInfo a;
        public float b;
        
        public a(final ResolveInfo a) {
            this.a = a;
        }
        
        public int a(final a a) {
            return Float.floatToIntBits(a.b) - Float.floatToIntBits(this.b);
        }
        
        @Override
        public boolean equals(final Object o) {
            if (this != o) {
                if (o == null) {
                    return false;
                }
                if (this.getClass() != o.getClass()) {
                    return false;
                }
                if (Float.floatToIntBits(this.b) != Float.floatToIntBits(((a)o).b)) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            return Float.floatToIntBits(this.b) + 31;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append("resolveInfo:").append(this.a.toString());
            sb.append("; weight:").append(new BigDecimal(this.b));
            sb.append("]");
            return sb.toString();
        }
    }
    
    public interface b
    {
        void a(final Intent p0, final List<a> p1, final List<c> p2);
    }
    
    public static final class c
    {
        public final ComponentName a;
        public final long b;
        public final float c;
        
        public c(final ComponentName a, final long b, final float c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        
        public c(final String s, final long n, final float n2) {
            this(ComponentName.unflattenFromString(s), n, n2);
        }
        
        @Override
        public boolean equals(final Object o) {
            if (this != o) {
                if (o == null) {
                    return false;
                }
                if (this.getClass() != o.getClass()) {
                    return false;
                }
                final c c = (c)o;
                if (this.a == null) {
                    if (c.a != null) {
                        return false;
                    }
                }
                else if (!this.a.equals((Object)c.a)) {
                    return false;
                }
                if (this.b != c.b) {
                    return false;
                }
                if (Float.floatToIntBits(this.c) != Float.floatToIntBits(c.c)) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            int hashCode;
            if (this.a == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.a.hashCode();
            }
            return ((hashCode + 31) * 31 + (int)(this.b ^ this.b >>> 32)) * 31 + Float.floatToIntBits(this.c);
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append("; activity:").append(this.a);
            sb.append("; time:").append(this.b);
            sb.append("; weight:").append(new BigDecimal(this.c));
            sb.append("]");
            return sb.toString();
        }
    }
    
    public interface d
    {
        boolean a(final e p0, final Intent p1);
    }
    
    private final class e extends AsyncTask<Object, Void, Void>
    {
        public Void a(final Object... p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: iconst_0       
            //     1: istore_2       
            //     2: aload_1        
            //     3: iconst_0       
            //     4: aaload         
            //     5: checkcast       Ljava/util/List;
            //     8: astore          4
            //    10: aload_1        
            //    11: iconst_1       
            //    12: aaload         
            //    13: checkcast       Ljava/lang/String;
            //    16: astore          5
            //    18: aload_0        
            //    19: getfield        android/support/v7/widget/e$e.a:Landroid/support/v7/widget/e;
            //    22: getfield        android/support/v7/widget/e.b:Landroid/content/Context;
            //    25: aload           5
            //    27: iconst_0       
            //    28: invokevirtual   android/content/Context.openFileOutput:(Ljava/lang/String;I)Ljava/io/FileOutputStream;
            //    31: astore_1       
            //    32: invokestatic    android/util/Xml.newSerializer:()Lorg/xmlpull/v1/XmlSerializer;
            //    35: astore          5
            //    37: aload           5
            //    39: aload_1        
            //    40: aconst_null    
            //    41: invokeinterface org/xmlpull/v1/XmlSerializer.setOutput:(Ljava/io/OutputStream;Ljava/lang/String;)V
            //    46: aload           5
            //    48: ldc             "UTF-8"
            //    50: iconst_1       
            //    51: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
            //    54: invokeinterface org/xmlpull/v1/XmlSerializer.startDocument:(Ljava/lang/String;Ljava/lang/Boolean;)V
            //    59: aload           5
            //    61: aconst_null    
            //    62: ldc             "historical-records"
            //    64: invokeinterface org/xmlpull/v1/XmlSerializer.startTag:(Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
            //    69: pop            
            //    70: aload           4
            //    72: invokeinterface java/util/List.size:()I
            //    77: istore_3       
            //    78: iload_2        
            //    79: iload_3        
            //    80: if_icmpge       213
            //    83: aload           4
            //    85: iconst_0       
            //    86: invokeinterface java/util/List.remove:(I)Ljava/lang/Object;
            //    91: checkcast       Landroid/support/v7/widget/e$c;
            //    94: astore          6
            //    96: aload           5
            //    98: aconst_null    
            //    99: ldc             "historical-record"
            //   101: invokeinterface org/xmlpull/v1/XmlSerializer.startTag:(Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
            //   106: pop            
            //   107: aload           5
            //   109: aconst_null    
            //   110: ldc             "activity"
            //   112: aload           6
            //   114: getfield        android/support/v7/widget/e$c.a:Landroid/content/ComponentName;
            //   117: invokevirtual   android/content/ComponentName.flattenToString:()Ljava/lang/String;
            //   120: invokeinterface org/xmlpull/v1/XmlSerializer.attribute:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
            //   125: pop            
            //   126: aload           5
            //   128: aconst_null    
            //   129: ldc             "time"
            //   131: aload           6
            //   133: getfield        android/support/v7/widget/e$c.b:J
            //   136: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
            //   139: invokeinterface org/xmlpull/v1/XmlSerializer.attribute:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
            //   144: pop            
            //   145: aload           5
            //   147: aconst_null    
            //   148: ldc             "weight"
            //   150: aload           6
            //   152: getfield        android/support/v7/widget/e$c.c:F
            //   155: invokestatic    java/lang/String.valueOf:(F)Ljava/lang/String;
            //   158: invokeinterface org/xmlpull/v1/XmlSerializer.attribute:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
            //   163: pop            
            //   164: aload           5
            //   166: aconst_null    
            //   167: ldc             "historical-record"
            //   169: invokeinterface org/xmlpull/v1/XmlSerializer.endTag:(Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
            //   174: pop            
            //   175: iload_2        
            //   176: iconst_1       
            //   177: iadd           
            //   178: istore_2       
            //   179: goto            78
            //   182: astore_1       
            //   183: getstatic       android/support/v7/widget/e.a:Ljava/lang/String;
            //   186: new             Ljava/lang/StringBuilder;
            //   189: dup            
            //   190: invokespecial   java/lang/StringBuilder.<init>:()V
            //   193: ldc             "Error writing historical record file: "
            //   195: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   198: aload           5
            //   200: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   203: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   206: aload_1        
            //   207: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   210: pop            
            //   211: aconst_null    
            //   212: areturn        
            //   213: aload           5
            //   215: aconst_null    
            //   216: ldc             "historical-records"
            //   218: invokeinterface org/xmlpull/v1/XmlSerializer.endTag:(Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
            //   223: pop            
            //   224: aload           5
            //   226: invokeinterface org/xmlpull/v1/XmlSerializer.endDocument:()V
            //   231: aload_0        
            //   232: getfield        android/support/v7/widget/e$e.a:Landroid/support/v7/widget/e;
            //   235: iconst_1       
            //   236: putfield        android/support/v7/widget/e.d:Z
            //   239: aload_1        
            //   240: ifnull          211
            //   243: aload_1        
            //   244: invokevirtual   java/io/FileOutputStream.close:()V
            //   247: aconst_null    
            //   248: areturn        
            //   249: astore_1       
            //   250: aconst_null    
            //   251: areturn        
            //   252: astore          4
            //   254: getstatic       android/support/v7/widget/e.a:Ljava/lang/String;
            //   257: new             Ljava/lang/StringBuilder;
            //   260: dup            
            //   261: invokespecial   java/lang/StringBuilder.<init>:()V
            //   264: ldc             "Error writing historical record file: "
            //   266: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   269: aload_0        
            //   270: getfield        android/support/v7/widget/e$e.a:Landroid/support/v7/widget/e;
            //   273: getfield        android/support/v7/widget/e.c:Ljava/lang/String;
            //   276: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   279: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   282: aload           4
            //   284: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   287: pop            
            //   288: aload_0        
            //   289: getfield        android/support/v7/widget/e$e.a:Landroid/support/v7/widget/e;
            //   292: iconst_1       
            //   293: putfield        android/support/v7/widget/e.d:Z
            //   296: aload_1        
            //   297: ifnull          211
            //   300: aload_1        
            //   301: invokevirtual   java/io/FileOutputStream.close:()V
            //   304: aconst_null    
            //   305: areturn        
            //   306: astore_1       
            //   307: aconst_null    
            //   308: areturn        
            //   309: astore          4
            //   311: getstatic       android/support/v7/widget/e.a:Ljava/lang/String;
            //   314: new             Ljava/lang/StringBuilder;
            //   317: dup            
            //   318: invokespecial   java/lang/StringBuilder.<init>:()V
            //   321: ldc             "Error writing historical record file: "
            //   323: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   326: aload_0        
            //   327: getfield        android/support/v7/widget/e$e.a:Landroid/support/v7/widget/e;
            //   330: getfield        android/support/v7/widget/e.c:Ljava/lang/String;
            //   333: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   336: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   339: aload           4
            //   341: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   344: pop            
            //   345: aload_0        
            //   346: getfield        android/support/v7/widget/e$e.a:Landroid/support/v7/widget/e;
            //   349: iconst_1       
            //   350: putfield        android/support/v7/widget/e.d:Z
            //   353: aload_1        
            //   354: ifnull          211
            //   357: aload_1        
            //   358: invokevirtual   java/io/FileOutputStream.close:()V
            //   361: aconst_null    
            //   362: areturn        
            //   363: astore_1       
            //   364: aconst_null    
            //   365: areturn        
            //   366: astore          4
            //   368: getstatic       android/support/v7/widget/e.a:Ljava/lang/String;
            //   371: new             Ljava/lang/StringBuilder;
            //   374: dup            
            //   375: invokespecial   java/lang/StringBuilder.<init>:()V
            //   378: ldc             "Error writing historical record file: "
            //   380: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   383: aload_0        
            //   384: getfield        android/support/v7/widget/e$e.a:Landroid/support/v7/widget/e;
            //   387: getfield        android/support/v7/widget/e.c:Ljava/lang/String;
            //   390: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   393: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   396: aload           4
            //   398: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   401: pop            
            //   402: aload_0        
            //   403: getfield        android/support/v7/widget/e$e.a:Landroid/support/v7/widget/e;
            //   406: iconst_1       
            //   407: putfield        android/support/v7/widget/e.d:Z
            //   410: aload_1        
            //   411: ifnull          211
            //   414: aload_1        
            //   415: invokevirtual   java/io/FileOutputStream.close:()V
            //   418: aconst_null    
            //   419: areturn        
            //   420: astore_1       
            //   421: aconst_null    
            //   422: areturn        
            //   423: astore          4
            //   425: aload_0        
            //   426: getfield        android/support/v7/widget/e$e.a:Landroid/support/v7/widget/e;
            //   429: iconst_1       
            //   430: putfield        android/support/v7/widget/e.d:Z
            //   433: aload_1        
            //   434: ifnull          441
            //   437: aload_1        
            //   438: invokevirtual   java/io/FileOutputStream.close:()V
            //   441: aload           4
            //   443: athrow         
            //   444: astore_1       
            //   445: goto            441
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  18     32     182    211    Ljava/io/FileNotFoundException;
            //  37     78     252    309    Ljava/lang/IllegalArgumentException;
            //  37     78     309    366    Ljava/lang/IllegalStateException;
            //  37     78     366    423    Ljava/io/IOException;
            //  37     78     423    448    Any
            //  83     175    252    309    Ljava/lang/IllegalArgumentException;
            //  83     175    309    366    Ljava/lang/IllegalStateException;
            //  83     175    366    423    Ljava/io/IOException;
            //  83     175    423    448    Any
            //  213    231    252    309    Ljava/lang/IllegalArgumentException;
            //  213    231    309    366    Ljava/lang/IllegalStateException;
            //  213    231    366    423    Ljava/io/IOException;
            //  213    231    423    448    Any
            //  243    247    249    252    Ljava/io/IOException;
            //  254    288    423    448    Any
            //  300    304    306    309    Ljava/io/IOException;
            //  311    345    423    448    Any
            //  357    361    363    366    Ljava/io/IOException;
            //  368    402    423    448    Any
            //  414    418    420    423    Ljava/io/IOException;
            //  437    441    444    448    Ljava/io/IOException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0078:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
}
