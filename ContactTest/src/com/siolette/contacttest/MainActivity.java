package com.siolette.contacttest;

import com.siolette.com.account.Constants;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends FragmentActivity implements OnClickListener {
//	private AuthenticatorDescription[] mAuthDescs;
	
	
	public static final String username = "siolette_account";
	
	Account mAccount;
	EditText user_et;
	EditText password_et;
	Button addAuth_btn;
	AccountManager _am;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_et = (EditText) findViewById(R.id.user_et);
        password_et = (EditText) findViewById(R.id.password_et);
        addAuth_btn = (Button) findViewById(R.id.add_btn);
        addAuth_btn.setOnClickListener(this);
        _am = AccountManager.get(this);
//        mAccount = CreateSyncAccount(this);
//        updateAuthDescriptions();
    }

	private Account CreateSyncAccount(Context context) {
		Account newAccount = new Account(username, Constants.ACCOUNT_TYPE);
		
//		AccountManager accountManager = (AccountManager) context.getSystemService(ACCOUNT_SERVICE);
		AccountManager accountManager = AccountManager.get(context);
		
		//addAccountExplicitly第2个参数填密码，第3个参数bundle putString("SERVER","") 传服务器地址
		if (accountManager.addAccountExplicitly(newAccount, null, null)) {
//			ContentResolver.setIsSyncable(mAccount, AUTHORITY, 1);
		} else {
		}
		return newAccount;
	}

	@Override
	public void onClick(View v) {
		Bundle options = new Bundle();
		options.putString(Constants.OPTIONS_USERNAME, "wwl");
		options.putString(Constants.OPTIONS_PASSWORD, "123456");
		_am.addAccount(Constants.ACCOUNT_TYPE, null, null, options, this, new AccountManagerCallback<Bundle>() {
			@Override
			public void run(AccountManagerFuture<Bundle> amfuture)
			{
				try
				{
					Log.d("tag", amfuture.getResult().toString());
				}
				catch (Exception e)
				{
					Log.e("tag", e.getMessage(), e);
				}
			}
		}, null);
	}

/*	private void updateAuthDescriptions() {
		mAuthDescs = AccountManager.get(this).getAuthenticatorTypes();
	}
	
	public AuthenticatorDescription[] getAuthenticatorTypes() {
            return mService.getAuthenticatorTypes();
    }*/
}
