package com.art.screenresultsample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.art.alligator.NavigationContext;
import com.art.alligator.NavigationContextBinder;
import com.art.alligator.NavigationFactory;
import com.art.alligator.Navigator;
import com.art.alligator.Screen;
import com.art.alligator.ScreenResult;
import com.art.alligator.ScreenResultListener;
import com.art.alligator.implementation.ScreenResultResolver;
import com.art.screenresultsample.R;
import com.art.screenresultsample.SampleApplication;
import com.art.screenresultsample.screens.ImagePickerScreen;
import com.art.screenresultsample.screens.MessageInputScreen;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Date: 12.03.2016
 * Time: 15:53
 *
 * @author Artur Artikov
 */
public class MainActivity extends AppCompatActivity implements ScreenResultListener {
	private Navigator mNavigator;
	private NavigationContextBinder mNavigationContextBinder;
	private NavigationFactory mNavigationFactory;

	@BindView(R.id.activity_main_button_input_message)
	Button mInputMessageButton;

	@BindView(R.id.activity_main_button_pick_image)
	Button mPickImageButton;

	@BindView(R.id.activity_main_text_view_message)
	TextView mMessageTextView;

	@BindView(R.id.activity_main_image_view)
	ImageView mImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		mNavigator = SampleApplication.getNavigator();
		mNavigationContextBinder = SampleApplication.getNavigationContextBinder();

		mInputMessageButton.setOnClickListener(v -> mNavigator.goForwardForResult(new MessageInputScreen()));
		mPickImageButton.setOnClickListener(v -> mNavigator.goForwardForResult(new ImagePickerScreen()));
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		ScreenResultResolver screenResultResolver = SampleApplication.getScreenResultResolver();
		screenResultResolver.handleActivityResult(requestCode, resultCode, data, this);
	}

	@Override
	public void onScreenResult(Class<? extends Screen> screenClass, ScreenResult result) {
		if (screenClass == MessageInputScreen.class) {
			onMessageInputted((MessageInputScreen.Result) result);
		} else if (screenClass == ImagePickerScreen.class) {
			onImagePicked((ImagePickerScreen.Result) result);
		}
	}

	private void onMessageInputted(MessageInputScreen.Result messageInputResult) {
		if (messageInputResult != null) {
			mMessageTextView.setText(getString(R.string.inputted_message_template, messageInputResult.getMessage()));
		} else {
			Toast.makeText(this, getString(R.string.cancelled), Toast.LENGTH_SHORT).show();
		}
	}

	private void onImagePicked(ImagePickerScreen.Result imagePickerResult) {
		if (imagePickerResult != null) {
			Picasso.with(this).load(imagePickerResult.getUri()).into(mImageView);
		} else {
			Toast.makeText(this, getString(R.string.cancelled), Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		mNavigationContextBinder.bind(new NavigationContext(this));
	}

	@Override
	protected void onPause() {
		mNavigationContextBinder.unbind();
		super.onPause();
	}

	@Override
	public void onBackPressed() {
		mNavigator.goBack();
	}
}
