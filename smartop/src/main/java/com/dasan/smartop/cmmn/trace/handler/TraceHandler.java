package com.dasan.smartop.cmmn.trace.handler;

public interface TraceHandler {

	/**
	 * todo 메소드.
	 * @param clazz trace 발생시키는 클래스 정보
	 * @param message 메세지키를 통해 보여주고자 하는 메세지 
	 */
	public void todo(Class<?> clazz, String message);

}
