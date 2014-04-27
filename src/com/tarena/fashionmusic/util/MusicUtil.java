package com.tarena.fashionmusic.util;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;
import com.yusun.music.R;
import com.yusun.music.entry.Music;
//YUN ADDED

public class MusicUtil extends Activity {
	private Context context;
	private ImageView yun_image;
	public MusicUtil(Context context) {
		super();
		this.context = context;

	}

	// 获取音乐文件
	public List<Music> getMusic() {
		List<Music> musics = new ArrayList<Music>();
		ContentResolver cr = context.getContentResolver();
		Cursor c = cr.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null,
				null, null, null);
		if (c != null) {
			while (c.moveToNext()) {
				Music music = new Music();
				int id = c.getInt(c
						.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
				String name = c.getString(c
						.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
				String path = c.getString(c
						.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
			
				music.setId(id);
				music.setMusicName(name);
				music.setMusicPath(path);
				
				musics.add(music);

			}

		}

		return musics;

	}

	// 进度转换成时间
	public static String PostionToTime(int time) {
		time /= 1000;
		int minute = time / 60;
		int hour = minute / 60;
		int sencond = time % 60;
		return String.format("%02d:%02d", minute, sencond);

	}

	// 获取音乐图片路径

	private String getAlbumArt(int album_id) {
		String mUriAlbums = "content://media/external/audio/albums";
		String[] projection = new String[] { "album_art" };
		Cursor cur = context.getContentResolver().query(
				Uri.parse(mUriAlbums + "/" + Integer.toString(album_id)),
				projection, null, null, null);
		String album_art = null;
		if (cur.getCount() > 0 && cur.getColumnCount() > 0) {
			cur.moveToNext();
			album_art = cur.getString(0);
		}
		cur.close();
		cur = null;
		return album_art;
	}

	// 获取音乐图片
	public BitmapDrawable getImage(int id)   {
		String albumArt = getAlbumArt(id);
		Bitmap bm = null;
		BitmapDrawable bmpDraw = null;
		if (albumArt == null) {
			//bm = BitmapFactory.decodeResource(context.getResources(),
		//			R.drawable.default_bg_l);
			yun_image=(ImageView)findViewById(R.id.iv_x);
			Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.default_bg_l);
	//		 bm = BitmapFactory.decodeResource(context.getResources(),
	//			                R.drawable.default_bg_l);
			
		//	bmpDraw = new BitmapDrawable(bm);
			yun_image.setImageBitmap(toRoundCorner(bitmap,80.f));
		} else {
			bm = BitmapFactory.decodeFile(albumArt);
			bmpDraw = new BitmapDrawable(bm);

		}
		return bmpDraw;
	}
	public static Bitmap toRoundCorner(Bitmap bitmap, float pixels) {
        System.out.println("图片是否变成圆角模式了+++++++++++++");
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);

        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        System.out.println("pixels+++++++" + pixels);

        return output;
    }


	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		String currentdate = sdf.format(d);

		return currentdate;

	}
}
