package com.example.drawerpagedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.leoguo.drawerpage.DrawerPageActivity;


public class MainActivity extends DrawerPageActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		// Open next page, not necessary.
		findViewById(R.id.btn_next).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				openNextPage();
			}
		});
	}
	
	private void openNextPage(){
		Intent intent = new Intent(this, NextActivity.class);
		startActivity(intent);
	}

}
