package nemop.web.dto;

public class ChannelDTO {

	private Long id;
	
	private String name;
	
	private String details;
	
	private Long createdByUserId;
	private String createdByUsername;
	
	private Long starredByUserId;
	private String starredByUsername;
	
	
	public ChannelDTO() {
		super();
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

	public Long getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCreatedByUserId(Long createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	public String getCreatedByUsername() {
		return createdByUsername;
	}

	public void setCreatedByUsername(String createdByUsername) {
		this.createdByUsername = createdByUsername;
	}

	public Long getStarredByUserId() {
		return starredByUserId;
	}

	public void setStarredByUserId(Long starredByUserId) {
		this.starredByUserId = starredByUserId;
	}

	public String getStarredByUsername() {
		return starredByUsername;
	}

	public void setStarredByUsername(String starredByUsername) {
		this.starredByUsername = starredByUsername;
	}
	
}
