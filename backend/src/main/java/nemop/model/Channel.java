package nemop.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="channels")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique=true,nullable=false)
    private String name;
    
    @Column
    private String details;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private User createdByUser;
    
    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> messages;
    
    
    public Channel() {
    	super();
    }

	public Channel(Long id, String name, String details, User createdByUser,
			List<Message> messages) {
		super();
		this.id = id;
		this.name = name;
		this.details = details;
		this.createdByUser = createdByUser;
		this.messages = messages;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public User getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
		if(!createdByUser.getCreatedChannels().contains(this)){
			createdByUser.getCreatedChannels().add(this);
		}
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	public void addMessages(Message message) {
		if(message.getChannel() != this) {
			message.setChannel(this);
		}
		messages.add(message);
	}

	@Override
	public String toString() {
		return "Channel [id=" + id + ", name=" + name + ", details=" + details + ", createdByUser=" + createdByUser
				+ ", messages=" + messages + "]";
	}
    
    
}
