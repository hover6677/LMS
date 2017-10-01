package com.db.mongodb;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public abstract class AbstractDAO {
	protected static AbstractDAO DAO ;
	protected AbstractDAO() {
		
	}
	
	public abstract boolean connDAO();
	public abstract MongoCollection getCollection();
	public abstract void setCollection();
	public abstract boolean addOrUpdate(Document doc);
	public abstract Document isFound(Document doc);
	public abstract boolean update(Document found, Document doc);
	//protected abstract boolean softDelete(Document found);
	//protected abstract boolean revertSoftDeletion(Document found);
	public abstract void closeDBConn();
	public abstract ArrayList fetch(Document request);
	
}
