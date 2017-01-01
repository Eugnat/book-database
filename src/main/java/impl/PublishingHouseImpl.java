package impl;

import java.util.List;

import javax.persistence.EntityManager;

import dao.PublishingHouseDAO;
import entities.PublishingHouse;
import utilityclasses.Factory;

public class PublishingHouseImpl implements PublishingHouseDAO {
	
	@Override
	public PublishingHouse findHouseByName(String name) {
		
		EntityManager em = Factory.getEntityManager();
		
		PublishingHouse house = em.createQuery("select h from PublishingHouse h where h.houseName = :name", PublishingHouse.class)
				                  .setParameter("name", name)
				                  .getSingleResult();
		
		return house;
		
	}

	@Override
	public List<PublishingHouse> findAll() {
		
		EntityManager em = Factory.getEntityManager();
		
		List<PublishingHouse> houseList = em.createNamedQuery("select h from PublishingHouse h", PublishingHouse.class).getResultList();
		
		return houseList;
	}

	@Override
	public PublishingHouse findById(int id) {
		
		EntityManager em = Factory.getEntityManager();
		
		PublishingHouse house = em.find(PublishingHouse.class, id);
		
		return house;
	}

}
