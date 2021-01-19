package nemop.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import nemop.enumeration.UserRole;

@Entity
@Table(name="users")
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable=false)
    private String firstName;

    @Column(nullable=false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    
    @Column(unique = true, nullable = false)
    private String eMail;
    
    @Column(nullable=false)
    private String gender;
    
    @Enumerated(EnumType.STRING)
    private UserRole role;
    
    @Column
    private Boolean active;
    
    @Column
    private String photoUrl;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Channel> createdChannels;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Channel> favouriteChannels;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> messages;
    
    
    public User(){

    }

	public User(Long id, String firstName, String lastName, String username, String password, String eMail,
			String gender, UserRole role, String photoUrl, List<Channel> createdChannels) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.eMail = eMail;
		this.gender = gender;
		this.role = role;
		this.photoUrl = photoUrl;
		this.createdChannels = createdChannels;
	}



	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public List<Channel> getCreatedChannels() {
		return createdChannels;
	}

	public void setCreatedChannels(List<Channel> createdChannels) {
		this.createdChannels = createdChannels;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", eMail=" + eMail + ", gender=" + gender + ", profileImage="
				+ "]";
	}

	
}
