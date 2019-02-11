package com.example.demo.domain;
import java.util.Arrays;
import java.util.Date;

import org.apache.ibatis.type.JdbcType;

public class Item {
	
	private String id;
	private String splx;
	private String title;
	private String sell_point;
	private Integer price;
	private String num;
	private String barcode;
	private byte[] image;
	private	String cid;
	private String status;
	private String created;
	private String updated;
	private String yxx;
	
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getSplx() {
		return splx;
	}
	public void setSplx(String splx) {
		this.splx = splx;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getSell_point() {
		return sell_point;
	}

	public void setSell_point(String sell_point) {
		this.sell_point = sell_point;
	}

	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getYxx() {
		return yxx;
	}

	public void setYxx(String yxx) {
		this.yxx = yxx;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", splx=" + splx + ", title=" + title + ", sell_point=" + sell_point + ", price="
				+ price + ", num=" + num + ", barcode=" + barcode + ", image=" + Arrays.toString(image) + ", cid=" + cid
				+ ", status=" + status + ", created=" + created + ", updated=" + updated + ", yxx=" + yxx + "]";
	}

}
