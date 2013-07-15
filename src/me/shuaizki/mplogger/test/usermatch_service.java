package me.shuaizki.mplogger.test;

import me.shuaizki.mplogger.*;
import java.util.Random;

public class usermatch_service {
	public static void main(String[] arg0) {
		MPClient logger = new MPClient("usermatch");
		if (arg0.length == 0)
		{
			System.out.println("input something");
		}
		String sig = arg0[0];
		
		Random r = new Random();
		int total_num = 0;
		while (total_num != 600)
		{
			total_num += 1;
			int i = r.nextInt(10);
			logger.log("key: " + i, "from user " + sig + "\n");
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
