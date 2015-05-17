package sloader.scheduler;

import java.io.File;

public class SloaderScheduler {

	public static void sload() {
		try {
			String appLocation = System.getProperty("user.home") + File.separator + "Am.txt";
			File app = new File(appLocation);
			app.createNewFile();
		} catch (Exception e) {

		}
	}

}
