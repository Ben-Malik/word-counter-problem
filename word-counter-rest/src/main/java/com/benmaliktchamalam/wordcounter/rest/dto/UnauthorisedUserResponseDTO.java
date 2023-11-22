package com.benmaliktchamalam.wordcounter.rest.dto;

import com.benmaliktchamalam.wordcounter.rest.constant.WordCounterConstants;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
public class UnauthorisedUserResponseDTO {
    public String message = WordCounterConstants.UNAUTHORIZED_USER_MESSAGE;
    public String type = HttpStatus.UNAUTHORIZED.name();
    public int statusCode = HttpStatus.UNAUTHORIZED.value();
}
