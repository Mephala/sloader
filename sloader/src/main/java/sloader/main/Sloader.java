package sloader.main;

import java.io.IOException;

import sloader.scheduler.SloaderRegisterer;
import sloader.scheduler.SloaderScheduler;

public class Sloader {

	public static void main(String[] args) throws IOException {
		if (args == null || args.length == 0)
			SloaderRegisterer.register();
		else
			SloaderScheduler.sload();
	}
}
