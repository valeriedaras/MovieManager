package model;

public class MovieFile {
	
	private String path ;
	
	private String name ;
	
	private String extension ;
	
	public MovieFile() {
		
	}
	
	public MovieFile(String path, String name, String extension) {
		this.path = path ;
		this.name = name ;
		this.extension = extension ;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public String toString() {
		return path+name+extension;
	}
}

