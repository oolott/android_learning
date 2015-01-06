package wwl.launcher.demo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class WwlLauncherDemoActivity extends Activity {
	
	public static final String ACTION_REMOVE_SHORTCUT = "com.android.launcher.action.UNINSTALL_SHORTCUT";
	public static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				installShortcut();
			}

			private void installShortcut() {
				Intent _returnIntent = new Intent(ACTION_INSTALL_SHORTCUT);
				_returnIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "wwlAuto");
				// 不允许重复创建
				_returnIntent.putExtra("duplicate", false);// 经测试不是根据快捷方式的名字判断重复的
		        // 应该是根据快链的Intent来判断是否重复的,即Intent.EXTRA_SHORTCUT_INTENT字段的value
		        // 但是名称不同时，虽然有的手机系统会显示Toast提示重复，仍然会建立快链
		        // 屏幕上没有空间时会提示
		        // 注意：重复创建的行为MIUI和三星手机上不太一样，小米上似乎不能重复创建快捷方式
//				_returnIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(WwlLauncherDemoActivity.this, R.drawable.sms_voice_input));
				_returnIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(WwlLauncherDemoActivity.this, WwlLauncherDemoActivity.class).setAction(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER));
				sendBroadcast(_returnIntent);
			}
		});
	}
	
	
	private void removeShortcut(String name) {
        // remove shortcut的方法在小米系统上不管用，在三星上可以移除
        Intent intent = new Intent(ACTION_REMOVE_SHORTCUT);

        // 名字
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

        // 设置关联程序
        Intent launcherIntent = new Intent(this,
        		WwlLauncherDemoActivity.class).setAction(Intent.ACTION_MAIN);

        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launcherIntent);

        // 发送广播
        sendBroadcast(intent);
    }
	
	
	//两个手机(小米、三星)都查不到
	private boolean hasInstallShortcut(String name) {

        boolean hasInstall = false;

        final String AUTHORITY = "com.android.launcher2.settings";
        Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
                + "/favorites?notify=true");

        // 这里总是failed to find provider info
        // com.android.launcher2.settings和com.android.launcher.settings都不行
        Cursor cursor = this.getContentResolver().query(CONTENT_URI,
                new String[] { "title", "iconResource" }, "title=?",
                new String[] { name }, null);

        if (cursor != null && cursor.getCount() > 0) {
            hasInstall = true;
        }

        return hasInstall;

    }
}
