package org.example.pruebaemployee.repository;

import org.example.pruebaemployee.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.name=:name and e.surname=:surname")
    Optional<Employee> findByNameAndSurname(@Param("name") String name, @Param("surname") String surname);
}
