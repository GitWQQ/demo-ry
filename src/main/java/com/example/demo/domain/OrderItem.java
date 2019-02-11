package com.example.demo.domain;

public class OrderItem {
	private String id;
	private String item_id;
	private String order_id;
	private Integer num;
	private String title;
	private Integer price;
	private Integer total_fee;
	private String pic_path;
	private String yxx;
	private String sfcl;
	private String gxsjc;
	
	
	public String getGxsjc() {
		return gxsjc;
	}
	public void setGxsjc(String gxsjc) {
		this.gxsjc = gxsjc;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
	public Integer getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}
	
	public String getPic_path() {
		return pic_path;
	}
	public void setPic_path(String pic_path) {
		this.pic_path = pic_path;
	}
	public String getYxx() {
		return yxx;
	}
	public void setYxx(String yxx) {
		this.yxx = yxx;
	}
	
	public String getSfcl() {
		return sfcl;
	}
	public void setSfcl(String sfcl) {
		this.sfcl = sfcl;
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", item_id=" + item_id + ", order_id=" + order_id + ", num=" + num + ", title="
				+ title + ", price=" + price + ", total_fee=" + total_fee + ", pic_path=" + pic_path + ", yxx=" + yxx
				+ ", sfcl=" + sfcl + "]";
	}
}
