package fr.esipe.ing3.pds.eight.bem;

/**
 * @author Warren D'ALMEIDA
 */

public class RSSFeed {
    protected String title;
    protected String link;
    protected String description;

	public RSSFeed() {
	}

	public RSSFeed(String title, String link, String description) {
		this.title = title;
		this.link = link;
		this.description = description;
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
}
