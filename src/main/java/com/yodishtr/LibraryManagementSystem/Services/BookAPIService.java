package com.yodishtr.LibraryManagementSystem.Services;

import com.yodishtr.LibraryManagementSystem.Config.APIProperties;
import com.yodishtr.LibraryManagementSystem.Config.HTTPClientConfig;
import com.yodishtr.LibraryManagementSystem.DataTransferObject.BookDTO;
import com.yodishtr.LibraryManagementSystem.Entities.Book;
import com.yodishtr.LibraryManagementSystem.Mappers.BookMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
public class BookAPIService {
    private final APIProperties apiProperties;
    private final HTTPClientConfig httpClientConfig;
    private final BookMapper bookMapper;

    public BookAPIService(APIProperties apiProperties, HTTPClientConfig httpClientConfig, BookMapper bookMapper) {
        this.apiProperties = apiProperties;
        this.httpClientConfig = httpClientConfig;
        this.bookMapper = bookMapper;
    }

    public Optional<Book> getBookByIsbn(String isbn){
        try {
            String query = "isbn:" + isbn;
            String urlRequest = UriComponentsBuilder
                    .fromUriString(apiProperties.getBaseurl())
                    .queryParam("q", query)
                    .queryParam("key", apiProperties.getKey())
                    .toUriString();
            BookDTO responseObject = httpClientConfig.getRestTemplate().getForObject(urlRequest, BookDTO.class);
            if (responseObject == null){
                return Optional.empty();
            }
            Book mappedBook = bookMapper.getBookFromBookDTOWithIsbn(responseObject, isbn);
            if (mappedBook == null){
                return Optional.empty();
            }
            return Optional.of(mappedBook);
        } catch (RestClientException e) {
            return Optional.empty();
        }
    }

    public Optional<BookDTO> getBookByTitle(String title){
        try {
            String query = "intitle:" + title;
            String urlRequest = UriComponentsBuilder
                    .fromUriString(apiProperties.getBaseurl())
                    .queryParam("q", query)
                    .queryParam("key", apiProperties.getKey())
                    .toUriString();
            BookDTO responseObject = httpClientConfig.getRestTemplate().getForObject(urlRequest, BookDTO.class);
            if (responseObject == null){
                return Optional.empty();
            }
            return Optional.of(responseObject);
        } catch (RestClientException e){
            return Optional.empty();
        }
    }

    public Optional<BookDTO> getBookByAuthor(String author){
        try {
            String query = "inauthor:" + author;
            String urlRequest = UriComponentsBuilder
                    .fromUriString(apiProperties.getBaseurl())
                    .queryParam("q", query)
                    .queryParam("key", apiProperties.getKey())
                    .toUriString();
            BookDTO responseObject = httpClientConfig.getRestTemplate().getForObject(urlRequest, BookDTO.class);
            if (responseObject == null){
                return Optional.empty();
            }
            return Optional.of(responseObject);
        } catch (RestClientException e){
            return Optional.empty();
        }
    }

}
