package com.luxoft.demoshopwithspring.messages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luxoft.demoshopwithspring.domain.ProductDto;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class ProductEvent {
    private String name;
    private String brand;
    private ProductDto.Quality quality;
    private Long stock;
    private Event event;

    @JsonIgnore
    public String getKey(){
        return name+brand+quality.name();
    }

    public enum Event{
        ADD,REMOVE,UNKNOWN
    }

}
