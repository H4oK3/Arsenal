// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.util.Iterator;
import java.util.Collection;
import android.support.v4.animation.AnimatorCompatHelper;
import java.util.List;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewCompat;
import java.util.ArrayList;

public class DefaultItemAnimator extends SimpleItemAnimator
{
    private static final boolean DEBUG = false;
    private ArrayList<ViewHolder> mAddAnimations;
    private ArrayList<ArrayList<ViewHolder>> mAdditionsList;
    private ArrayList<ViewHolder> mChangeAnimations;
    private ArrayList<ArrayList<ChangeInfo>> mChangesList;
    private ArrayList<ViewHolder> mMoveAnimations;
    private ArrayList<ArrayList<MoveInfo>> mMovesList;
    private ArrayList<ViewHolder> mPendingAdditions;
    private ArrayList<ChangeInfo> mPendingChanges;
    private ArrayList<MoveInfo> mPendingMoves;
    private ArrayList<ViewHolder> mPendingRemovals;
    private ArrayList<ViewHolder> mRemoveAnimations;
    
    public DefaultItemAnimator() {
        this.mPendingRemovals = new ArrayList<ViewHolder>();
        this.mPendingAdditions = new ArrayList<ViewHolder>();
        this.mPendingMoves = new ArrayList<MoveInfo>();
        this.mPendingChanges = new ArrayList<ChangeInfo>();
        this.mAdditionsList = new ArrayList<ArrayList<ViewHolder>>();
        this.mMovesList = new ArrayList<ArrayList<MoveInfo>>();
        this.mChangesList = new ArrayList<ArrayList<ChangeInfo>>();
        this.mAddAnimations = new ArrayList<ViewHolder>();
        this.mMoveAnimations = new ArrayList<ViewHolder>();
        this.mRemoveAnimations = new ArrayList<ViewHolder>();
        this.mChangeAnimations = new ArrayList<ViewHolder>();
    }
    
    private void animateAddImpl(final ViewHolder viewHolder) {
        final ViewPropertyAnimatorCompat animate = ViewCompat.animate(viewHolder.itemView);
        this.mAddAnimations.add(viewHolder);
        animate.alpha(1.0f).setDuration(((RecyclerView.ItemAnimator)this).getAddDuration()).setListener(new VpaListenerAdapter() {
            @Override
            public void onAnimationCancel(final View view) {
                ViewCompat.setAlpha(view, 1.0f);
            }
            
            @Override
            public void onAnimationEnd(final View view) {
                animate.setListener(null);
                DefaultItemAnimator.this.dispatchAddFinished(viewHolder);
                DefaultItemAnimator.this.mAddAnimations.remove(viewHolder);
                DefaultItemAnimator.this.dispatchFinishedWhenDone();
            }
            
            @Override
            public void onAnimationStart(final View view) {
                DefaultItemAnimator.this.dispatchAddStarting(viewHolder);
            }
        }).start();
    }
    
    private void animateChangeImpl(final ChangeInfo changeInfo) {
        final ViewHolder oldHolder = changeInfo.oldHolder;
        View itemView;
        if (oldHolder == null) {
            itemView = null;
        }
        else {
            itemView = oldHolder.itemView;
        }
        final ViewHolder newHolder = changeInfo.newHolder;
        View itemView2;
        if (newHolder != null) {
            itemView2 = newHolder.itemView;
        }
        else {
            itemView2 = null;
        }
        if (itemView != null) {
            final ViewPropertyAnimatorCompat setDuration = ViewCompat.animate(itemView).setDuration(((RecyclerView.ItemAnimator)this).getChangeDuration());
            this.mChangeAnimations.add(changeInfo.oldHolder);
            setDuration.translationX(changeInfo.toX - changeInfo.fromX);
            setDuration.translationY(changeInfo.toY - changeInfo.fromY);
            setDuration.alpha(0.0f).setListener(new VpaListenerAdapter() {
                @Override
                public void onAnimationEnd(final View view) {
                    setDuration.setListener(null);
                    ViewCompat.setAlpha(view, 1.0f);
                    ViewCompat.setTranslationX(view, 0.0f);
                    ViewCompat.setTranslationY(view, 0.0f);
                    DefaultItemAnimator.this.dispatchChangeFinished(changeInfo.oldHolder, true);
                    DefaultItemAnimator.this.mChangeAnimations.remove(changeInfo.oldHolder);
                    DefaultItemAnimator.this.dispatchFinishedWhenDone();
                }
                
                @Override
                public void onAnimationStart(final View view) {
                    DefaultItemAnimator.this.dispatchChangeStarting(changeInfo.oldHolder, true);
                }
            }).start();
        }
        if (itemView2 != null) {
            final ViewPropertyAnimatorCompat animate = ViewCompat.animate(itemView2);
            this.mChangeAnimations.add(changeInfo.newHolder);
            animate.translationX(0.0f).translationY(0.0f).setDuration(((RecyclerView.ItemAnimator)this).getChangeDuration()).alpha(1.0f).setListener(new VpaListenerAdapter() {
                @Override
                public void onAnimationEnd(final View view) {
                    animate.setListener(null);
                    ViewCompat.setAlpha(itemView2, 1.0f);
                    ViewCompat.setTranslationX(itemView2, 0.0f);
                    ViewCompat.setTranslationY(itemView2, 0.0f);
                    DefaultItemAnimator.this.dispatchChangeFinished(changeInfo.newHolder, false);
                    DefaultItemAnimator.this.mChangeAnimations.remove(changeInfo.newHolder);
                    DefaultItemAnimator.this.dispatchFinishedWhenDone();
                }
                
                @Override
                public void onAnimationStart(final View view) {
                    DefaultItemAnimator.this.dispatchChangeStarting(changeInfo.newHolder, false);
                }
            }).start();
        }
    }
    
    private void animateMoveImpl(final ViewHolder viewHolder, int n, int n2, final int n3, final int n4) {
        final View itemView = viewHolder.itemView;
        n = n3 - n;
        n2 = n4 - n2;
        if (n != 0) {
            ViewCompat.animate(itemView).translationX(0.0f);
        }
        if (n2 != 0) {
            ViewCompat.animate(itemView).translationY(0.0f);
        }
        final ViewPropertyAnimatorCompat animate = ViewCompat.animate(itemView);
        this.mMoveAnimations.add(viewHolder);
        animate.setDuration(((RecyclerView.ItemAnimator)this).getMoveDuration()).setListener(new VpaListenerAdapter() {
            @Override
            public void onAnimationCancel(final View view) {
                if (n != 0) {
                    ViewCompat.setTranslationX(view, 0.0f);
                }
                if (n2 != 0) {
                    ViewCompat.setTranslationY(view, 0.0f);
                }
            }
            
            @Override
            public void onAnimationEnd(final View view) {
                animate.setListener(null);
                DefaultItemAnimator.this.dispatchMoveFinished(viewHolder);
                DefaultItemAnimator.this.mMoveAnimations.remove(viewHolder);
                DefaultItemAnimator.this.dispatchFinishedWhenDone();
            }
            
            @Override
            public void onAnimationStart(final View view) {
                DefaultItemAnimator.this.dispatchMoveStarting(viewHolder);
            }
        }).start();
    }
    
    private void animateRemoveImpl(final ViewHolder viewHolder) {
        final ViewPropertyAnimatorCompat animate = ViewCompat.animate(viewHolder.itemView);
        this.mRemoveAnimations.add(viewHolder);
        animate.setDuration(((RecyclerView.ItemAnimator)this).getRemoveDuration()).alpha(0.0f).setListener(new VpaListenerAdapter() {
            @Override
            public void onAnimationEnd(final View view) {
                animate.setListener(null);
                ViewCompat.setAlpha(view, 1.0f);
                DefaultItemAnimator.this.dispatchRemoveFinished(viewHolder);
                DefaultItemAnimator.this.mRemoveAnimations.remove(viewHolder);
                DefaultItemAnimator.this.dispatchFinishedWhenDone();
            }
            
            @Override
            public void onAnimationStart(final View view) {
                DefaultItemAnimator.this.dispatchRemoveStarting(viewHolder);
            }
        }).start();
    }
    
    private void dispatchFinishedWhenDone() {
        if (!this.isRunning()) {
            ((RecyclerView.ItemAnimator)this).dispatchAnimationsFinished();
        }
    }
    
    private void endChangeAnimation(final List<ChangeInfo> list, final ViewHolder viewHolder) {
        for (int i = list.size() - 1; i >= 0; --i) {
            final ChangeInfo changeInfo = list.get(i);
            if (this.endChangeAnimationIfNecessary(changeInfo, viewHolder) && changeInfo.oldHolder == null && changeInfo.newHolder == null) {
                list.remove(changeInfo);
            }
        }
    }
    
    private void endChangeAnimationIfNecessary(final ChangeInfo changeInfo) {
        if (changeInfo.oldHolder != null) {
            this.endChangeAnimationIfNecessary(changeInfo, changeInfo.oldHolder);
        }
        if (changeInfo.newHolder != null) {
            this.endChangeAnimationIfNecessary(changeInfo, changeInfo.newHolder);
        }
    }
    
    private boolean endChangeAnimationIfNecessary(final ChangeInfo changeInfo, final ViewHolder viewHolder) {
        boolean b = false;
        if (changeInfo.newHolder == viewHolder) {
            changeInfo.newHolder = null;
        }
        else {
            if (changeInfo.oldHolder != viewHolder) {
                return false;
            }
            changeInfo.oldHolder = null;
            b = true;
        }
        ViewCompat.setAlpha(viewHolder.itemView, 1.0f);
        ViewCompat.setTranslationX(viewHolder.itemView, 0.0f);
        ViewCompat.setTranslationY(viewHolder.itemView, 0.0f);
        this.dispatchChangeFinished(viewHolder, b);
        return true;
    }
    
    private void resetAnimation(final ViewHolder viewHolder) {
        AnimatorCompatHelper.clearInterpolator(viewHolder.itemView);
        this.endAnimation(viewHolder);
    }
    
    @Override
    public boolean animateAdd(final ViewHolder viewHolder) {
        this.resetAnimation(viewHolder);
        ViewCompat.setAlpha(viewHolder.itemView, 0.0f);
        this.mPendingAdditions.add(viewHolder);
        return true;
    }
    
    @Override
    public boolean animateChange(final ViewHolder viewHolder, final ViewHolder viewHolder2, final int n, final int n2, final int n3, final int n4) {
        if (viewHolder == viewHolder2) {
            return this.animateMove(viewHolder, n, n2, n3, n4);
        }
        final float translationX = ViewCompat.getTranslationX(viewHolder.itemView);
        final float translationY = ViewCompat.getTranslationY(viewHolder.itemView);
        final float alpha = ViewCompat.getAlpha(viewHolder.itemView);
        this.resetAnimation(viewHolder);
        final int n5 = (int)(n3 - n - translationX);
        final int n6 = (int)(n4 - n2 - translationY);
        ViewCompat.setTranslationX(viewHolder.itemView, translationX);
        ViewCompat.setTranslationY(viewHolder.itemView, translationY);
        ViewCompat.setAlpha(viewHolder.itemView, alpha);
        if (viewHolder2 != null) {
            this.resetAnimation(viewHolder2);
            ViewCompat.setTranslationX(viewHolder2.itemView, -n5);
            ViewCompat.setTranslationY(viewHolder2.itemView, -n6);
            ViewCompat.setAlpha(viewHolder2.itemView, 0.0f);
        }
        this.mPendingChanges.add(new ChangeInfo(viewHolder, viewHolder2, n, n2, n3, n4));
        return true;
    }
    
    @Override
    public boolean animateMove(final ViewHolder viewHolder, int n, int n2, final int n3, final int n4) {
        final View itemView = viewHolder.itemView;
        n += (int)ViewCompat.getTranslationX(viewHolder.itemView);
        n2 += (int)ViewCompat.getTranslationY(viewHolder.itemView);
        this.resetAnimation(viewHolder);
        final int n5 = n3 - n;
        final int n6 = n4 - n2;
        if (n5 == 0 && n6 == 0) {
            this.dispatchMoveFinished(viewHolder);
            return false;
        }
        if (n5 != 0) {
            ViewCompat.setTranslationX(itemView, -n5);
        }
        if (n6 != 0) {
            ViewCompat.setTranslationY(itemView, -n6);
        }
        this.mPendingMoves.add(new MoveInfo(viewHolder, n, n2, n3, n4));
        return true;
    }
    
    @Override
    public boolean animateRemove(final ViewHolder viewHolder) {
        this.resetAnimation(viewHolder);
        this.mPendingRemovals.add(viewHolder);
        return true;
    }
    
    void cancelAll(final List<ViewHolder> list) {
        for (int i = list.size() - 1; i >= 0; --i) {
            ViewCompat.animate(list.get(i).itemView).cancel();
        }
    }
    
    @Override
    public void endAnimation(final ViewHolder viewHolder) {
        final View itemView = viewHolder.itemView;
        ViewCompat.animate(itemView).cancel();
        for (int i = this.mPendingMoves.size() - 1; i >= 0; --i) {
            if (this.mPendingMoves.get(i).holder == viewHolder) {
                ViewCompat.setTranslationY(itemView, 0.0f);
                ViewCompat.setTranslationX(itemView, 0.0f);
                this.dispatchMoveFinished(viewHolder);
                this.mPendingMoves.remove(i);
            }
        }
        this.endChangeAnimation(this.mPendingChanges, viewHolder);
        if (this.mPendingRemovals.remove(viewHolder)) {
            ViewCompat.setAlpha(itemView, 1.0f);
            this.dispatchRemoveFinished(viewHolder);
        }
        if (this.mPendingAdditions.remove(viewHolder)) {
            ViewCompat.setAlpha(itemView, 1.0f);
            this.dispatchAddFinished(viewHolder);
        }
        for (int j = this.mChangesList.size() - 1; j >= 0; --j) {
            final ArrayList<ChangeInfo> list = this.mChangesList.get(j);
            this.endChangeAnimation(list, viewHolder);
            if (list.isEmpty()) {
                this.mChangesList.remove(j);
            }
        }
        for (int k = this.mMovesList.size() - 1; k >= 0; --k) {
            final ArrayList<MoveInfo> list2 = this.mMovesList.get(k);
            int l = list2.size() - 1;
            while (l >= 0) {
                if (list2.get(l).holder == viewHolder) {
                    ViewCompat.setTranslationY(itemView, 0.0f);
                    ViewCompat.setTranslationX(itemView, 0.0f);
                    this.dispatchMoveFinished(viewHolder);
                    list2.remove(l);
                    if (list2.isEmpty()) {
                        this.mMovesList.remove(k);
                        break;
                    }
                    break;
                }
                else {
                    --l;
                }
            }
        }
        for (int n = this.mAdditionsList.size() - 1; n >= 0; --n) {
            final ArrayList<ViewHolder> list3 = this.mAdditionsList.get(n);
            if (list3.remove(viewHolder)) {
                ViewCompat.setAlpha(itemView, 1.0f);
                this.dispatchAddFinished(viewHolder);
                if (list3.isEmpty()) {
                    this.mAdditionsList.remove(n);
                }
            }
        }
        if (this.mRemoveAnimations.remove(viewHolder)) {}
        if (this.mAddAnimations.remove(viewHolder)) {}
        if (this.mChangeAnimations.remove(viewHolder)) {}
        if (this.mMoveAnimations.remove(viewHolder)) {}
        this.dispatchFinishedWhenDone();
    }
    
    @Override
    public void endAnimations() {
        for (int i = this.mPendingMoves.size() - 1; i >= 0; --i) {
            final MoveInfo moveInfo = this.mPendingMoves.get(i);
            final View itemView = moveInfo.holder.itemView;
            ViewCompat.setTranslationY(itemView, 0.0f);
            ViewCompat.setTranslationX(itemView, 0.0f);
            this.dispatchMoveFinished(moveInfo.holder);
            this.mPendingMoves.remove(i);
        }
        for (int j = this.mPendingRemovals.size() - 1; j >= 0; --j) {
            this.dispatchRemoveFinished(this.mPendingRemovals.get(j));
            this.mPendingRemovals.remove(j);
        }
        for (int k = this.mPendingAdditions.size() - 1; k >= 0; --k) {
            final ViewHolder viewHolder = this.mPendingAdditions.get(k);
            ViewCompat.setAlpha(viewHolder.itemView, 1.0f);
            this.dispatchAddFinished(viewHolder);
            this.mPendingAdditions.remove(k);
        }
        for (int l = this.mPendingChanges.size() - 1; l >= 0; --l) {
            this.endChangeAnimationIfNecessary(this.mPendingChanges.get(l));
        }
        this.mPendingChanges.clear();
        if (!this.isRunning()) {
            return;
        }
        for (int n = this.mMovesList.size() - 1; n >= 0; --n) {
            final ArrayList<MoveInfo> list = this.mMovesList.get(n);
            for (int n2 = list.size() - 1; n2 >= 0; --n2) {
                final MoveInfo moveInfo2 = list.get(n2);
                final View itemView2 = moveInfo2.holder.itemView;
                ViewCompat.setTranslationY(itemView2, 0.0f);
                ViewCompat.setTranslationX(itemView2, 0.0f);
                this.dispatchMoveFinished(moveInfo2.holder);
                list.remove(n2);
                if (list.isEmpty()) {
                    this.mMovesList.remove(list);
                }
            }
        }
        for (int n3 = this.mAdditionsList.size() - 1; n3 >= 0; --n3) {
            final ArrayList<ViewHolder> list2 = this.mAdditionsList.get(n3);
            for (int n4 = list2.size() - 1; n4 >= 0; --n4) {
                final ViewHolder viewHolder2 = list2.get(n4);
                ViewCompat.setAlpha(viewHolder2.itemView, 1.0f);
                this.dispatchAddFinished(viewHolder2);
                list2.remove(n4);
                if (list2.isEmpty()) {
                    this.mAdditionsList.remove(list2);
                }
            }
        }
        for (int n5 = this.mChangesList.size() - 1; n5 >= 0; --n5) {
            final ArrayList<ChangeInfo> list3 = this.mChangesList.get(n5);
            for (int n6 = list3.size() - 1; n6 >= 0; --n6) {
                this.endChangeAnimationIfNecessary(list3.get(n6));
                if (list3.isEmpty()) {
                    this.mChangesList.remove(list3);
                }
            }
        }
        this.cancelAll(this.mRemoveAnimations);
        this.cancelAll(this.mMoveAnimations);
        this.cancelAll(this.mAddAnimations);
        this.cancelAll(this.mChangeAnimations);
        ((RecyclerView.ItemAnimator)this).dispatchAnimationsFinished();
    }
    
    @Override
    public boolean isRunning() {
        return !this.mPendingAdditions.isEmpty() || !this.mPendingChanges.isEmpty() || !this.mPendingMoves.isEmpty() || !this.mPendingRemovals.isEmpty() || !this.mMoveAnimations.isEmpty() || !this.mRemoveAnimations.isEmpty() || !this.mAddAnimations.isEmpty() || !this.mChangeAnimations.isEmpty() || !this.mMovesList.isEmpty() || !this.mAdditionsList.isEmpty() || !this.mChangesList.isEmpty();
    }
    
    @Override
    public void runPendingAnimations() {
        boolean b;
        if (!this.mPendingRemovals.isEmpty()) {
            b = true;
        }
        else {
            b = false;
        }
        boolean b2;
        if (!this.mPendingMoves.isEmpty()) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        boolean b3;
        if (!this.mPendingChanges.isEmpty()) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        boolean b4;
        if (!this.mPendingAdditions.isEmpty()) {
            b4 = true;
        }
        else {
            b4 = false;
        }
        if (b || b2 || b4 || b3) {
            final Iterator<ViewHolder> iterator = this.mPendingRemovals.iterator();
            while (iterator.hasNext()) {
                this.animateRemoveImpl(iterator.next());
            }
            this.mPendingRemovals.clear();
            if (b2) {
                final ArrayList<MoveInfo> list = new ArrayList<MoveInfo>();
                list.addAll(this.mPendingMoves);
                this.mMovesList.add(list);
                this.mPendingMoves.clear();
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        for (final MoveInfo moveInfo : list) {
                            DefaultItemAnimator.this.animateMoveImpl(moveInfo.holder, moveInfo.fromX, moveInfo.fromY, moveInfo.toX, moveInfo.toY);
                        }
                        list.clear();
                        DefaultItemAnimator.this.mMovesList.remove(list);
                    }
                };
                if (b) {
                    ViewCompat.postOnAnimationDelayed(list.get(0).holder.itemView, runnable, ((RecyclerView.ItemAnimator)this).getRemoveDuration());
                }
                else {
                    runnable.run();
                }
            }
            if (b3) {
                final ArrayList<ChangeInfo> list2 = new ArrayList<ChangeInfo>();
                list2.addAll(this.mPendingChanges);
                this.mChangesList.add(list2);
                this.mPendingChanges.clear();
                final Runnable runnable2 = new Runnable() {
                    @Override
                    public void run() {
                        final Iterator<ChangeInfo> iterator = list2.iterator();
                        while (iterator.hasNext()) {
                            DefaultItemAnimator.this.animateChangeImpl((ChangeInfo)iterator.next());
                        }
                        list2.clear();
                        DefaultItemAnimator.this.mChangesList.remove(list2);
                    }
                };
                if (b) {
                    ViewCompat.postOnAnimationDelayed(list2.get(0).oldHolder.itemView, runnable2, ((RecyclerView.ItemAnimator)this).getRemoveDuration());
                }
                else {
                    runnable2.run();
                }
            }
            if (b4) {
                final ArrayList<ViewHolder> list3 = new ArrayList<ViewHolder>();
                list3.addAll(this.mPendingAdditions);
                this.mAdditionsList.add(list3);
                this.mPendingAdditions.clear();
                final Runnable runnable3 = new Runnable() {
                    @Override
                    public void run() {
                        final Iterator<ViewHolder> iterator = list3.iterator();
                        while (iterator.hasNext()) {
                            DefaultItemAnimator.this.animateAddImpl(iterator.next());
                        }
                        list3.clear();
                        DefaultItemAnimator.this.mAdditionsList.remove(list3);
                    }
                };
                if (b || b2 || b3) {
                    long removeDuration;
                    if (b) {
                        removeDuration = ((RecyclerView.ItemAnimator)this).getRemoveDuration();
                    }
                    else {
                        removeDuration = 0L;
                    }
                    long moveDuration;
                    if (b2) {
                        moveDuration = ((RecyclerView.ItemAnimator)this).getMoveDuration();
                    }
                    else {
                        moveDuration = 0L;
                    }
                    long changeDuration;
                    if (b3) {
                        changeDuration = ((RecyclerView.ItemAnimator)this).getChangeDuration();
                    }
                    else {
                        changeDuration = 0L;
                    }
                    ViewCompat.postOnAnimationDelayed(list3.get(0).itemView, runnable3, removeDuration + Math.max(moveDuration, changeDuration));
                    return;
                }
                runnable3.run();
            }
        }
    }
    
    private static class ChangeInfo
    {
        public int fromX;
        public int fromY;
        public ViewHolder newHolder;
        public ViewHolder oldHolder;
        public int toX;
        public int toY;
        
        private ChangeInfo(final ViewHolder oldHolder, final ViewHolder newHolder) {
            this.oldHolder = oldHolder;
            this.newHolder = newHolder;
        }
        
        private ChangeInfo(final ViewHolder viewHolder, final ViewHolder viewHolder2, final int fromX, final int fromY, final int toX, final int toY) {
            this(viewHolder, viewHolder2);
            this.fromX = fromX;
            this.fromY = fromY;
            this.toX = toX;
            this.toY = toY;
        }
        
        @Override
        public String toString() {
            return "ChangeInfo{oldHolder=" + this.oldHolder + ", newHolder=" + this.newHolder + ", fromX=" + this.fromX + ", fromY=" + this.fromY + ", toX=" + this.toX + ", toY=" + this.toY + '}';
        }
    }
    
    private static class MoveInfo
    {
        public int fromX;
        public int fromY;
        public ViewHolder holder;
        public int toX;
        public int toY;
        
        private MoveInfo(final ViewHolder holder, final int fromX, final int fromY, final int toX, final int toY) {
            this.holder = holder;
            this.fromX = fromX;
            this.fromY = fromY;
            this.toX = toX;
            this.toY = toY;
        }
    }
    
    private static class VpaListenerAdapter implements ViewPropertyAnimatorListener
    {
        @Override
        public void onAnimationCancel(final View view) {
        }
        
        @Override
        public void onAnimationEnd(final View view) {
        }
        
        @Override
        public void onAnimationStart(final View view) {
        }
    }
}
