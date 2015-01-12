package wwl.launcher.demo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.LiveFolders;
import android.view.Menu;
import android.view.MenuItem;

public class LiveFolderSampleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getIntent().getAction().equals(
				LiveFolders.ACTION_CREATE_LIVE_FOLDER)) {
			Intent _returnIntent = new Intent();

			// 设置名字
			_returnIntent.putExtra(LiveFolders.EXTRA_LIVE_FOLDER_NAME, "电话簿");

			// 设置图标

			_returnIntent.putExtra(LiveFolders.EXTRA_LIVE_FOLDER_ICON,
					Intent.ShortcutIconResource.fromContext(this,
							R.drawable.sms_voice_input));

			// 设置单击事件
			_returnIntent.putExtra(LiveFolders.EXTRA_LIVE_FOLDER_BASE_INTENT,
					new Intent(this, WwlLauncherDemoActivity.class));

			// 设置显示模式

			_returnIntent.putExtra(LiveFolders.EXTRA_LIVE_FOLDER_DISPLAY_MODE,
					LiveFolders.DISPLAY_MODE_LIST);

			// 设置数据

			_returnIntent.setData(Uri
					.parse("content://contacts/live_folders/people"));

			setResult(RESULT_OK, _returnIntent);
		} else {

			setResult(RESULT_CANCELED);

		}

	  finish();
	}
}
