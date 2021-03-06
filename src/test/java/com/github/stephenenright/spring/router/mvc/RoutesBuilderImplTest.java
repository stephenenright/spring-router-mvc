package com.github.stephenenright.spring.router.mvc;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.strictMock;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.stephenenright.spring.router.mvc.RouteSegments.LiteralSegment;
import com.github.stephenenright.spring.router.mvc.RouteSegments.PathSegment;
import com.github.stephenenright.spring.router.mvc.RouteSegments.PathSeparatorSegment;

public class RoutesBuilderImplTest {

	private RouteCollection mockRoutesCollection;
	private RouteParser mockRouteParser;
	private RoutesBuilder routesBuilder;

	@Before
	public void setup() {
		mockRoutesCollection = strictMock(RouteCollection.class);
		mockRouteParser = strictMock(RouteParser.class);

		RoutesConfig routesConfig = new RoutesConfig() {
			
			@Override
			public void registerRoutes(RoutesBuilder routes) {
				
				
			}
			
			@Override
			public String name() {
				return "testRoutes";
			}
		};
		
		
		
		routesBuilder = new RoutesBuilderImpl(routesConfig,mockRoutesCollection,
				mockRouteParser, new RouteControllers(
						new HashMap<String, Object>()), false);

	}

	@Test
	public void addRoute() {
		List<PathSegment> pathSegments = new LinkedList<PathSegment>();

		pathSegments.add(new LiteralSegment("segment1"));
		pathSegments.add(new PathSeparatorSegment());
		pathSegments.add(new LiteralSegment("segment2"));

		expect(mockRoutesCollection.containsName("route1")).andReturn(false);
		expect(mockRouteParser.parseRoute("segment1/segement2")).andReturn(
				new RouteParsed(pathSegments));

		expect(mockRoutesCollection.containsName("route1")).andReturn(false);
		mockRoutesCollection.add(anyString() ,anyObject(Route.class));

		replay(mockRoutesCollection);
		replay(mockRouteParser);

		Route route = routesBuilder.add("route1", "segment1/segement2",
				"TestController", "List");

		Assert.assertNotNull("Expected route to be created", route);
		Assert.assertEquals("Expected route name to be route1",
				route.getName(), "route1");
		Assert.assertEquals("Expected route action to be List",
				route.getAction(), "List");
		Assert.assertEquals("Expected route path to be segment1/segement2",
				route.getPath(), "segment1/segement2");

	}

	@Test
	public void addRoute_errorNameNotUnique() {

		expect(mockRoutesCollection.containsName("route1")).andReturn(false);
		expect(mockRouteParser.parseRoute("segment1/segement2")).andThrow(
				new RouterExceptions.RouteParseException("Parse Exception"));

		replay(mockRoutesCollection);
		replay(mockRouteParser);

		try {
			routesBuilder.add("route1", "segment1/segement2", "TestController",
					"List");
		} catch (RouterExceptions.RouteParseException rpe) {
			return;
		}

		Assert.fail("Expected RouteParseException to be thrown");

	}

	@Test
	public void addRoute_errorParsing() {

		expect(mockRoutesCollection.containsName("route1")).andReturn(true);

		replay(mockRoutesCollection);

		try {
			routesBuilder.add("route1", "segment1/segement2", "TestController",
					"List");
		} catch (RouterExceptions.RouteException re) {
			return;
		}

		Assert.fail("Expected RouteException to be thrown");

	}

}
