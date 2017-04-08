package revolware.com.hackaton_android.data_access;

/**
 * Created by xgallom on 08-Apr-17.
 */

public enum WebApiRefs {
    RSS_FEED("feed");

    private URI uri;
    private WebApiRefs(String uri){
        this.uri = new URI(uri);
    }

    public URI getURI (Server server) {
        return server.getUri().path(uri);
    }

}