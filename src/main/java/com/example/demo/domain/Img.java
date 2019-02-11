package com.example.demo.domain;

import java.util.Arrays;

public class Img  extends Item{
	private String id;
	private String imglx;
	private byte[] img;
	private byte[] img2;
	private byte[] img3;
	private String img_name;
	private String img2_name;
	private String img3_name;
	private String created;
	private String gxsjc;
	private String yxx;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public byte[] getImg() {
		return img;
	}
	public String getImglx() {
		return imglx;
	}
	public void setImglx(String imglx) {
		this.imglx = imglx;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	
	public byte[] getImg2() {
		return img2;
	}
	public void setImg2(byte[] img2) {
		this.img2 = img2;
	}
	public byte[] getImg3() {
		return img3;
	}
	public void setImg3(byte[] img3) {
		this.img3 = img3;
	}
	public String getImg_name() {
		return img_name;
	}
	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}
	public String getImg2_name() {
		return img2_name;
	}
	public void setImg2_name(String img2_name) {
		this.img2_name = img2_name;
	}
	public String getImg3_name() {
		return img3_name;
	}
	public void setImg3_name(String img3_name) {
		this.img3_name = img3_name;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getGxsjc() {
		return gxsjc;
	}
	public void setGxsjc(String gxsjc) {
		this.gxsjc = gxsjc;
	}
	public String getYxx() {
		return yxx;
	}
	public void setYxx(String yxx) {
		this.yxx = yxx;
	}
	@Override
	public String toString() {
		return "Img [id=" + id + ", imglx=" + imglx + ", img=" + Arrays.toString(img) + ", img2="
				+ Arrays.toString(img2) + ", img3=" + Arrays.toString(img3) + ", img_name=" + img_name + ", img2_name="
				+ img2_name + ", img3_name=" + img3_name + ", created=" + created + ", gxsjc=" + gxsjc + ", yxx=" + yxx
				+ "]";
	}
	
}
