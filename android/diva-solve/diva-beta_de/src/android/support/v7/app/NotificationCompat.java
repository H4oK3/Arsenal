// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.v4.media.session.MediaSessionCompat;
import android.app.PendingIntent;
import android.os.Build$VERSION;
import android.content.Context;
import android.support.v7.internal.app.NotificationCompatImpl21;
import java.util.List;
import android.support.v7.internal.app.NotificationCompatImplBase;
import android.app.Notification;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;

public class NotificationCompat extends android.support.v4.app.NotificationCompat
{
    private static void addBigMediaStyleToBuilderJellybean(final Notification notification, final android.support.v4.app.NotificationCompat.Builder builder) {
        if (builder.mStyle instanceof MediaStyle) {
            final MediaStyle mediaStyle = (MediaStyle)builder.mStyle;
            android.support.v7.internal.app.NotificationCompatImplBase.overrideBigContentView(notification, builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mNumber, builder.mLargeIcon, builder.mSubText, builder.mUseChronometer, builder.mNotification.when, builder.mActions, mediaStyle.mShowCancelButton, mediaStyle.mCancelButtonIntent);
        }
    }
    
    private static void addMediaStyleToBuilderIcs(final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, final android.support.v4.app.NotificationCompat.Builder builder) {
        if (builder.mStyle instanceof MediaStyle) {
            final MediaStyle mediaStyle = (MediaStyle)builder.mStyle;
            android.support.v7.internal.app.NotificationCompatImplBase.overrideContentView(notificationBuilderWithBuilderAccessor, builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mNumber, builder.mLargeIcon, builder.mSubText, builder.mUseChronometer, builder.mNotification.when, builder.mActions, mediaStyle.mActionsToShowInCompact, mediaStyle.mShowCancelButton, mediaStyle.mCancelButtonIntent);
        }
    }
    
    private static void addMediaStyleToBuilderLollipop(final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, final Style style) {
        if (style instanceof MediaStyle) {
            final MediaStyle mediaStyle = (MediaStyle)style;
            final int[] mActionsToShowInCompact = mediaStyle.mActionsToShowInCompact;
            Object token;
            if (mediaStyle.mToken != null) {
                token = mediaStyle.mToken.getToken();
            }
            else {
                token = null;
            }
            NotificationCompatImpl21.addMediaStyle(notificationBuilderWithBuilderAccessor, mActionsToShowInCompact, token);
        }
    }
    
    public static class Builder extends android.support.v4.app.NotificationCompat.Builder
    {
        public Builder(final Context context) {
            super(context);
        }
        
        @Override
        protected BuilderExtender getExtender() {
            if (Build$VERSION.SDK_INT >= 21) {
                return new LollipopExtender();
            }
            if (Build$VERSION.SDK_INT >= 16) {
                return new JellybeanExtender();
            }
            if (Build$VERSION.SDK_INT >= 14) {
                return new IceCreamSandwichExtender();
            }
            return super.getExtender();
        }
    }
    
    private static class IceCreamSandwichExtender extends BuilderExtender
    {
        @Override
        public Notification build(final Builder builder, final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            addMediaStyleToBuilderIcs(notificationBuilderWithBuilderAccessor, builder);
            return notificationBuilderWithBuilderAccessor.build();
        }
    }
    
    private static class JellybeanExtender extends BuilderExtender
    {
        @Override
        public Notification build(final Builder builder, final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            addMediaStyleToBuilderIcs(notificationBuilderWithBuilderAccessor, builder);
            final Notification build = notificationBuilderWithBuilderAccessor.build();
            addBigMediaStyleToBuilderJellybean(build, builder);
            return build;
        }
    }
    
    private static class LollipopExtender extends BuilderExtender
    {
        @Override
        public Notification build(final Builder builder, final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            addMediaStyleToBuilderLollipop(notificationBuilderWithBuilderAccessor, builder.mStyle);
            return notificationBuilderWithBuilderAccessor.build();
        }
    }
    
    public static class MediaStyle extends Style
    {
        int[] mActionsToShowInCompact;
        PendingIntent mCancelButtonIntent;
        boolean mShowCancelButton;
        MediaSessionCompat.Token mToken;
        
        public MediaStyle() {
            this.mActionsToShowInCompact = null;
        }
        
        public MediaStyle(final Builder builder) {
            this.mActionsToShowInCompact = null;
            ((Style)this).setBuilder(builder);
        }
        
        public MediaStyle setCancelButtonIntent(final PendingIntent mCancelButtonIntent) {
            this.mCancelButtonIntent = mCancelButtonIntent;
            return this;
        }
        
        public MediaStyle setMediaSession(final MediaSessionCompat.Token mToken) {
            this.mToken = mToken;
            return this;
        }
        
        public MediaStyle setShowActionsInCompactView(final int... mActionsToShowInCompact) {
            this.mActionsToShowInCompact = mActionsToShowInCompact;
            return this;
        }
        
        public MediaStyle setShowCancelButton(final boolean mShowCancelButton) {
            this.mShowCancelButton = mShowCancelButton;
            return this;
        }
    }
}
