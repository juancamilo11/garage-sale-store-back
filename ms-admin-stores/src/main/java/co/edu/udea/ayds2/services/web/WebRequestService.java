package co.edu.udea.ayds2.services.web;

import co.edu.udea.ayds2.services.email.interfaces.MailSender;
import co.edu.udea.ayds2.services.web.interfaces.WebRequest;

public class WebRequestService {

    private final WebRequest webRequest;

    public WebRequestService(WebRequest webRequest) {
        this.webRequest = webRequest;
    }

    public WebRequest getWebRequest() {
        return webRequest;
    }
}
