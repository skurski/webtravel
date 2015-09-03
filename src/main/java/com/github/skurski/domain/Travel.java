package com.github.skurski.domain;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="travel")
public class Travel implements Serializable {

	private static final long serialVersionUID = 4756067774633614109L;

	@Id
	@GeneratedValue
	private int id;

	private String name;
	private String location;
	private String description;
	private String latitude;
	private String longitude;
	
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    
    @OneToMany(mappedBy="travel")
    private Set<Gallery> gallery;
    
    @Transient
    private String thumbnail = "resources/upload/thumbnail.png";
    
    public Travel() {}
    
    public Travel(String name, String location, String description, String latitude, String longitude, User user, Set<Gallery> gallery) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user = user;
        this.gallery = gallery;
    }

	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLongitude() {
		return longitude;
	}
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getLatitude() {
		return latitude;
	}
	
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
    public Set<Gallery> getGallery() {
    	return gallery;
    }
    
    public void setGallery(Set<Gallery> gallery) {
    	this.gallery = gallery;
    }
    
	public void setThumbnail(String thumb) {
		thumbnail = thumb;
	}

	public String getThumbnail() {
		return thumbnail;
	}

}
