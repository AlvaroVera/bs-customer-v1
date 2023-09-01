package pe.intercorp.retail.customers.model.specification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import pe.intercorp.retail.customers.model.dto.CustomerDto;
import pe.intercorp.retail.customers.model.entity.CustomerEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CustomerSpecification implements Specification<CustomerEntity> {

    private String email;
    private String dni;

    @Override
    public Predicate toPredicate(Root<CustomerEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (dni != null) {
            predicates.add(criteriaBuilder.equal(root.get("dni"), dni));
        }

        if (email != null) {
            predicates.add(criteriaBuilder.equal(root.get("email"), email));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
