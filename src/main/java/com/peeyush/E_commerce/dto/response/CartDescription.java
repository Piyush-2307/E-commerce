package com.peeyush.E_commerce.dto.response;

import java.util.List;

public record CartDescription(Integer total, List<CartSummaryResponse> cartSummaryRespons) {
}
