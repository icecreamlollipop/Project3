package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Order.*;
import Roll.*;
import Store.*;
import java.util.*;

class TestThings {

	@Test public void test_roll_factory() {
		Roll r = RollType.factory_create(RollType.EGG);
		assertEquals(2.23, r.cost());
		assertEquals(RollType.EGG, r.type());
	}
	
	@Test public void test_roll_decorating_single() {
		Roll r = RollType.factory_create(RollType.SPRING);
		r = new Fillings(r);
		assertEquals(3.23+.3, r.cost());
		assertEquals(RollType.SPRING, r.type());
	}
	
	@Test public void test_roll_decorating_multi() {
		Roll r = RollType.factory_create(RollType.EGG);
		r = new Toppings(r);
		r = new Sauces(r);
		r = new Toppings(r);
		assertEquals(2.23+.24+.22+.24,r.cost());
		assertEquals(RollType.EGG, r.type());
	}
	
	@Test public void test_customer_factory() {
		Customer r = CustomerType.factory_create(CustomerType.CASUAL);
		assertEquals(CustomerType.CASUAL, r.type());
	}
	
	@Test public void test_business_initial() {
		Customer c = CustomerType.factory_create(CustomerType.BUSINESS);
		Map<RollType,Integer> m = c.initialOrder();
		assertEquals(RollType.values().length, m.size());
		for (RollType rt : RollType.values()) {
			assertEquals(2, m.get(rt));
		}
	}
	@Test public void test_business_shortage() {
		Customer c = CustomerType.factory_create(CustomerType.BUSINESS);
		Map<RollType,Integer> m = c.initialOrder();
		m = c.shortageOrder(null);
		assertEquals(0, m.size());
	}
	
	@Test public void test_casual_initial() {
		Customer c = CustomerType.factory_create(CustomerType.CASUAL);
		Map<RollType,Integer> m = c.initialOrder();
		assertEquals(1, m.size());
		for (int val : m.values()) {
			assertTrue(1 <= val && val <= 3);
		}
		Inventory inv = new Inventory();
		inv.numLeft.put(RollType.EGG, 2);
	}
	@Test public void test_casual_shortage() {
		Customer c = CustomerType.factory_create(CustomerType.CASUAL);
		Map<RollType,Integer> m = c.initialOrder();
		Inventory inv = new Inventory();
		inv.numLeft.put(RollType.SAUSAGE, 1);
		m = c.shortageOrder(inv);
		assertEquals(1,m.size());
		assertEquals(1,m.get(RollType.SAUSAGE));
	}
	
	@Test public void test_catering_initial() {
		Customer c = CustomerType.factory_create(CustomerType.CATERING);
		Map<RollType,Integer> m = c.initialOrder();
		assertEquals(3,m.size());
		for (int val : m.values()) {
			assertEquals(5, val);
		}
	}
	@Test public void test_catering_shortage() {
		Customer c = CustomerType.factory_create(CustomerType.CATERING);
		Map<RollType,Integer> m = c.initialOrder();
		Inventory inv = new Inventory();
		inv.numLeft.put(RollType.JELLY, 1234);
		m = c.shortageOrder(inv);
		assertEquals(5,m.size());
		assertEquals(15,m.get(RollType.JELLY));
	}
	
}
