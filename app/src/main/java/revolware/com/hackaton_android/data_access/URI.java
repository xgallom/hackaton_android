package revolware.com.hackaton_android.data_access;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class URI {
    private String path;

    public URI (String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    /**
     *
     * @param name template identifier
     * @param value value which will replace the template
     * @return new URI with replaced template
     */
    public URI resolveTemplate(String name, String value) {
        return new URI(getPath().replace("{" + name + "}", value));
    }

    /**
     *
     * @return URL Object
     * @throws MalformedURLException
     */
    public URL getURL () throws MalformedURLException {
        return new URL(getPath());
    }

    public URI path(URI uri){
        return new URI(getPath() + (getPath().endsWith("/") ? "" : "/") + uri.getPath());
    }
}