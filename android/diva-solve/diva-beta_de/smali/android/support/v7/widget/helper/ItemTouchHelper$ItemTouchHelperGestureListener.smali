.class Landroid/support/v7/widget/helper/ItemTouchHelper$ItemTouchHelperGestureListener;
.super Landroid/view/GestureDetector$SimpleOnGestureListener;
.source "ItemTouchHelper.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroid/support/v7/widget/helper/ItemTouchHelper;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "ItemTouchHelperGestureListener"
.end annotation


# instance fields
.field final synthetic this$0:Landroid/support/v7/widget/helper/ItemTouchHelper;


# direct methods
.method private constructor <init>(Landroid/support/v7/widget/helper/ItemTouchHelper;)V
    .locals 0

    .prologue
    .line 2181
    iput-object p1, p0, Landroid/support/v7/widget/helper/ItemTouchHelper$ItemTouchHelperGestureListener;->this$0:Landroid/support/v7/widget/helper/ItemTouchHelper;

    invoke-direct {p0}, Landroid/view/GestureDetector$SimpleOnGestureListener;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Landroid/support/v7/widget/helper/ItemTouchHelper;Landroid/support/v7/widget/helper/ItemTouchHelper$1;)V
    .locals 0
    .param p1, "x0"    # Landroid/support/v7/widget/helper/ItemTouchHelper;
    .param p2, "x1"    # Landroid/support/v7/widget/helper/ItemTouchHelper$1;

    .prologue
    .line 2181
    invoke-direct {p0, p1}, Landroid/support/v7/widget/helper/ItemTouchHelper$ItemTouchHelperGestureListener;-><init>(Landroid/support/v7/widget/helper/ItemTouchHelper;)V

    return-void
.end method


# virtual methods
.method public onDown(Landroid/view/MotionEvent;)Z
    .locals 1
    .param p1, "e"    # Landroid/view/MotionEvent;

    .prologue
    .line 2185
    const/4 v0, 0x1

    return v0
.end method

.method public onLongPress(Landroid/view/MotionEvent;)V
    .locals 9
    .param p1, "e"    # Landroid/view/MotionEvent;

    .prologue
    .line 2190
    iget-object v6, p0, Landroid/support/v7/widget/helper/ItemTouchHelper$ItemTouchHelperGestureListener;->this$0:Landroid/support/v7/widget/helper/ItemTouchHelper;

    invoke-static {v6, p1}, Landroid/support/v7/widget/helper/ItemTouchHelper;->access$2400(Landroid/support/v7/widget/helper/ItemTouchHelper;Landroid/view/MotionEvent;)Landroid/view/View;

    move-result-object v0

    .line 2191
    .local v0, "child":Landroid/view/View;
    if-eqz v0, :cond_0

    .line 2192
    iget-object v6, p0, Landroid/support/v7/widget/helper/ItemTouchHelper$ItemTouchHelperGestureListener;->this$0:Landroid/support/v7/widget/helper/ItemTouchHelper;

    invoke-static {v6}, Landroid/support/v7/widget/helper/ItemTouchHelper;->access$300(Landroid/support/v7/widget/helper/ItemTouchHelper;)Landroid/support/v7/widget/RecyclerView;

    move-result-object v6

    invoke-virtual {v6, v0}, Landroid/support/v7/widget/RecyclerView;->getChildViewHolder(Landroid/view/View;)Landroid/support/v7/widget/RecyclerView$ViewHolder;

    move-result-object v3

    .line 2193
    .local v3, "vh":Landroid/support/v7/widget/RecyclerView$ViewHolder;
    if-eqz v3, :cond_0

    .line 2194
    iget-object v6, p0, Landroid/support/v7/widget/helper/ItemTouchHelper$ItemTouchHelperGestureListener;->this$0:Landroid/support/v7/widget/helper/ItemTouchHelper;

    iget-object v6, v6, Landroid/support/v7/widget/helper/ItemTouchHelper;->mCallback:Landroid/support/v7/widget/helper/ItemTouchHelper$Callback;

    iget-object v7, p0, Landroid/support/v7/widget/helper/ItemTouchHelper$ItemTouchHelperGestureListener;->this$0:Landroid/support/v7/widget/helper/ItemTouchHelper;

    invoke-static {v7}, Landroid/support/v7/widget/helper/ItemTouchHelper;->access$300(Landroid/support/v7/widget/helper/ItemTouchHelper;)Landroid/support/v7/widget/RecyclerView;

    move-result-object v7

    invoke-static {v6, v7, v3}, Landroid/support/v7/widget/helper/ItemTouchHelper$Callback;->access$2100(Landroid/support/v7/widget/helper/ItemTouchHelper$Callback;Landroid/support/v7/widget/RecyclerView;Landroid/support/v7/widget/RecyclerView$ViewHolder;)Z

    move-result v6

    if-nez v6, :cond_1

    .line 2219
    .end local v3    # "vh":Landroid/support/v7/widget/RecyclerView$ViewHolder;
    :cond_0
    :goto_0
    return-void

    .line 2197
    .restart local v3    # "vh":Landroid/support/v7/widget/RecyclerView$ViewHolder;
    :cond_1
    const/4 v6, 0x0

    invoke-static {p1, v6}, Landroid/support/v4/view/MotionEventCompat;->getPointerId(Landroid/view/MotionEvent;I)I

    move-result v2

    .line 2201
    .local v2, "pointerId":I
    iget-object v6, p0, Landroid/support/v7/widget/helper/ItemTouchHelper$ItemTouchHelperGestureListener;->this$0:Landroid/support/v7/widget/helper/ItemTouchHelper;

    iget v6, v6, Landroid/support/v7/widget/helper/ItemTouchHelper;->mActivePointerId:I

    if-ne v2, v6, :cond_0

    .line 2202
    iget-object v6, p0, Landroid/support/v7/widget/helper/ItemTouchHelper$ItemTouchHelperGestureListener;->this$0:Landroid/support/v7/widget/helper/ItemTouchHelper;

    iget v6, v6, Landroid/support/v7/widget/helper/ItemTouchHelper;->mActivePointerId:I

    invoke-static {p1, v6}, Landroid/support/v4/view/MotionEventCompat;->findPointerIndex(Landroid/view/MotionEvent;I)I

    move-result v1

    .line 2204
    .local v1, "index":I
    invoke-static {p1, v1}, Landroid/support/v4/view/MotionEventCompat;->getX(Landroid/view/MotionEvent;I)F

    move-result v4

    .line 2205
    .local v4, "x":F
    invoke-static {p1, v1}, Landroid/support/v4/view/MotionEventCompat;->getY(Landroid/view/MotionEvent;I)F

    move-result v5

    .line 2206
    .local v5, "y":F
    iget-object v6, p0, Landroid/support/v7/widget/helper/ItemTouchHelper$ItemTouchHelperGestureListener;->this$0:Landroid/support/v7/widget/helper/ItemTouchHelper;

    iput v4, v6, Landroid/support/v7/widget/helper/ItemTouchHelper;->mInitialTouchX:F

    .line 2207
    iget-object v6, p0, Landroid/support/v7/widget/helper/ItemTouchHelper$ItemTouchHelperGestureListener;->this$0:Landroid/support/v7/widget/helper/ItemTouchHelper;

    iput v5, v6, Landroid/support/v7/widget/helper/ItemTouchHelper;->mInitialTouchY:F

    .line 2208
    iget-object v6, p0, Landroid/support/v7/widget/helper/ItemTouchHelper$ItemTouchHelperGestureListener;->this$0:Landroid/support/v7/widget/helper/ItemTouchHelper;

    iget-object v7, p0, Landroid/support/v7/widget/helper/ItemTouchHelper$ItemTouchHelperGestureListener;->this$0:Landroid/support/v7/widget/helper/ItemTouchHelper;

    const/4 v8, 0x0

    iput v8, v7, Landroid/support/v7/widget/helper/ItemTouchHelper;->mDy:F

    iput v8, v6, Landroid/support/v7/widget/helper/ItemTouchHelper;->mDx:F

    .line 2213
    iget-object v6, p0, Landroid/support/v7/widget/helper/ItemTouchHelper$ItemTouchHelperGestureListener;->this$0:Landroid/support/v7/widget/helper/ItemTouchHelper;

    iget-object v6, v6, Landroid/support/v7/widget/helper/ItemTouchHelper;->mCallback:Landroid/support/v7/widget/helper/ItemTouchHelper$Callback;

    invoke-virtual {v6}, Landroid/support/v7/widget/helper/ItemTouchHelper$Callback;->isLongPressDragEnabled()Z

    move-result v6

    if-eqz v6, :cond_0

    .line 2214
    iget-object v6, p0, Landroid/support/v7/widget/helper/ItemTouchHelper$ItemTouchHelperGestureListener;->this$0:Landroid/support/v7/widget/helper/ItemTouchHelper;

    const/4 v7, 0x2

    invoke-static {v6, v3, v7}, Landroid/support/v7/widget/helper/ItemTouchHelper;->access$800(Landroid/support/v7/widget/helper/ItemTouchHelper;Landroid/support/v7/widget/RecyclerView$ViewHolder;I)V

    goto :goto_0
.end method
