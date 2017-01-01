package dao;

import java.util.List;

import entities.PublishingHouse;

public interface PublishingHouseDAO {

	PublishingHouse findHouseByName(String name);
	
	List<PublishingHouse> findAll();
	PublishingHouse findById(int id);
}
