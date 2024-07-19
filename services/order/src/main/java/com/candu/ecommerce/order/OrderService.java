package com.candu.ecommerce.order;

import com.candu.ecommerce.customer.CustomerClient;
import com.candu.ecommerce.exceptions.BusinessException;
import com.candu.ecommerce.orderline.OrderLineRequest;
import com.candu.ecommerce.orderline.OrderLineService;
import com.candu.ecommerce.product.ProductClient;
import com.candu.ecommerce.product.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    public Integer createOrder(OrderRequest request) {

        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists" +
                        " with the provided id::" + request.customerId()));

        this.productClient.purchaseProducts(request.products());

        var order = repository.save(mapper.toOrder(request));

        for (PurchaseRequest purchaseRequest: request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        return null;
    }
}
