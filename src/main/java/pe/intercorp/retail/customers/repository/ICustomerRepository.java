package pe.intercorp.retail.customers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.intercorp.retail.customers.model.entity.CustomerEntity;
import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long>, JpaSpecificationExecutor<CustomerEntity> {

    @Query(nativeQuery = true, value = "SELECT year,month,count(*) as born FROM \n" +
            "(SELECT YEAR(fecha_nacimiento) as year, MONTH(fecha_nacimiento) as month\n" +
            "  FROM customers \n" +
            "  ) as t\n" +
            "GROUP BY year,month order by year desc, month desc, born desc")
    List<Object> getIndicators();

}
