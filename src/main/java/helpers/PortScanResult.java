package helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public final class PortScanResult {

    private final String ip;
    private final int port;
    private final boolean isOpen;

}
