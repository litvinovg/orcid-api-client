/* $This file is distributed under the terms of the license in /doc/license.txt$ */

package edu.cornell.mannlib.orcidclient.context;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.cornell.mannlib.orcidclient.OrcidClientException;
import edu.cornell.mannlib.orcidclient.actions.ActionManager;
import edu.cornell.mannlib.orcidclient.auth.AuthorizationManager;
import edu.cornell.mannlib.orcidclient.orcidmessage.OrcidMessage;

/**
 * TODO
 */
public abstract class OrcidClientContext {
	public enum Setting {
		CLIENT_ID, API_BASE_URL, MESSAGE_VERSION, OAUTH_URL, CALLBACK_PATH, WEBAPP_BASE_URL, CLIENT_SECRET
	}

	// ----------------------------------------------------------------------
	// The factory
	// ----------------------------------------------------------------------

	private static volatile OrcidClientContext instance = new OrcidClientContextNotInitialized();

	public static synchronized void initialize(Map<Setting, String> settings)
			throws OrcidClientException {
		if (instance instanceof OrcidClientContextImpl) {
			throw new IllegalStateException("Already initialized: " + instance);
		} else {
			instance = new OrcidClientContextImpl(settings);
		}
	}

	public static OrcidClientContext getInstance() {
		return instance;

	}

	// ----------------------------------------------------------------------
	// The interface
	// ----------------------------------------------------------------------

	public abstract ActionManager getActionManager(HttpServletRequest req);

	public abstract AuthorizationManager getAuthorizationManager(
			HttpServletRequest req);

	public abstract String getSetting(Setting setting);

	public abstract String getCallbackUrl();

	public abstract String getAuthCodeRequestUrl();

	public abstract String getAccessTokenRequestUrl();

	public abstract OrcidMessage unmarshall(String xml)
			throws OrcidClientException;

	public abstract String marshall(OrcidMessage message)
			throws OrcidClientException;

	// ----------------------------------------------------------------------
	// The empty implementation
	// ----------------------------------------------------------------------

	private static class OrcidClientContextNotInitialized extends
			OrcidClientContext {
		private static final String MESSAGE = "OrcidClientContext has not been initialized";

		@Override
		public ActionManager getActionManager(HttpServletRequest req) {
			throw new IllegalStateException(MESSAGE);
		}

		@Override
		public AuthorizationManager getAuthorizationManager(
				HttpServletRequest req) {
			throw new IllegalStateException(MESSAGE);
		}

		@Override
		public String getSetting(Setting setting) {
			throw new IllegalStateException(MESSAGE);
		}

		@Override
		public String getCallbackUrl() {
			throw new IllegalStateException(MESSAGE);
		}

		@Override
		public String getAuthCodeRequestUrl() {
			throw new IllegalStateException(MESSAGE);
		}

		@Override
		public String getAccessTokenRequestUrl() {
			throw new IllegalStateException(MESSAGE);
		}

		@Override
		public OrcidMessage unmarshall(String xml) {
			throw new IllegalStateException(MESSAGE);
		}

		@Override
		public String marshall(OrcidMessage message) {
			throw new IllegalStateException(MESSAGE);
		}

	}

}
