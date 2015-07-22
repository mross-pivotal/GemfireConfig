/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gopivotal.training;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gopivotal.bookshop.buslogic.BookLoader;
import com.gopivotal.bookshop.buslogic.CustomerLoader;
import com.gopivotal.bookshop.buslogic.OrderLoader;

public class LoadRegions {
	static Log log = LogFactory.getLog(LoadRegions.class);

	private ClientCache cache;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context= new ClassPathXmlApplicationContext("META-INF/spring/gemfire/cache-config.xml");
		ClientCache cache = context.getBean("clientCache", ClientCache.class);
		if (cache != null) {
			new LoadRegions(cache).populateGemFire();
		} else {
			System.out.println("Failed to initialize Client Cache");
		}
	}
	
	public LoadRegions(ClientCache cache) {
		this.cache =  cache;
	}

	public void populateGemFire()
	{
		this.populateBooks();
		this.populateCustomers();
		this.populateOrders();
	}
	
	public void populateCustomers()
	{
		CustomerLoader loader = new CustomerLoader();
		loader.setCache(cache);
		loader.getCustomerRegion();
		loader.populateCustomers();
	}
	
	public void populateOrders()
	{
		OrderLoader loader = new OrderLoader();
		loader.setCache(cache);
		loader.populateBookOrders();
		loader.populateInventory();
	}
	
	public void populateBooks()
	{
		BookLoader loader = new BookLoader();
		loader.setCache(cache);
		loader.getBookMasterRegion();
		loader.populateBooks();
		
	}
	
}
