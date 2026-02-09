package com.yodishtr.LibraryManagementSystem.Mappers;

import com.yodishtr.LibraryManagementSystem.DataTransferObject.BookDTO;
import com.yodishtr.LibraryManagementSystem.Entities.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookMapper {

    public BookMapper(){}

    public Book getBookFromBookDTOWithIsbn(BookDTO bookDTO, String isbn) {
        Book currBook = new Book();
        if (bookDTO.getItems() == null){
            return null;
        }
        BookDTO.BookItem bookItem = bookDTO.getItems().get(0);
        if (bookItem.getVolumeInfo() == null){
            return null;
        }
        BookDTO.VolumeInfo volumeInfo = bookItem.getVolumeInfo();
        currBook.setIsbn(isbn);
        currBook.setTitle(volumeInfo.getTitle());
        if (volumeInfo.getAuthors() != null){
            currBook.setAuthor(String.join(", ", volumeInfo.getAuthors()));
        }
        currBook.setPageCount(volumeInfo.getPageCount());
        currBook.setPublisher(volumeInfo.getPublisher());
        currBook.setDescription(volumeInfo.getDescription());
        currBook.setLanguage(volumeInfo.getLanguage());
        if (volumeInfo.getCategories() != null && !volumeInfo.getCategories().isEmpty()){
            currBook.setCategory(volumeInfo.getCategories().get(0));
        }
        if (volumeInfo.getPublishedDate() != null){
            String[] publishedDate = volumeInfo.getPublishedDate().split("-");
            currBook.setPublicationYear(Integer.parseInt(publishedDate[0]));
        }
        if (volumeInfo.getImageLinks() != null){
            currBook.setThumbnail(volumeInfo.getImageLinks().getBestImage());
        }

        return currBook;
    }
}
