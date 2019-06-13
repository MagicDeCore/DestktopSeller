package app.entity.external.data.auth.repository;

import app.entity.external.data.auth.domain.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer> {
    ArrayList<Company> findAllByName(String name);
    Company findByName(String name);
    Company findByUserId(Integer userId);
}