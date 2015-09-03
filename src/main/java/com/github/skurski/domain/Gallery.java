package com.github.skurski.domain;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="gallery")
public class Gallery implements Serializable {

	@Id
	@GeneratedValue
	private int id;

	private String path;
	private String title;
	
    @ManyToOne
    @JoinColumn(name="travel_id", nullable=false)
    private Travel travel;
    
    public Gallery() {}
    
    public Gallery(String path, String title, Travel travel) {
        this.path = path;
        this.title = title;
        this.travel = travel;
    }

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Travel getTravel() {
		return travel;
	}

	public void setTravel(Travel travel) {
		this.travel = travel;
	}

}
