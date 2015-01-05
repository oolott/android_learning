package wwl.launcher.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WwlLauncherDemoActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _returnIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
				_returnIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "wwlAuto");
//				_returnIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(WwlLauncherDemoActivity.this, R.drawable.sms_voice_input));
				_returnIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(WwlLauncherDemoActivity.this, WwlLauncherDemoActivity.class));
				sendBroadcast(_returnIntent);
			}
		});
	}
}
