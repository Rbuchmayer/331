package hw5.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * ImplementationTests is a test suite used to encapsulate all
 * tests specific to your implementation of this problem set.
 *
 * For instance, unit tests for your individual methods would
 * go here.
 */

@RunWith(Suite.class)
@SuiteClasses({ CheckAsserts.class, EdgeTests.class, GraphTests.class})
public final class ImplementationTests
{
  
}

