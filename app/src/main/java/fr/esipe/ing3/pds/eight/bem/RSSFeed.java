package fr.esipe.ing3.pds.eight.bem;

/**
 * @author Warren D'ALMEIDA
 */

public class RSSFeed {
    private String title;
	private String link;
	private String description;
	private String img;

	public RSSFeed() {
	}

	public RSSFeed(String title, String link, String description) {
		this.title = title;
		this.link = link;
		this.description = description;
	}

	public RSSFeed(String title, String link, String description, String img) {
		this.title = title;
		this.link = link;
		this.description = description;
		this.img = img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
}
