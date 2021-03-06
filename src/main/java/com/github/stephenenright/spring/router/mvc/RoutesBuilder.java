package com.github.stephenenright.spring.router.mvc;

import com.github.stephenenright.spring.router.mvc.constraint.RouteConstraints;

public interface RoutesBuilder {

	Route add(String name, String path, String controller, String action);

	Route add(String name, String path, String controller, String action,
			HttpMethod... methods);

	Route add(String name, String path, String controller, String action,
			RouteConstraints constraints, HttpMethod... methods);
	
	void add(RouteConfiger configer);
	void addApi(RouteConfiger routesApi);

}
