package org.perlhacker.webqr.resources;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.perlhacker.webqr.QrCodeUtils;
import org.perlhacker.webqr.api.DecodeRequest;
import org.perlhacker.webqr.api.DecodeResult;

import com.codahale.metrics.annotation.Timed;

@Path("/qrcode")
public class QrCodeResource {
	@POST
	@Timed
	public DecodeResult decode(@NotNull @Valid DecodeRequest url) throws Exception {
		final String tempFile = QrCodeUtils.downloadToTempFile(url.getUrl());
		final String decoded = QrCodeUtils.decode(tempFile);
		return new DecodeResult(decoded);
	}
}