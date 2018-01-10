// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import android.content.res.Resources;
import java.io.FileNotFoundException;
import android.net.Uri$Builder;
import android.os.Bundle;
import android.text.style.TextAppearanceSpan;
import android.text.SpannableString;
import android.util.TypedValue;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.res.Resources$NotFoundException;
import android.util.Log;
import android.net.Uri;
import android.support.v4.b.a;
import android.graphics.drawable.Drawable;
import android.content.ComponentName;
import android.database.Cursor;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable$ConstantState;
import java.util.WeakHashMap;
import android.content.Context;
import android.app.SearchableInfo;
import android.app.SearchManager;
import android.view.View$OnClickListener;
import android.support.v4.widget.s;

class ap extends s implements View$OnClickListener
{
    private final SearchManager j;
    private final SearchView k;
    private final SearchableInfo l;
    private final Context m;
    private final WeakHashMap<String, Drawable$ConstantState> n;
    private final int o;
    private boolean p;
    private int q;
    private ColorStateList r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    
    public ap(final Context m, final SearchView k, final SearchableInfo l, final WeakHashMap<String, Drawable$ConstantState> n) {
        super(m, k.getSuggestionRowLayout(), null, true);
        this.p = false;
        this.q = 1;
        this.s = -1;
        this.t = -1;
        this.u = -1;
        this.v = -1;
        this.w = -1;
        this.x = -1;
        this.j = (SearchManager)this.d.getSystemService("search");
        this.k = k;
        this.l = l;
        this.o = k.getSuggestionCommitIconResId();
        this.m = m;
        this.n = n;
    }
    
    private Drawable a(final ComponentName componentName) {
        final Drawable$ConstantState drawable$ConstantState = null;
        final String flattenToShortString = componentName.flattenToShortString();
        if (!this.n.containsKey(flattenToShortString)) {
            final Drawable b = this.b(componentName);
            Drawable$ConstantState constantState;
            if (b == null) {
                constantState = drawable$ConstantState;
            }
            else {
                constantState = b.getConstantState();
            }
            this.n.put(flattenToShortString, constantState);
            return b;
        }
        final Drawable$ConstantState drawable$ConstantState2 = this.n.get(flattenToShortString);
        if (drawable$ConstantState2 == null) {
            return null;
        }
        return drawable$ConstantState2.newDrawable(this.m.getResources());
    }
    
    private Drawable a(final String s) {
        Drawable drawable;
        if (s == null || s.length() == 0 || "0".equals(s)) {
            drawable = null;
        }
        else {
            try {
                final int int1 = Integer.parseInt(s);
                final String string = "android.resource://" + this.m.getPackageName() + "/" + int1;
                if ((drawable = this.b(string)) == null) {
                    final Drawable a = android.support.v4.b.a.a(this.m, int1);
                    this.a(string, a);
                    return a;
                }
            }
            catch (NumberFormatException ex) {
                if ((drawable = this.b(s)) == null) {
                    final Drawable b = this.b(Uri.parse(s));
                    this.a(s, b);
                    return b;
                }
            }
            catch (Resources$NotFoundException ex2) {
                Log.w("SuggestionsAdapter", "Icon resource not found: " + s);
                return null;
            }
        }
        return drawable;
    }
    
    private static String a(final Cursor cursor, final int n) {
        if (n == -1) {
            return null;
        }
        try {
            return cursor.getString(n);
        }
        catch (Exception ex) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", (Throwable)ex);
            return null;
        }
    }
    
    public static String a(final Cursor cursor, final String s) {
        return a(cursor, cursor.getColumnIndex(s));
    }
    
    private void a(final ImageView imageView, final Drawable imageDrawable, final int visibility) {
        imageView.setImageDrawable(imageDrawable);
        if (imageDrawable == null) {
            imageView.setVisibility(visibility);
            return;
        }
        imageView.setVisibility(0);
        imageDrawable.setVisible(false, false);
        imageDrawable.setVisible(true, false);
    }
    
    private void a(final TextView textView, final CharSequence text) {
        textView.setText(text);
        if (TextUtils.isEmpty(text)) {
            textView.setVisibility(8);
            return;
        }
        textView.setVisibility(0);
    }
    
    private void a(final String s, final Drawable drawable) {
        if (drawable != null) {
            this.n.put(s, drawable.getConstantState());
        }
    }
    
    private Drawable b(final ComponentName componentName) {
        final PackageManager packageManager = this.d.getPackageManager();
        ActivityInfo activityInfo;
        int iconResource;
        try {
            activityInfo = packageManager.getActivityInfo(componentName, 128);
            iconResource = activityInfo.getIconResource();
            if (iconResource == 0) {
                return null;
            }
        }
        catch (PackageManager$NameNotFoundException ex) {
            Log.w("SuggestionsAdapter", ex.toString());
            return null;
        }
        final Drawable drawable = packageManager.getDrawable(componentName.getPackageName(), iconResource, activityInfo.applicationInfo);
        if (drawable == null) {
            Log.w("SuggestionsAdapter", "Invalid icon resource " + iconResource + " for " + componentName.flattenToShortString());
            return null;
        }
        return drawable;
    }
    
    private Drawable b(final Uri p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc_w           "android.resource"
        //     3: aload_1        
        //     4: invokevirtual   android/net/Uri.getScheme:()Ljava/lang/String;
        //     7: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    10: istore_2       
        //    11: iload_2        
        //    12: ifeq            94
        //    15: aload_0        
        //    16: aload_1        
        //    17: invokevirtual   android/support/v7/widget/ap.a:(Landroid/net/Uri;)Landroid/graphics/drawable/Drawable;
        //    20: astore_3       
        //    21: aload_3        
        //    22: areturn        
        //    23: astore_3       
        //    24: new             Ljava/io/FileNotFoundException;
        //    27: dup            
        //    28: new             Ljava/lang/StringBuilder;
        //    31: dup            
        //    32: invokespecial   java/lang/StringBuilder.<init>:()V
        //    35: ldc_w           "Resource does not exist: "
        //    38: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    41: aload_1        
        //    42: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    45: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    48: invokespecial   java/io/FileNotFoundException.<init>:(Ljava/lang/String;)V
        //    51: athrow         
        //    52: astore_3       
        //    53: ldc             "SuggestionsAdapter"
        //    55: new             Ljava/lang/StringBuilder;
        //    58: dup            
        //    59: invokespecial   java/lang/StringBuilder.<init>:()V
        //    62: ldc_w           "Icon not found: "
        //    65: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    68: aload_1        
        //    69: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    72: ldc_w           ", "
        //    75: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    78: aload_3        
        //    79: invokevirtual   java/io/FileNotFoundException.getMessage:()Ljava/lang/String;
        //    82: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    85: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    88: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //    91: pop            
        //    92: aconst_null    
        //    93: areturn        
        //    94: aload_0        
        //    95: getfield        android/support/v7/widget/ap.m:Landroid/content/Context;
        //    98: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //   101: aload_1        
        //   102: invokevirtual   android/content/ContentResolver.openInputStream:(Landroid/net/Uri;)Ljava/io/InputStream;
        //   105: astore_3       
        //   106: aload_3        
        //   107: ifnonnull       138
        //   110: new             Ljava/io/FileNotFoundException;
        //   113: dup            
        //   114: new             Ljava/lang/StringBuilder;
        //   117: dup            
        //   118: invokespecial   java/lang/StringBuilder.<init>:()V
        //   121: ldc_w           "Failed to open "
        //   124: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   127: aload_1        
        //   128: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   131: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   134: invokespecial   java/io/FileNotFoundException.<init>:(Ljava/lang/String;)V
        //   137: athrow         
        //   138: aload_3        
        //   139: aconst_null    
        //   140: invokestatic    android/graphics/drawable/Drawable.createFromStream:(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
        //   143: astore          4
        //   145: aload_3        
        //   146: invokevirtual   java/io/InputStream.close:()V
        //   149: aload           4
        //   151: areturn        
        //   152: astore_3       
        //   153: ldc             "SuggestionsAdapter"
        //   155: new             Ljava/lang/StringBuilder;
        //   158: dup            
        //   159: invokespecial   java/lang/StringBuilder.<init>:()V
        //   162: ldc_w           "Error closing icon stream for "
        //   165: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   168: aload_1        
        //   169: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   172: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   175: aload_3        
        //   176: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   179: pop            
        //   180: aload           4
        //   182: areturn        
        //   183: astore          4
        //   185: aload_3        
        //   186: invokevirtual   java/io/InputStream.close:()V
        //   189: aload           4
        //   191: athrow         
        //   192: astore_3       
        //   193: ldc             "SuggestionsAdapter"
        //   195: new             Ljava/lang/StringBuilder;
        //   198: dup            
        //   199: invokespecial   java/lang/StringBuilder.<init>:()V
        //   202: ldc_w           "Error closing icon stream for "
        //   205: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   208: aload_1        
        //   209: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   212: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   215: aload_3        
        //   216: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   219: pop            
        //   220: goto            189
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                             
        //  -----  -----  -----  -----  -------------------------------------------------
        //  0      11     52     94     Ljava/io/FileNotFoundException;
        //  15     21     23     52     Landroid/content/res/Resources$NotFoundException;
        //  15     21     52     94     Ljava/io/FileNotFoundException;
        //  24     52     52     94     Ljava/io/FileNotFoundException;
        //  94     106    52     94     Ljava/io/FileNotFoundException;
        //  110    138    52     94     Ljava/io/FileNotFoundException;
        //  138    145    183    223    Any
        //  145    149    152    183    Ljava/io/IOException;
        //  145    149    52     94     Ljava/io/FileNotFoundException;
        //  153    180    52     94     Ljava/io/FileNotFoundException;
        //  185    189    192    223    Ljava/io/IOException;
        //  185    189    52     94     Ljava/io/FileNotFoundException;
        //  189    192    52     94     Ljava/io/FileNotFoundException;
        //  193    220    52     94     Ljava/io/FileNotFoundException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 107, Size: 107
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3551)
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
    
    private Drawable b(final String s) {
        final Drawable$ConstantState drawable$ConstantState = this.n.get(s);
        if (drawable$ConstantState == null) {
            return null;
        }
        return drawable$ConstantState.newDrawable();
    }
    
    private CharSequence b(final CharSequence charSequence) {
        if (this.r == null) {
            final TypedValue typedValue = new TypedValue();
            this.d.getTheme().resolveAttribute(android.support.v7.a.a.a.textColorSearchUrl, typedValue, true);
            this.r = this.d.getResources().getColorStateList(typedValue.resourceId);
        }
        final SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan((Object)new TextAppearanceSpan((String)null, 0, 0, this.r, (ColorStateList)null), 0, charSequence.length(), 33);
        return (CharSequence)spannableString;
    }
    
    private void d(final Cursor cursor) {
        Bundle extras;
        if (cursor != null) {
            extras = cursor.getExtras();
        }
        else {
            extras = null;
        }
        if (extras == null || extras.getBoolean("in_progress")) {}
    }
    
    private Drawable e(final Cursor cursor) {
        Drawable a;
        if (this.v == -1) {
            a = null;
        }
        else if ((a = this.a(cursor.getString(this.v))) == null) {
            return this.g(cursor);
        }
        return a;
    }
    
    private Drawable f(final Cursor cursor) {
        if (this.w == -1) {
            return null;
        }
        return this.a(cursor.getString(this.w));
    }
    
    private Drawable g(final Cursor cursor) {
        final Drawable a = this.a(this.l.getSearchActivity());
        if (a != null) {
            return a;
        }
        return this.d.getPackageManager().getDefaultActivityIcon();
    }
    
    Cursor a(final SearchableInfo searchableInfo, final String s, final int n) {
        if (searchableInfo != null) {
            final String suggestAuthority = searchableInfo.getSuggestAuthority();
            if (suggestAuthority != null) {
                final Uri$Builder fragment = new Uri$Builder().scheme("content").authority(suggestAuthority).query("").fragment("");
                final String suggestPath = searchableInfo.getSuggestPath();
                if (suggestPath != null) {
                    fragment.appendEncodedPath(suggestPath);
                }
                fragment.appendPath("search_suggest_query");
                final String suggestSelection = searchableInfo.getSuggestSelection();
                String[] array;
                if (suggestSelection != null) {
                    array = new String[] { s };
                }
                else {
                    fragment.appendPath(s);
                    array = null;
                }
                if (n > 0) {
                    fragment.appendQueryParameter("limit", String.valueOf(n));
                }
                return this.d.getContentResolver().query(fragment.build(), (String[])null, suggestSelection, array, (String)null);
            }
        }
        return null;
    }
    
    public Cursor a(final CharSequence charSequence) {
        String string;
        if (charSequence == null) {
            string = "";
        }
        else {
            string = charSequence.toString();
        }
        if (this.k.getVisibility() != 0 || this.k.getWindowVisibility() != 0) {
            return null;
        }
        try {
            final Cursor a = this.a(this.l, string, 50);
            if (a != null) {
                a.getCount();
                return a;
            }
        }
        catch (RuntimeException ex) {
            Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", (Throwable)ex);
        }
        return null;
    }
    
    Drawable a(final Uri uri) {
        final String authority = uri.getAuthority();
        if (TextUtils.isEmpty((CharSequence)authority)) {
            throw new FileNotFoundException("No authority: " + uri);
        }
        Resources resourcesForApplication;
        List pathSegments;
        try {
            resourcesForApplication = this.d.getPackageManager().getResourcesForApplication(authority);
            pathSegments = uri.getPathSegments();
            if (pathSegments == null) {
                throw new FileNotFoundException("No path: " + uri);
            }
        }
        catch (PackageManager$NameNotFoundException ex) {
            throw new FileNotFoundException("No package found for authority: " + uri);
        }
        final int size = pathSegments.size();
        while (true) {
            Label_0210: {
                if (size != 1) {
                    break Label_0210;
                }
                try {
                    final int n = Integer.parseInt(pathSegments.get(0));
                    if (n == 0) {
                        throw new FileNotFoundException("No resource found for: " + uri);
                    }
                    return resourcesForApplication.getDrawable(n);
                }
                catch (NumberFormatException ex2) {
                    throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                }
            }
            if (size == 2) {
                final int n = resourcesForApplication.getIdentifier((String)pathSegments.get(1), (String)pathSegments.get(0), authority);
                continue;
            }
            break;
        }
        throw new FileNotFoundException("More than two path segments: " + uri);
    }
    
    @Override
    public View a(final Context context, final Cursor cursor, final ViewGroup viewGroup) {
        final View a = super.a(context, cursor, viewGroup);
        a.setTag((Object)new a(a));
        ((ImageView)a.findViewById(android.support.v7.a.a.f.edit_query)).setImageResource(this.o);
        return a;
    }
    
    public void a(final int q) {
        this.q = q;
    }
    
    public void a(final Cursor cursor) {
        if (this.p) {
            Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
            if (cursor != null) {
                cursor.close();
            }
        }
        else {
            try {
                super.a(cursor);
                if (cursor != null) {
                    this.s = cursor.getColumnIndex("suggest_text_1");
                    this.t = cursor.getColumnIndex("suggest_text_2");
                    this.u = cursor.getColumnIndex("suggest_text_2_url");
                    this.v = cursor.getColumnIndex("suggest_icon_1");
                    this.w = cursor.getColumnIndex("suggest_icon_2");
                    this.x = cursor.getColumnIndex("suggest_flags");
                }
            }
            catch (Exception ex) {
                Log.e("SuggestionsAdapter", "error changing cursor and caching columns", (Throwable)ex);
            }
        }
    }
    
    public void a(final View view, final Context context, final Cursor cursor) {
        final a a = (a)view.getTag();
        int int1;
        if (this.x != -1) {
            int1 = cursor.getInt(this.x);
        }
        else {
            int1 = 0;
        }
        if (a.a != null) {
            this.a(a.a, a(cursor, this.s));
        }
        if (a.b != null) {
            final String a2 = a(cursor, this.u);
            CharSequence charSequence;
            if (a2 != null) {
                charSequence = this.b((CharSequence)a2);
            }
            else {
                charSequence = a(cursor, this.t);
            }
            if (TextUtils.isEmpty(charSequence)) {
                if (a.a != null) {
                    a.a.setSingleLine(false);
                    a.a.setMaxLines(2);
                }
            }
            else if (a.a != null) {
                a.a.setSingleLine(true);
                a.a.setMaxLines(1);
            }
            this.a(a.b, charSequence);
        }
        if (a.c != null) {
            this.a(a.c, this.e(cursor), 4);
        }
        if (a.d != null) {
            this.a(a.d, this.f(cursor), 8);
        }
        if (this.q == 2 || (this.q == 1 && (int1 & 0x1) != 0x0)) {
            a.e.setVisibility(0);
            a.e.setTag((Object)a.a.getText());
            a.e.setOnClickListener((View$OnClickListener)this);
            return;
        }
        a.e.setVisibility(8);
    }
    
    public CharSequence c(final Cursor cursor) {
        if (cursor != null) {
            final String a = a(cursor, "suggest_intent_query");
            if (a != null) {
                return a;
            }
            if (this.l.shouldRewriteQueryFromData()) {
                final String a2 = a(cursor, "suggest_intent_data");
                if (a2 != null) {
                    return a2;
                }
            }
            if (this.l.shouldRewriteQueryFromText()) {
                final String a3 = a(cursor, "suggest_text_1");
                if (a3 != null) {
                    return a3;
                }
            }
        }
        return null;
    }
    
    public View getView(final int n, View view, final ViewGroup viewGroup) {
        try {
            view = super.getView(n, view, viewGroup);
            return view;
        }
        catch (RuntimeException ex) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", (Throwable)ex);
            final View a = this.a(this.d, this.c, viewGroup);
            if (a != null) {
                ((a)a.getTag()).a.setText((CharSequence)ex.toString());
            }
            return a;
        }
    }
    
    public boolean hasStableIds() {
        return false;
    }
    
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.d(this.a());
    }
    
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        this.d(this.a());
    }
    
    public void onClick(final View view) {
        final Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.k.a((CharSequence)tag);
        }
    }
    
    private static final class a
    {
        public final TextView a;
        public final TextView b;
        public final ImageView c;
        public final ImageView d;
        public final ImageView e;
        
        public a(final View view) {
            this.a = (TextView)view.findViewById(16908308);
            this.b = (TextView)view.findViewById(16908309);
            this.c = (ImageView)view.findViewById(16908295);
            this.d = (ImageView)view.findViewById(16908296);
            this.e = (ImageView)view.findViewById(android.support.v7.a.a.f.edit_query);
        }
    }
}
