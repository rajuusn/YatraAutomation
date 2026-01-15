package testRunners;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features= {".//features/login.feature"},  glue={"stepDefinitions","hooks"},
//@CucumberOptions(features= {"@target/rerun.txt"},  glue={"stepDefinitions","hooks"},

//features= {"@target/rerun.txt"},for the reruning failed tests
plugin= {"pretty", "html:reports/myreport.html", 
		  "rerun:target/rerun.txt",
		  "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		},
		
dryRun=false,    // checks mapping between scenario steps and step definition methods
monochrome=true,    // to avoid junk characters in output
publish=true)// to publish the report in the cucumber server

public class TestRunner {

}
