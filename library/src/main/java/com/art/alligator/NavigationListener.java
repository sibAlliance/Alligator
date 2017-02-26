package com.art.alligator;

/**
 * Date: 26.02.2017
 * Time: 18:51
 *
 * @author Artur Artikov
 */

public interface NavigationListener {
	void onExecuted(Command command);
	void onError(Command command, String message);
}
