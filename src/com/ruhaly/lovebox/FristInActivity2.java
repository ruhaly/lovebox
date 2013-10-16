package com.ruhaly.lovebox;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class FristInActivity2 extends SuperActivity {

	@ViewInject(R.id.viewPager)
	private ViewPager viewPager;

	private List<Integer> imgs = new ArrayList<Integer>();
	private List<View> viewList = new ArrayList<View>();
	private Adapter adapter;

	@Override
	public void initData() {

	}

	@Override
	public void initLayout(Bundle savedInstanceState) {
		setContentView(R.layout.firstin_layout);
		ViewUtils.inject(this);
		imgs.add(R.drawable.lead0);
		imgs.add(R.drawable.lead1);
		imgs.add(R.drawable.lead2);
		imgs.add(R.drawable.lead3);
		imgs.add(R.drawable.lead4);
		imgs.add(R.drawable.lead5);
		for (int i = 0; i < imgs.size(); i++) {
			View view = null;
			if (i == imgs.size() - 1) {
				view = LayoutInflater.from(getBaseContext()).inflate(
						R.layout.last_lead_item_layout, null);
				ImageView imgOn = (ImageView) view.findViewById(R.id.imgViewOn);
				imgOn.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						showToast("12312");
					}
				});
			} else {
				view = LayoutInflater.from(getBaseContext()).inflate(
						R.layout.lead_item_layout, null);
			}
			ImageView img = (ImageView) view.findViewById(R.id.imageView);
			img.setBackgroundResource(imgs.get(i));

			viewList.add(view);
		}

		if (null == adapter) {
			adapter = new Adapter();
			viewPager.setAdapter(adapter);
		}

	}

	class Adapter extends PagerAdapter {

		public Adapter() {
		}

		@Override
		public int getCount() {
			return viewList.size();
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			((ViewPager) container).addView(viewList.get(position), 0);
			return viewList.get(position);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		/**
		 * 要将已经不再显示的视图从ViewPager的视图集合中移除掉
		 */
		@Override
		public void destroyItem(View v, int position, Object oldView) {
			((ViewPager) v).removeView((View) oldView);
		}
	}
}
