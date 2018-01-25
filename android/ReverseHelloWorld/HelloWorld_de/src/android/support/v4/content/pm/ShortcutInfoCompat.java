// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content.pm;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.annotation.TargetApi;
import android.text.TextUtils;
import android.graphics.drawable.Icon;
import android.content.pm.ShortcutInfo$Builder;
import android.content.pm.ShortcutInfo;
import android.content.Intent$ShortcutIconResource;
import android.os.Parcelable;
import android.content.Intent;
import android.graphics.Bitmap;
import android.content.Context;
import android.content.ComponentName;

public class ShortcutInfoCompat
{
    private ComponentName mActivity;
    private Context mContext;
    private CharSequence mDisabledMessage;
    private Bitmap mIconBitmap;
    private int mIconId;
    private String mId;
    private Intent[] mIntents;
    private CharSequence mLabel;
    private CharSequence mLongLabel;
    
    Intent addToIntent(final Intent intent) {
        intent.putExtra("android.intent.extra.shortcut.INTENT", (Parcelable)this.mIntents[this.mIntents.length - 1]).putExtra("android.intent.extra.shortcut.NAME", this.mLabel.toString());
        if (this.mIconId != 0) {
            intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", (Parcelable)Intent$ShortcutIconResource.fromContext(this.mContext, this.mIconId));
        }
        if (this.mIconBitmap != null) {
            intent.putExtra("android.intent.extra.shortcut.ICON", (Parcelable)this.mIconBitmap);
        }
        return intent;
    }
    
    @TargetApi(25)
    ShortcutInfo toShortcutInfo() {
        final ShortcutInfo$Builder setIntents = new ShortcutInfo$Builder(this.mContext, this.mId).setShortLabel(this.mLabel).setIntents(this.mIntents);
        if (this.mIconId != 0) {
            setIntents.setIcon(Icon.createWithResource(this.mContext, this.mIconId));
        }
        else if (this.mIconBitmap != null) {
            setIntents.setIcon(Icon.createWithBitmap(this.mIconBitmap));
        }
        if (!TextUtils.isEmpty(this.mLongLabel)) {
            setIntents.setLongLabel(this.mLongLabel);
        }
        if (!TextUtils.isEmpty(this.mDisabledMessage)) {
            setIntents.setDisabledMessage(this.mDisabledMessage);
        }
        if (this.mActivity != null) {
            setIntents.setActivity(this.mActivity);
        }
        return setIntents.build();
    }
    
    public static class Builder
    {
        private final ShortcutInfoCompat mInfo;
        
        public Builder(@NonNull final Context context, @NonNull final String s) {
            (this.mInfo = new ShortcutInfoCompat(null)).mContext = context;
            this.mInfo.mId = s;
        }
        
        @NonNull
        public ShortcutInfoCompat build() {
            if (TextUtils.isEmpty(this.mInfo.mLabel)) {
                throw new IllegalArgumentException("Shortcut much have a non-empty label");
            }
            if (this.mInfo.mIntents == null || this.mInfo.mIntents.length == 0) {
                throw new IllegalArgumentException("Shortcut much have an intent");
            }
            return this.mInfo;
        }
        
        @NonNull
        public Builder setActivity(@NonNull final ComponentName componentName) {
            this.mInfo.mActivity = componentName;
            return this;
        }
        
        @NonNull
        public Builder setDisabledMessage(@NonNull final CharSequence charSequence) {
            this.mInfo.mDisabledMessage = charSequence;
            return this;
        }
        
        @NonNull
        public Builder setIcon(@DrawableRes final int n) {
            this.mInfo.mIconId = n;
            return this;
        }
        
        @NonNull
        public Builder setIcon(@NonNull final Bitmap bitmap) {
            this.mInfo.mIconBitmap = bitmap;
            return this;
        }
        
        @NonNull
        public Builder setIntent(@NonNull final Intent intent) {
            return this.setIntents(new Intent[] { intent });
        }
        
        @NonNull
        public Builder setIntents(@NonNull final Intent[] array) {
            this.mInfo.mIntents = array;
            return this;
        }
        
        @NonNull
        public Builder setLongLabel(@NonNull final CharSequence charSequence) {
            this.mInfo.mLongLabel = charSequence;
            return this;
        }
        
        @NonNull
        public Builder setShortLabel(@NonNull final CharSequence charSequence) {
            this.mInfo.mLabel = charSequence;
            return this;
        }
    }
}
