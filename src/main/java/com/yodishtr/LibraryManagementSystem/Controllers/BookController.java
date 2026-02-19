package com.yodishtr.LibraryManagementSystem.Controllers;

import com.yodishtr.LibraryManagementSystem.Entities.Book;
import com.yodishtr.LibraryManagementSystem.Entities.User;
import com.yodishtr.LibraryManagementSystem.Repository.UserRepository;
import com.yodishtr.LibraryManagementSystem.Services.BookPopulationService;
import com.yodishtr.LibraryManagementSystem.Services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final UserRepository userRepository;

    public BookController(BookService bookService, UserRepository userRepository) {
        this.bookService = bookService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String showBooks(Model model){
        ArrayList<Book> allBooksList = new ArrayList<Book>(bookService.getAllBooks());
        model.addAttribute("books", allBooksList);
        return "library";
    }

    @PostMapping("/{bookId}/borrow")
    public String borrowBook(@PathVariable Long bookId, Principal principal){
        String currUserName = principal.getName();
        Optional<User> optionalUser = userRepository.findByUsername(currUserName);
        if (optionalUser.isEmpty()){
            return "redirect:/books";
        }
        User user = optionalUser.get();
        boolean borrowResult = bookService.borrowBook(bookId, user);
        return "redirect:/books/";
    }

}
