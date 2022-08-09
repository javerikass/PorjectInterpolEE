package by.tms.projectinterpol.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tag implements Serializable {

    private long id;
    private String tag;
    private List<News> news = new ArrayList<>();

    public Tag(long id, String tag, List<News> news) {
        this.id = id;
        this.tag = tag;
        this.news = news;
    }

    public Tag() {
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private long id;
        private String tag;
        private List<News> news = new ArrayList<>();

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        public Builder news(List<News> news) {
            this.news = news;
            return this;
        }

        public Tag build() {
            return new Tag(id,tag, news);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag1 = (Tag) o;
        return id == tag1.id && Objects.equals(tag, tag1.tag) && Objects.equals(news, tag1.news);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tag, news);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", news=" + news +
                '}';
    }
}
