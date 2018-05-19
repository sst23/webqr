package org.perlhacker.webqr;

import org.perlhacker.webqr.health.QrCodeHealthCheck;
import org.perlhacker.webqr.resources.QrCodeResource;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class WebQrApplication extends Application<WebQrConfiguration> {

	public static void main(final String[] args) throws Exception {
		System.setProperty("java.awt.headless", "true");
		new WebQrApplication().run(args);
	}

	@Override
	public String getName() {
		return "WebQr";
	}

	@Override
	public void initialize(final Bootstrap<WebQrConfiguration> bootstrap) {
		bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
	}

	@Override
	public void run(final WebQrConfiguration configuration, final Environment environment) {
		environment.healthChecks().register("qrcode", new QrCodeHealthCheck());
		final QrCodeResource resource = new QrCodeResource();
		environment.jersey().register(resource);
	}
}