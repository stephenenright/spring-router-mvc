package spring.router.mvc.constraint.parameter;

import org.junit.Assert;
import org.junit.Test;

import spring.router.mvc.constraint.BaseConstraintTest;

public class NumericConstraintTest extends BaseConstraintTest {

	@Test
	public void match() {
		NumericConstraint numeric = new NumericConstraint();

		Assert.assertFalse("Expected constraint to fail",
				numeric.match(mockedRequest, mockedRoute, "num", null));

		Assert.assertFalse("Expected constraint to fail",
				numeric.match(mockedRequest, mockedRoute, "num", ""));

		Assert.assertFalse("Expected constraint to fail",
				numeric.match(mockedRequest, mockedRoute, "num", "   "));

		Assert.assertFalse("Expected constraint to fail",
				numeric.match(mockedRequest, mockedRoute, "num", "3434.33"));

		Assert.assertTrue("Expected constraint to match",
				numeric.match(mockedRequest, mockedRoute, "num", "0"));

		Assert.assertTrue("Expected constraint to match",
				numeric.match(mockedRequest, mockedRoute, "num", "34343443"));

	}

}
