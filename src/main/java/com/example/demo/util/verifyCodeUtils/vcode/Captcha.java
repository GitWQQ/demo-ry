package com.example.demo.util.verifyCodeUtils.vcode;

import java.awt.Color;
import java.awt.Font;
import java.io.OutputStream;

/**
 * <p>
 * ��֤�������,��ʱ��֧������
 * </p>
 * 
 * @author: wuhongjun
 * @version:1.0
 */
public abstract class Captcha extends Randoms {
	protected Font font = new Font("Verdana", Font.ITALIC | Font.BOLD, 28); // ����
	protected int len = 5; // ��֤������ַ�����
	protected int width = 150; // ��֤����ʾ���
	protected int height = 40; // ��֤����ʾ�߶�
	private String chars = null; // ����ַ���

	/**
	 * ��������ַ�����
	 * 
	 * @return �ַ�����
	 */
	protected char[] alphas() {
		char[] cs = new char[len];
		for (int i = 0; i < len; i++) {
			cs[i] = alpha();
		}
		chars = new String(cs);
		return cs;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * ������Χ��������ɫ
	 * 
	 * @return Color �����ɫ
	 */
	protected Color color(int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + num(bc - fc);
		int g = fc + num(bc - fc);
		int b = fc + num(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * ��֤�����,���󷽷���������ʵ��
	 * 
	 * @param os
	 *            �����
	 */
	public abstract void out(OutputStream os);

	/**
	 * ��ȡ����ַ���
	 * 
	 * @return string
	 */
	public String text() {
		return chars;
	}
}