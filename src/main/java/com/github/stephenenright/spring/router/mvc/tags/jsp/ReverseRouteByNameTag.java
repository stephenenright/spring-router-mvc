package com.github.stephenenright.spring.router.mvc.tags.jsp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.github.stephenenright.spring.router.mvc.RouteHelper;
import com.github.stephenenright.spring.router.mvc.RouteParameterCollection;


public class ReverseRouteByNameTag extends SimpleTagSupport implements
		DynamicAttributes {

	private String name;

	private Map<String, Object> attrMap = new HashMap<String, Object>();

	public void doTag() throws JspException, IOException,
			IllegalArgumentException {
		JspWriter out = getJspContext().getOut();

		String route = RouteHelper.reverse(name, new RouteParameterCollection(
				attrMap));

		if (route != null) {
			out.write(route);
		}

	}

	@Override
	public void setDynamicAttribute(String uri, String name, Object value)
			throws JspException {
		attrMap.put(name, value);
	}

	public void setName(String name) {
		this.name = name;
	}

}
