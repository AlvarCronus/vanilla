/*
 * Copyright (C) 2012 Christopher Eby <kreed@kreed.org>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.kreed.vanilla;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

/**
 * Framework methods only in Honeycomb or above go here.
 */
@TargetApi(11)
public class CompatHoneycomb {
	/**
	 * Notifies the LibraryActivity ViewPager of selected tab changes.
	 */
	@TargetApi(11)
	private static class TabListener implements ActionBar.TabListener {
		/**
		 * The activity to notify of tab changes.
		 */
		private final LibraryActivity mActivity;

		/**
		 * Create the listener.
		 *
		 * @param activity The activity to notify of tab changes.
		 */
		public TabListener(LibraryActivity activity)
		{
			mActivity = activity;
		}

		@Override
		public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft)
		{
		}

		@Override
		public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft)
		{
			mActivity.mViewPager.setCurrentItem(tab.getPosition());
		}

		@Override
		public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft)
		{
		}
	}

	/**
	 * Add ActionBar tabs for LibraryActivity.
	 *
	 * @param activity The activity to add to.
	 */
	public static void addActionBarTabs(LibraryActivity activity)
	{
		ActionBar.TabListener listener = new TabListener(activity);

		ActionBar ab = activity.getActionBar();
		ab.addTab(ab.newTab()
			.setText(R.string.artists)
			.setTabListener(listener));
		ab.addTab(ab.newTab()
			.setText(R.string.albums)
			.setTabListener(listener));
		ab.addTab(ab.newTab()
			.setText(R.string.songs)
			.setTabListener(listener));
		ab.addTab(ab.newTab()
			.setText(R.string.playlists)
			.setTabListener(listener));
		ab.addTab(ab.newTab()
			.setText(R.string.genres)
			.setTabListener(listener));
		ab.addTab(ab.newTab()
			.setText(R.string.files)
			.setTabListener(listener));
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	}

	/**
	 * Call {@link ListView#setFastScrollAlwaysVisible(boolean)} on the given ListView.
	 */
	public static void setFastScrollAlwaysVisible(ListView view, boolean visible)
	{
		view.setFastScrollAlwaysVisible(visible);
	}

	/**
	 * Call {@link MenuItem#setActionView(View)} on the given MenuItem.
	 */
	public static void setActionView(MenuItem item, View view)
	{
		item.setActionView(view);
	}

	/**
	 * Call {@link MenuItem#setShowAsAction(int)} on the given MenuItem.
	 */
	public static void setShowAsAction(MenuItem item, int mode)
	{
		item.setShowAsAction(mode);
	}

	/**
	 * Select the ActionBar tab at the given position.
	 *
	 * @param activity The activity that owns the ActionBar.
	 * @param position The tab's position.
	 */
	public static void selectTab(Activity activity, int position)
	{
		ActionBar ab = activity.getActionBar();
		ab.selectTab(ab.getTabAt(position));
	}

	/**
	 * Call {@link android.provider.MediaStore.Audio.Genres#getContentUriForAudioId(String,int)}
	 * on the external volume.
	 */
	public static Uri getContentUriForAudioId(int id)
	{
		return MediaStore.Audio.Genres.getContentUriForAudioId("external", id);
	}

	/**
	 * Call {@link KeyEvent#hasNoModifiers()}.
	 */
	public static boolean hasNoModifiers(KeyEvent event)
	{
		return event.hasNoModifiers();
	}

	/**
	 * Call {@link KeyEvent#hasModifiers(int)}.
	 */
	public static boolean hasModifiers(KeyEvent event, int modifiers)
	{
		return event.hasModifiers(modifiers);
	}
}
