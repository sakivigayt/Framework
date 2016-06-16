

package com.drivers;

import java.net.URISyntaxException;

import java.util.ArrayList;

import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class DriverConfig {

    private boolean setAssumeUntrustedCertificateIssuer = true;
    private boolean setAcceptUntrustedCertificates = true;
    private boolean enableJavascript = true;
    private WebDriver driver;
    private String hubUrl;
    private String ffProfilePath;
    private String operaProfilePath;
    private String ffBinPath;
    private String ieDriverPath;
    private String chromeDriverPath;
    private String chromeBinPath;
    private int webSessionTimeout = 90 * 1000;
    public static final int DEFAULT_IMPLICIT_WAIT_TIMEOUT = 5;
    public static final int DEFAULT_EXPLICIT_WAIT_TIME_OUT = 15;
    public static final int DEFAULT_PAGE_LOAD_TIMEOUT = 90;
    private double implicitWaitTimeout = DEFAULT_IMPLICIT_WAIT_TIMEOUT;
    private int explicitWaitTimeout = DEFAULT_EXPLICIT_WAIT_TIME_OUT;
    private int pageLoadTimeout = DEFAULT_PAGE_LOAD_TIMEOUT;
    private String outputDirectory;
    private String browserVersion;
    private Platform webPlatform;
    private String userAgentOverride;
    private String ntlmAuthTrustedUris;
    private String browserDownloadDir;
    private boolean addJSErrorCollectorExtension = false;
    private ArrayList<WebDriverEventListener> webDriverListeners;
    private boolean useFirefoxDefaultProfile = true;
    private int browserWindowWidth = -1;
    private int browserWindowHeight = -1;

    private String proxyHost;

    private String testType;

    
    private String automationName;

    private String appiumServerURL;
    private String mobilePlatformName;
    private String mobilePlatformVersion;
    private String deviceName;
    private String app;

    private String browserName;

    /**
     * 
     */
    private String appPackage;
    private String appActivity;
    private String newCommandTimeout;

    /**Returns the list of WebDriverListeners
     * @return webDriverListeners
     */
    public ArrayList<WebDriverEventListener> getWebDriverListeners() {
        return webDriverListeners;
    }

    /**sets the WebDriverListener
     * @param webDriverListeners
     */
    public void setWebDriverListeners(final ArrayList<WebDriverEventListener> webDriverListeners) {
        this.webDriverListeners = webDriverListeners;
    }

    /**
     * @param listeners
     */
    public void setWebDriverListeners(final String listeners) {
        ArrayList<WebDriverEventListener> listenerList = new ArrayList<WebDriverEventListener>();
        String[] list = listeners.split(",");
        for (String aList : list) {

            WebDriverEventListener listener = null;
            try {
                if (!aList.equals("")) {
                    listener = (WebDriverEventListener) (Class.forName(aList)).newInstance();
                    listenerList.add(listener);
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }

        this.webDriverListeners = listenerList;
    }

    /**
     * @return browserDownloadDir
     */
    public String getBrowserDownloadDir() {
        return browserDownloadDir;
    }

    /**
     * @return browserVersion
     */
    public String getBrowserVersion() {
        return browserVersion;
    }

    /**
     * @return chromeBinPath
     */
    public String getChromeBinPath() {
        return chromeBinPath;
    }

    /**
     * @return chromeDriverPath
     */
    public String getChromeDriverPath() {
        return chromeDriverPath;
    }

    /**
     * @return driver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * @return explicitWaitTimeout
     */
    public int getExplicitWaitTimeout() {
        if (explicitWaitTimeout < getImplicitWaitTimeout()) {
            return (int) getImplicitWaitTimeout();
        } else {
            return explicitWaitTimeout;
        }
    }

    /**
     * @return ffBinPath
     */
    public String getFirefoxBinPath() {
        return ffBinPath;
    }

    /**
     * @return ffProfilePath
     */
    public String getFirefoxProfilePath() {
        if (ffProfilePath == null && getClass().getResource("/profiles/customProfileDirCUSTFF") != null) {

            try {
                return getClass().getResource("/profiles/customProfileDirCUSTFF").toURI().getPath();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        } else {
            return ffProfilePath;
        }
    }

    /**
     * @return hubUrl
     */
    public String getHubUrl() {
        return hubUrl;
    }

    /**
     * @return ieDriverPath
     */
    public String getIeDriverPath() {
        return ieDriverPath;
    }

    /**
     * @return implicitWaitTimeout
     */
    public double getImplicitWaitTimeout() {
        return implicitWaitTimeout;
    }

    /**
     * @return ntlmAuthTrustedUris
     */
    public String getNtlmAuthTrustedUris() {
        return ntlmAuthTrustedUris;
    }

    /**
     * @return operaProfilePath
     */
    public String getOperaProfilePath() {
        if (operaProfilePath == null && getClass().getResource("/profiles/operaProfile") != null) {

            try {
                return getClass().getResource("/profiles/operaProfile").toURI().getPath();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }

        return operaProfilePath;
    }

    /**
     * @return outputDirectory
     */
    public String getOutputDirectory() {
        return outputDirectory;
    }

    /**
     * @return pageLoadTimeout
     */
    public int getPageLoadTimeout() {
        return pageLoadTimeout;
    }

    /**
     * @return webPlatform
     */
    public Platform getWebPlatform() {
        return webPlatform;
    }

    /** 
     * @return proxy
     */
    public Proxy getProxy() {
        Proxy proxy = null;
        if (proxyHost != null) {
            proxy = new Proxy();
            proxy.setProxyType(ProxyType.MANUAL);
            proxy.setHttpProxy(proxyHost);
            proxy.setFtpProxy(proxyHost);
            proxy.setSslProxy(proxyHost);
        }

        return proxy;

    }

    /**
     * @return proxyHost
     */
    public String getProxyHost() {
        return proxyHost;
    }

    /**
     * @return userAgentOverride
     */
    public String getUserAgentOverride() {
        return this.userAgentOverride;
    }

    /**
     * @return webSessionTimeout
     */
    public int getWebSessionTimeout() {
        return webSessionTimeout;
    }

    /**
     * @return addJSErrorCollectorExtension
     */
    public boolean isAddJSErrorCollectorExtension() {
        return addJSErrorCollectorExtension;
    }

    /**
     * @return useFirefoxDefaultProfile
     */
    public boolean isUseFirefoxDefaultProfile() {
        return this.useFirefoxDefaultProfile;
    }

    /**
     * @param useFirefoxDefaultProfile
     */
    public void setUseFirefoxDefaultProfile(final boolean useFirefoxDefaultProfile) {
        this.useFirefoxDefaultProfile = useFirefoxDefaultProfile;
    }

    /**
     * @return enableJavascript
     */
    public boolean isEnableJavascript() {
        return enableJavascript;
    }

    /**
     * @return setAcceptUntrustedCertificates
     */
    public boolean isSetAcceptUntrustedCertificates() {
        return setAcceptUntrustedCertificates;
    }

    /**
     * @return setAssumeUntrustedCertificateIssuer
     */
    public boolean isSetAssumeUntrustedCertificateIssuer() {
        return setAssumeUntrustedCertificateIssuer;
    }

    /**
     * @param addJSErrorCollectorExtension
     */
    public void setAddJSErrorCollectorExtension(final boolean addJSErrorCollectorExtension) {
        this.addJSErrorCollectorExtension = addJSErrorCollectorExtension;
    }

    /**
     * @param browserDownloadDir
     */
    public void setBrowserDownloadDir(final String browserDownloadDir) {
        this.browserDownloadDir = browserDownloadDir;
    }

    /**
     * @param browserVersion
     */
    public void setBrowserVersion(final String browserVersion) {
        this.browserVersion = browserVersion;
    }

    /**
     * @param chromeBinPath
     */
    public void setChromeBinPath(final String chromeBinPath) {
        this.chromeBinPath = chromeBinPath;
    }

    /**
     * @param chromeDriverPath
     */
    public void setChromeDriverPath(final String chromeDriverPath) {
        this.chromeDriverPath = chromeDriverPath;
    }

    /**
     * @param driver
     */
    public void setDriver(final WebDriver driver) {
        this.driver = driver;
    }

    /**
     * @param enableJavascript
     */
    public void setEnableJavascript(final boolean enableJavascript) {
        this.enableJavascript = enableJavascript;
    }

    /**
     * @param explicitWaitTimeout
     */
    public void setExplicitWaitTimeout(final int explicitWaitTimeout) {
        this.explicitWaitTimeout = explicitWaitTimeout;
    }

    /**
     * @param ffBinPath
     */
    public void setFfBinPath(final String ffBinPath) {
        this.ffBinPath = ffBinPath;
    }

    /**
     * @param ffProfilePath
     */
    public void setFfProfilePath(final String ffProfilePath) {
        this.ffProfilePath = ffProfilePath;
    }

    /**
     * @param hubUrl
     */
    public void setHubUrl(final String hubUrl) {
        this.hubUrl = hubUrl;
    }

    /**
     * @param ieDriverPath
     */
    public void setIeDriverPath(final String ieDriverPath) {
        this.ieDriverPath = ieDriverPath;
    }

    /**
     * @param implicitWaitTimeout
     */
    public void setImplicitWaitTimeout(final double implicitWaitTimeout) {
        this.implicitWaitTimeout = implicitWaitTimeout;
    }

    /**
     * @param ntlmAuthTrustedUris
     */
    public void setNtlmAuthTrustedUris(final String ntlmAuthTrustedUris) {
        this.ntlmAuthTrustedUris = ntlmAuthTrustedUris;
    }

    /**
     * @param operaProfilePath
     */
    public void setOperaProfilePath(final String operaProfilePath) {
        this.operaProfilePath = operaProfilePath;
    }

    /**
     * @param outputDirectory
     */
    public void setOutputDirectory(final String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    /**
     * @param pageLoadTimeout
     */
    public void setPageLoadTimeout(final int pageLoadTimeout) {
        this.pageLoadTimeout = pageLoadTimeout;
    }

    /**
     * @param webPlatform
     */
    public void setWebPlatform(final Platform webPlatform) {
        this.webPlatform = webPlatform;
    }

    /**
     * @param proxyHost
     */
    public void setProxyHost(final String proxyHost) {
        this.proxyHost = proxyHost;
    }

    /**
     * @param setAcceptUntrustedCertificates
     */
    public void setSetAcceptUntrustedCertificates(final boolean setAcceptUntrustedCertificates) {
        this.setAcceptUntrustedCertificates = setAcceptUntrustedCertificates;
    }

    /**
     * @param setAssumeUntrustedCertificateIssuer
     */
    public void setSetAssumeUntrustedCertificateIssuer(final boolean setAssumeUntrustedCertificateIssuer) {
        this.setAssumeUntrustedCertificateIssuer = setAssumeUntrustedCertificateIssuer;
    }

    /**
     * @param userAgentOverride
     */
    public void setUserAgentOverride(final String userAgentOverride) {
        this.userAgentOverride = userAgentOverride;
    }

    /**
     * @param webSessionTimeout
     */
    public void setWebSessionTimeout(final int webSessionTimeout) {
        this.webSessionTimeout = webSessionTimeout;
    }

    /**
     * @return browserWindowWidth
     */
    public int getBrowserWindowWidth() {
        return browserWindowWidth;
    }

    /**
     * @param browserWindowWidth
     */
    public void setBrowserWindowWidth(final int browserWindowWidth) {
        this.browserWindowWidth = browserWindowWidth;
    }

    /**
     * @return browserWindowHeight
     */
    public int getBrowserWindowHeight() {
        return browserWindowHeight;
    }

    /**
     * @param browserWindowHeight
     */
    public void setBrowserWindowHeight(final int browserWindowHeight) {
        this.browserWindowHeight = browserWindowHeight;
    }

    /**
     * @return automationName
     */
    public String getAutomationName() {
        return automationName;
    }

    /**
     * @param automationName
     */
    public void setAutomationName(final String automationName) {
        this.automationName = automationName;
    }

    /**
     * @return mobilePlatformVersion
     */
    public String getMobilePlatformVersion() {
        return mobilePlatformVersion;
    }

    /**
     * @param mobilePlatformVersion
     */
    public void setMobilePlatformVersion(final String mobilePlatformVersion) {
        this.mobilePlatformVersion = mobilePlatformVersion;
    }

    /**
     * @return deviceName
     */ 
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * @param deviceName
     */
    public void setDeviceName(final String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * @return app
     */
    public String getApp() {
        return app;
    }

    /**
     * @param app
     */
    public void setApp(final String app) {
        this.app = app;
    }

    /**
     * @return browserName
     */
    public String getBrowserName() {
        return browserName;
    }

    /**
     * @param browserName
     */
    public void setBrowserName(final String browserName) {
        this.browserName = browserName;
    }

    /**
     * @return appPackage
     */ 
    public String getAppPackage() {
        return appPackage;
    }

    /**
     * @param appPackage
     */
    public void setAppPackage(final String appPackage) {
        this.appPackage = appPackage;
    }

    /**
     * @return appActivity
     */
    public String getAppActivity() {
        return appActivity;
    }

    /**
     * @param appActivity
     */
    public void setAppActivity(final String appActivity) {
        this.appActivity = appActivity;
    }

    /**
     * @return newCommandTimeout
     */
    public String getNewCommandTimeout() {
        return newCommandTimeout;
    }

    /**
     * @param newCommandTimeout
     */
    public void setNewCommandTimeout(final String newCommandTimeout) {
        this.newCommandTimeout = newCommandTimeout;
    }

    /**
     * @return appiumServerURL
     */
    public String getAppiumServerURL() {
        return appiumServerURL;
    }

    /**
     * @param appiumServerURL
     */
    public void setAppiumServerURL(final String appiumServerURL) {
        this.appiumServerURL = appiumServerURL;
    }

    /**
     * @return mobilePlatformName
     */
    public String getMobilePlatformName() {
        return mobilePlatformName;
    }

    /**
     * @param mobilePlatformName
     */
    public void setMobilePlatformName(final String mobilePlatformName) {
        this.mobilePlatformName = mobilePlatformName;
    }

    /**
     * @return testType
     */
    public String getTestType() {
        return testType;
    }

    /**
     * @param testType
     */
    public void setTestType(final String testType) {
        this.testType = testType;
    }
}

