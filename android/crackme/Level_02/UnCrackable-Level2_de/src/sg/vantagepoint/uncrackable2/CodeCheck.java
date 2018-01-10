// 
// Decompiled by Procyon v0.5.30
// 

package sg.vantagepoint.uncrackable2;

public class CodeCheck
{
    private native boolean bar(final byte[] p0);
    
    public boolean a(final String s) {
        return this.bar(s.getBytes());
    }
}
