package spring.router.mvc.constraint.parameter;

import org.junit.Assert;
import org.junit.Test;

import spring.router.mvc.constraint.BaseConstraintTest;

public class EmailConstraintTest extends BaseConstraintTest {

	@Test
	public void match() {
		EmailConstraint emailRequired = EmailConstraint.email();

		Assert.assertFalse("Expected constraint to fail",
				emailRequired.match(mockedRequest, mockedRoute, "email", null));

		Assert.assertFalse("Expected constraint to fail",
				emailRequired.match(mockedRequest, mockedRoute, "email", ""));

		Assert.assertFalse("Expected constraint to fail",
				emailRequired.match(mockedRequest, mockedRoute, "email", " "));

		Assert.assertFalse("Expected constraint to fail", emailRequired.match(
				mockedRequest, mockedRoute, "email", "test1test.com"));

		Assert.assertFalse("Expected constraint to fail", emailRequired.match(
				mockedRequest, mockedRoute, "email", "test1@testcom"));

		Assert.assertTrue("Expected constraint to match", emailRequired.match(
				mockedRequest, mockedRoute, "email", "test1@test.com"));

	}

}
