package com.dasan.smartop.cmmn.exception.manager;

import com.dasan.smartop.cmmn.exception.handler.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultExceptionHandleManager extends AbstractExceptionHandleManager implements ExceptionHandlerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionHandleManager.class);

	@Override
	public boolean run(Exception exception) throws Exception {
		LOGGER.debug(" DefaultExceptionHandleManager.run() ");
		// 매칭조건이 false 인 경우
		if (!enableMatcher()) {
			return false;
		}
		for (String pattern : patterns) {
			LOGGER.debug("pattern = {}, thisPackageName = {}", pattern, thisPackageName);
			LOGGER.debug("pm.match(pattern, thisPackageName) = {}", pm.match(pattern, thisPackageName));
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
