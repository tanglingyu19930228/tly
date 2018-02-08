package com.tly01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * nio¸´ÖÆÎÄ¼þ
 * 
 * @author Administrator
 *
 */
public class nio {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream(new File("H://jdk-8u151-windows-x64.exe"));
		FileOutputStream fos = new FileOutputStream(new File("H://a.exe"));
		FileChannel fic = fis.getChannel();
		FileChannel foc = fos.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(2048);
		while (true) {
			buffer.clear();
			int i = fic.read(buffer);
			if (i == -1) {
				break;
			}
			buffer.flip();
			foc.write(buffer);
		}
		fis.close();
		fos.close();
	}
}
