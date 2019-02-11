package com.example.demo.domain;

public class Zd {
	
	private String zddm;
	private String mc;
	private String dm;
	private String parentid;
	private String yxx;
	
	
	public String getZddm() {
		return zddm;
	}
	public void setZddm(String zddm) {
		this.zddm = zddm;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getYxx() {
		return yxx;
	}
	public void setYxx(String yxx) {
		this.yxx = yxx;
	}
	@Override
	public String toString() {
		return "Zd [zddm=" + zddm + ", mc=" + mc + ", dm=" + dm + ", parentid=" + parentid + ", yxx=" + yxx + "]";
	}
}
