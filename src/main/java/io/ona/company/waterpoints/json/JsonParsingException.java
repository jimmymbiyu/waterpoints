package io.ona.company.waterpoints.json;

/**
 * Thrown when the json cannot be parsed.
 * 
 * @author Ndung'u Mbiyu
 *
 */
public class JsonParsingException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public JsonParsingException(Throwable throwable) {
        super(throwable);
    }
}
