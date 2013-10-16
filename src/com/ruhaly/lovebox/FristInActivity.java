package com.ruhaly.lovebox;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ViewFlipper;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 首次进入导航 Class Name: FristInActivity.java Function:
 * 
 * Modifications:
 * 
 * @author ruhaly DateTime 2013-10-16 上午10:40:32
 * @version 1.0
 */
public class FristInActivity extends SuperActivity implements OnGestureListener {

	@ViewInject(R.id.viewFlipper)
	private ViewFlipper viewFlipper;

	private int[] imgs = { R.drawable.lead0, R.drawable.lead1,
			R.drawable.lead2, R.drawable.lead3, R.drawable.lead4,
			R.drawable.lead5 };

	private GestureDetector gd;

	@Override
	public void initData() {

	}

	@Override
	public void initLayout(Bundle savedInstanceState) {
		setContentView(R.layout.firstin_layout);
		ViewUtils.inject(this);
		gd = new GestureDetector(this);
		initImageToFlipper();
	}

	public void initImageToFlipper() {
		for (int i = 0; i < imgs.length; i++) {
			ImageView iv = new ImageView(getBaseContext());
			iv.setBackgroundResource(imgs[i]);
			iv.setScaleType(ScaleType.FIT_XY);
			viewFlipper.addView(iv, new LayoutParams(LayoutParams.FILL_PARENT,
					LayoutParams.FILL_PARENT));
		}
		// viewFlipper.setAutoStart(true);
		// viewFlipper.setFlipInterval(4000);
		// if (viewFlipper.isAutoStart() && !viewFlipper.isFlipping()) {
		// viewFlipper.startFlipping();
		// }
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		viewFlipper.stopFlipping();
		viewFlipper.setAutoStart(false);
		return gd.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if (e2.getX() - e1.getX() > 120) { // 从左向右滑动（左进右出）
			Animation rInAnim = AnimationUtils.loadAnimation(getBaseContext(),
					R.anim.push_right_in); // 向右滑动左侧进入的渐变效果（alpha 0.1 -> 1.0）
			Animation rOutAnim = AnimationUtils.loadAnimation(getBaseContext(),
					R.anim.push_right_out); // 向右滑动右侧滑出的渐变效果（alpha 1.0 -> 0.1）

			viewFlipper.setInAnimation(rInAnim);
			viewFlipper.setOutAnimation(rOutAnim);
			viewFlipper.showPrevious();
			return true;
		} else if (e2.getX() - e1.getX() < -120) { // 从右向左滑动（右进左出）
			Animation lInAnim = AnimationUtils.loadAnimation(
					FristInActivity.this, R.anim.push_left_in); // 向左滑动左侧进入的渐变效果（alpha
																// 0.1 -> 1.0）
			Animation lOutAnim = AnimationUtils.loadAnimation(getBaseContext(),
					R.anim.push_left_out); // 向左滑动右侧滑出的渐变效果（alpha 1.0 -> 0.1）

			viewFlipper.setInAnimation(lInAnim);
			viewFlipper.setOutAnimation(lOutAnim);
			viewFlipper.showNext();
			return true;
		}
		return true;
	}
}
