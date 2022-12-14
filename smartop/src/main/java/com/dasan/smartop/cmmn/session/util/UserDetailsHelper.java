package com.dasan.smartop.cmmn.session.util;

import java.util.List;

import com.dasan.smartop.cmmn.session.service.UserDetailsService;


/**
 * UserDetails Helper 클래스
 */

public class UserDetailsHelper {
	
		static UserDetailsService userDetailsService;
	
		public UserDetailsService getUserDetailsService() {
			return userDetailsService;
		}

		public void setUserDetailsService(
				UserDetailsService userDetailsService) {
			this.userDetailsService = userDetailsService;
		}

		/**
		 * 인증된 사용자객체를 VO형식으로 가져온다.
		 * @return Object - 사용자 ValueObject
		 */
		public static Object getAuthenticatedUser() {
			return userDetailsService.getAuthenticatedUser();
		}

		/**
		 * 인증된 사용자의 권한 정보를 가져온다.
		 * 
		 * @return List - 사용자 권한정보 목록
		 */
		public static List<String> getAuthorities() {
			

			return userDetailsService.getAuthorities();
		}
		
		/**
		 * 인증된 사용자 여부를 체크한다.
		 * @return Boolean - 인증된 사용자 여부(TRUE / FALSE)	
		 */
		public static Boolean isAuthenticated() {
			
			return userDetailsService.isAuthenticated();
		}
}