package com.example.demo.domain;

import java.sql.Date;
import java.sql.JDBCType;

public class Order {
	/**
	 * 订单id
	 */
	private String order_id;
	/**
	 * 实付金额,精确到2位小数;单位:元。如:200.07，表示:200元7分
	 */
	private String payment;
	/**
	 * 支付类型，1、在线支付，2、货到付款
	 */
	private Integer payment_type;
	
	/**
	 * 邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
	 */
	private String post_fee;
	
	/**
	 * 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
	 */
	private Integer status;
	/**
	 * 订单创建时间
	 */
	private Date create_time;
	
	/**
	 * 订单更新时间
	 */
	private Date update_time;
	/**
	 * 付款时间
	 */
	private Date payment_time;
	/**
	 * 发货时间
	 */
	private Date consign_time;
	/**
	 * 交易完成时间
	 */
	private Date end_time;
	/**
	 * 交易关闭时间
	 */
	private Date close_time;
	/**
	 * 物流名称
	 */
	private String shipping_name;
	
	/**
	 * 物流单号
	 */
	private String shipping_code;
	/**
	 * 用户id
	 */
	private Integer user_id;
	
	/**
	 * 买家昵称
	 * @return
	 */
	private String  buyer_nick;
	/**
	 *买家留言 
	 */
	private String buyer_message;
	
	/**
	 * 有效性
	 */
	private String yxx;
	/**
	 * 更新时间戳
	 */
	private String gxsjc;

	
	
	public String getGxsjc() {
		return gxsjc;
	}
	public void setGxsjc(String gxsjc) {
		this.gxsjc = gxsjc;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public Integer getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(Integer payment_type) {
		this.payment_type = payment_type;
	}
	public String getPost_fee() {
		return post_fee;
	}
	public void setPost_fee(String post_fee) {
		this.post_fee = post_fee;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Date getPayment_time() {
		return payment_time;
	}
	public void setPayment_time(Date payment_time) {
		this.payment_time = payment_time;
	}
	public Date getConsign_time() {
		return consign_time;
	}
	public void setConsign_time(Date consign_time) {
		this.consign_time = consign_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public Date getClose_time() {
		return close_time;
	}
	public void setClose_time(Date close_time) {
		this.close_time = close_time;
	}
	public String getShipping_name() {
		return shipping_name;
	}
	public void setShipping_name(String shipping_name) {
		this.shipping_name = shipping_name;
	}
	public String getShipping_code() {
		return shipping_code;
	}
	public void setShipping_code(String shipping_code) {
		this.shipping_code = shipping_code;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	
	public String getBuyer_nick() {
		return buyer_nick;
	}
	public void setBuyer_nick(String buyer_nick) {
		this.buyer_nick = buyer_nick;
	}
	public String getBuyer_message() {
		return buyer_message;
	}
	public void setBuyer_message(String buyer_message) {
		this.buyer_message = buyer_message;
	}
	
	
	public String getYxx() {
		return yxx;
	}
	public void setYxx(String yxx) {
		this.yxx = yxx;
	}
	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", payment=" + payment + ", payment_type=" + payment_type + ", post_fee="
				+ post_fee + ", status=" + status + ", create_time=" + create_time + ", update_time=" + update_time
				+ ", payment_time=" + payment_time + ", consign_time=" + consign_time + ", end_time=" + end_time
				+ ", close_time=" + close_time + ", shipping_name=" + shipping_name + ", shipping_code=" + shipping_code
				+ ", user_id=" + user_id + ", buyer_nick=" + buyer_nick + ", buyer_message=" + buyer_message + ", yxx="
				+ yxx + "]";
	}
	
}
