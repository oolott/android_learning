package com.siolette.account;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

public class SyncAdapter extends AbstractThreadedSyncAdapter {

	ContentResolver mContentResolver;
	
	public SyncAdapter(Context context, boolean autoInitialize,
			boolean allowParallelSyncs) {
		super(context, autoInitialize, allowParallelSyncs);
		mContentResolver = context.getContentResolver();
	}

	public SyncAdapter(Context context, boolean autoInitialize) {
		super(context, autoInitialize);
		mContentResolver = context.getContentResolver();
	}

	/**
	 * 传输数据代码
	 */
	@Override
	public void onPerformSync(Account account, Bundle extras, String authority,
			ContentProviderClient provider, SyncResult syncResult) {
	}

}
