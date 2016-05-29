package io.ona.company.waterpoints.json;

/**
 * 
 * Thrown when a String cannot be converted to a {@link WaterPointStatus}
 * member.
 * 
 * @author Ndung'u Mbiyu
 *
 */
public class UnknownWaterPointStatusTextException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String unknownWaterPointStatusText;

    public UnknownWaterPointStatusTextException(
            String unknownWaterPointStatusText) {
        super();
        this.unknownWaterPointStatusText = unknownWaterPointStatusText;
    }

    @Override
    public String toString() {

        return "UnknownWaterPointStatusTextException [unknownWaterPointStatusText="
                + unknownWaterPointStatusText + "]";
    }
}
