package com.github.greatwqs.app.utils;

import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.net.PercentEscaper;
import com.google.common.net.UrlEscapers;

/***
 *
 */
public class UrlUtil {

	private static final String URL_FORM_PARAMETER_OTHER_SAFE_CHARS = "-_.*";

	@SuppressWarnings("UnstableApiUsage")
	public static String escapeContentDisposition(String str) {
		PercentEscaper percentEscaper = new PercentEscaper(URL_FORM_PARAMETER_OTHER_SAFE_CHARS, false);
		return percentEscaper.escape(str);
	}

	public static String encodeEscape(String str) {
		String encoded = UrlEscapers.urlFragmentEscaper().escape(str);
		//Chrome parse "," incorrect, so encode "," as hex.
		return encoded.replace(",", "%2C");
	}

	public static String replaceParam(String url, String paramName, Object value) {
		return UriComponentsBuilder.fromHttpUrl(url).replaceQueryParam(paramName,
			value.toString()).build().toUriString();
	}
}
