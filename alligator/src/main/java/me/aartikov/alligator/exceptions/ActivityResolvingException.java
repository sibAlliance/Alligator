package me.aartikov.alligator.exceptions;

import me.aartikov.alligator.Screen;

/**
 * Date: 12.03.2017
 * Time: 15:43
 *
 * @author Artur Artikov
 */

/**
 * Exception thrown when an implicit intent can't be resolved.
 */
public class ActivityResolvingException extends NavigationException {
	private Screen mScreen;

	public ActivityResolvingException(Screen screen) {
		super("Failed to resolve an activity for a screen " + screen.getClass().getSimpleName());
		mScreen = screen;
	}

	public Screen getScreen() {
		return mScreen;
	}
}
