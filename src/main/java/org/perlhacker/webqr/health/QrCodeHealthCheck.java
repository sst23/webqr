package org.perlhacker.webqr.health;

import java.io.File;

import org.perlhacker.webqr.QrCodeUtils;

import com.codahale.metrics.health.HealthCheck;

public class QrCodeHealthCheck extends HealthCheck {
	@Override
	protected Result check() throws Exception {
		final String string = "das ist ein test";
		final File tempFile = File.createTempFile("qrcodehealth", null);
		tempFile.deleteOnExit();
		final String path = tempFile.getPath();

		QrCodeUtils.encode(string, path, 300);
		String decoded = QrCodeUtils.decode(path);

		return string.equals(decoded) ? Result.healthy() : Result.unhealthy("Something wrong with " + path);
	}

}