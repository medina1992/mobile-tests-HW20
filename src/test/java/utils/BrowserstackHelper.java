package utils;


import java.net.MalformedURLException;
import java.net.URL;


import static io.restassured.RestAssured.given;
import static utils.EnvironmentHelper.bsLogin;
import static utils.EnvironmentHelper.bsPassword;

public class BrowserstackHelper {

    public static URL getBrowserstackUrl() {
        try {
            return new URL("https://" + bsLogin + ":" + bsPassword + "@hub-cloud.browserstack.com/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getBrowserstackVideoUrl(String sessionId) {
        String video_url = given()
                .auth().basic(bsLogin, bsPassword)
                .when()
                .get("https://api-cloud.browserstack.com/app-automate/sessions/" + sessionId +".json")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .path("automation_session.video_url");

        System.out.println("video_url: " + video_url);
        return video_url;
    }

    public static String getBSPublicLink(String sessionId){
        String publicUrl = given()
                .auth().basic(bsLogin, bsPassword)
                .when()
                .get("https://api-cloud.browserstack.com/app-automate/sessions/" + sessionId +".json")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .path("automation_session.public_url");

        System.out.println("bs_public_url: " + publicUrl);
        System.out.println("bs_build_url: " + publicUrl.split("/sessions/")[0]);
        return publicUrl;
    }
}
