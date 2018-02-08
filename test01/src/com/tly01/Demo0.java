package com.tly01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * java nio实现文件复制
 * @author Administrator
 *
 */
public class Demo0 {
	public static void main(String[] args) throws IOException {
		//使用FileInputStream打开一个文件输入流
		FileInputStream fis=new FileInputStream("D:\\工具及软件\\VMware-workstation-full-14.0.0.24051.exe");
		//使用FileOutPutStream打开一个文件输出流
		FileOutputStream fos=new FileOutputStream("D:\\ss\\a.exe");
		//得到文件输入流的通道
		FileChannel ifc=fis.getChannel();
		//得到文件输出流的通道
		FileChannel ofc=fos.getChannel();
		//分配一个字节缓冲区,大小为1024
		ByteBuffer buffer=ByteBuffer.allocate(1024);
		while(true){
		//清空缓冲区,使其处于可接受字节状态
		buffer.clear();
		//从文件输入流通道里读取数据,大小取决于缓冲区大小,以及文件剩余字节大小	
		int i=ifc.read(buffer);
		//如果返回值为-1表示已经读取完毕
		if(i==-1){
			break;
		}
		//反转缓冲区,使其处于可写入通道状态
		buffer.flip();
		//把缓冲区数据写入文件输出流通道
		ofc.write(buffer);
		}
		fis.close();
		fos.close();
	}
}
