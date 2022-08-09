package by.tms.projectinterpol.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class News {

    private long id;
    private String text;
    private String headline;
    private LocalDate publicationDate;
    private List<Tag> tag = new ArrayList<>();

    public News(long id, String text, String headline, LocalDate publicationDate, List<Tag> tag) {
        this.id = id;
        this.text = text;
        this.headline = headline;
        this.publicationDate = publicationDate;
        this.tag = tag;
    }

    public News() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private long id;
        private String text;
        private String headline;
        private LocalDate publicationDate;
        private List<Tag> tag = new ArrayList<>();

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder headline(String headline) {
            this.headline = headline;
            return this;
        }

        public Builder publicationDate(LocalDate publicationDate) {
            this.publicationDate = publicationDate;
            return this;
        }

        public Builder tag(List<Tag> tag) {
            this.tag = tag;
            return this;
        }

        public News build() {
            return new News(id, text, headline, publicationDate, tag);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return id == news.id && Objects.equals(text, news.text) && Objects.equals(headline, news.headline) && Objects.equals(publicationDate, news.publicationDate) && Objects.equals(tag, news.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, headline, publicationDate, tag);
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", headline='" + headline + '\'' +
                ", publicationDate=" + publicationDate +
                ", tag=" + tag +
                '}';
    }
}
