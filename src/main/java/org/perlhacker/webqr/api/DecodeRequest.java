package org.perlhacker.webqr.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DecodeRequest {
	private final String url;

	@JsonCreator
	public DecodeRequest(@JsonProperty("url") String url) {
		this.url = url;
	}

	@JsonProperty
	public String getUrl() {
		return url;
	}
}