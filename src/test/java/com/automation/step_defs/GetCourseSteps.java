package com.automation.step_defs;

import com.automation.pages.*;
import com.automation.utilities.ConfigurationReader ;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class GetCourseSteps {

    WebDriver driver = Hooks.getDriver();
    String url = ConfigurationReader.get("url");
    PageObjects page = new PageObjects();

    @Given("go to preferences page")
    public void go_to_preferences_page() {
        driver.get(url);
    }

    @Given("select audio {string}")
    public void select_audio(String audioSet) {
        page.selectAudioPreferences(audioSet);
    }

    @Given("select subtitles {string}")
    public void select_subtitles(String subtitlesSet) {page.selectSubtitlesPreferences(subtitlesSet);
    }

    @Given("click Submit button")
    public void click_submit_button() {
        page.buttonSubmit.click();
    }

    @Then("verify the page loaded fully")
    public void verify_the_page_loaded_fully() {
        assertEquals("Page not loaded FULLY!", page.isPercentageFullyLoaded(), "100%");
    }

    @Then("verify user landed to home page")
    public void verify_user_landed_to_home_page() {
        assertTrue("Home page not opened!", page.isHomePage());
    }

    @When("click Begin button")
    public void click_begin_button() {
        page.clickBegin();
    }


    @When("click right arrow for next page")
    public void click_right_arrow_for_next_page() {
        page.clickRightArrow();
    }


    @Then("verify the right arrow unlocked just after the video finished")
    public void verify_the_right_arrow_unlocked_just_after_the_video_finished() {
        assertTrue("Right arrow is not unlocked!", page.isArrowLocked());
        assertTrue("Right arrow is still locked!", page.isArrowUnlocked());
        assertTrue("Video not completed!", page.isVideoFinished());
    }

    @When("close pop-up")
    public void close_pop_up() {
        page.clickClosePopup();
    }


    @Then("verify the right arrow locked until all readings done")
    public void verify_the_right_arrow_locked_until_all_readings_done() {
        assertTrue("Right arrow is not unlocked!", page.isArrowLocked());
    }

    @Then("verify the right arrow unlocked just after readings finished")
    public void verify_the_right_arrow_unlocked_just_after_readings_finished() {
        assertTrue("Right arrow is still locked!", page.isRightArrowUnlocked());
    }

    @Then("click down arrow")
    public void click_down_arrow() {
        page.clickDownArrow();
    }

    @Then("click {string}")
    public void click(String topic) {
        page.clickTopic(topic);
    }

    @Then("verify header is {string}")
    public void verify_header_is(String expectedHeader) {
        assertEquals("Header NOT matched!", expectedHeader,new PageObjects().getHeaderText());
    }

    @Then("take a screenshot of diploma")
    public void take_a_screenshot_of_diploma() {
        new PageObjects().takeScreenshotDiploma();
    }

}
