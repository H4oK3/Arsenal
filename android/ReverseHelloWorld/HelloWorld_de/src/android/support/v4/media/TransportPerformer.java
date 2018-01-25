// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.view.KeyEvent;
import android.os.SystemClock;

@Deprecated
public abstract class TransportPerformer
{
    static final int AUDIOFOCUS_GAIN = 1;
    static final int AUDIOFOCUS_GAIN_TRANSIENT = 2;
    static final int AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK = 3;
    static final int AUDIOFOCUS_LOSS = -1;
    static final int AUDIOFOCUS_LOSS_TRANSIENT = -2;
    static final int AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK = -3;
    
    @Deprecated
    public void onAudioFocusChange(int n) {
        final int n2 = 0;
        switch (n) {
            default: {
                n = n2;
                break;
            }
            case -1: {
                n = 127;
                break;
            }
        }
        if (n != 0) {
            final long uptimeMillis = SystemClock.uptimeMillis();
            this.onMediaButtonDown(n, new KeyEvent(uptimeMillis, uptimeMillis, 0, n, 0));
            this.onMediaButtonUp(n, new KeyEvent(uptimeMillis, uptimeMillis, 1, n, 0));
        }
    }
    
    @Deprecated
    public int onGetBufferPercentage() {
        return 100;
    }
    
    @Deprecated
    public abstract long onGetCurrentPosition();
    
    @Deprecated
    public abstract long onGetDuration();
    
    @Deprecated
    public int onGetTransportControlFlags() {
        return 60;
    }
    
    @Deprecated
    public abstract boolean onIsPlaying();
    
    @Deprecated
    public boolean onMediaButtonDown(final int n, final KeyEvent keyEvent) {
        switch (n) {
            default: {
                return true;
            }
            case 126: {
                this.onStart();
                return true;
            }
            case 127: {
                this.onPause();
                return true;
            }
            case 86: {
                this.onStop();
                return true;
            }
            case 79:
            case 85: {
                if (this.onIsPlaying()) {
                    this.onPause();
                    return true;
                }
                this.onStart();
                return true;
            }
        }
    }
    
    @Deprecated
    public boolean onMediaButtonUp(final int n, final KeyEvent keyEvent) {
        return true;
    }
    
    @Deprecated
    public abstract void onPause();
    
    @Deprecated
    public abstract void onSeekTo(final long p0);
    
    @Deprecated
    public abstract void onStart();
    
    @Deprecated
    public abstract void onStop();
}
