package com.example.drawerpagedemo;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.leoguo.drawerpage.DrawerPageActivity;

public class NextActivity extends DrawerPageActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.next_activity);

		// Set back button that is located on the title bar. Not necessary.
		findViewById(R.id.tv_back).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

}
