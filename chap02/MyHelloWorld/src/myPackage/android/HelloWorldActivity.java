package myPackage.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class HelloWorldActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.v("myAndroid", "I'm in create");
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.v("myAndroid", "I'm in destroy");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.v("myAndroid", "I'm in pause");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.v("myAndroid", "I'm in restart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.v("myAndroid", "I'm in resume");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.v("myAndroid", "I'm in start");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.v("myAndroid", "I'm in stop");
	}
}