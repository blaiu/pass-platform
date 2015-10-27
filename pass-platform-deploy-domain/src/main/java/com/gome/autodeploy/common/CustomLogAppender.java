/**
 * 
 */
package com.gome.autodeploy.common;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

/**
 * @author bailu-ds
 *
 */
public class CustomLogAppender extends DailyRollingFileAppender {

	@Override
	public boolean isAsSevereAsThreshold(Priority priority) {
		return this.getThreshold().equals(priority);
	}

	
	
}
