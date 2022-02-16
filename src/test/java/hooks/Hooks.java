package hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import io.cucumber.plugin.event.Result;
import io.cucumber.plugin.event.Status;
import utils.Logger;

import java.io.*;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Hooks {

        @Before
        public static void openBrowser(Scenario scenario) {
            Logger.info("************************************************************************************");
            Logger.info(String.format("Start scenario: %s", scenario.getName()));
            Logger.info("************************************************************************************");
       //     Configuration.startMaximized = true;
        }
        @After
        public void attachments(Scenario scenario) {
            if (scenario.isFailed()) {
                try {
                    Field delegateField = scenario.getClass().getDeclaredField("delegate");
                    delegateField.setAccessible(true);
                    TestCaseState testCaseState = (TestCaseState) delegateField.get(scenario);
                    Field stepResultsField = testCaseState.getClass().getDeclaredField("stepResults");
                    stepResultsField.setAccessible(true);
                    ArrayList<Result> stepResults = (ArrayList<Result>) stepResultsField.get(testCaseState);
                    for (Result result : stepResults) {
                        if (result.getStatus().is(Status.FAILED)) {
                            Logger.error("Scenario failed: ");
                        }
                    }
                } catch (Exception ignored){}

                final String name = String.format("Screenshot_%s_%s",
                        scenario.getName(),
                        LocalDateTime.now().toString()
                );
                String screenshotFilePath = Selenide.screenshot(name);
                assert screenshotFilePath != null;
                final File screenshotFile = new File(screenshotFilePath);
                try {
                    final InputStream fileInputStream = new DataInputStream(new FileInputStream(screenshotFile));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
}
