package mojtaba.safaeian.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Mojtaba Safaeian
 *         Created at: 09/25/2017.
 */
public class ChartRequest {

    @NotNull
    @Size(min = 1, max = 1, message = "Dimension must contain exactly one value")
    private List<String> dimensions;

    @NotNull
    @Size(min = 1, message = "measures must contain at least one value")
    private List<String> measures;

    public List<String> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<String> dimensions) {
        this.dimensions = dimensions;
    }

    public List<String> getMeasures() {
        return measures;
    }

    public void setMeasures(List<String> measures) {
        this.measures = measures;
    }
}
