package com.dasan.smartop.cmmn.exception.manager;

import javax.annotation.Resource;
import com.dasan.smartop.cmmn.exception.handler.ExceptionHandler;
import org.springframework.context.MessageSource;
import org.springframework.util.PathMatcher;

public abstract class AbstractExceptionHandleManager {

	@Resource(name = "messageSource")
	protected MessageSource messageSource;

	protected Exception ex;
	protected String thisPackageName;
	protected String[] patterns;
	protected ExceptionHandler[] handlers;
	protected PathMatcher pm;

	/**
	 * setPatterns 메소드.
	 * 패키지, 클래스 이름으로 패턴등록(Ant형식의 매칭)
	 * @param patterns 패턴리스트
	 */
	public void setPatterns(String[] patterns) {
		this.patterns = patterns;
	}

	/**
	 * setHandlers 메소드.
	 * ExceptionHandler 리스트 등록
	 * @param handlers handler리스트
	 */
	public void setHandlers(ExceptionHandler[] handlers) {
		this.handlers = handlers;
	}

	/**
	 * setPackageName 메소드.
	 * 비교할 클래스 정보
	 * @param canonicalName 비교할 클래스명
	 */
	public void setPackageName(String canonicalName) {
		this.thisPackageName = canonicalName;
	}

	/**
	 * getPackagename 메소드.
	 * @return packageName
	 */
	public String getPackageName() {
		return this.thisPackageName;
	}

	/**
	 * setException 메소드.
	 * @param be Exception
	 */
	public void setException(Exception be) {
		this.ex = be;
	}

	/**
	 * setReqExpMatcher 메소드.
	 * @param pm 별도의 PathMatcher
	 */
	public void setReqExpMatcher(PathMatcher pm) {
		this.pm = pm;
	}

	/**
	 * PathMatcher 가 있는지 여부 반환.
	 * @return boolean true|false
	 */
	public boolean hasReqExpMatcher() {
		return this.enableMatcher();
	}

	/**
	 * PathMatcher 가 있는지 여부 반환.
	 * @return boolean true|false
	 */
	public boolean enableMatcher() {
		return (this.pm == null) ? false : true;
	}

	/**
	 * 상속받아 구현해야할 메스드 하지만 미리구현은 먼저 해둠. 실 구현체에서 override 하여 구현해야 함.
	 * @param exception 발생한 Exception
	 * @return boolean 실행성공여부
	 */
	public boolean run(Exception exception) throws Exception {
		if (!enableMatcher()) {
			return false;
		}
		for (String pattern : patterns) {
			if (pm.match(pattern, thisPackageName)) {
				for (ExceptionHandler eh : handlers) {
					eh.occur(exception, getPackageName());
				}
				break;
			}
		}
		return true;
	}

}
