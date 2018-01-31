// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import android.net.Uri;
import android.widget.RemoteViews;
import android.content.Context;
import android.graphics.Bitmap;
import android.app.PendingIntent;
import android.os.Parcelable;
import java.util.Iterator;
import android.app.Notification;
import android.os.Bundle;
import java.util.ArrayList;
import android.os.Build$VERSION;
import android.support.annotation.ColorInt;

public class NotificationCompat
{
    public static final String CATEGORY_ALARM = "alarm";
    public static final String CATEGORY_CALL = "call";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_ERROR = "err";
    public static final String CATEGORY_EVENT = "event";
    public static final String CATEGORY_MESSAGE = "msg";
    public static final String CATEGORY_PROGRESS = "progress";
    public static final String CATEGORY_PROMO = "promo";
    public static final String CATEGORY_RECOMMENDATION = "recommendation";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_SYSTEM = "sys";
    public static final String CATEGORY_TRANSPORT = "transport";
    @ColorInt
    public static final int COLOR_DEFAULT = 0;
    public static final int DEFAULT_ALL = -1;
    public static final int DEFAULT_LIGHTS = 4;
    public static final int DEFAULT_SOUND = 1;
    public static final int DEFAULT_VIBRATE = 2;
    public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
    public static final String EXTRA_BIG_TEXT = "android.bigText";
    public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
    public static final String EXTRA_INFO_TEXT = "android.infoText";
    public static final String EXTRA_LARGE_ICON = "android.largeIcon";
    public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
    public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
    public static final String EXTRA_PEOPLE = "android.people";
    public static final String EXTRA_PICTURE = "android.picture";
    public static final String EXTRA_PROGRESS = "android.progress";
    public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
    public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
    public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
    public static final String EXTRA_SHOW_WHEN = "android.showWhen";
    public static final String EXTRA_SMALL_ICON = "android.icon";
    public static final String EXTRA_SUB_TEXT = "android.subText";
    public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
    public static final String EXTRA_TEMPLATE = "android.template";
    public static final String EXTRA_TEXT = "android.text";
    public static final String EXTRA_TEXT_LINES = "android.textLines";
    public static final String EXTRA_TITLE = "android.title";
    public static final String EXTRA_TITLE_BIG = "android.title.big";
    public static final int FLAG_AUTO_CANCEL = 16;
    public static final int FLAG_FOREGROUND_SERVICE = 64;
    public static final int FLAG_GROUP_SUMMARY = 512;
    public static final int FLAG_HIGH_PRIORITY = 128;
    public static final int FLAG_INSISTENT = 4;
    public static final int FLAG_LOCAL_ONLY = 256;
    public static final int FLAG_NO_CLEAR = 32;
    public static final int FLAG_ONGOING_EVENT = 2;
    public static final int FLAG_ONLY_ALERT_ONCE = 8;
    public static final int FLAG_SHOW_LIGHTS = 1;
    private static final NotificationCompatImpl IMPL;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = -1;
    public static final int PRIORITY_MAX = 2;
    public static final int PRIORITY_MIN = -2;
    public static final int STREAM_DEFAULT = -1;
    public static final int VISIBILITY_PRIVATE = 0;
    public static final int VISIBILITY_PUBLIC = 1;
    public static final int VISIBILITY_SECRET = -1;
    
    static {
        if (Build$VERSION.SDK_INT >= 21) {
            IMPL = (NotificationCompatImpl)new NotificationCompatImplApi21();
            return;
        }
        if (Build$VERSION.SDK_INT >= 20) {
            IMPL = (NotificationCompatImpl)new NotificationCompatImplApi20();
            return;
        }
        if (Build$VERSION.SDK_INT >= 19) {
            IMPL = (NotificationCompatImpl)new NotificationCompatImplKitKat();
            return;
        }
        if (Build$VERSION.SDK_INT >= 16) {
            IMPL = (NotificationCompatImpl)new NotificationCompatImplJellybean();
            return;
        }
        if (Build$VERSION.SDK_INT >= 14) {
            IMPL = (NotificationCompatImpl)new NotificationCompatImplIceCreamSandwich();
            return;
        }
        if (Build$VERSION.SDK_INT >= 11) {
            IMPL = (NotificationCompatImpl)new NotificationCompatImplHoneycomb();
            return;
        }
        if (Build$VERSION.SDK_INT >= 9) {
            IMPL = (NotificationCompatImpl)new NotificationCompatImplGingerbread();
            return;
        }
        IMPL = (NotificationCompatImpl)new NotificationCompatImplBase();
    }
    
    private static void addActionsToBuilder(final NotificationBuilderWithActions notificationBuilderWithActions, final ArrayList<Action> list) {
        final Iterator<Action> iterator = list.iterator();
        while (iterator.hasNext()) {
            notificationBuilderWithActions.addAction(iterator.next());
        }
    }
    
    private static void addStyleToBuilderJellybean(final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, final Style style) {
        if (style != null) {
            if (style instanceof BigTextStyle) {
                final BigTextStyle bigTextStyle = (BigTextStyle)style;
                NotificationCompatJellybean.addBigTextStyle(notificationBuilderWithBuilderAccessor, bigTextStyle.mBigContentTitle, bigTextStyle.mSummaryTextSet, bigTextStyle.mSummaryText, bigTextStyle.mBigText);
            }
            else {
                if (style instanceof InboxStyle) {
                    final InboxStyle inboxStyle = (InboxStyle)style;
                    NotificationCompatJellybean.addInboxStyle(notificationBuilderWithBuilderAccessor, inboxStyle.mBigContentTitle, inboxStyle.mSummaryTextSet, inboxStyle.mSummaryText, inboxStyle.mTexts);
                    return;
                }
                if (style instanceof BigPictureStyle) {
                    final BigPictureStyle bigPictureStyle = (BigPictureStyle)style;
                    NotificationCompatJellybean.addBigPictureStyle(notificationBuilderWithBuilderAccessor, bigPictureStyle.mBigContentTitle, bigPictureStyle.mSummaryTextSet, bigPictureStyle.mSummaryText, bigPictureStyle.mPicture, bigPictureStyle.mBigLargeIcon, bigPictureStyle.mBigLargeIconSet);
                }
            }
        }
    }
    
    public static Action getAction(final Notification notification, final int n) {
        return NotificationCompat.IMPL.getAction(notification, n);
    }
    
    public static int getActionCount(final Notification notification) {
        return NotificationCompat.IMPL.getActionCount(notification);
    }
    
    public static String getCategory(final Notification notification) {
        return NotificationCompat.IMPL.getCategory(notification);
    }
    
    public static Bundle getExtras(final Notification notification) {
        return NotificationCompat.IMPL.getExtras(notification);
    }
    
    public static String getGroup(final Notification notification) {
        return NotificationCompat.IMPL.getGroup(notification);
    }
    
    public static boolean getLocalOnly(final Notification notification) {
        return NotificationCompat.IMPL.getLocalOnly(notification);
    }
    
    private static Notification[] getNotificationArrayFromBundle(final Bundle bundle, final String s) {
        final Parcelable[] parcelableArray = bundle.getParcelableArray(s);
        if (parcelableArray instanceof Notification[] || parcelableArray == null) {
            return (Notification[])parcelableArray;
        }
        final Notification[] array = new Notification[parcelableArray.length];
        for (int i = 0; i < parcelableArray.length; ++i) {
            array[i] = (Notification)parcelableArray[i];
        }
        bundle.putParcelableArray(s, (Parcelable[])array);
        return array;
    }
    
    public static String getSortKey(final Notification notification) {
        return NotificationCompat.IMPL.getSortKey(notification);
    }
    
    public static boolean isGroupSummary(final Notification notification) {
        return NotificationCompat.IMPL.isGroupSummary(notification);
    }
    
    public static class Action extends NotificationCompatBase.Action
    {
        public static final Factory FACTORY;
        public PendingIntent actionIntent;
        public int icon;
        private final Bundle mExtras;
        private final RemoteInput[] mRemoteInputs;
        public CharSequence title;
        
        static {
            FACTORY = new Factory() {
                public NotificationCompat.Action build(final int n, final CharSequence charSequence, final PendingIntent pendingIntent, final Bundle bundle, final RemoteInputCompatBase.RemoteInput[] array) {
                    return new NotificationCompat.Action(n, charSequence, pendingIntent, bundle, (RemoteInput[])array);
                }
                
                public NotificationCompat.Action[] newArray(final int n) {
                    return new NotificationCompat.Action[n];
                }
            };
        }
        
        public Action(final int n, final CharSequence charSequence, final PendingIntent pendingIntent) {
            this(n, charSequence, pendingIntent, new Bundle(), null);
        }
        
        private Action(final int icon, final CharSequence charSequence, final PendingIntent actionIntent, Bundle mExtras, final RemoteInput[] mRemoteInputs) {
            this.icon = icon;
            this.title = NotificationCompat.Builder.limitCharSequenceLength(charSequence);
            this.actionIntent = actionIntent;
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            this.mExtras = mExtras;
            this.mRemoteInputs = mRemoteInputs;
        }
        
        @Override
        public PendingIntent getActionIntent() {
            return this.actionIntent;
        }
        
        @Override
        public Bundle getExtras() {
            return this.mExtras;
        }
        
        @Override
        public int getIcon() {
            return this.icon;
        }
        
        public RemoteInput[] getRemoteInputs() {
            return this.mRemoteInputs;
        }
        
        @Override
        public CharSequence getTitle() {
            return this.title;
        }
        
        public static final class Builder
        {
            private final Bundle mExtras;
            private final int mIcon;
            private final PendingIntent mIntent;
            private ArrayList<RemoteInput> mRemoteInputs;
            private final CharSequence mTitle;
            
            public Builder(final int n, final CharSequence charSequence, final PendingIntent pendingIntent) {
                this(n, charSequence, pendingIntent, new Bundle());
            }
            
            private Builder(final int mIcon, final CharSequence charSequence, final PendingIntent mIntent, final Bundle mExtras) {
                this.mIcon = mIcon;
                this.mTitle = NotificationCompat.Builder.limitCharSequenceLength(charSequence);
                this.mIntent = mIntent;
                this.mExtras = mExtras;
            }
            
            public Builder(final Action action) {
                this(action.icon, action.title, action.actionIntent, new Bundle(action.mExtras));
            }
            
            public Builder addExtras(final Bundle bundle) {
                if (bundle != null) {
                    this.mExtras.putAll(bundle);
                }
                return this;
            }
            
            public Builder addRemoteInput(final RemoteInput remoteInput) {
                if (this.mRemoteInputs == null) {
                    this.mRemoteInputs = new ArrayList<RemoteInput>();
                }
                this.mRemoteInputs.add(remoteInput);
                return this;
            }
            
            public Action build() {
                RemoteInput[] array;
                if (this.mRemoteInputs != null) {
                    array = this.mRemoteInputs.toArray(new RemoteInput[this.mRemoteInputs.size()]);
                }
                else {
                    array = null;
                }
                return new Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, array);
            }
            
            public Builder extend(final Extender extender) {
                extender.extend(this);
                return this;
            }
            
            public Bundle getExtras() {
                return this.mExtras;
            }
        }
        
        public interface Extender
        {
            Builder extend(final Builder p0);
        }
        
        public static final class WearableExtender implements Extender
        {
            private static final int DEFAULT_FLAGS = 1;
            private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
            private static final int FLAG_AVAILABLE_OFFLINE = 1;
            private static final String KEY_CANCEL_LABEL = "cancelLabel";
            private static final String KEY_CONFIRM_LABEL = "confirmLabel";
            private static final String KEY_FLAGS = "flags";
            private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
            private CharSequence mCancelLabel;
            private CharSequence mConfirmLabel;
            private int mFlags;
            private CharSequence mInProgressLabel;
            
            public WearableExtender() {
                this.mFlags = 1;
            }
            
            public WearableExtender(final Action action) {
                this.mFlags = 1;
                final Bundle bundle = action.getExtras().getBundle("android.wearable.EXTENSIONS");
                if (bundle != null) {
                    this.mFlags = bundle.getInt("flags", 1);
                    this.mInProgressLabel = bundle.getCharSequence("inProgressLabel");
                    this.mConfirmLabel = bundle.getCharSequence("confirmLabel");
                    this.mCancelLabel = bundle.getCharSequence("cancelLabel");
                }
            }
            
            private void setFlag(final int n, final boolean b) {
                if (b) {
                    this.mFlags |= n;
                    return;
                }
                this.mFlags &= ~n;
            }
            
            public WearableExtender clone() {
                final WearableExtender wearableExtender = new WearableExtender();
                wearableExtender.mFlags = this.mFlags;
                wearableExtender.mInProgressLabel = this.mInProgressLabel;
                wearableExtender.mConfirmLabel = this.mConfirmLabel;
                wearableExtender.mCancelLabel = this.mCancelLabel;
                return wearableExtender;
            }
            
            @Override
            public Builder extend(final Builder builder) {
                final Bundle bundle = new Bundle();
                if (this.mFlags != 1) {
                    bundle.putInt("flags", this.mFlags);
                }
                if (this.mInProgressLabel != null) {
                    bundle.putCharSequence("inProgressLabel", this.mInProgressLabel);
                }
                if (this.mConfirmLabel != null) {
                    bundle.putCharSequence("confirmLabel", this.mConfirmLabel);
                }
                if (this.mCancelLabel != null) {
                    bundle.putCharSequence("cancelLabel", this.mCancelLabel);
                }
                builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
                return builder;
            }
            
            public CharSequence getCancelLabel() {
                return this.mCancelLabel;
            }
            
            public CharSequence getConfirmLabel() {
                return this.mConfirmLabel;
            }
            
            public CharSequence getInProgressLabel() {
                return this.mInProgressLabel;
            }
            
            public boolean isAvailableOffline() {
                return (this.mFlags & 0x1) != 0x0;
            }
            
            public WearableExtender setAvailableOffline(final boolean b) {
                this.setFlag(1, b);
                return this;
            }
            
            public WearableExtender setCancelLabel(final CharSequence mCancelLabel) {
                this.mCancelLabel = mCancelLabel;
                return this;
            }
            
            public WearableExtender setConfirmLabel(final CharSequence mConfirmLabel) {
                this.mConfirmLabel = mConfirmLabel;
                return this;
            }
            
            public WearableExtender setInProgressLabel(final CharSequence mInProgressLabel) {
                this.mInProgressLabel = mInProgressLabel;
                return this;
            }
        }
    }
    
    public static class BigPictureStyle extends Style
    {
        Bitmap mBigLargeIcon;
        boolean mBigLargeIconSet;
        Bitmap mPicture;
        
        public BigPictureStyle() {
        }
        
        public BigPictureStyle(final NotificationCompat.Builder builder) {
            ((Style)this).setBuilder(builder);
        }
        
        public BigPictureStyle bigLargeIcon(final Bitmap mBigLargeIcon) {
            this.mBigLargeIcon = mBigLargeIcon;
            this.mBigLargeIconSet = true;
            return this;
        }
        
        public BigPictureStyle bigPicture(final Bitmap mPicture) {
            this.mPicture = mPicture;
            return this;
        }
        
        public BigPictureStyle setBigContentTitle(final CharSequence charSequence) {
            this.mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(charSequence);
            return this;
        }
        
        public BigPictureStyle setSummaryText(final CharSequence charSequence) {
            this.mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(charSequence);
            this.mSummaryTextSet = true;
            return this;
        }
    }
    
    public static class BigTextStyle extends Style
    {
        CharSequence mBigText;
        
        public BigTextStyle() {
        }
        
        public BigTextStyle(final NotificationCompat.Builder builder) {
            ((Style)this).setBuilder(builder);
        }
        
        public BigTextStyle bigText(final CharSequence charSequence) {
            this.mBigText = NotificationCompat.Builder.limitCharSequenceLength(charSequence);
            return this;
        }
        
        public BigTextStyle setBigContentTitle(final CharSequence charSequence) {
            this.mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(charSequence);
            return this;
        }
        
        public BigTextStyle setSummaryText(final CharSequence charSequence) {
            this.mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(charSequence);
            this.mSummaryTextSet = true;
            return this;
        }
    }
    
    public static class Builder
    {
        private static final int MAX_CHARSEQUENCE_LENGTH = 5120;
        public ArrayList<Action> mActions;
        String mCategory;
        int mColor;
        public CharSequence mContentInfo;
        PendingIntent mContentIntent;
        public CharSequence mContentText;
        public CharSequence mContentTitle;
        public Context mContext;
        Bundle mExtras;
        PendingIntent mFullScreenIntent;
        String mGroupKey;
        boolean mGroupSummary;
        public Bitmap mLargeIcon;
        boolean mLocalOnly;
        public Notification mNotification;
        public int mNumber;
        public ArrayList<String> mPeople;
        int mPriority;
        int mProgress;
        boolean mProgressIndeterminate;
        int mProgressMax;
        Notification mPublicVersion;
        boolean mShowWhen;
        String mSortKey;
        public Style mStyle;
        public CharSequence mSubText;
        RemoteViews mTickerView;
        public boolean mUseChronometer;
        int mVisibility;
        
        public Builder(final Context mContext) {
            this.mShowWhen = true;
            this.mActions = new ArrayList<Action>();
            this.mLocalOnly = false;
            this.mColor = 0;
            this.mVisibility = 0;
            this.mNotification = new Notification();
            this.mContext = mContext;
            this.mNotification.when = System.currentTimeMillis();
            this.mNotification.audioStreamType = -1;
            this.mPriority = 0;
            this.mPeople = new ArrayList<String>();
        }
        
        protected static CharSequence limitCharSequenceLength(final CharSequence charSequence) {
            if (charSequence != null && charSequence.length() > 5120) {
                return charSequence.subSequence(0, 5120);
            }
            return charSequence;
        }
        
        private void setFlag(final int n, final boolean b) {
            if (b) {
                final Notification mNotification = this.mNotification;
                mNotification.flags |= n;
                return;
            }
            final Notification mNotification2 = this.mNotification;
            mNotification2.flags &= ~n;
        }
        
        public Builder addAction(final int n, final CharSequence charSequence, final PendingIntent pendingIntent) {
            this.mActions.add(new Action(n, charSequence, pendingIntent));
            return this;
        }
        
        public Builder addAction(final Action action) {
            this.mActions.add(action);
            return this;
        }
        
        public Builder addExtras(final Bundle bundle) {
            if (bundle != null) {
                if (this.mExtras != null) {
                    this.mExtras.putAll(bundle);
                    return this;
                }
                this.mExtras = new Bundle(bundle);
            }
            return this;
        }
        
        public Builder addPerson(final String s) {
            this.mPeople.add(s);
            return this;
        }
        
        public Notification build() {
            return NotificationCompat.IMPL.build(this, this.getExtender());
        }
        
        public Builder extend(final NotificationCompat.Extender extender) {
            extender.extend(this);
            return this;
        }
        
        protected BuilderExtender getExtender() {
            return new BuilderExtender();
        }
        
        public Bundle getExtras() {
            if (this.mExtras == null) {
                this.mExtras = new Bundle();
            }
            return this.mExtras;
        }
        
        @Deprecated
        public Notification getNotification() {
            return this.build();
        }
        
        public Builder setAutoCancel(final boolean b) {
            this.setFlag(16, b);
            return this;
        }
        
        public Builder setCategory(final String mCategory) {
            this.mCategory = mCategory;
            return this;
        }
        
        public Builder setColor(@ColorInt final int mColor) {
            this.mColor = mColor;
            return this;
        }
        
        public Builder setContent(final RemoteViews contentView) {
            this.mNotification.contentView = contentView;
            return this;
        }
        
        public Builder setContentInfo(final CharSequence charSequence) {
            this.mContentInfo = limitCharSequenceLength(charSequence);
            return this;
        }
        
        public Builder setContentIntent(final PendingIntent mContentIntent) {
            this.mContentIntent = mContentIntent;
            return this;
        }
        
        public Builder setContentText(final CharSequence charSequence) {
            this.mContentText = limitCharSequenceLength(charSequence);
            return this;
        }
        
        public Builder setContentTitle(final CharSequence charSequence) {
            this.mContentTitle = limitCharSequenceLength(charSequence);
            return this;
        }
        
        public Builder setDefaults(final int defaults) {
            this.mNotification.defaults = defaults;
            if ((defaults & 0x4) != 0x0) {
                final Notification mNotification = this.mNotification;
                mNotification.flags |= 0x1;
            }
            return this;
        }
        
        public Builder setDeleteIntent(final PendingIntent deleteIntent) {
            this.mNotification.deleteIntent = deleteIntent;
            return this;
        }
        
        public Builder setExtras(final Bundle mExtras) {
            this.mExtras = mExtras;
            return this;
        }
        
        public Builder setFullScreenIntent(final PendingIntent mFullScreenIntent, final boolean b) {
            this.mFullScreenIntent = mFullScreenIntent;
            this.setFlag(128, b);
            return this;
        }
        
        public Builder setGroup(final String mGroupKey) {
            this.mGroupKey = mGroupKey;
            return this;
        }
        
        public Builder setGroupSummary(final boolean mGroupSummary) {
            this.mGroupSummary = mGroupSummary;
            return this;
        }
        
        public Builder setLargeIcon(final Bitmap mLargeIcon) {
            this.mLargeIcon = mLargeIcon;
            return this;
        }
        
        public Builder setLights(@ColorInt int ledARGB, int flags, final int ledOffMS) {
            final int n = 1;
            this.mNotification.ledARGB = ledARGB;
            this.mNotification.ledOnMS = flags;
            this.mNotification.ledOffMS = ledOffMS;
            if (this.mNotification.ledOnMS != 0 && this.mNotification.ledOffMS != 0) {
                ledARGB = 1;
            }
            else {
                ledARGB = 0;
            }
            final Notification mNotification = this.mNotification;
            flags = this.mNotification.flags;
            if (ledARGB != 0) {
                ledARGB = n;
            }
            else {
                ledARGB = 0;
            }
            mNotification.flags = (ledARGB | (flags & 0xFFFFFFFE));
            return this;
        }
        
        public Builder setLocalOnly(final boolean mLocalOnly) {
            this.mLocalOnly = mLocalOnly;
            return this;
        }
        
        public Builder setNumber(final int mNumber) {
            this.mNumber = mNumber;
            return this;
        }
        
        public Builder setOngoing(final boolean b) {
            this.setFlag(2, b);
            return this;
        }
        
        public Builder setOnlyAlertOnce(final boolean b) {
            this.setFlag(8, b);
            return this;
        }
        
        public Builder setPriority(final int mPriority) {
            this.mPriority = mPriority;
            return this;
        }
        
        public Builder setProgress(final int mProgressMax, final int mProgress, final boolean mProgressIndeterminate) {
            this.mProgressMax = mProgressMax;
            this.mProgress = mProgress;
            this.mProgressIndeterminate = mProgressIndeterminate;
            return this;
        }
        
        public Builder setPublicVersion(final Notification mPublicVersion) {
            this.mPublicVersion = mPublicVersion;
            return this;
        }
        
        public Builder setShowWhen(final boolean mShowWhen) {
            this.mShowWhen = mShowWhen;
            return this;
        }
        
        public Builder setSmallIcon(final int icon) {
            this.mNotification.icon = icon;
            return this;
        }
        
        public Builder setSmallIcon(final int icon, final int iconLevel) {
            this.mNotification.icon = icon;
            this.mNotification.iconLevel = iconLevel;
            return this;
        }
        
        public Builder setSortKey(final String mSortKey) {
            this.mSortKey = mSortKey;
            return this;
        }
        
        public Builder setSound(final Uri sound) {
            this.mNotification.sound = sound;
            this.mNotification.audioStreamType = -1;
            return this;
        }
        
        public Builder setSound(final Uri sound, final int audioStreamType) {
            this.mNotification.sound = sound;
            this.mNotification.audioStreamType = audioStreamType;
            return this;
        }
        
        public Builder setStyle(final Style mStyle) {
            if (this.mStyle != mStyle) {
                this.mStyle = mStyle;
                if (this.mStyle != null) {
                    this.mStyle.setBuilder(this);
                }
            }
            return this;
        }
        
        public Builder setSubText(final CharSequence charSequence) {
            this.mSubText = limitCharSequenceLength(charSequence);
            return this;
        }
        
        public Builder setTicker(final CharSequence charSequence) {
            this.mNotification.tickerText = limitCharSequenceLength(charSequence);
            return this;
        }
        
        public Builder setTicker(final CharSequence charSequence, final RemoteViews mTickerView) {
            this.mNotification.tickerText = limitCharSequenceLength(charSequence);
            this.mTickerView = mTickerView;
            return this;
        }
        
        public Builder setUsesChronometer(final boolean mUseChronometer) {
            this.mUseChronometer = mUseChronometer;
            return this;
        }
        
        public Builder setVibrate(final long[] vibrate) {
            this.mNotification.vibrate = vibrate;
            return this;
        }
        
        public Builder setVisibility(final int mVisibility) {
            this.mVisibility = mVisibility;
            return this;
        }
        
        public Builder setWhen(final long when) {
            this.mNotification.when = when;
            return this;
        }
    }
    
    protected static class BuilderExtender
    {
        public Notification build(final NotificationCompat.Builder builder, final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return notificationBuilderWithBuilderAccessor.build();
        }
    }
    
    public static final class CarExtender implements NotificationCompat.Extender
    {
        private static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
        private static final String EXTRA_COLOR = "app_color";
        private static final String EXTRA_CONVERSATION = "car_conversation";
        private static final String EXTRA_LARGE_ICON = "large_icon";
        private static final String TAG = "CarExtender";
        private int mColor;
        private Bitmap mLargeIcon;
        private UnreadConversation mUnreadConversation;
        
        public CarExtender() {
            this.mColor = 0;
        }
        
        public CarExtender(final Notification notification) {
            this.mColor = 0;
            if (Build$VERSION.SDK_INT >= 21) {
                Bundle bundle;
                if (NotificationCompat.getExtras(notification) == null) {
                    bundle = null;
                }
                else {
                    bundle = NotificationCompat.getExtras(notification).getBundle("android.car.EXTENSIONS");
                }
                if (bundle != null) {
                    this.mLargeIcon = (Bitmap)bundle.getParcelable("large_icon");
                    this.mColor = bundle.getInt("app_color", 0);
                    this.mUnreadConversation = (UnreadConversation)NotificationCompat.IMPL.getUnreadConversationFromBundle(bundle.getBundle("car_conversation"), UnreadConversation.FACTORY, RemoteInput.FACTORY);
                }
            }
        }
        
        @Override
        public NotificationCompat.Builder extend(final NotificationCompat.Builder builder) {
            if (Build$VERSION.SDK_INT < 21) {
                return builder;
            }
            final Bundle bundle = new Bundle();
            if (this.mLargeIcon != null) {
                bundle.putParcelable("large_icon", (Parcelable)this.mLargeIcon);
            }
            if (this.mColor != 0) {
                bundle.putInt("app_color", this.mColor);
            }
            if (this.mUnreadConversation != null) {
                bundle.putBundle("car_conversation", NotificationCompat.IMPL.getBundleForUnreadConversation(this.mUnreadConversation));
            }
            builder.getExtras().putBundle("android.car.EXTENSIONS", bundle);
            return builder;
        }
        
        @ColorInt
        public int getColor() {
            return this.mColor;
        }
        
        public Bitmap getLargeIcon() {
            return this.mLargeIcon;
        }
        
        public UnreadConversation getUnreadConversation() {
            return this.mUnreadConversation;
        }
        
        public CarExtender setColor(@ColorInt final int mColor) {
            this.mColor = mColor;
            return this;
        }
        
        public CarExtender setLargeIcon(final Bitmap mLargeIcon) {
            this.mLargeIcon = mLargeIcon;
            return this;
        }
        
        public CarExtender setUnreadConversation(final UnreadConversation mUnreadConversation) {
            this.mUnreadConversation = mUnreadConversation;
            return this;
        }
        
        public static class UnreadConversation extends NotificationCompatBase.UnreadConversation
        {
            static final Factory FACTORY;
            private final long mLatestTimestamp;
            private final String[] mMessages;
            private final String[] mParticipants;
            private final PendingIntent mReadPendingIntent;
            private final RemoteInput mRemoteInput;
            private final PendingIntent mReplyPendingIntent;
            
            static {
                FACTORY = new Factory() {
                    public CarExtender.UnreadConversation build(final String[] array, final RemoteInputCompatBase.RemoteInput remoteInput, final PendingIntent pendingIntent, final PendingIntent pendingIntent2, final String[] array2, final long n) {
                        return new CarExtender.UnreadConversation(array, (RemoteInput)remoteInput, pendingIntent, pendingIntent2, array2, n);
                    }
                };
            }
            
            UnreadConversation(final String[] mMessages, final RemoteInput mRemoteInput, final PendingIntent mReplyPendingIntent, final PendingIntent mReadPendingIntent, final String[] mParticipants, final long mLatestTimestamp) {
                this.mMessages = mMessages;
                this.mRemoteInput = mRemoteInput;
                this.mReadPendingIntent = mReadPendingIntent;
                this.mReplyPendingIntent = mReplyPendingIntent;
                this.mParticipants = mParticipants;
                this.mLatestTimestamp = mLatestTimestamp;
            }
            
            public long getLatestTimestamp() {
                return this.mLatestTimestamp;
            }
            
            public String[] getMessages() {
                return this.mMessages;
            }
            
            public String getParticipant() {
                if (this.mParticipants.length > 0) {
                    return this.mParticipants[0];
                }
                return null;
            }
            
            public String[] getParticipants() {
                return this.mParticipants;
            }
            
            public PendingIntent getReadPendingIntent() {
                return this.mReadPendingIntent;
            }
            
            public RemoteInput getRemoteInput() {
                return this.mRemoteInput;
            }
            
            public PendingIntent getReplyPendingIntent() {
                return this.mReplyPendingIntent;
            }
            
            public static class Builder
            {
                private long mLatestTimestamp;
                private final List<String> mMessages;
                private final String mParticipant;
                private PendingIntent mReadPendingIntent;
                private RemoteInput mRemoteInput;
                private PendingIntent mReplyPendingIntent;
                
                public Builder(final String mParticipant) {
                    this.mMessages = new ArrayList<String>();
                    this.mParticipant = mParticipant;
                }
                
                public Builder addMessage(final String s) {
                    this.mMessages.add(s);
                    return this;
                }
                
                public UnreadConversation build() {
                    return new UnreadConversation(this.mMessages.toArray(new String[this.mMessages.size()]), this.mRemoteInput, this.mReplyPendingIntent, this.mReadPendingIntent, new String[] { this.mParticipant }, this.mLatestTimestamp);
                }
                
                public Builder setLatestTimestamp(final long mLatestTimestamp) {
                    this.mLatestTimestamp = mLatestTimestamp;
                    return this;
                }
                
                public Builder setReadPendingIntent(final PendingIntent mReadPendingIntent) {
                    this.mReadPendingIntent = mReadPendingIntent;
                    return this;
                }
                
                public Builder setReplyAction(final PendingIntent mReplyPendingIntent, final RemoteInput mRemoteInput) {
                    this.mRemoteInput = mRemoteInput;
                    this.mReplyPendingIntent = mReplyPendingIntent;
                    return this;
                }
            }
        }
    }
    
    public interface Extender
    {
        NotificationCompat.Builder extend(final NotificationCompat.Builder p0);
    }
    
    public static class InboxStyle extends Style
    {
        ArrayList<CharSequence> mTexts;
        
        public InboxStyle() {
            this.mTexts = new ArrayList<CharSequence>();
        }
        
        public InboxStyle(final NotificationCompat.Builder builder) {
            this.mTexts = new ArrayList<CharSequence>();
            ((Style)this).setBuilder(builder);
        }
        
        public InboxStyle addLine(final CharSequence charSequence) {
            this.mTexts.add(NotificationCompat.Builder.limitCharSequenceLength(charSequence));
            return this;
        }
        
        public InboxStyle setBigContentTitle(final CharSequence charSequence) {
            this.mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(charSequence);
            return this;
        }
        
        public InboxStyle setSummaryText(final CharSequence charSequence) {
            this.mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(charSequence);
            this.mSummaryTextSet = true;
            return this;
        }
    }
    
    interface NotificationCompatImpl
    {
        Notification build(final NotificationCompat.Builder p0, final BuilderExtender p1);
        
        Action getAction(final Notification p0, final int p1);
        
        int getActionCount(final Notification p0);
        
        Action[] getActionsFromParcelableArrayList(final ArrayList<Parcelable> p0);
        
        Bundle getBundleForUnreadConversation(final NotificationCompatBase.UnreadConversation p0);
        
        String getCategory(final Notification p0);
        
        Bundle getExtras(final Notification p0);
        
        String getGroup(final Notification p0);
        
        boolean getLocalOnly(final Notification p0);
        
        ArrayList<Parcelable> getParcelableArrayListForActions(final Action[] p0);
        
        String getSortKey(final Notification p0);
        
        NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(final Bundle p0, final NotificationCompatBase.UnreadConversation.Factory p1, final RemoteInputCompatBase.RemoteInput.Factory p2);
        
        boolean isGroupSummary(final Notification p0);
    }
    
    static class NotificationCompatImplApi20 extends NotificationCompatImplKitKat
    {
        @Override
        public Notification build(final NotificationCompat.Builder builder, final BuilderExtender builderExtender) {
            final NotificationCompatApi20.Builder builder2 = new NotificationCompatApi20.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate, builder.mShowWhen, builder.mUseChronometer, builder.mPriority, builder.mSubText, builder.mLocalOnly, builder.mPeople, builder.mExtras, builder.mGroupKey, builder.mGroupSummary, builder.mSortKey);
            addActionsToBuilder(builder2, builder.mActions);
            addStyleToBuilderJellybean(builder2, builder.mStyle);
            return builderExtender.build(builder, builder2);
        }
        
        @Override
        public Action getAction(final Notification notification, final int n) {
            return (Action)NotificationCompatApi20.getAction(notification, n, Action.FACTORY, RemoteInput.FACTORY);
        }
        
        @Override
        public Action[] getActionsFromParcelableArrayList(final ArrayList<Parcelable> list) {
            return (Action[])NotificationCompatApi20.getActionsFromParcelableArrayList(list, Action.FACTORY, RemoteInput.FACTORY);
        }
        
        @Override
        public String getGroup(final Notification notification) {
            return NotificationCompatApi20.getGroup(notification);
        }
        
        @Override
        public boolean getLocalOnly(final Notification notification) {
            return NotificationCompatApi20.getLocalOnly(notification);
        }
        
        @Override
        public ArrayList<Parcelable> getParcelableArrayListForActions(final Action[] array) {
            return NotificationCompatApi20.getParcelableArrayListForActions(array);
        }
        
        @Override
        public String getSortKey(final Notification notification) {
            return NotificationCompatApi20.getSortKey(notification);
        }
        
        @Override
        public boolean isGroupSummary(final Notification notification) {
            return NotificationCompatApi20.isGroupSummary(notification);
        }
    }
    
    static class NotificationCompatImplApi21 extends NotificationCompatImplApi20
    {
        @Override
        public Notification build(final NotificationCompat.Builder builder, final BuilderExtender builderExtender) {
            final NotificationCompatApi21.Builder builder2 = new NotificationCompatApi21.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate, builder.mShowWhen, builder.mUseChronometer, builder.mPriority, builder.mSubText, builder.mLocalOnly, builder.mCategory, builder.mPeople, builder.mExtras, builder.mColor, builder.mVisibility, builder.mPublicVersion, builder.mGroupKey, builder.mGroupSummary, builder.mSortKey);
            addActionsToBuilder(builder2, builder.mActions);
            addStyleToBuilderJellybean(builder2, builder.mStyle);
            return builderExtender.build(builder, builder2);
        }
        
        @Override
        public Bundle getBundleForUnreadConversation(final NotificationCompatBase.UnreadConversation unreadConversation) {
            return NotificationCompatApi21.getBundleForUnreadConversation(unreadConversation);
        }
        
        @Override
        public String getCategory(final Notification notification) {
            return NotificationCompatApi21.getCategory(notification);
        }
        
        @Override
        public NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(final Bundle bundle, final NotificationCompatBase.UnreadConversation.Factory factory, final RemoteInputCompatBase.RemoteInput.Factory factory2) {
            return NotificationCompatApi21.getUnreadConversationFromBundle(bundle, factory, factory2);
        }
    }
    
    static class NotificationCompatImplBase implements NotificationCompatImpl
    {
        @Override
        public Notification build(final NotificationCompat.Builder builder, final BuilderExtender builderExtender) {
            final Notification mNotification = builder.mNotification;
            mNotification.setLatestEventInfo(builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentIntent);
            if (builder.mPriority > 0) {
                mNotification.flags |= 0x80;
            }
            return mNotification;
        }
        
        @Override
        public Action getAction(final Notification notification, final int n) {
            return null;
        }
        
        @Override
        public int getActionCount(final Notification notification) {
            return 0;
        }
        
        @Override
        public Action[] getActionsFromParcelableArrayList(final ArrayList<Parcelable> list) {
            return null;
        }
        
        @Override
        public Bundle getBundleForUnreadConversation(final NotificationCompatBase.UnreadConversation unreadConversation) {
            return null;
        }
        
        @Override
        public String getCategory(final Notification notification) {
            return null;
        }
        
        @Override
        public Bundle getExtras(final Notification notification) {
            return null;
        }
        
        @Override
        public String getGroup(final Notification notification) {
            return null;
        }
        
        @Override
        public boolean getLocalOnly(final Notification notification) {
            return false;
        }
        
        @Override
        public ArrayList<Parcelable> getParcelableArrayListForActions(final Action[] array) {
            return null;
        }
        
        @Override
        public String getSortKey(final Notification notification) {
            return null;
        }
        
        @Override
        public NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(final Bundle bundle, final NotificationCompatBase.UnreadConversation.Factory factory, final RemoteInputCompatBase.RemoteInput.Factory factory2) {
            return null;
        }
        
        @Override
        public boolean isGroupSummary(final Notification notification) {
            return false;
        }
    }
    
    static class NotificationCompatImplGingerbread extends NotificationCompatImplBase
    {
        @Override
        public Notification build(final NotificationCompat.Builder builder, final BuilderExtender builderExtender) {
            final Notification mNotification = builder.mNotification;
            mNotification.setLatestEventInfo(builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentIntent);
            final Notification add = NotificationCompatGingerbread.add(mNotification, builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentIntent, builder.mFullScreenIntent);
            if (builder.mPriority > 0) {
                add.flags |= 0x80;
            }
            return add;
        }
    }
    
    static class NotificationCompatImplHoneycomb extends NotificationCompatImplBase
    {
        @Override
        public Notification build(final NotificationCompat.Builder builder, final BuilderExtender builderExtender) {
            return NotificationCompatHoneycomb.add(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon);
        }
    }
    
    static class NotificationCompatImplIceCreamSandwich extends NotificationCompatImplBase
    {
        @Override
        public Notification build(final NotificationCompat.Builder builder, final BuilderExtender builderExtender) {
            return builderExtender.build(builder, new NotificationCompatIceCreamSandwich.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate));
        }
    }
    
    static class NotificationCompatImplJellybean extends NotificationCompatImplBase
    {
        @Override
        public Notification build(final NotificationCompat.Builder builder, final BuilderExtender builderExtender) {
            final NotificationCompatJellybean.Builder builder2 = new NotificationCompatJellybean.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate, builder.mUseChronometer, builder.mPriority, builder.mSubText, builder.mLocalOnly, builder.mExtras, builder.mGroupKey, builder.mGroupSummary, builder.mSortKey);
            addActionsToBuilder(builder2, builder.mActions);
            addStyleToBuilderJellybean(builder2, builder.mStyle);
            return builderExtender.build(builder, builder2);
        }
        
        @Override
        public Action getAction(final Notification notification, final int n) {
            return (Action)NotificationCompatJellybean.getAction(notification, n, Action.FACTORY, RemoteInput.FACTORY);
        }
        
        @Override
        public int getActionCount(final Notification notification) {
            return NotificationCompatJellybean.getActionCount(notification);
        }
        
        @Override
        public Action[] getActionsFromParcelableArrayList(final ArrayList<Parcelable> list) {
            return (Action[])NotificationCompatJellybean.getActionsFromParcelableArrayList(list, Action.FACTORY, RemoteInput.FACTORY);
        }
        
        @Override
        public Bundle getExtras(final Notification notification) {
            return NotificationCompatJellybean.getExtras(notification);
        }
        
        @Override
        public String getGroup(final Notification notification) {
            return NotificationCompatJellybean.getGroup(notification);
        }
        
        @Override
        public boolean getLocalOnly(final Notification notification) {
            return NotificationCompatJellybean.getLocalOnly(notification);
        }
        
        @Override
        public ArrayList<Parcelable> getParcelableArrayListForActions(final Action[] array) {
            return NotificationCompatJellybean.getParcelableArrayListForActions(array);
        }
        
        @Override
        public String getSortKey(final Notification notification) {
            return NotificationCompatJellybean.getSortKey(notification);
        }
        
        @Override
        public boolean isGroupSummary(final Notification notification) {
            return NotificationCompatJellybean.isGroupSummary(notification);
        }
    }
    
    static class NotificationCompatImplKitKat extends NotificationCompatImplJellybean
    {
        @Override
        public Notification build(final NotificationCompat.Builder builder, final BuilderExtender builderExtender) {
            final NotificationCompatKitKat.Builder builder2 = new NotificationCompatKitKat.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate, builder.mShowWhen, builder.mUseChronometer, builder.mPriority, builder.mSubText, builder.mLocalOnly, builder.mPeople, builder.mExtras, builder.mGroupKey, builder.mGroupSummary, builder.mSortKey);
            addActionsToBuilder(builder2, builder.mActions);
            addStyleToBuilderJellybean(builder2, builder.mStyle);
            return builderExtender.build(builder, builder2);
        }
        
        @Override
        public Action getAction(final Notification notification, final int n) {
            return (Action)NotificationCompatKitKat.getAction(notification, n, Action.FACTORY, RemoteInput.FACTORY);
        }
        
        @Override
        public int getActionCount(final Notification notification) {
            return NotificationCompatKitKat.getActionCount(notification);
        }
        
        @Override
        public Bundle getExtras(final Notification notification) {
            return NotificationCompatKitKat.getExtras(notification);
        }
        
        @Override
        public String getGroup(final Notification notification) {
            return NotificationCompatKitKat.getGroup(notification);
        }
        
        @Override
        public boolean getLocalOnly(final Notification notification) {
            return NotificationCompatKitKat.getLocalOnly(notification);
        }
        
        @Override
        public String getSortKey(final Notification notification) {
            return NotificationCompatKitKat.getSortKey(notification);
        }
        
        @Override
        public boolean isGroupSummary(final Notification notification) {
            return NotificationCompatKitKat.isGroupSummary(notification);
        }
    }
    
    public abstract static class Style
    {
        CharSequence mBigContentTitle;
        NotificationCompat.Builder mBuilder;
        CharSequence mSummaryText;
        boolean mSummaryTextSet;
        
        public Style() {
            this.mSummaryTextSet = false;
        }
        
        public Notification build() {
            Notification build = null;
            if (this.mBuilder != null) {
                build = this.mBuilder.build();
            }
            return build;
        }
        
        public void setBuilder(final NotificationCompat.Builder mBuilder) {
            if (this.mBuilder != mBuilder) {
                this.mBuilder = mBuilder;
                if (this.mBuilder != null) {
                    this.mBuilder.setStyle(this);
                }
            }
        }
    }
    
    public static final class WearableExtender implements NotificationCompat.Extender
    {
        private static final int DEFAULT_CONTENT_ICON_GRAVITY = 8388613;
        private static final int DEFAULT_FLAGS = 1;
        private static final int DEFAULT_GRAVITY = 80;
        private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
        private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
        private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
        private static final int FLAG_HINT_HIDE_ICON = 2;
        private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
        private static final int FLAG_START_SCROLL_BOTTOM = 8;
        private static final String KEY_ACTIONS = "actions";
        private static final String KEY_BACKGROUND = "background";
        private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
        private static final String KEY_CONTENT_ICON = "contentIcon";
        private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
        private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
        private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
        private static final String KEY_DISPLAY_INTENT = "displayIntent";
        private static final String KEY_FLAGS = "flags";
        private static final String KEY_GRAVITY = "gravity";
        private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
        private static final String KEY_PAGES = "pages";
        public static final int SCREEN_TIMEOUT_LONG = -1;
        public static final int SCREEN_TIMEOUT_SHORT = 0;
        public static final int SIZE_DEFAULT = 0;
        public static final int SIZE_FULL_SCREEN = 5;
        public static final int SIZE_LARGE = 4;
        public static final int SIZE_MEDIUM = 3;
        public static final int SIZE_SMALL = 2;
        public static final int SIZE_XSMALL = 1;
        public static final int UNSET_ACTION_INDEX = -1;
        private ArrayList<Action> mActions;
        private Bitmap mBackground;
        private int mContentActionIndex;
        private int mContentIcon;
        private int mContentIconGravity;
        private int mCustomContentHeight;
        private int mCustomSizePreset;
        private PendingIntent mDisplayIntent;
        private int mFlags;
        private int mGravity;
        private int mHintScreenTimeout;
        private ArrayList<Notification> mPages;
        
        public WearableExtender() {
            this.mActions = new ArrayList<Action>();
            this.mFlags = 1;
            this.mPages = new ArrayList<Notification>();
            this.mContentIconGravity = 8388613;
            this.mContentActionIndex = -1;
            this.mCustomSizePreset = 0;
            this.mGravity = 80;
        }
        
        public WearableExtender(final Notification notification) {
            this.mActions = new ArrayList<Action>();
            this.mFlags = 1;
            this.mPages = new ArrayList<Notification>();
            this.mContentIconGravity = 8388613;
            this.mContentActionIndex = -1;
            this.mCustomSizePreset = 0;
            this.mGravity = 80;
            final Bundle extras = NotificationCompat.getExtras(notification);
            Bundle bundle;
            if (extras != null) {
                bundle = extras.getBundle("android.wearable.EXTENSIONS");
            }
            else {
                bundle = null;
            }
            if (bundle != null) {
                final Action[] actionsFromParcelableArrayList = NotificationCompat.IMPL.getActionsFromParcelableArrayList(bundle.getParcelableArrayList("actions"));
                if (actionsFromParcelableArrayList != null) {
                    Collections.addAll(this.mActions, actionsFromParcelableArrayList);
                }
                this.mFlags = bundle.getInt("flags", 1);
                this.mDisplayIntent = (PendingIntent)bundle.getParcelable("displayIntent");
                final Notification[] access$500 = getNotificationArrayFromBundle(bundle, "pages");
                if (access$500 != null) {
                    Collections.addAll(this.mPages, access$500);
                }
                this.mBackground = (Bitmap)bundle.getParcelable("background");
                this.mContentIcon = bundle.getInt("contentIcon");
                this.mContentIconGravity = bundle.getInt("contentIconGravity", 8388613);
                this.mContentActionIndex = bundle.getInt("contentActionIndex", -1);
                this.mCustomSizePreset = bundle.getInt("customSizePreset", 0);
                this.mCustomContentHeight = bundle.getInt("customContentHeight");
                this.mGravity = bundle.getInt("gravity", 80);
                this.mHintScreenTimeout = bundle.getInt("hintScreenTimeout");
            }
        }
        
        private void setFlag(final int n, final boolean b) {
            if (b) {
                this.mFlags |= n;
                return;
            }
            this.mFlags &= ~n;
        }
        
        public WearableExtender addAction(final Action action) {
            this.mActions.add(action);
            return this;
        }
        
        public WearableExtender addActions(final List<Action> list) {
            this.mActions.addAll(list);
            return this;
        }
        
        public WearableExtender addPage(final Notification notification) {
            this.mPages.add(notification);
            return this;
        }
        
        public WearableExtender addPages(final List<Notification> list) {
            this.mPages.addAll(list);
            return this;
        }
        
        public WearableExtender clearActions() {
            this.mActions.clear();
            return this;
        }
        
        public WearableExtender clearPages() {
            this.mPages.clear();
            return this;
        }
        
        public WearableExtender clone() {
            final WearableExtender wearableExtender = new WearableExtender();
            wearableExtender.mActions = new ArrayList<Action>(this.mActions);
            wearableExtender.mFlags = this.mFlags;
            wearableExtender.mDisplayIntent = this.mDisplayIntent;
            wearableExtender.mPages = new ArrayList<Notification>(this.mPages);
            wearableExtender.mBackground = this.mBackground;
            wearableExtender.mContentIcon = this.mContentIcon;
            wearableExtender.mContentIconGravity = this.mContentIconGravity;
            wearableExtender.mContentActionIndex = this.mContentActionIndex;
            wearableExtender.mCustomSizePreset = this.mCustomSizePreset;
            wearableExtender.mCustomContentHeight = this.mCustomContentHeight;
            wearableExtender.mGravity = this.mGravity;
            wearableExtender.mHintScreenTimeout = this.mHintScreenTimeout;
            return wearableExtender;
        }
        
        @Override
        public NotificationCompat.Builder extend(final NotificationCompat.Builder builder) {
            final Bundle bundle = new Bundle();
            if (!this.mActions.isEmpty()) {
                bundle.putParcelableArrayList("actions", (ArrayList)NotificationCompat.IMPL.getParcelableArrayListForActions(this.mActions.toArray(new Action[this.mActions.size()])));
            }
            if (this.mFlags != 1) {
                bundle.putInt("flags", this.mFlags);
            }
            if (this.mDisplayIntent != null) {
                bundle.putParcelable("displayIntent", (Parcelable)this.mDisplayIntent);
            }
            if (!this.mPages.isEmpty()) {
                bundle.putParcelableArray("pages", (Parcelable[])this.mPages.toArray((Parcelable[])new Notification[this.mPages.size()]));
            }
            if (this.mBackground != null) {
                bundle.putParcelable("background", (Parcelable)this.mBackground);
            }
            if (this.mContentIcon != 0) {
                bundle.putInt("contentIcon", this.mContentIcon);
            }
            if (this.mContentIconGravity != 8388613) {
                bundle.putInt("contentIconGravity", this.mContentIconGravity);
            }
            if (this.mContentActionIndex != -1) {
                bundle.putInt("contentActionIndex", this.mContentActionIndex);
            }
            if (this.mCustomSizePreset != 0) {
                bundle.putInt("customSizePreset", this.mCustomSizePreset);
            }
            if (this.mCustomContentHeight != 0) {
                bundle.putInt("customContentHeight", this.mCustomContentHeight);
            }
            if (this.mGravity != 80) {
                bundle.putInt("gravity", this.mGravity);
            }
            if (this.mHintScreenTimeout != 0) {
                bundle.putInt("hintScreenTimeout", this.mHintScreenTimeout);
            }
            builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
            return builder;
        }
        
        public List<Action> getActions() {
            return this.mActions;
        }
        
        public Bitmap getBackground() {
            return this.mBackground;
        }
        
        public int getContentAction() {
            return this.mContentActionIndex;
        }
        
        public int getContentIcon() {
            return this.mContentIcon;
        }
        
        public int getContentIconGravity() {
            return this.mContentIconGravity;
        }
        
        public boolean getContentIntentAvailableOffline() {
            return (this.mFlags & 0x1) != 0x0;
        }
        
        public int getCustomContentHeight() {
            return this.mCustomContentHeight;
        }
        
        public int getCustomSizePreset() {
            return this.mCustomSizePreset;
        }
        
        public PendingIntent getDisplayIntent() {
            return this.mDisplayIntent;
        }
        
        public int getGravity() {
            return this.mGravity;
        }
        
        public boolean getHintAvoidBackgroundClipping() {
            return (this.mFlags & 0x10) != 0x0;
        }
        
        public boolean getHintHideIcon() {
            return (this.mFlags & 0x2) != 0x0;
        }
        
        public int getHintScreenTimeout() {
            return this.mHintScreenTimeout;
        }
        
        public boolean getHintShowBackgroundOnly() {
            return (this.mFlags & 0x4) != 0x0;
        }
        
        public List<Notification> getPages() {
            return this.mPages;
        }
        
        public boolean getStartScrollBottom() {
            return (this.mFlags & 0x8) != 0x0;
        }
        
        public WearableExtender setBackground(final Bitmap mBackground) {
            this.mBackground = mBackground;
            return this;
        }
        
        public WearableExtender setContentAction(final int mContentActionIndex) {
            this.mContentActionIndex = mContentActionIndex;
            return this;
        }
        
        public WearableExtender setContentIcon(final int mContentIcon) {
            this.mContentIcon = mContentIcon;
            return this;
        }
        
        public WearableExtender setContentIconGravity(final int mContentIconGravity) {
            this.mContentIconGravity = mContentIconGravity;
            return this;
        }
        
        public WearableExtender setContentIntentAvailableOffline(final boolean b) {
            this.setFlag(1, b);
            return this;
        }
        
        public WearableExtender setCustomContentHeight(final int mCustomContentHeight) {
            this.mCustomContentHeight = mCustomContentHeight;
            return this;
        }
        
        public WearableExtender setCustomSizePreset(final int mCustomSizePreset) {
            this.mCustomSizePreset = mCustomSizePreset;
            return this;
        }
        
        public WearableExtender setDisplayIntent(final PendingIntent mDisplayIntent) {
            this.mDisplayIntent = mDisplayIntent;
            return this;
        }
        
        public WearableExtender setGravity(final int mGravity) {
            this.mGravity = mGravity;
            return this;
        }
        
        public WearableExtender setHintAvoidBackgroundClipping(final boolean b) {
            this.setFlag(16, b);
            return this;
        }
        
        public WearableExtender setHintHideIcon(final boolean b) {
            this.setFlag(2, b);
            return this;
        }
        
        public WearableExtender setHintScreenTimeout(final int mHintScreenTimeout) {
            this.mHintScreenTimeout = mHintScreenTimeout;
            return this;
        }
        
        public WearableExtender setHintShowBackgroundOnly(final boolean b) {
            this.setFlag(4, b);
            return this;
        }
        
        public WearableExtender setStartScrollBottom(final boolean b) {
            this.setFlag(8, b);
            return this;
        }
    }
}
