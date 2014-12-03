package com.leoguo.drawerpage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * DrawerPageLayout provide the sliding left or right feature. This layout can
 * has only one child View.
 * 
 * @author GL
 * 
 */
public class DrawerPageLayout extends FrameLayout {
	private static final float TOUCH_SLOP_SENSITIVITY = 1.f;
	private static final float CLOSE_THRESHOLD = 7.f / 16;
	private final ViewDragHelper mRightDragger;
	private OnSlidingListener mOnSlidingListener;
	private boolean mSlidingOutBegin = false;
	private Drawable mShadow;

	public DrawerPageLayout(Context context) {
		this(context, null);
	}

	public DrawerPageLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DrawerPageLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mRightDragger = ViewDragHelper.create(this, TOUCH_SLOP_SENSITIVITY,
				new ViewDragCallback());
		// Make sure the layout can be displayed within a developer tool.
		if (!isInEditMode()) {
			setShadow(R.drawable.edge_shadow);
		}
	}

	/**
	 * Set the resource id of the shadow drawable that will be drawn at the left
	 * edge of the layout. A default shadow will be used if a custom shadow is
	 * not set.
	 * 
	 * @param resId
	 *            the resource id of the shadow drawable.
	 */
	public void setShadow(int resId) {
		setShadow(getResources().getDrawable(resId));
	}

	/**
	 * Set the shadow drawable that will be drawn at the left edge of the
	 * layout. A default shadow will be used if a custom shadow is not set.
	 * 
	 * @param drawable
	 *            the shadow drawable
	 */
	public void setShadow(Drawable drawable) {
		mShadow = drawable;
		invalidate();
	}

	@Override
	protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
		final boolean result = super.drawChild(canvas, child, drawingTime);
		if (mShadow != null) {
			// Draw the shadow at the left edge of the layout.
			final int shadowWidth = mShadow.getIntrinsicWidth();
			final int left = child.getLeft();
			mShadow.setBounds(left - shadowWidth, child.getTop(), left,
					child.getBottom());
			mShadow.draw(canvas);
		}
		return result;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		final int action = MotionEventCompat.getActionMasked(ev);
		if (action == MotionEvent.ACTION_CANCEL
				|| action == MotionEvent.ACTION_UP) {
			mRightDragger.cancel();
			return false;
		}
		return mRightDragger.shouldInterceptTouchEvent(ev);
	}

	@Override
	public void computeScroll() {
		if (mRightDragger.continueSettling(true)) {
			invalidate();
		} else if (mSlidingOutBegin) {
			// Sliding process finished.
			mSlidingOutBegin = false;
			if (mOnSlidingListener != null) {
				mOnSlidingListener.onSlidingOut();
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mRightDragger.processTouchEvent(event);
		return true;
	}

	private class ViewDragCallback extends ViewDragHelper.Callback {

		@Override
		public boolean tryCaptureView(View child, int pointerId) {
			return true;
		}

		@Override
		public int clampViewPositionHorizontal(View child, int left, int dx) {
			int leftBound = getPaddingLeft();
			int rightBound = getWidth();
			int newLeft = Math.min(Math.max(left, leftBound), rightBound);
			return newLeft;
		}

		@Override
		public void onViewPositionChanged(View changedView, int left, int top,
				int dx, int dy) {
			invalidate();
		}

		@Override
		public void onViewReleased(View releasedChild, float xvel, float yvel) {
			int left = releasedChild.getLeft();
			if (left > getWidth() * CLOSE_THRESHOLD) {
				// Begin sliding out the View as it is slid more than half of
				// its width by user.
				mRightDragger.settleCapturedViewAt(getWidth(),
						releasedChild.getTop());
				mSlidingOutBegin = true;
			} else {
				// Begin sliding back to the original place as it is not slid to
				// the close threshold.
				mRightDragger.settleCapturedViewAt(getPaddingLeft(),
						releasedChild.getTop());
				mSlidingOutBegin = false;
			}
			invalidate();
		}

	}

	public void setOnSlidingListener(OnSlidingListener l) {
		mOnSlidingListener = l;
	}

	/**
	 * Listen to the sliding event.
	 * 
	 * @author GL
	 * 
	 */
	public interface OnSlidingListener {
		/**
		 * Notify that the layout will slide out, Activity that use this layout
		 * should be closed.
		 */
		public void onSlidingOut();
	}

}
