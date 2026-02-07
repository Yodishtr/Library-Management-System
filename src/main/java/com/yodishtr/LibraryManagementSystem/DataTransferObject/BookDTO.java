package com.yodishtr.LibraryManagementSystem.DataTransferObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.jfr.DataAmount;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDTO {

    private Integer totalItems;
    private List<BookItem> items;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class BookItem{
        private String id;
        private VolumeInfo volumeInfo;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class VolumeInfo{
        private String title;
        private List<String> authors;
        private String publisher;
        private String publishedDate;
        private String description;
        private Integer pageCount;
        private List<String> categories;
        private ImageLinks imageLinks;
        private String language;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ImageLinks{
        private String thumbnail;
        private String smallThumbnail;
        private String small;
        private String medium;
        private String large;

        public String getBestImage() {
            if (medium != null) return medium;
            if (small != null) return small;
            if (thumbnail != null) return thumbnail;
            return smallThumbnail;
        }
    }
}
