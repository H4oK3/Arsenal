// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.ViewConfiguration;
import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.view.accessibility.AccessibilityManager;
import android.view.MotionEvent;
import android.content.res.Resources;
import android.content.Context;
import android.graphics.Rect;
import android.widget.Toast;
import android.view.View$OnHoverListener;
import android.view.View$OnLongClickListener;
import android.text.TextUtils;
import android.view.View;
import android.support.annotation.RequiresApi;

@RequiresApi(14)
class ViewCompatICS
{
    public static void setTooltipText(final View view, final CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            view.setOnLongClickListener((View$OnLongClickListener)null);
            view.setLongClickable(false);
            view.setOnHoverListener((View$OnHoverListener)null);
            return;
        }
        new TooltipHandler(view, charSequence);
    }
    
    private static class TooltipHandler implements View$OnLongClickListener, View$OnHoverListener
    {
        private final View mAnchor;
        private final Runnable mShowRunnable;
        private Toast mTooltip;
        private final CharSequence mTooltipText;
        
        TooltipHandler(final View mAnchor, final CharSequence mTooltipText) {
            this.mShowRunnable = new Runnable() {
                @Override
                public void run() {
                    TooltipHandler.this.show(1);
                }
            };
            this.mAnchor = mAnchor;
            this.mTooltipText = mTooltipText;
            this.mAnchor.setOnLongClickListener((View$OnLongClickListener)this);
            this.mAnchor.setOnHoverListener((View$OnHoverListener)this);
        }
        
        private void hide() {
            if (this.mTooltip != null) {
                this.mTooltip.cancel();
                this.mTooltip = null;
            }
            this.mAnchor.getHandler().removeCallbacks(this.mShowRunnable);
        }
        
        private void show(final int n) {
            final Context context = this.mAnchor.getContext();
            final Resources resources = context.getResources();
            final int widthPixels = resources.getDisplayMetrics().widthPixels;
            final int heightPixels = resources.getDisplayMetrics().heightPixels;
            final Rect rect = new Rect();
            this.mAnchor.getWindowVisibleDisplayFrame(rect);
            if (rect.left < 0 && rect.top < 0) {
                final int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
                int dimensionPixelSize;
                if (identifier > 0) {
                    dimensionPixelSize = resources.getDimensionPixelSize(identifier);
                }
                else {
                    dimensionPixelSize = 0;
                }
                rect.set(0, dimensionPixelSize, widthPixels, heightPixels);
            }
            final int[] array = new int[2];
            this.mAnchor.getLocationOnScreen(array);
            int n3;
            final int n2 = n3 = array[0] + this.mAnchor.getWidth() / 2;
            if (ViewCompat.getLayoutDirection(this.mAnchor) == 0) {
                n3 = widthPixels - n2;
            }
            final int n4 = array[1];
            this.hide();
            this.mTooltip = Toast.makeText(context, this.mTooltipText, n);
            if (n4 < rect.height() * 0.8) {
                this.mTooltip.setGravity(8388661, n3, this.mAnchor.getHeight() + n4 - rect.top);
            }
            else {
                this.mTooltip.setGravity(8388693, n3, rect.bottom - n4);
            }
            this.mTooltip.show();
        }
        
        public boolean onHover(final View view, final MotionEvent motionEvent) {
            final AccessibilityManager accessibilityManager = (AccessibilityManager)this.mAnchor.getContext().getSystemService("accessibility");
            if (!accessibilityManager.isEnabled() || !AccessibilityManagerCompat.isTouchExplorationEnabled(accessibilityManager)) {
                final int action = motionEvent.getAction();
                if (action == 7) {
                    this.hide();
                    this.mAnchor.getHandler().postDelayed(this.mShowRunnable, (long)ViewConfiguration.getLongPressTimeout());
                    return false;
                }
                if (action == 10) {
                    this.hide();
                    return false;
                }
            }
            return false;
        }
        
        public boolean onLongClick(final View view) {
            this.show(0);
            return true;
        }
    }
}
