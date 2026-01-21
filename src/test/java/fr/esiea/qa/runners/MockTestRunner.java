package fr.esiea.qa.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/mocks.feature")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports/mock-cucumber.html")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "fr.esiea.qa.steps,fr.esiea.qa.hooks")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@mocks")
@ConfigurationParameter(key = SNIPPET_TYPE_PROPERTY_NAME, value = "camelcase")
public class MockTestRunner {
}
