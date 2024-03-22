package de.pdbm.starter.business.messages.boundary;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import de.pdbm.starter.business.messages.entity.Message;

@Stateless
public class MessageService {

	@PersistenceContext
	EntityManager em;
	
	
	public void save(Message message) {
		em.persist(message);
	}
	
	
	public List<Message> getAllMessages() {
		return em.createQuery("SELECT m FROM Message m ORDER by m.id", Message.class).getResultList();
	}
	
	
	public Message getLastMessage() {
		return em.createQuery("SELECT m FROM Message m WHERE m.id = (SELECT MAX(m.id) FROM Message m)", Message.class).getSingleResult();
	}
	
}
