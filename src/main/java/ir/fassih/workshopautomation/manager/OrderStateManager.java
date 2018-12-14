package ir.fassih.workshopautomation.manager;

import ir.fassih.workshopautomation.entity.orderstate.OrderStateEntity;
import ir.fassih.workshopautomation.repository.OrderStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderStateManager extends AbstractManager<OrderStateEntity, Long> {


    private final static String REGISTRATION_CODE = "REGISTRATION";
    private final static String REJECTED_CODE     = "REJECTED";



    @Autowired
    public OrderStateManager(OrderStateRepository repository) {
        super(repository, OrderStateEntity.class);
    }


    private OrderStateRepository getMyRepo() {
        return (OrderStateRepository) repository;
    }


    @Transactional(readOnly = true)
    public OrderStateEntity loadFirstStates() {
        return getMyRepo().findOneByCode(REGISTRATION_CODE);
    }

    @Transactional(readOnly = true)
    public OrderStateEntity nextOf(OrderStateEntity entity) {
        return repository.findOne((root, query, cb) ->
            cb.and( cb.equal( root.get("parent"), entity.getId() ),
                cb.or(cb.notEqual(root.get("deleted"), Boolean.TRUE), cb.isNull(root.get("deleted")) ) ) );
    }

}
