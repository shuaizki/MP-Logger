package me.shuaizki.mplogger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import me.shuaizki.util.TimeUtil;

public class Logger {
	public Logger() {
	}
	
	static class LogFile {
		private static HashSet<String> log_set;
		private BufferedWriter bw;
		private Timer timer;
		private String svc_name;
		private Map<String, ArrayList<String>> content_buff;
		
		static {
			log_set = new HashSet<String>();
			File log_dir = new File("log/");
			for (File f : log_dir.listFiles()) {
				if (!f.isDirectory() || f.getName().startsWith("."))
					continue;
				log_set.add(f.getName());
			}
		}	
		
		public LogFile(String svc_name) {
			content_buff = new HashMap<String, ArrayList<String>>();
			createFile(svc_name);
			timer = new Timer();
			this.svc_name = svc_name;
			TimerTask recorder = new record();
			TimerTask refresh = new refreshLogFile();
			
			timer.schedule(recorder, 30000);
			timer.schedule(refresh, TimeUtil.getNextDay(), 24 * 3600 * 1000);
		}
		
		public void log(String key, String content)
		{
			ArrayList<String> contents = content_buff.get(key);
			if (contents == null)
			{
				contents = new ArrayList<String>();
				content_buff.put(key, contents);
			}
			contents.add(content);
		}

		private void createFile(String sig) {
			File log_dir = new File("log/", sig);
			System.out.println(log_set);
			if (!log_set.contains(sig)) {
				if (!log_dir.mkdir()) {
					throw new RuntimeException(sig + "'s not created");
				}
				log_set.add(sig);
			}
			if (bw != null)
			{
				try {
					bw.flush();
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}
			try {
				bw = new BufferedWriter(new FileWriter(log_dir.getAbsolutePath() + "/"+ TimeUtil.getSimpleDate() + ".txt", true));
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		
		public class refreshLogFile extends TimerTask {

			@Override
			public void run() {
				createFile(svc_name);
			}
		}
		
		public class record extends TimerTask {

			@Override
			public void run() {
				for (String key: content_buff.keySet())
				{
					ArrayList<String> contents = content_buff.get(key);
					try {
						bw.write("-----new log----" + TimeUtil.getTime() + '\n');
						bw.write(key+ '\n');
						for (String content: contents)
						{
							bw.write(content);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private Map<String, LogFile> logfile_map;

	public Logger(String service_type)  {
		logfile_map = new HashMap<String, LogFile> ();
	}
	
	public void log(String svc_name, String key, String msg)
	{
		LogFile logfile = logfile_map.get(svc_name);
		if (logfile == null)
		{
			logfile = new LogFile(svc_name);
		}
		logfile.log(key, msg);
	}

	public static void main(String[] arg0) {
	}
}
