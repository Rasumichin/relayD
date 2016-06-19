package com.relayd.util;

import java.util.UUID;

/**
 * Little helper to print 10 generated UUIDs to the console.
 * They can be used to be copied into SQL scripts etc.
 * 
 * @author Rasumichin (Erik@relayd.de)
 * @since 19.06.2016
 * status initial
 * 
 */
public class UuidPrinter {

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(UUID.randomUUID().toString());
		}
	}
}
