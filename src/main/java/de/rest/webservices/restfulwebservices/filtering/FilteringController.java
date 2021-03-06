package de.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

    @GetMapping("/filtering-statically")
    public SomeBeanForStaticFiltering retriveSomeBean() {
        return new SomeBeanForStaticFiltering("value1", "value2", "value3");
    }

    @GetMapping("/filtering-dynamically")
    public MappingJacksonValue retriveSomeBeanDynamically() {

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        SomeBeanForDynamicFiltering someBean = new SomeBeanForDynamicFiltering("value1", "value2", "value3");
        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        mapping.setFilters(filterProvider);

        return mapping;
    }
}
