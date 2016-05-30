package io.ona.company.waterpoints.json;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.testng.annotations.Test;

public class WaterPointStatusTest {

    @Test
    public void testFromString_ThrowsExceptionOnUnknownRawInput() {

        try {
            String unknownRawInput = "unknown";
            WaterPointStatus.fromString(unknownRawInput);
            fail("WaterPointStatus.create(" + unknownRawInput
                    + ") should throw an "
                    + UnknownWaterPointStatusTextException.class.getName()
                    + "exception");
        } catch (UnknownWaterPointStatusTextException e) {
        }
    }

    @Test
    public void testFromString_IsCaseInsensitive()
            throws UnknownWaterPointStatusTextException {

        assertEquals(WaterPointStatus.fromString("YES"),
                WaterPointStatus.WORKING);

        assertEquals(WaterPointStatus.fromString("yes"),
                WaterPointStatus.WORKING);

        assertEquals(WaterPointStatus.fromString("no"),
                WaterPointStatus.NOT_WORKING);

        assertEquals(WaterPointStatus.fromString("NO"),
                WaterPointStatus.NOT_WORKING);
    }
}
