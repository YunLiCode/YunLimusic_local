package com.yusun.music.main.adapter;



import com.yusun.music.tools.ImageUtil;

import com.yusun.music.R;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	private int currentId=-1;
    private int the_diskuan,the_disgao;
	/**
	 * 皮肤预览资源的ID数组
	 * */
	
	
	public ImageAdapter(Context mContext,int currentId){
		this.mContext=mContext;
		this.currentId=currentId;
		
		WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);
		the_diskuan=(int) ((outMetrics.widthPixels/3)-10);
		the_disgao=(int) (the_diskuan*1.7);
	}
	public void setCurrentId(int currentId){
		this.currentId=currentId;
		notifyDataSetChanged();
	}


	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView view=null;
		if(convertView==null){
			view=new ImageView(mContext);
			view.setLayoutParams(new GridView.LayoutParams(the_diskuan,the_disgao));
			view.setScaleType(ImageView.ScaleType.FIT_CENTER);

			
		}else{
			view=(ImageView) convertView;
		}
		return view;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
