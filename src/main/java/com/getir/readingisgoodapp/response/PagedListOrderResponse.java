package com.getir.readingisgoodapp.response;

import com.getir.readingisgoodapp.dto.BookOrderDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class PagedListOrderResponse {
    private Page<BookOrderDTO> page;

    public PagedListOrderResponse(Page<BookOrderDTO> page) {
        this.page = page;
    }
}
