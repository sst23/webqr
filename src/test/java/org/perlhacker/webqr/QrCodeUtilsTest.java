package org.perlhacker.webqr;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class QrCodeUtilsTest {
	@Test
	public void writeQrCode() throws Exception {
		final String string = "das ist ein test";
		QrCodeUtils.encode(string, "/tmp/test.png", 300);

		String decoded = QrCodeUtils.decode("/tmp/test.png");
		assertTrue("", string.equals(decoded));
	}
}