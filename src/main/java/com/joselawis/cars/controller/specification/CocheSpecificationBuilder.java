package com.joselawis.cars.controller.specification;

import com.joselawis.cars.controller.filters.SearchCriteria;
import com.joselawis.cars.entity.Coche;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CocheSpecificationBuilder {

  private final List<SearchCriteria> params;

  /** The constructor */
  public CocheSpecificationBuilder() {
    params = new ArrayList<>();
  }

  public CocheSpecificationBuilder with(String key, String operation, Object value) {
    params.add(new SearchCriteria(key, operation, value));
    return this;
  }

  public Specification<Coche> build() {
    if (params.isEmpty()) {
      return new Specification<Coche>() {

        @Override
        public Predicate toPredicate(
            Root<Coche> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
          // TODO Auto-generated method stub
          return null;
        }
      };
    }

    List<Specification> specs =
        params.stream().map(CocheSpecification::new).collect(Collectors.toList());

    Specification result = specs.get(0);

    for (int i = 1; i < params.size(); i++) {
      result = Specification.where(result).and(specs.get(i));
    }
    return result;
  }
}
