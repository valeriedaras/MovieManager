package allocine;

import java.util.List;

public class Result {
	
	private Integer code ;
	
	private List<String> originalTitle ;
	
	private List<String> title ;
	
	private String year ;
	
	private int score ;
	
	public Result() {
		this.score = 0 ;
	}
	

	public Integer getCode() {
		return code;
	}

	public List<String> getOriginalTitle() {
		return originalTitle;
	}
	
	public List<String> getTitle() {
		return title;
	}

	public String getYear() {
		return year;
	}

	public int getScore() {
		return score;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public void setOriginalTitle(List<String> originalTitle) {
		this.originalTitle = originalTitle;
	}

	public void setTitle(List<String> title) {
		this.title = title;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setScore(int score) {
		this.score = score;
	}

	
}
