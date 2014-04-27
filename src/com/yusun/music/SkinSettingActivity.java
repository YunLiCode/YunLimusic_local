package com.yusun.music;



import com.yusun.music.R;
import com.yusun.music.R.string;
import com.yusun.music.main.adapter.ImageAdapter;
import com.yusun.music.tools.Setting;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class SkinSettingActivity extends SettingActivity {
	private GridView gv_skin;
	private ImageAdapter adapter;
	private Setting mSetting;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏显示
		this.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
     	resultCode=2;
		setBackButton();
		setTopTitle(getResources().getString(R.string.skin_settings));
		
		mSetting=new Setting(this, true);
		
		gv_skin.setAdapter(adapter);
		gv_skin.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
				//更新GridView
				adapter.setCurrentId(position);
				//更新背景图片
				
			}
		});
		

	}
	
}
