package com.software.domain;

public class Warehourse {
    private Integer id;
    private String name;
    private String location;
    private String time;
    private String label;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

	@Override
	public String toString() {
		return "Warehourse [id=" + id + ", name=" + name + ", location=" + location + ", time=" + time + ", label="
				+ label + "]";
	}
    
    
}