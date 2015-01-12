package com.siolette.account;

import com.siolette.contacttest.MainActivity;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Authenticator extends AbstractAccountAuthenticator {

	private Context mContext;
	

	public Authenticator(Context context) {
		super(context);
		mContext = context;
	}

	@Override
	public Bundle editProperties(AccountAuthenticatorResponse response,
			String accountType) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 返回添加账户的结果
	 */
	@Override
	public Bundle addAccount(AccountAuthenticatorResponse response,
			String accountType, String authTokenType,
			String[] requiredFeatures, Bundle options)
			throws NetworkErrorException {
		Log.i(this.getClass().getSimpleName(), "addAccount");
		/*Log.d("auth_tag", accountType + " - " + authTokenType);
		Bundle bundle = new Bundle();
		Intent intent = new Intent(mContext, MainActivity.class);
		intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
		bundle.putParcelable(AccountManager.KEY_INTENT, intent);
		return bundle;*/
		
		// There are two cases here:
        // 1) We are called with a username/password; this comes from the traditional email
        //    app UI; we simply create the account and return the proper bundle
		Log.i(this.getClass().getSimpleName(), options==null?"null":"hasuUser="+options.containsKey(Constants.OPTIONS_USERNAME)+",hasPwd="+options.containsKey(Constants.OPTIONS_PASSWORD));
        if (options != null && options.containsKey(Constants.OPTIONS_PASSWORD)
                && options.containsKey(Constants.OPTIONS_USERNAME)) {
        	Log.i(this.getClass().getSimpleName(), "参数足够，无须跳转");
            final Account account = new Account(options.getString(Constants.OPTIONS_USERNAME),
            		Constants.ACCOUNT_TYPE);
            
            //添加一个账户
          //addAccountExplicitly第2个参数填密码，第3个参数bundle putString("SERVER","") 传服务器地址
            boolean isAddSuccess = AccountManager.get(mContext).addAccountExplicitly(
                        account, options.getString(Constants.OPTIONS_PASSWORD), null);
//            Log.i(this.getClass().getSimpleName(), "isAddSuccess = "+isAddSuccess);
//            ...
            Log.i(this.getClass().getSimpleName(), "isAddAccountSuccess="+isAddSuccess);
            
            Bundle b = new Bundle();
            b.putString(AccountManager.KEY_ACCOUNT_NAME, options.getString(Constants.OPTIONS_USERNAME));
            b.putString(AccountManager.KEY_ACCOUNT_TYPE, Constants.ACCOUNT_TYPE);
            return b;
        // 2) The other case is that we're creating a new account from an Account manager
        //    activity.  In this case, we add an intent that will be used to gather the
        //    account information...
        } else {
        	Log.i(this.getClass().getSimpleName(), "参数不足，跳转到账号密码输入页面!");
            Bundle b = new Bundle();
            Intent intent = new Intent(mContext, MainActivity.class);
            intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
            b.putParcelable(AccountManager.KEY_INTENT, intent);
            return b;
        }
	}

	@Override
	public Bundle confirmCredentials(AccountAuthenticatorResponse response,
			Account account, Bundle options) throws NetworkErrorException {
		Log.i(this.getClass().getSimpleName(), "confirmCredentials");
		return null;
	}

	@Override
	public Bundle getAuthToken(AccountAuthenticatorResponse response,
			Account account, String authTokenType, Bundle options)
			throws NetworkErrorException {
		Log.i(this.getClass().getSimpleName(), "getAuthToken");
		return null;
	}

	@Override
	public String getAuthTokenLabel(String authTokenType) {
		Log.i(this.getClass().getSimpleName(), "getAuthTokenLabel");
		return null;
	}

	@Override
	public Bundle updateCredentials(AccountAuthenticatorResponse response,
			Account account, String authTokenType, Bundle options)
			throws NetworkErrorException {
		Log.i(this.getClass().getSimpleName(), "updateCredentials");
		return null;
	}

	@Override
	public Bundle hasFeatures(AccountAuthenticatorResponse response,
			Account account, String[] features) throws NetworkErrorException {
		Log.i(this.getClass().getSimpleName(), "hasFeatures");
		return null;
	}

}
