package com.dasan.smartop.cmmn.session;

import java.io.Serializable;

/**
 * 세션 VO 클래스
 */

public class SessionVO implements Serializable {
	
	/** 아이디 */
	private String userId;
	/** 이름 */
	private String userNm;
	/** 전화번호 */
	private String telNum;
	/** 이메일 */
	private String email;
	/** 우편번호 */
	private String zipCd;
	/** 주소1 */
	private String addr1;
	/** 주소2 */
	private String addr2;
	/** 딜러ID */
	private String dealerId;
	/** 딜러명 */
	private String dealerNm;
	/** 브랜드ID */
	private String brandId;
	/** 브랜드명 */
	private String brandNm;
	/** 매장ID */
	private String storId;
	/** 매장명 */
	private String storNm;
	/** 사용자구분코드 */
	private String userSecCd;
	/** 사용자구분코드명 */
	private String userSecNm;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getTelNum() {
		return telNum;
	}
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZipCd() {
		return zipCd;
	}
	public void setZipCd(String zipCd) {
		this.zipCd = zipCd;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getDealerId() {
		return dealerId;
	}
	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}
	public String getDealerNm() {
		return dealerNm;
	}
	public void setDealerNm(String dealerNm) {
		this.dealerNm = dealerNm;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getBrandNm() {
		return brandNm;
	}
	public void setBrandNm(String brandNm) {
		this.brandNm = brandNm;
	}
	public String getStorId() {
		return storId;
	}
	public void setStorId(String storId) {
		this.storId = storId;
	}
	public String getStorNm() {
		return storNm;
	}
	public void setStorNm(String storNm) {
		this.storNm = storNm;
	}
	public String getUserSecCd() {
		return userSecCd;
	}
	public void setUserSecCd(String userSecCd) {
		this.userSecCd = userSecCd;
	}
	public String getUserSecNm() {
		return userSecNm;
	}
	public void setUserSecNm(String userSecNm) {
		this.userSecNm = userSecNm;
	}
	@Override
	public String toString() {
		return "SessionVO [userId=" + userId + ", userNm=" + userNm + ", telNum=" + telNum + ", email=" + email
				+ ", zipCd=" + zipCd + ", addr1=" + addr1 + ", addr2=" + addr2 + ", dealerId=" + dealerId
				+ ", dealerNm=" + dealerNm + ", brandId=" + brandId + ", brandNm=" + brandNm + ", storId=" + storId
				+ ", storNm=" + storNm + ", userSecCd=" + userSecCd + ", userSecNm=" + userSecNm + "]";
	}
	
	
}