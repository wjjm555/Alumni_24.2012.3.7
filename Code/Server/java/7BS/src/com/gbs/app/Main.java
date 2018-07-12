package com.gbs.app;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.gbs.app.model.cards.KillBaseCard;
import com.gbs.app.model.cards.model.Card;
import com.gbs.app.model.cards.model.Card.Count;
import com.gbs.app.model.cards.model.Card.Suit;
import com.gbs.app.model.players.Role;

public class Main {

	public static void main(String[] args) {
		long[][] whichs = new long[][] { new long[6], new long[6], new long[6], new long[6], new long[6], new long[6], new long[6], new long[6], new long[6], new long[6] };
		File file = new File("C:\\Users\\jianmingchen\\Desktop\\new.txt");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = br.readLine()) != null) {
				String whichStr = line.substring(75, 76);
				String stepStr = line.substring(72, 73);
				String timeStr = line.substring(84, 97);
				int which = Integer.parseInt(whichStr);
				int step = Integer.parseInt(stepStr);
				long time = Long.parseLong(timeStr);
				whichs[which][step - 1] = time;
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (long[] times : whichs) {
			String str = "Spend Times:";
			for (int i = 1; i < times.length; ++i) {
				long start = times[i - 1];
				long end = times[i];

				str += " Step " + i + ":" + (end - start);

			}
			System.out.println(str);
		}

	}

	public static void sendMessage(String type, String content, String... keys) {
		System.out.println("sendMessage...");
	}

	public static void sendMessage(String type, String content, String key) {
		System.out.println("sendMessage3");
	}

	public static void sendMessage(String type, String content, String key, String key2) {
		System.out.println("sendMessage4");
	}
}
