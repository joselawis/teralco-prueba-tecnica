package com.joselawis.cars.controller.filters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class StringToCocheFilter implements Converter<String, CocheFilter> {

  private static final String SEPARATOR = "&";
  private static final String EQUALS_OPERATOR = " eq ";

  @Override
  public CocheFilter convert(String filterString) {
    String[] filters = filterString.split(SEPARATOR);
    ArrayList<SearchCriteria> criteria = new ArrayList<>();
    for (String filter : filters) {
      criteria.add(stringToSearchCriteria(filter));
    }
    return CocheFilter.builder().criteria(criteria).build();
  }

  private SearchCriteria stringToSearchCriteria(String filter) {
    String key = filter.split(EQUALS_OPERATOR)[0];
    String value = filter.split(EQUALS_OPERATOR)[1];
    return SearchCriteria.builder().key(key).operation(EQUALS_OPERATOR.trim()).value(value).build();
  }
}
