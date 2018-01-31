// 
// Decompiled by Procyon v0.5.30
// 

package jakhar.aseem.diva;

public class DivaJni
{
    private static final String soName = "divajni";
    
    static {
        System.loadLibrary("divajni");
    }
    
    public native int access(final String p0);
    
    public native int initiateLaunchSequence(final String p0);
}
