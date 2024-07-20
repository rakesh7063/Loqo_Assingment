package com.example.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MyErrorDetails {
    private LocalDateTime localDateTime;
    private String message;
    private String description;


}
