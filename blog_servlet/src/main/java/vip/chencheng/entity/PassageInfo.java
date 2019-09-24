package vip.chencheng.entity;

import java.sql.Date;

public class PassageInfo {
	private int pnumber;
	private String ptitle;
	private Date ptime;
	private String plabel;
	private String pcount;
	private String pcoming;
	private String pcomingUrl;
	private String pimage;
	private int pkind;
	private String pkindName;
	private int pbelong;
	private String pbelongName;
	private String pdescribe;
	
	
	
	public int getPbelong() {
		return pbelong;
	}
	public void setPbelong(int pbelong) {
		this.pbelong = pbelong;
	}
	public String getPbelongName() {
		return pbelongName;
	}
	public void setPbelongName(String pbelongName) {
		this.pbelongName = pbelongName;
	}
	public int getPnumber() {
		return pnumber;
	}
	public void setPnumber(int pnumber) {
		this.pnumber = pnumber;
	}
	public String getPtitle() {
		return ptitle;
	}
	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}
	public Date getPtime() {
		return ptime;
	}
	public void setPtime(Date ptime) {
		this.ptime = ptime;
	}
	public String getPlabel() {
		return plabel;
	}
	public void setPlabel(String plabel) {
		this.plabel = plabel;
	}
	public String getPcount() {
		return pcount;
	}
	public void setPcount(String pcount) {
		this.pcount = pcount;
	}
	public String getPcoming() {
		return pcoming;
	}
	public void setPcoming(String pcoming) {
		this.pcoming = pcoming;
	}
	public String getPcomingUrl() {
		return pcomingUrl;
	}
	public void setPcomingUrl(String pcomingUrl) {
		this.pcomingUrl = pcomingUrl;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	public int getPkind() {
		return pkind;
	}
	public void setPkind(int pkind) {
		this.pkind = pkind;
	}
	public String getPkindName() {
		return pkindName;
	}
	public void setPkindName(String pkindName) {
		this.pkindName = pkindName;
	}
	public String getPdescribe() {
		return pdescribe;
	}
	public void setPdescribe(String pdescribe) {
		this.pdescribe = pdescribe;
	}
	
}

