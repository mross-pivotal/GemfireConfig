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

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gopivotal.bookshop.domain.Customer;

public class VerifyClient {
	static Log log = LogFactory.getLog(VerifyClient.class);

	private ClientCache cache;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context= new ClassPathXmlApplicationContext("META-INF/spring/gemfire/cache-config.xml");
		ClientCache cache = context.getBean("clientCache", ClientCache.class);
		if (cache != null) {
			new VerifyClient(cache).testClient();
		} else {
			System.out.println("Failed to initialize Client Cache");
		}
	}
	
	public VerifyClient(ClientCache cache) {
		this.cache =  cache;
	}

	public void testClient()
	{
		Region<Integer,Customer> customers = cache.getRegion("Customer");
		Customer customer = customers.get(5598);
		if (customer != null) {
			System.out.println("Fetched customer: " + customer);
		} else {
			System.out.println("Failed to fetch customer");
		}
	}
	
	
}
