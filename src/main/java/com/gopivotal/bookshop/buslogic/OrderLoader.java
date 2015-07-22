package com.gopivotal.bookshop.buslogic;

import java.util.ArrayList;
import java.util.Date;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gopivotal.bookshop.domain.BookOrder;
import com.gopivotal.bookshop.domain.BookOrderItem;
import com.gopivotal.bookshop.domain.InventoryItem;

public class OrderLoader
{
	
	private ClientCache cache;
	
	public static void main(String[] args)
	{
		OrderLoader loader = new OrderLoader();
		loader.getCache();
		loader.populateBookOrders();
		loader.closeCache();
	}
	
	public void setCache (ClientCache cache)
	{
		this.cache = cache;
	}
	

	public void populateBookOrders()
	{
		Region<Integer, BookOrder> orders = cache.getRegion("BookOrder");
		// Order for Kari Powell for book: A Treatise of Treatises
		BookOrder order1 = new BookOrder(new Integer(17699), new Date(), (float)5.99, new Date(), new ArrayList(), new Integer(5598), (float)40.98);
		order1.addOrderItem(new BookOrderItem (1, new Integer(123), (float)1, (float)0));
		orders.put(new Integer(17699), order1);
		
		// Order for Lula Wax   book: A Treatise of Treatises & Clifford the Big Red Dog
		BookOrder order2 = new BookOrder(new Integer(17700), new Date(), (float)5.99, new Date(), new ArrayList(), new Integer(5543), (float)52.97);
		order2.addOrderItem(new BookOrderItem (1, new Integer(123), (float)1, (float)0));
		order2.addOrderItem(new BookOrderItem(2,new Integer(456), (float)1,(float)0));
		orders.put(new Integer(17700), order2);
	}
	
	
	public void populateInventory()
	{
		Region <Integer, InventoryItem> inventory = cache.getRegion("InventoryItem");
		inventory.put(new Integer(123), new InventoryItem(123, (float)12.50, (float)34.99, (float)12.0, "BookRUs", "Seattle"));
		inventory.put(new Integer(456), new InventoryItem(456, (float)12.50, (float)11.99, (float)1.0, "BookRUs", "Seattle"));
	}
	
	public void closeCache()
	{
		cache.close();
	}
	
	public void getCache()
	{
		this.cache = new ClientCacheFactory()
        .set("name", "ClientWorker")
        .set("cache-xml-file", "xml/clientCache.xml")
        .create();
	}
}
