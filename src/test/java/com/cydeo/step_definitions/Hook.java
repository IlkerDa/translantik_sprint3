package com.cydeo.step_definitions;


import com.cydeo.utilities.BrowserUtils;
import com.cydeo.utilities.Driver;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hook {
   @After
   public void teardownScenario(Scenario scenario){

       //scenario.isFailed() --> if scenario fails this method will return TRUE boolean value

       if (scenario.isFailed()){

           byte [] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
           scenario.attach(screenshot, "image/png", scenario.getName());
       }


       BrowserUtils.sleep(5);
       Driver.closeDriver();

   }
}
