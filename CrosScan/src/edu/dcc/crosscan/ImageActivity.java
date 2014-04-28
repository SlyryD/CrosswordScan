package edu.dcc.crosscan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;

public class ImageActivity extends Activity {

	public static final String TAG = "CrosswordScan/ImageActivity";

	private PinchImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Inflate layout
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);

		// Fill layout with image view
		imageView = (PinchImageView) findViewById(R.id.imageView);

		// Add image to view
		Intent intent = getIntent();
		String path = intent.getStringExtra(ScanActivity.PHOTO);
		if (path.equals("null")) {
			Bitmap photo = BitmapFactory.decodeResource(getResources(),
					R.drawable.no_photo);
			imageView.setImageBitmap(photo);
		} else {
			Bitmap photo = BitmapFactory.decodeFile(path);
			imageView.setImageBitmap(rotateBitmap(photo, 90));
		}
	}

	private Bitmap rotateBitmap(Bitmap source, float angle) {
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		return Bitmap.createBitmap(source, 0, 0, source.getWidth(),
				source.getHeight(), matrix, true);
	}
}
