// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.graphics.Canvas;
import android.graphics.Shader;
import android.graphics.Shader$TileMode;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.os.Build$VERSION;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.graphics.drawable.shapes.Shape;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.content.Context;
import android.view.animation.Animation$AnimationListener;
import android.widget.ImageView;

class CircleImageView extends ImageView
{
    private static final int FILL_SHADOW_COLOR = 1023410176;
    private static final int KEY_SHADOW_COLOR = 503316480;
    private static final int SHADOW_ELEVATION = 4;
    private static final float SHADOW_RADIUS = 3.5f;
    private static final float X_OFFSET = 0.0f;
    private static final float Y_OFFSET = 1.75f;
    private Animation$AnimationListener mListener;
    private int mShadowRadius;
    
    public CircleImageView(final Context context, final int color, final float n) {
        super(context);
        final float density = this.getContext().getResources().getDisplayMetrics().density;
        final int n2 = (int)(n * density * 2.0f);
        final int n3 = (int)(1.75f * density);
        final int n4 = (int)(0.0f * density);
        this.mShadowRadius = (int)(3.5f * density);
        ShapeDrawable backgroundDrawable;
        if (this.elevationSupported()) {
            backgroundDrawable = new ShapeDrawable((Shape)new OvalShape());
            ViewCompat.setElevation((View)this, 4.0f * density);
        }
        else {
            backgroundDrawable = new ShapeDrawable((Shape)new OvalShadow(this.mShadowRadius, n2));
            ViewCompat.setLayerType((View)this, 1, backgroundDrawable.getPaint());
            backgroundDrawable.getPaint().setShadowLayer((float)this.mShadowRadius, (float)n4, (float)n3, 503316480);
            final int mShadowRadius = this.mShadowRadius;
            this.setPadding(mShadowRadius, mShadowRadius, mShadowRadius, mShadowRadius);
        }
        backgroundDrawable.getPaint().setColor(color);
        this.setBackgroundDrawable((Drawable)backgroundDrawable);
    }
    
    private boolean elevationSupported() {
        return Build$VERSION.SDK_INT >= 21;
    }
    
    public void onAnimationEnd() {
        super.onAnimationEnd();
        if (this.mListener != null) {
            this.mListener.onAnimationEnd(this.getAnimation());
        }
    }
    
    public void onAnimationStart() {
        super.onAnimationStart();
        if (this.mListener != null) {
            this.mListener.onAnimationStart(this.getAnimation());
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
        if (!this.elevationSupported()) {
            this.setMeasuredDimension(this.getMeasuredWidth() + this.mShadowRadius * 2, this.getMeasuredHeight() + this.mShadowRadius * 2);
        }
    }
    
    public void setAnimationListener(final Animation$AnimationListener mListener) {
        this.mListener = mListener;
    }
    
    public void setBackgroundColor(final int color) {
        if (this.getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable)this.getBackground()).getPaint().setColor(color);
        }
    }
    
    public void setBackgroundColorRes(final int n) {
        this.setBackgroundColor(this.getContext().getResources().getColor(n));
    }
    
    private class OvalShadow extends OvalShape
    {
        private int mCircleDiameter;
        private RadialGradient mRadialGradient;
        private Paint mShadowPaint;
        
        public OvalShadow(final int n, final int mCircleDiameter) {
            this.mShadowPaint = new Paint();
            CircleImageView.this.mShadowRadius = n;
            this.mCircleDiameter = mCircleDiameter;
            this.mRadialGradient = new RadialGradient((float)(this.mCircleDiameter / 2), (float)(this.mCircleDiameter / 2), (float)CircleImageView.this.mShadowRadius, new int[] { 1023410176, 0 }, (float[])null, Shader$TileMode.CLAMP);
            this.mShadowPaint.setShader((Shader)this.mRadialGradient);
        }
        
        public void draw(final Canvas canvas, final Paint paint) {
            final int width = CircleImageView.this.getWidth();
            final int height = CircleImageView.this.getHeight();
            canvas.drawCircle((float)(width / 2), (float)(height / 2), (float)(this.mCircleDiameter / 2 + CircleImageView.this.mShadowRadius), this.mShadowPaint);
            canvas.drawCircle((float)(width / 2), (float)(height / 2), (float)(this.mCircleDiameter / 2), paint);
        }
    }
}
