package com.automation.pages;

import com.automation.step_defs.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import static com.automation.utilities.BrowserUtils.*;

public class PageObjects {

    public PageObjects() {
        PageFactory.initElements(Hooks.getDriver(), this);
    }
    public WebDriver driver = Hooks.getDriver();

    public void waitArrowToBeUnlocked(WebElement element, Duration timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
        wait.until(ExpectedConditions.attributeContains(element, "class", "active"));
    }

    public void waitVideoToBeStarted(By by, Duration timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
        wait.until(ExpectedConditions.textToBe(by, "0:01"));
    }

    public void handleIFrame(WebElement iFrame){
        driver.switchTo().frame(iFrame);
    }

    /****
     *  for PreferencesPage
     */
    @FindBy(xpath = "//label[@for='settings-audio-on']")
    public WebElement buttonAudioOn;

    @FindBy(xpath = "//label[@for='settings-subtitles-on']")
    public WebElement buttonSubtitlesOn;

    @FindBy(xpath = "//label[@for='settings-audio-off']")
    public WebElement buttonAudioOff;

    @FindBy(xpath = "//label[@for='settings-subtitles-off']")
    public WebElement buttonSubtitlesOff;

    @FindBy(css = "#pref_save-button")
    public WebElement buttonSubmit;

    @FindBy(xpath = "//*[@id='player']")
    public WebElement iFrame;

    public void selectAudioPreferences(String on_off){
        waitForPageToLoad(Duration.ofSeconds(5));
        handleIFrame(iFrame);
        waitForVisibility(buttonAudioOn, Duration.ofSeconds(3));
        if (on_off.equalsIgnoreCase("on")) {
            buttonAudioOn.click();
            System.out.println("Audio on");
        }
        else buttonAudioOff.click();
    }

    public void selectSubtitlesPreferences(String on_off){
        if (buttonAudioOn.isSelected()) {
            if (on_off.equalsIgnoreCase("on")) {
                buttonSubtitlesOn.click();
            }
            else buttonSubtitlesOff.click();
        }
    }

    /****
     *  for LoadPage
     */
    @FindBy(xpath = "//*[@id='preloader-panel']/div/div[2]/span")
    public WebElement loadPercentage;

    public String isPercentageFullyLoaded(){
        waitForPageToLoad(Duration.ofSeconds(3));
        waitForVisibility(loadPercentage, Duration.ofSeconds(3));
        String textLoadPercentage = loadPercentage.getText();
        while(!textLoadPercentage.equals("100%")){
            textLoadPercentage = loadPercentage.getText();
        }
        return textLoadPercentage;
    }

    /****
     *  for HomePage
     */
    @FindBy(css = "#progress-bar-beginBtn")
    public WebElement buttonBegin;

    public By locatorButtonBegin = By.cssSelector("#progress-bar-beginBtn");

    public Boolean isHomePage() {
//        waitUntilVisibilityOfElementLocated(locatorButtonBegin, Duration.ofMinutes(20));
        boolean isBeginPresent = false;
        if (buttonBegin.isDisplayed()) {
            isBeginPresent = true;
        }
        return isBeginPresent;
    }

    public void clickBegin(){
        waitFor(2);
        buttonBegin.click();
    }

    /****
     *  for CourseMenuPage
     */
    @FindBy(xpath = "//h1[@class='headerText']")
    public WebElement header2;

    @FindBy(css = "#progress-bar-nextBtn")
    public WebElement btnRightArrow;

    @FindBy(xpath = "//*[@id=\"progress_pageNums\"]/div")
    public WebElement pageNumber;


    public void clickRightArrow(){
        waitForPageToLoad(Duration.ofSeconds(3));
        waitArrowToBeUnlocked(btnRightArrow,Duration.ofSeconds(5));
        btnRightArrow.click();
    }

    public int getPageNumber(){
        return Integer.parseInt(pageNumber.getText().substring(12,14).trim());
    }

    /*****
     *  for IntroPage
     */
    @FindBy(xpath = "//*[@id=\"SectionTitle3-body\"]/div/div/header/h2")
    public WebElement header3;

    /*****
     *  for WelcomePage and the pages that require watching a video
     *  use to verify duration of video, and the video is finished or not, and the arrow is locked or unlocked
     */
    @FindAll({
            @FindBy(css = "#media-counter-elapsed"),
            @FindBy(xpath = "/html/body/div[2]/main/section/div[2]/div/div/div[2]/span[1]"),
            @FindBy(xpath = "//*[@id=\"media-counter-elapsed\"]")
    })
    public WebElement elapsedTime;

    public By byElapsedTime = By.cssSelector("#media-counter-elapsed");

    @FindBy(css = "#media-counter-duration")
    public WebElement duration;

    @FindBy(css = "#play-button")
    public WebElement btnPlay;

    @FindBy(xpath = "//*[@id=\"pause-button\"]")
    public WebElement btnPause;

    public Boolean isArrowLocked(){
        return btnRightArrow.getAttribute("class").equals("locked");
    }
    public Boolean isVideoFinished(){
        return elapsedTime.getText().equals(duration.getText());
    }

    public Boolean isArrowUnlocked(){
        waitVideoToBeStarted(byElapsedTime, Duration.ofSeconds(20));
        waitFor(3);
        btnPause.click();
        String textDuration = duration.getText().trim();
        String[] minuteSecond = textDuration.split(":");
        int minute = Integer.parseInt(minuteSecond[0]);
        int second = Integer.parseInt(minuteSecond[1]);
        int durationInSeconds = minute * 60 + second;
        btnPlay.click();
        waitFor(durationInSeconds - 2);
        return btnRightArrow.isDisplayed();
    }

    public Boolean isRightArrowUnlocked(){
        return btnRightArrow.getAttribute("class").equals("active");
    }

    /*****
     *  for Page5
     */
    @FindBy(xpath = "//*[@id=\"PopupButtons-body\"]/div/div[1]/div/div/header/h2")
    public WebElement header5;

    @FindBy(xpath = "//*[@id=\"popup_close-button\"]")
    public WebElement btnClose;

    @FindBy(xpath = "//*[@id=\"PopupButtons-body\"]/div/div[2]/div/div/div/button")
    public List<WebElement> topics;

    public void clickTopic(String topic){
        switch (topic) {
            case "Seek", "Business network", "Profitability" -> topics.get(0).click();
            case "Listen", "Event", "Innovation" -> topics.get(1).click();
            case "Represent", "Conversation", "Effectiveness" -> topics.get(2).click();
        }
        waitFor(5);
    }

    public void clickClosePopup(){
        btnClose.click();
        waitFor(1);
    }

    /*****
     * for Page6
     ********/
    @FindBy(xpath = "//*[@id=\"TextOnly4-body\"]/div/div/header/h2")
    public WebElement header6;

    /*******
     * for Page7
     *******/
    @FindBy(xpath = "//*[@id=\"SectionTitle3-body\"]/div/div/header/h2")
    public WebElement header7;

    /*****
     * for Page8
     ******/
    @FindBy(xpath = "//*[@id=\"TextOnly4-body\"]/div/div/header/h2")
    public WebElement header8;

    /*****
     * for Page9
     ********/
    @FindBy(xpath = "//*[@id=\"SectionTitle3-body\"]/div/div/header/h2")
    public WebElement header9;

    /******
     * for Page10
    *******/
    @FindBy(xpath = "//*[@id=\"TextOnly1-body\"]/div/div/header/h2")
    public WebElement header10;

    @FindBy(xpath = "//*[@id=\"z1004001\"]/div[3]/button[2]")
    public WebElement btnDownArrow;

    public void clickDownArrow(){
        btnDownArrow.click();
        waitFor(1);
    }

    /****
     * for Page11
     */
    @FindBy(xpath = "//*[@id=\"SectionTitle3-body\"]/div/div/header/h2")
    public WebElement header11;

    /*****
     * for Page12
      */
    @FindBy(xpath = "//*[@id=\"SectionTitle3-body\"]/div/div/header/h2")
    public WebElement header12;

    /******
     * for Page13
      */
    @FindBy(xpath = "//section[@id='TextOnly3-body']/div/div/header/h2")
    public WebElement header13;


    /*****
     * for Page15
      */
    @FindBy(xpath = "//header[@class='header']/h2")
    public WebElement header15;

    /*****
     * for Page16
      */

    @FindBy(xpath = "//header[@class='st3-header']/h2")
    public WebElement header16;

    @FindBy(xpath = "//*[@id=\"TextOnly2-body\"]/div/div/header/h2")
    public WebElement header18;

    @FindBy(xpath = "//*[@id=\"MultiColumnStaticList-body\"]/div/div[1]/div/header/h2")
    public WebElement header20;

    @FindBy(xpath = "//*[@id=\"SectionTitle3-body\"]/div/div/header/h2")
    public WebElement header21;

    @FindBy(xpath = "//*[@id=\"MultiColumnStaticList-body\"]/div/div[1]/div/header/h2")
    public WebElement header22;

    @FindBy(xpath = "//*[@id=\"Diploma1-body\"]/section/div[5]/h1")
    public WebElement header23;

    @FindBy(xpath = "//*[@id=\"Diploma1-body\"]")
    public WebElement pageDiploma;

    public String getHeaderText() {
        int pageNum = getPageNumber();
        return switch (pageNum) {
            case 2 -> header2.getText();
            case 3 -> header3.getText();
            case 5 -> header5.getText();
            case 6 -> header6.getText();
            case 7 -> header7.getText();
            case 8 -> header8.getText();
            case 9 -> header9.getText();
            case 10 -> header10.getText();
            case 11 -> header11.getText();
            case 12 -> header12.getText();
            case 13 -> header13.getText();
            case 15 -> header15.getText();
            case 16 -> header16.getText();
            case 18 -> header18.getText();
            case 20 -> header20.getText();
            case 21 -> header21.getText();
            case 22 -> header22.getText();
            case 23 -> header23.getText();
            default -> "Page " + pageNum;
        };
    }

    public void takeScreenshotDiploma() {
        waitForPageToLoad(Duration.ofSeconds(2));
        waitFor(2);
        captureScreenOfSpecificElement(pageDiploma);
    }

}
