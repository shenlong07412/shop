package net.shopxx.util;

import java.util.List;

import net.shopxx.Filter;
import net.shopxx.Order;
import net.shopxx.Pageable;
import net.shopxx.Filter.Operator;
import net.shopxx.Order.Direction;

public class PageableUtils {
	public static void setFilter(Pageable pageable,Operator op, String property, Object object) {
		List<Filter> filters = pageable.getFilters();
		Filter filter = new Filter();
		filter.setProperty(property);
        filter.setValue(object);
        filter.setOperator(op);
        filters.add(filter);
        pageable.setFilters(filters);
	}
	
	public static void setOrders(Pageable pageable,String property, Direction direction) {
		List<Order> orders = pageable.getOrders();
		Order order=new Order();
		order.setProperty(property);
		order.setDirection(direction);
		orders.add(order);
		pageable.setOrders(orders);
	}
}
