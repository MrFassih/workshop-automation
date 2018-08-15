package ir.fassih.workshopautomation.rest;

import ir.fassih.workshopautomation.core.datamanagment.model.SearchModel;
import ir.fassih.workshopautomation.entity.order.OrderEntity;
import ir.fassih.workshopautomation.manager.GoodsManager;
import ir.fassih.workshopautomation.manager.OrderManager;
import ir.fassih.workshopautomation.manager.OrderStateManager;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/rest/myOrder")
public class MyOrderService extends AbstractRestService<OrderEntity, Long> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private GoodsManager goodsManager;

    @Autowired
    private OrderStateManager orderStateManager;

    @Autowired
    public MyOrderService(OrderManager manager) {
        super(manager);
    }

    @Override
    public Page<OrderEntity> search(@RequestParam Map<String, String> params) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SearchModel searchModel = createSearchModel(params);
        searchModel.getFilters().put("EQ:creator.username", authentication.getName());
        return manager.search(searchModel);
    }

    @Override
    protected Map<String, Object> optionsInternal() {
        Map<String, Object> optionsInternal = super.optionsInternal();
        optionsInternal.put("products", goodsManager.loadNotDeletes().stream().map(p -> mapper.map(p, ProductDto.class))
                .collect(Collectors.toList()));
        optionsInternal.put("states", orderStateManager.loadAll());
        return optionsInternal;
    }



    @Override
    public void save(OrderEntity entity) {
        throw new UnsupportedOperationException();
    }


    @Data
    public static class ProductDto {
        private Long id;
        private String title;
    }

}
