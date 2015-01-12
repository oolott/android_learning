package com.siolette.account;

import android.accounts.AccountManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AuthenticationService extends Service {
	private Authenticator mAuthenticator;
	
	@Override
	public void onCreate() {
		mAuthenticator = new Authenticator(this);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		/*if (intent.getAction().equals(AccountManager.ACTION_AUTHENTICATOR_INTENT)) {
			return getAuthenticator().getIBinder();
		}
		return null;*/
		return getAuthenticator().getIBinder();
	}

	public Authenticator getAuthenticator() {
		if (mAuthenticator == null) {
			mAuthenticator = new Authenticator(this);
		}
		return mAuthenticator;
	}

	public void setAuthenticator(Authenticator authenticator) {
		this.mAuthenticator = authenticator;
	}
	
	/*public AuthenticatorDescription[] getAuthenticatorTypes() {
     
        final int userId = UserHandle.getCallingUserId();
        final long identityToken = clearCallingIdentity();
        try {
            Collection<AccountAuthenticatorCache.ServiceInfo<AuthenticatorDescription>>
                    authenticatorCollection = mAuthenticatorCache.getAllServices(userId);
            AuthenticatorDescription[] types =
                    new AuthenticatorDescription[authenticatorCollection.size()];
            int i = 0;
            for (AccountAuthenticatorCache.ServiceInfo<AuthenticatorDescription> authenticator
                    : authenticatorCollection) {
                types[i] = authenticator.type;
                i++;
            }
            return types;
        } finally {
            restoreCallingIdentity(identityToken);
        }
    }*/

}
