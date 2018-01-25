// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

@Deprecated
public abstract class TransportController
{
    @Deprecated
    public abstract int getBufferPercentage();
    
    @Deprecated
    public abstract long getCurrentPosition();
    
    @Deprecated
    public abstract long getDuration();
    
    @Deprecated
    public abstract int getTransportControlFlags();
    
    @Deprecated
    public abstract boolean isPlaying();
    
    @Deprecated
    public abstract void pausePlaying();
    
    @Deprecated
    public abstract void registerStateListener(final TransportStateListener p0);
    
    @Deprecated
    public abstract void seekTo(final long p0);
    
    @Deprecated
    public abstract void startPlaying();
    
    @Deprecated
    public abstract void stopPlaying();
    
    @Deprecated
    public abstract void unregisterStateListener(final TransportStateListener p0);
}
