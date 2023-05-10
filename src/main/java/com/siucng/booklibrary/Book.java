package com.siucng.booklibrary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @NonNull
    private String name;
    @NonNull
    private String isbn;
    
}
