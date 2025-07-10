package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/${env}.properties"  // будет подставлено значение -Denv=...
})
public interface BrowserStackConfig extends Config {

    @Key("userName")
    String userName();

    @Key("accessKey")
    String accessKey();

    @Key("app")
    String app();

    @Key("deviceName")
    String deviceName();

    @Key("platformName")
    String platformName();

    @Key("platformVersion")
    String platformVersion();

    @Key("projectName")
    String projectName();

    @Key("buildName")
    String buildName();

    @Key("sessionName")
    String sessionName();

    @Key("remoteUrl")
    String remoteUrl();
}

