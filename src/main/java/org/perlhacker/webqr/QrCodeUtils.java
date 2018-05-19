package org.perlhacker.webqr;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

public final class QrCodeUtils {

	private QrCodeUtils() {
	}

	/**
	 * Returns the decoded string attached to the given file
	 * 
	 * @param path
	 *            the location of the filesystem
	 * @return the string inside the qr code image
	 * @throws IOException
	 * @throws NotFoundException
	 */
	public static String decode(String path) throws IOException, NotFoundException {
		final FileInputStream fis = new FileInputStream(path);
		final BufferedImageLuminanceSource bils = new BufferedImageLuminanceSource(ImageIO.read(fis));
		final BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(bils));
		
		Result result = new MultiFormatReader().decode(bitmap);
		return result.getText();
	}

	/**
	 * Creates a new QR code image
	 * 
	 * @param data
	 *            the data which is to be written
	 * @param path
	 *            the location of the file which will be created
	 * @param dimensions
	 *            the size of the new image
	 * @throws IOException
	 * @throws WriterException
	 */
	public static void encode(String data, String path, int dimensions) throws IOException, WriterException {
		final String encoded = new String(data.getBytes("UTF-8"), "UTF-8");
		final BitMatrix matrix = new MultiFormatWriter().encode(encoded, BarcodeFormat.QR_CODE, dimensions, dimensions);

		MatrixToImageWriter.writeToPath(matrix, "png", FileSystems.getDefault().getPath(path));
	}

	/**
	 * Fetches the given URL to a new temp file
	 * 
	 * @param location
	 *            a downloadble url
	 * @return the location of the new tempfile in the filesystem
	 * @throws IOException
	 */
	public static String downloadToTempFile(String location) throws IOException {
		URL url = new URL(location);
		File tempFile = File.createTempFile("qrcodeutils", null);
		Path tempPath = FileSystems.getDefault().getPath(tempFile.getPath());

		try (InputStream in = url.openStream()) {
			Files.copy(in, tempPath, StandardCopyOption.REPLACE_EXISTING);
		}

		return tempFile.getPath();
	}
}