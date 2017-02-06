package spring.router.mvc;

import org.springframework.util.StringUtils;

public class RouteApiConfiger {

	private RouteConfiger listApi;
	private RouteConfiger getApi;
	private RouteConfiger editApi;
	private RouteConfiger addApi;

	public RouteApiConfiger(String baseName, String controller, String collectionPath) {
		init(baseName, controller, collectionPath);
	}

	private void init(String baseName, String controller, String collectionPath) {
		initRoutes(baseName, controller, collectionPath);
	}

	private void initRoutes(String baseName, String controller, String collectionPath) {
		listApi = initRouteConfig(baseName, "list", controller, "list", collectionPath, HttpMethod.GET);
		getApi = initRouteConfig(baseName, "get", controller, "get", appendPath(collectionPath, "/{id}"),
				HttpMethod.GET);
		editApi = initRouteConfig(baseName, "edit", controller, "edit", appendPath(collectionPath, "/{id}"),
				HttpMethod.POST);
		addApi = initRouteConfig(baseName, "add", controller, "add", collectionPath, HttpMethod.POST);

	}

	private RouteConfiger initRouteConfig(String baseName, String nameSuffix, String controller, String action,
			String path, HttpMethod... method) {
		return new RouteConfiger().setName(createRouteName(baseName, nameSuffix)).setAction(action)
				.addHttpMethod(HttpMethod.GET).setController(controller).setPath(path);

	}

	private String appendPath(String base, String path) {
		return base;

	}

	public RouteApiConfiger enableListApi(boolean enabled) {
		listApi.setEnabled(enabled);
		return this;
	}
	
	public RouteApiConfiger enableEditApi(boolean enabled) {
		editApi.setEnabled(enabled);
		return this;
	}
	
	public RouteApiConfiger enableAddApi(boolean enabled) {
		addApi.setEnabled(enabled);
		return this;
	}

	public RouteApiConfiger enableGetApi(boolean enabled) {
		getApi.setEnabled(enabled);
		return this;
	}

	public RouteConfiger getListApi() {
		return listApi;
	}

	public RouteConfiger getGetApi() {
		return getApi;
	}

	public RouteConfiger getEditApi() {
		return editApi;
	}

	public RouteConfiger getAddApi() {
		return addApi;
	}

	private String createRouteName(String base, String suffix) {
		return base + StringUtils.capitalize(suffix) + "Api";
	}

}