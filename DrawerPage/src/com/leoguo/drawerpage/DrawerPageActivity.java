package com.leoguo.drawerpage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.leoguo.drawerpage.DrawerPageLayout.OnSlidingListener;

/**
 * DrawerPageActivity is an Activity whose Content View is like a drawer which
 * can be slid right or left to show the Activity below it or to close itself.
 * If you slide it to the right NO more than half of the screen width, you can
 * see the the Activity below it, and it will bounce back after you release it.
 * If you slide it to the right more than half of the screen width, it will
 * slide out and close the Activity after you release it.
 * 
 * @author GL
 * 
 */
public class DrawerPageActivity extends Activity implements OnSlidingListener {
	private DrawerPageLayout mContentParent;
	private final ViewGroup.LayoutParams MATCH_PARENT_LP = new ViewGroup.LayoutParams(
			ViewGroup.LayoutParams.MATCH_PARENT,
			ViewGroup.LayoutParams.MATCH_PARENT);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContentParent = new DrawerPageLayout(this);
		mContentParent.setOnSlidingListener(this);
		super.setContentView(mContentParent, MATCH_PARENT_LP);
	}

	@Override
	public void setContentView(int layoutResID) {
		if (mContentParent == null) {
			throw new IllegalStateException("onCreate() method is not called.");
		}
		mContentParent.removeAllViews();
		getLayoutInflater().inflate(layoutResID, mContentParent);
	}

	@Override
	public void setContentView(View view) {
		setContentView(view, MATCH_PARENT_LP);
	}

	@Override
	public void setContentView(View view, LayoutParams params) {
		if (mContentParent == null) {
			throw new IllegalStateException("onCreate() method is not called.");
		}
		mContentParent.removeAllViews();
		mContentParent.addView(view, params);
	}

	@Override
	public void onSlidingOut() {
		// Close the Activity as user sliding out.
		finish();
	}

}
