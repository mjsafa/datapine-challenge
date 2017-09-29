package mojtaba.safaeian.web.rest;

import mojtaba.safaeian.application.service.ChartService;
import mojtaba.safaeian.domain.chart.ChartResult;
import mojtaba.safaeian.domain.chart.InvalidQueryException;
import mojtaba.safaeian.domain.chart.Query;
import mojtaba.safaeian.web.dto.ChartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mojtaba Safaeian
 *         Created at: 09/25/2017.
 */


@RestController
@RequestMapping("/chart")
public class ChartController {

    @Autowired
    private ChartService chartService;

    @ResponseStatus(value= HttpStatus.BAD_REQUEST,
            reason="Invalid Query")  // 400
    @ExceptionHandler(InvalidQueryException.class)
    public void conflict() {
        // Nothing to do
    }

    @GetMapping
    public ChartResult getChart(@NotNull @Valid ChartRequest chartRequest){
        List<Query> queries = chartRequest.getMeasures().stream()
                .map(measure -> new Query(chartRequest.getDimensions().get(0), measure))
                .collect(Collectors.toList());
        return chartService.createChartResults(queries);
    }
}
