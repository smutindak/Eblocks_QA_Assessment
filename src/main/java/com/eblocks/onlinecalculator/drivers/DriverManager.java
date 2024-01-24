package com.eblocks.onlinecalculator.drivers;

import com.eblocks.onlinecalculator.enums.Browsers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Objects;


public class DriverManager {

    /**
     * This Driver Manager class implements the web driver logic to be able
     * to initiate the browser while running tests
     * It caters for single and parallel execution of tests and also
     * cross-browser testing using Selenium Grid
     * Check this docker image to see the implementation https://hub.docker.com/r/selenium/hub
     */
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private static final String HUB_URL = "http://localhost:4444/wd/hub";
    private static final Logger LOG = LogManager.getLogger("DriverManager.class");
    private static final String NO_SANDBOX = "--no-sandbox";
    private static final String DISABLE_DEV_SHM = "--disable-dev-shm-usage";
    //private static final String CUSTOM_WINDOW_SIZE = "--window-size=1050,600";
    private static final String HEADLESS = "--headless";

    public static void createDriver(final Browsers browser) {
        switch (browser) {
            case FIREFOX -> setupFirefoxDriver();
            case EDGE -> setupEdgeDriver();
            case REMOTE_CHROME -> setupRemoteChrome();
            case REMOTE_FIREFOX -> setupRemoteFirefox();
            case REMOTE_EDGE -> setupRemoteEdge();
            default -> setupChromeDriver();
        }
        setupBrowserTimeouts();
    }

    public static WebDriver getDriver() {
        return DriverManager.DRIVER.get();
    }

    public static void quitDriver() {
        if (null != DRIVER.get()) {
            LOG.info("Closing the driver...");
            getDriver().quit();
            DRIVER.remove();
        }
    }

    private static void setDriver(final WebDriver driver) {
        DriverManager.DRIVER.set(driver);
    }

    private static void setupBrowserTimeouts() {
        LOG.info("Setting Browser Timeouts....");
        getDriver().manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(30));
        getDriver().manage()
                .timeouts()
                .pageLoadTimeout(Duration.ofSeconds(30));
        getDriver().manage()
                .timeouts()
                .scriptTimeout(Duration.ofSeconds(30));
    }

    private static void setupChromeDriver() {
        LOG.info("Setting up Chrome Driver....");
        final var isHeadless = Boolean.parseBoolean(
                Objects.requireNonNullElse(System.getProperty("headless"), "false"));
        final var chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("safebrowsing.enabled", "true");
        chromePrefs.put("download.default_directory",
                String.valueOf(Paths.get(System.getProperty("user.dir"))));

        final var options = new ChromeOptions();
        options.addArguments(NO_SANDBOX);
        options.addArguments(DISABLE_DEV_SHM);
        //options.addArguments(CUSTOM_WINDOW_SIZE);
        if (isHeadless) {
            options.addArguments(HEADLESS);
        }
        options.addArguments("--safebrowsing-disable-download-protection");
        options.setExperimentalOption("prefs", chromePrefs);

        setDriver(WebDriverManager.chromedriver()
                .capabilities(options)
                .create());
        LOG.info("Chrome Driver created successfully!");
    }

    private static void setupEdgeDriver() {
        LOG.info("Setting up Edge Driver....");
        setDriver(WebDriverManager.edgedriver()
                .create());
        LOG.info("Edge Driver created successfully!");
    }

    private static void setupFirefoxDriver() {
        LOG.info("Setting up Firefox Driver....");
        final var options = new FirefoxOptions();
        options.addArguments(NO_SANDBOX);
        options.addArguments(DISABLE_DEV_SHM);
        //options.addArguments(HEADLESS);
        setDriver(WebDriverManager.firefoxdriver()
                .capabilities(options)
                .create());
        LOG.info("Firefox Driver created successfully!");
    }

    private static void setupRemoteChrome() {
        try {
            LOG.info("Setting up Remote Chrome Driver....");
            final var options = new ChromeOptions();
            options.addArguments(NO_SANDBOX);
            options.addArguments(DISABLE_DEV_SHM);
            setDriver(new RemoteWebDriver(new URL(HUB_URL), options));
            LOG.info("Remote Chrome Driver created successfully!");
        } catch (final MalformedURLException e) {
            LOG.error("Error setting remote_chrome", e);
        }
    }

    private static void setupRemoteEdge() {
        try {
            LOG.info("Setting up Remote Edge Driver....");
            final var edgeOptions = new EdgeOptions();
            edgeOptions.addArguments(NO_SANDBOX);
            edgeOptions.addArguments(DISABLE_DEV_SHM);
            setDriver(new RemoteWebDriver(new URL(HUB_URL), edgeOptions));
            LOG.info("Remote Edge Driver created successfully!");
        } catch (final MalformedURLException e) {
            LOG.error("Error setting remote_edge", e);
        }
    }

    private static void setupRemoteFirefox() {
        try {

            LOG.info("Setting up Remote Firefox Driver....");
            final var firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments(NO_SANDBOX);
            firefoxOptions.addArguments(DISABLE_DEV_SHM);

            setDriver(new RemoteWebDriver(new URL(HUB_URL), firefoxOptions));
            LOG.info("Remote Firefox Driver created successfully!");
        } catch (final MalformedURLException e) {
            LOG.error("Error setting remote_firefox", e);
        }
    }

    private DriverManager() {
    }
}
