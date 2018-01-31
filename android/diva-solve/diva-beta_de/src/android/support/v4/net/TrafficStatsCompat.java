// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.net;

import java.net.SocketException;
import java.net.Socket;
import android.os.Build$VERSION;

public class TrafficStatsCompat
{
    private static final TrafficStatsCompatImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 14) {
            IMPL = (TrafficStatsCompatImpl)new IcsTrafficStatsCompatImpl();
            return;
        }
        IMPL = (TrafficStatsCompatImpl)new BaseTrafficStatsCompatImpl();
    }
    
    public static void clearThreadStatsTag() {
        TrafficStatsCompat.IMPL.clearThreadStatsTag();
    }
    
    public static int getThreadStatsTag() {
        return TrafficStatsCompat.IMPL.getThreadStatsTag();
    }
    
    public static void incrementOperationCount(final int n) {
        TrafficStatsCompat.IMPL.incrementOperationCount(n);
    }
    
    public static void incrementOperationCount(final int n, final int n2) {
        TrafficStatsCompat.IMPL.incrementOperationCount(n, n2);
    }
    
    public static void setThreadStatsTag(final int threadStatsTag) {
        TrafficStatsCompat.IMPL.setThreadStatsTag(threadStatsTag);
    }
    
    public static void tagSocket(final Socket socket) throws SocketException {
        TrafficStatsCompat.IMPL.tagSocket(socket);
    }
    
    public static void untagSocket(final Socket socket) throws SocketException {
        TrafficStatsCompat.IMPL.untagSocket(socket);
    }
    
    static class BaseTrafficStatsCompatImpl implements TrafficStatsCompatImpl
    {
        private ThreadLocal<SocketTags> mThreadSocketTags;
        
        BaseTrafficStatsCompatImpl() {
            this.mThreadSocketTags = new ThreadLocal<SocketTags>() {
                @Override
                protected SocketTags initialValue() {
                    return new SocketTags();
                }
            };
        }
        
        @Override
        public void clearThreadStatsTag() {
            this.mThreadSocketTags.get().statsTag = -1;
        }
        
        @Override
        public int getThreadStatsTag() {
            return this.mThreadSocketTags.get().statsTag;
        }
        
        @Override
        public void incrementOperationCount(final int n) {
        }
        
        @Override
        public void incrementOperationCount(final int n, final int n2) {
        }
        
        @Override
        public void setThreadStatsTag(final int statsTag) {
            this.mThreadSocketTags.get().statsTag = statsTag;
        }
        
        @Override
        public void tagSocket(final Socket socket) {
        }
        
        @Override
        public void untagSocket(final Socket socket) {
        }
        
        private static class SocketTags
        {
            public int statsTag;
            
            private SocketTags() {
                this.statsTag = -1;
            }
        }
    }
    
    static class IcsTrafficStatsCompatImpl implements TrafficStatsCompatImpl
    {
        @Override
        public void clearThreadStatsTag() {
            TrafficStatsCompatIcs.clearThreadStatsTag();
        }
        
        @Override
        public int getThreadStatsTag() {
            return TrafficStatsCompatIcs.getThreadStatsTag();
        }
        
        @Override
        public void incrementOperationCount(final int n) {
            TrafficStatsCompatIcs.incrementOperationCount(n);
        }
        
        @Override
        public void incrementOperationCount(final int n, final int n2) {
            TrafficStatsCompatIcs.incrementOperationCount(n, n2);
        }
        
        @Override
        public void setThreadStatsTag(final int threadStatsTag) {
            TrafficStatsCompatIcs.setThreadStatsTag(threadStatsTag);
        }
        
        @Override
        public void tagSocket(final Socket socket) throws SocketException {
            TrafficStatsCompatIcs.tagSocket(socket);
        }
        
        @Override
        public void untagSocket(final Socket socket) throws SocketException {
            TrafficStatsCompatIcs.untagSocket(socket);
        }
    }
    
    interface TrafficStatsCompatImpl
    {
        void clearThreadStatsTag();
        
        int getThreadStatsTag();
        
        void incrementOperationCount(final int p0);
        
        void incrementOperationCount(final int p0, final int p1);
        
        void setThreadStatsTag(final int p0);
        
        void tagSocket(final Socket p0) throws SocketException;
        
        void untagSocket(final Socket p0) throws SocketException;
    }
}
