package me.aartikov.alligator;

/**
 * Date: 11.02.2017
 * Time: 11:37
 *
 * @author Artur Artikov
 */

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public interface NavigationFactory {
	@Nullable ScreenImplementation getScreenImplementation(Class<? extends Screen> screenClass);

	@Nullable Class<? extends Screen> getScreenClass(Activity activity);

	@Nullable Class<? extends Screen> getScreenClass(Fragment fragment);

	@Nullable Class<? extends Screen> getScreenClass(int requestCode);

	@Nullable Class<? extends Screen> getPreviousScreenClass(Activity activity);
}