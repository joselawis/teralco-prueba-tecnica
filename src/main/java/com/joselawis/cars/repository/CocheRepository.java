package com.joselawis.cars.repository;

import com.joselawis.cars.entity.Coche;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface CocheRepository extends CrudRepository<Coche, Long> {

    @Query(
            "select distinct c from Coche c "
                    + "left join fetch c.precios p "
                    + "where c.id = :id "
                    + "and :date between p.startDate and p.endDate")
    List<Coche> getAllByIdAndDate(@Param("date") Timestamp date, @Param("id") Long id);
}
