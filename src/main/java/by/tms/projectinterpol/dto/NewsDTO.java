package by.tms.projectinterpol.dto;

import by.tms.projectinterpol.entity.News;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NewsDTO implements Serializable {

    private long id;
    private String text;
    private String headline;
    private LocalDate publicationDate;
    private List<TagDTO> tagDTO = new ArrayList<>();

    public NewsDTO(long id, String text, String headline, LocalDate publicationDate, List<TagDTO> tagDTO) {
        this.id = id;
        this.text = text;
        this.headline = headline;
        this.publicationDate = publicationDate;
        this.tagDTO = tagDTO;
    }

    public NewsDTO() {
    }

    public static News.Builder builder() {
        return new News.Builder();
    }

    public static class Builder {
        private long id;
        private String text;
        private String headline;
        private LocalDate publicationDate;
        private List<TagDTO> tagDTO = new ArrayList<>();

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

        public Builder tag(List<TagDTO> tagDTO) {
            this.tagDTO = tagDTO;
            return this;
        }

        public NewsDTO build() {
            return new NewsDTO(id, text, headline, publicationDate, tagDTO);
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

    public List<TagDTO> getTagDTO() {
        return tagDTO;
    }

    public void setTagDTO(List<TagDTO> tagDTO) {
        this.tagDTO = tagDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsDTO news = (NewsDTO) o;
        return id == news.id && Objects.equals(text, news.text) && Objects.equals(headline, news.headline) && Objects.equals(publicationDate, news.publicationDate) && Objects.equals(tagDTO, news.tagDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, headline, publicationDate, tagDTO);
    }

    @Override
    public String toString() {
        return "NewsDTO{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", headline='" + headline + '\'' +
                ", publicationDate=" + publicationDate +
                ", tagDTO=" + tagDTO +
                '}';
    }
}
