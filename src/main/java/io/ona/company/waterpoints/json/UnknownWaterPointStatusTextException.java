package io.ona.company.waterpoints.json;

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
