import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class EaseMyTripDemo {
    static AppiumDriver driver;

    public static void main(String[] args) throws InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "10BDBK0NL3000H8");
        capabilities.setCapability("appium:app", "C:\\Users\\ADMIN\\Downloads\\MobileAutomationAPKs-master\\MobileAutomationAPKs-master\\EaseMyTrip.apk");
        capabilities.setCapability("appPackage", "com.easemytrip.android");
        capabilities.setCapability("appActivity", "com.easemytrip.android.SplashScreenActivity");

        driver = new AppiumDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement dontAllowButton = driver.findElement(By.id("com.android.permissioncontroller:id/permission_deny_button"));
        dontAllowButton.click();

        WebElement flightsButton = driver.findElement(By.xpath("(//android.widget.TextView[@text=\"Flights\"])[1]"));
        flightsButton.click();

        WebElement fromLocation = driver.findElement(By.id("com.easemytrip.android:id/search_flight_origin_code"));
        fromLocation.click();

        WebElement fromTextbox = driver.findElement(By.id("com.easemytrip.android:id/edt_depart_airport"));
        fromTextbox.click();
        fromTextbox.sendKeys("Mumbai");

        driver.findElement(By.id("com.easemytrip.android:id/search_airport_list_item_name")).click();

        WebElement toLocation = driver.findElement(By.id("com.easemytrip.android:id/search_flight_destination_code"));
        toLocation.click();

        WebElement toTextbox = driver.findElement(By.id("com.easemytrip.android:id/edt_arrival_airport"));
        toTextbox.click();
        toTextbox.sendKeys("Delhi");

        driver.findElement(By.id("com.easemytrip.android:id/search_airport_list_item_name")).click();

        WebElement departureDate = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/search_flight_departure_date\"]"));
        departureDate.click();

        int retry = 0;
        do {
            try {
                WebElement selectDate = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Date is 04 Feb 2025\"]"));
                selectDate.click();
                break;
            } catch (StaleElementReferenceException e) {
                    retry++;
            }
        }while(retry<=2);


        WebElement travellers = driver.findElement(By.id("com.easemytrip.android:id/textViewPaxCount"));
        travellers.click();

        WebElement noOfAdults = driver.findElement(By.id("com.easemytrip.android:id/adult_two"));
        noOfAdults.click();

        WebElement noOfChilds = driver.findElement(By.id("com.easemytrip.android:id/child_two"));
        noOfChilds.click();

        WebElement noOfInfants = driver.findElement(By.id("com.easemytrip.android:id/infant_one"));
        noOfInfants.click();

        WebElement doneButton = driver.findElement(By.id("com.easemytrip.android:id/tvDone"));
        doneButton.click();

        WebElement searchButton = driver.findElement(By.id("com.easemytrip.android:id/button_flight_Search"));
        searchButton.click();

        List<WebElement> flightNames = driver.findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/tv_flight_name\"]"));
        List<WebElement> flightDepartureTimings = driver.findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/tv_flight_timing\"]"));
        List<WebElement> flightArrivalTimings = driver.findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/tv_flight_timing_arival\"]"));
        List<WebElement> flightDurations = driver.findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/tv_travelling_duration\"]"));
        List<WebElement> flightPrices = driver.findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/tv_flight_rate_discounted\"]"));

        for (int numberOfFlights = 1; numberOfFlights <= 2; numberOfFlights++) {


            System.out.println(flightNames.get(numberOfFlights).getText() + " | " +
                    flightDepartureTimings.get(numberOfFlights).getText() + " - " +
                    flightArrivalTimings.get(numberOfFlights).getText() + " | " +
                    flightDurations.get(numberOfFlights).getText() + " | " +
                    flightPrices.get(numberOfFlights).getText()
            );
        }

    }

    public static void scroll(int startX, int startY, int endX, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "Finger");

        Sequence sequence = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));

    }


}
