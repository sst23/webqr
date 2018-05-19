package org.perlhacker.webqr.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DecodeRequest {
	private String url;

	public DecodeRequest() {
	}

	public DecodeRequest(String url) {
		this.url = url;
	}

	@JsonProperty
	public String getUrl() {
		return url;
	}
}