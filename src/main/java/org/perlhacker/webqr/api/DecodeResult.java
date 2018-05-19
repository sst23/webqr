package org.perlhacker.webqr.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DecodeResult {
	private final String result;

	@JsonCreator
	public DecodeResult(@JsonProperty("result") String result) {
		this.result = result;
	}

	@JsonProperty
	public String getResult() {
		return result;
	}
}