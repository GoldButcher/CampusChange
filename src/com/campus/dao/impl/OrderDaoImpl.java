package com.campus.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.campus.dao.IOrderDao;
import com.campus.dto.OrderDto;
import com.campus.entity.Order;
import com.campus.model.DataTable2Order;

@Repository
public class OrderDaoImpl extends BaseDaoImpl<Order, Integer> implements IOrderDao {
    @Resource
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<OrderDto> getMyOrders(Integer userId) throws Exception {
        String sql = "SELECT go.GOId AS gOId,go.GoodId AS goodId,go.IsChange AS isChange,go.OrderId AS orderId,g.UserId AS sellerUserId,u.UserName AS buyllerName,g.GoodIntroduction AS goodIntroduction,g.GoodName AS sellgoodName,g.Price AS sellprice,O.CreateDate AS createDate,o.ModifyDate AS modifyDate,o.OrderStatus AS orderStatus,g.isOrdered AS isOrdered  "
                + "FROM (((tb_order o JOIN tb_go go ON o.OrderId=go.OrderId) JOIN tb_good g ON g.GoodId=go.GoodId) JOIN tb_user u ON u.UserId=o.UserId) "
                + "WHERE o.UserId=:userId and (go.isChange=1 or go.isChange=2) order by createDate desc ";
        Session session = getSession();
        Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(OrderDto.class));
        query.setInteger("userId", userId);
        List<OrderDto> result = query.list();
        return result;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<OrderDto> getSellOrders(Integer userId) throws Exception {
        String sql = "SELECT go.GOId AS gOId,go.GoodId AS goodId,go.IsChange AS isChange,go.OrderId AS orderId,o.UserId AS buyllerUserId,u.UserName AS buyllerName,g.GoodIntroduction AS goodIntroduction,g.GoodName AS sellgoodName,g.Price AS sellprice,O.CreateDate AS createDate,o.ModifyDate AS modifyDate,o.OrderStatus AS orderStatus,g.isOrdered AS isOrdered  "
                + "FROM  (((tb_good g JOIN tb_go go ON g.GoodId=go.GoodId) JOIN tb_order o ON go.OrderId=o.OrderId) JOIN tb_user u ON o.UserId=u.UserId)"
                + "WHERE g.isOrdered=1 AND g.UserId=:userId and (go.isChange=0 or go.isChange=3) order by createDate desc";
        Session session = getSession();
        Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(OrderDto.class));
        query.setInteger("userId", userId);
        List<OrderDto> result = query.list();
        return result;
    }

	@Override
	public Long getorder(int orderStatus) {
		String hql = "SELECT COUNT(*) FROM Order as o where o.orderStatus =:orderStatus";
		Query query = getSession().createQuery(hql);
		query.setInteger("orderStatus", orderStatus);
		return (Long) query.uniqueResult();
	}

	@Override
	public List<OrderDto> getOrder4DT(DataTable2Order dataTables) {
		 String sql = "SELECT go.GOId AS gOId,go.GoodId AS goodId,go.IsChange AS isChange,go.OrderId AS orderId,g.UserId AS sellerUserId,u.UserName AS sellerName,g.GoodIntroduction AS goodIntroduction,g.GoodName AS sellgoodName,g.Price AS sellprice,O.CreateDate AS createDate,o.ModifyDate AS modifyDate,o.OrderStatus AS orderStatus,g.isOrdered AS isOrdered  "
	                + "FROM (((tb_order o JOIN tb_go go ON o.OrderId=go.OrderId) JOIN tb_good g ON g.GoodId=go.GoodId) JOIN tb_user u ON u.UserId=o.UserId)"
	                +"where go.isChange=0 or go.isChange=2";
	        Session session = getSession();
	        Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(OrderDto.class));
	        query.setMaxResults(dataTables.getLength());
	        query.setFirstResult(dataTables.getStart());
			List<OrderDto> result = query.list();
	        return result;
	}

	@Override
	public Long getorderCount() {
		String sql = "SELECT go.GOId AS gOId,go.GoodId AS goodId,go.IsChange AS isChange,go.OrderId AS orderId,g.UserId AS sellerUserId,u.UserName AS buyllerName,g.GoodIntroduction AS goodIntroduction,g.GoodName AS sellgoodName,g.Price AS sellprice,O.CreateDate AS createDate,o.ModifyDate AS modifyDate,o.OrderStatus AS orderStatus,g.isOrdered AS isOrdered  "
                + "FROM (((tb_order o JOIN tb_go go ON o.OrderId=go.OrderId) JOIN tb_good g ON g.GoodId=go.GoodId) JOIN tb_user u ON u.UserId=o.UserId)"
                +"where go.isChange=0 or go.isChange=2";
        Session session = getSession();
        Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(OrderDto.class));
        return (long) query.list().size();
	}

}