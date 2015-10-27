/**
 * 
 */
package com.gome.autodeploy.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.velocity.VelocityToolboxView;

/**
 * @author bailu-ds
 *
 */
public class VelocityToolbox2View   {

//	@Override
//	protected Context createVelocityContext(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ViewToolContext ctx = new ViewToolContext(getVelocityEngine(), request, response, getServletContext());
//		ctx.putAll(model);
//
//		if (this.getToolboxConfigLocation() != null) {
//			ToolManager tm = new ToolManager();
//			tm.setVelocityEngine(getVelocityEngine());
//			tm.configure(getServletContext().getRealPath(getToolboxConfigLocation()));
//			
//			if (tm.getToolboxFactory().hasTools(Scope.REQUEST)) {
//				ctx.addToolbox(tm.getToolboxFactory().createToolbox(Scope.REQUEST));
//			}
//			
//			if (tm.getToolboxFactory().hasTools(Scope.APPLICATION)) {
//				ctx.addToolbox(tm.getToolboxFactory().createToolbox(Scope.APPLICATION));
//			}
//			
//			if (tm.getToolboxFactory().hasTools(Scope.SESSION)) {
//				ctx.addToolbox(tm.getToolboxFactory().createToolbox(Scope.SESSION));
//			}
//		}
//		
//		return ctx;
//	}

}
