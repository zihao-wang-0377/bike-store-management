package de.pdbm.starter.view;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;

import de.pdbm.starter.business.messages.boundary.MessageService;
import de.pdbm.starter.business.messages.entity.Message;

@Named
@RequestScoped
public class MessageController {
	
	@NotNull
	private String input;
	
	@Inject
	MessageService messageService;
	
	
	public MessageController() {
	}
	
	/**
	 * Saves input as Message text. 
	 */
//	public void save() {
//		messageService.save(new Message(input));
//	}
	public String save() {
		messageService.save(new Message(input));
		return "alles-gut.xhtml?faces-redirction=true";
	}
	
	public Message getLastMessage() {
		return messageService.getLastMessage();
	}

	
	// Getter and Setter
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	
}
