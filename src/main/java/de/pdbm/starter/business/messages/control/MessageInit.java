package de.pdbm.starter.business.messages.control;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import de.pdbm.starter.business.messages.entity.Message;

@Startup
@Singleton
public class MessageInit {

	@PersistenceContext
	EntityManager em;
	
	@PostConstruct
	public void init() {
		em.persist(new Message("Hello world"));
	}
}
