package me.shuaizki.mplogger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class Logger {
	private BufferedWriter bw;
	private int Counter;

	static class LogFile {
		private static HashSet<String> log_set;
		private static SimpleDateFormat df;
		static {
			log_set = new HashSet<String>();
			File log_dir = new File("log/");
			for (File f : log_dir.listFiles()) {
				if (!f.isDirectory() || f.getName().startsWith("."))
					continue;
				log_set.add(f.getName());
			}

		}

		public static String getFile(String sig) {
			File log_dir = new File("log/", sig);
			System.out.println(log_set);
			if (!log_set.contains(sig)) {
				if (!log_dir.mkdir()) {
					throw new RuntimeException(sig + "'s not created");
				}
				log_set.add(sig);
			}
			df = new SimpleDateFormat("yyyy_MM_dd");
			Date date = new Date();
			String d = df.format(date);
			return log_dir.getAbsolutePath() + "/"+ d + ".txt"; }
	}

	public Logger(String service_type)  {
		String service_log_name = LogFile.getFile(service_type);
		try {
			bw = new BufferedWriter(new FileWriter(service_log_name));
		} catch (IOException e) {
			throw new RuntimeException("log file " + service_log_name + " created failed");
		}
	}
	
	public void log(Object o) {
		String a = str(o);
		try {
			bw.write(a);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String str(Object o) {
		return null;
	}

	public static void main(String[] arg0) {
		Logger tmp_logger = new Logger("tmp") {

			@Override
			public String str(Object o) {
				String a = (String) o;
				return a;
			}
		};
		tmp_logger.log("what the....");
	}
}
