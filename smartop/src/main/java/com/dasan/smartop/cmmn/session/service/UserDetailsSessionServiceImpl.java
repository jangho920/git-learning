package com.dasan.smartop.cmmn.session.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.dasan.smartop.cmmn.AbstractServiceImpl;

public class UserDetailsSessionServiceImpl extends AbstractServiceImpl implements UserDetailsService {

	public Object getAuthenticatedUser() {
		return RequestContextHolder.getRequestAttributes().getAttribute("sessionVO", RequestAttributes.SCOPE_SESSION);
	}

	public List<String> getAuthorities() {

		// 권한 설정을 리턴한다.
		List<String> listAuth = new ArrayList<String>();

		return listAuth;
	}

	public Boolean isAuthenticated() {
		// 인증된 유저인지 확인한다.

		if (RequestContextHolder.getRequestAttributes() == null) {
			return false;
		} else {

			if (RequestContextHolder.getRequestAttributes().getAttribute(
					"loginVO", RequestAttributes.SCOPE_SESSION) == null) {
				return false;
			} else {
				return true;
			}
		}


	}

}