package org.perlhacker.webqr.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DecodeResult {
	private String result;

	public DecodeResult() {
	}

	public DecodeResult(String result) {
		this.result = result;
	}

	@JsonProperty
	public String getResult() {
		return result;
	}
}