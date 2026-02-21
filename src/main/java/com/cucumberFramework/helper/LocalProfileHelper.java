package com.cucumberFramework.helper;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Loads credentials and config from local-profiles.properties only.
 * Automation uses these local profiles whenever it runs—no external/Amazon credentials.
 */
public final class LocalProfileHelper {

	private static final String PROFILES_FILE = "local-profiles.properties";
	private static Properties props;

	/** Base URL for local login/signup pages (file:// or jar-resource). */
	public static String getLocalWebBaseUrl() {
		URL url = LocalProfileHelper.class.getClassLoader().getResource("web/login.html");
		if (url == null) throw new RuntimeException("Local web folder not found: web/login.html");
		String s = url.toString();
		int last = s.lastIndexOf('/');
		return last >= 0 ? s.substring(0, last + 1) : s;
	}

	private static void loadIfNeeded() {
		if (props != null) return;
		props = new Properties();
		try (InputStream in = LocalProfileHelper.class.getClassLoader().getResourceAsStream(PROFILES_FILE)) {
			if (in != null) props.load(in);
		} catch (Exception e) {
			throw new RuntimeException("Could not load local profiles from " + PROFILES_FILE, e);
		}
	}

	public static String getDefaultUsername() {
		loadIfNeeded();
		return props.getProperty("default.username", "testuser");
	}

	public static String getDefaultPassword() {
		loadIfNeeded();
		return props.getProperty("default.password", "testpass123");
	}

	public static String getSignupUsername() {
		loadIfNeeded();
		return props.getProperty("signup.username", "newuser");
	}

	public static String getSignupEmail() {
		loadIfNeeded();
		return props.getProperty("signup.email", "newuser@test.local");
	}

	public static String getSignupPassword() {
		loadIfNeeded();
		return props.getProperty("signup.password", "newpass123");
	}
}
