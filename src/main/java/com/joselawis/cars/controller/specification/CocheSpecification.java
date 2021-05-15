package com.joselawis.cars.controller.specification;

import com.joselawis.cars.controller.filters.SearchCriteria;
import com.joselawis.cars.entity.Coche;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CocheSpecification implements Specification<Coche> {
  private final SearchCriteria criteria;

  public CocheSpecification(SearchCriteria criteria) {
    this.criteria = criteria;
  }

  @Override
  public Predicate toPredicate(Root<Coche> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    // TODO Mapear en funcion de la key y del operator
    if (criteria.getOperation().equalsIgnoreCase("eq")) {
      return builder.equal(root.get(criteria.getKey()), criteria.getValue());
    }
    return null;
  }
}
