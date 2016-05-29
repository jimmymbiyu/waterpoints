package io.ona.company.waterpoints.json;

public enum WaterPointStatus {

    WORKING("YES"), NOT_WORKING("NO");

    private String text;

    private WaterPointStatus(String text) {
        this.text = text;
    }

    public static WaterPointStatus fromString(String rawStatus)
            throws UnknownWaterPointStatusTextException {

        if (rawStatus != null) {
            for (WaterPointStatus status : WaterPointStatus.values()) {
                if (rawStatus.equalsIgnoreCase(status.text)) {
                    return status;
                }
            }
        }
        throw new UnknownWaterPointStatusTextException(rawStatus);
    }

}
