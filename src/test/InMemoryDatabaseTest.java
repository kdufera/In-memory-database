package test;

import static org.junit.Assert.*;

import org.junit.Test;

import database.InMemoryDatabase;

public class InMemoryDatabaseTest { 
	
	// Test cases used to test specific methods 

	@Test
	public void testSetData() {
		InMemoryDatabase inMemDatabase = new InMemoryDatabase();
		inMemDatabase.setData("SET a foo");
        assertEquals("foo",inMemDatabase.getData("GET a"));
	}
	
	@Test
	public void testDeleteData() {
		InMemoryDatabase inMemDatabase = new InMemoryDatabase();
		inMemDatabase.setData("SET a foo");
		inMemDatabase.deleteData("DELETE a");
        assertEquals("NULL",inMemDatabase.getData("GET a"));
	}
	
	@Test
	public void testGetData() {
		InMemoryDatabase inMemDatabase = new InMemoryDatabase();
		inMemDatabase.setData("SET a foo");
        assertEquals("foo",inMemDatabase.getData("GET a"));
	}
	

	@Test
	public void testRollBackTransactions() {
		InMemoryDatabase inMemDatabase = new InMemoryDatabase();
		inMemDatabase.beginTransactions("BEGIN");
		inMemDatabase.setData("SET a foo");
		inMemDatabase.beginTransactions("BEGIN");
		inMemDatabase.setData("SET a bar");
		inMemDatabase.rollBackTransactions("ROLLBACK");
        assertEquals("foo",inMemDatabase.getData("GET a"));
	}

}
