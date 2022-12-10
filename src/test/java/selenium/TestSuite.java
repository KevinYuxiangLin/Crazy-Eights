package selenium;

import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("JUnit Suite")
@SelectClasses({ATests.class})
@IncludeClassNamePatterns(".*Tests")

public class TestSuite {
}
