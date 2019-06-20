/**
 * 
 */
package hr.finalium.ednevnik.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

/**
 * Razred koji sadržava pomoćne metode.
 * 
 * @author Zlatko
 *
 */
public class Util {
	/**
	 * Pretvara {@link InputStream} u polje byte-ova.
	 * 
	 * @param is
	 *            input stream
	 * @return polje byteova stvoreno iz input streama
	 * @throws IOException
	 *             ako se dogodi greška kod čitanja
	 */
	public static byte[] getBytes(InputStream is) throws IOException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		int nRead;
		byte[] data = new byte[16384];

		while ((nRead = is.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
		}

		buffer.flush();

		return buffer.toByteArray();
	}

	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.MONTH) < 8 ? Calendar.getInstance().get(Calendar.YEAR) - 1
				: Calendar.getInstance().get(Calendar.YEAR);
	}
}
