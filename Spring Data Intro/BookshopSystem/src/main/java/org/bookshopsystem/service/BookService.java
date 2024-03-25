package org.bookshopsystem.service;

import java.io.IOException;
import java.util.*;

public interface BookService {
    void seedBooks() throws IOException;

    List<String> findAllBooksAfterYear2000();

    List<String> findAllByBookByGeorgePowellOrdered();
}
