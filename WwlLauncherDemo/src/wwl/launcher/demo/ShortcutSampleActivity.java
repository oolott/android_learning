package wwl.launcher.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ShortcutSampleActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getIntent().getAction().equals(Intent.ACTION_CREATE_SHORTCUT)) {
			Intent _returnIntent = new Intent();
			_returnIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "WwlShortcut");
			_returnIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(this, R.drawable.sms_voice_input));
			_returnIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(this, WwlLauncherDemoActivity.class));
			setResult(RESULT_OK, _returnIntent);
			finish();
		}
	}
}
