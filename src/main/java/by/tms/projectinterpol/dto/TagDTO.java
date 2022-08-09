package by.tms.projectinterpol.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TagDTO implements Serializable {

    private String tag;
    private List<NewsDTO> newsDTO = new ArrayList<>();

    public TagDTO(String tag, List<NewsDTO> newsDTO) {
        this.tag = tag;
        this.newsDTO = newsDTO;
    }

    public TagDTO() {
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private String tag;
        private List<NewsDTO> newsDTO = new ArrayList<>();

        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        public Builder news(List<NewsDTO> newsDTO) {
            this.newsDTO = newsDTO;
            return this;
        }

        public TagDTO build() {
            return new TagDTO(tag, newsDTO);
        }
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<NewsDTO> getNews() {
        return newsDTO;
    }

    public void setNews(List<NewsDTO> newsDTO) {
        this.newsDTO = newsDTO;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tag='" + tag + '\'' +
                ", news=" + newsDTO +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagDTO tagDTO = (TagDTO) o;
        return Objects.equals(tag, tagDTO.tag) && Objects.equals(newsDTO, tagDTO.newsDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag, newsDTO);
    }
}
