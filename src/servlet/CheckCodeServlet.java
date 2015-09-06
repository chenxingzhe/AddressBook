package servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckCodeServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		//绘图
		//step1，创建一个内存映像（画布）
		BufferedImage image = new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);
		//step2，获得画笔
		Graphics g = image.getGraphics();
		//step3，给画笔设置颜色
		g.setColor(Color.WHITE);
		//step4，设置画布的背景颜色
		g.fillRect(0, 0, 80, 30);
		//step5，生成一个随机数
		String number = getNumber(2);
		

		HttpSession session = request.getSession();
		session.setAttribute("number_pic", number);
		
		
		//step6，将随机数绘制到图片上
		Random r= new Random();
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		g.setFont(new Font("Microsoft Yahei",Font.ITALIC,24));
		g.drawString(number, 3, 24);
		
		//step7，干扰线
		for (int i = 0; i < 5; i++) {
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.drawLine(r.nextInt(80), r.nextInt(30), r.nextInt(80), r.nextInt(30));
		}
		
		//压缩图片，然后输出
		//step1，设置服务器返回的数据类型为图片
		response.setContentType("image/jpeg");
		//step2，获得输出流
		OutputStream os = response.getOutputStream();
		//step3，压缩图片并输出
		ImageIO.write(image, "jpeg", os);
		os.close();
		
	}
	private String getNumber(int size){
		String number="";
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random r= new Random();
		for (int i = 0; i < size; i++) {
			number += str.charAt(r.nextInt(36));
		}
		return number;
	}
}
