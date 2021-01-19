package nemop.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String content;
    
    @Column
    private Timestamp timeStamp;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Channel channel;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    
    
    public Message() {
    	super();
    }


	public Message(Long id, String content, Timestamp timeStamp, Channel channel, User user) {
		super();
		this.id = id;
		this.content = content;
		this.timeStamp = timeStamp;
		this.channel = channel;
		this.user = user;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
		if(!channel.getMessages().contains(this)){
			channel.getMessages().add(this);
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		if(!user.getMessages().contains(this)){
			user.getMessages().add(this);
		}
	}


	@Override
	public String toString() {
		return "Message [id=" + id + ", content=" + content + ", timeStamp=" + timeStamp + ", channel=" + channel
				+ ", user=" + user + "]";
	}
    
	
    
}
