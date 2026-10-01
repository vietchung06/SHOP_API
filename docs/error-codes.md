# ### Error Codes

T| Mã lỗi                    | HTTP Status | Ý nghĩa                              | Endpoint có thể trả                                                                           |
| ------------------------- | :---------: | ------------------------------------ | --------------------------------------------------------------------------------------------- |
| `VALIDATION_ERROR`        |   **400**   | Dữ liệu gửi lên không hợp lệ         | `POST /products`
                                                                                   `POST /categories`
                                                                                   `POST /customers`
                                                                                   `POST /orders`                 |
| `RESOURCE_NOT_FOUND`      |   **404**   | Không tìm thấy tài nguyên yêu cầu    | `GET /products/{id}`
                                                                                   `GET /categories/{id}`
                                                                                   `GET /customers/{id}`
                                                                                    `GET /orders/{id}` |
| `PRODUCT_ALREADY_EXISTS`  |   **409**   | Sản phẩm đã tồn tại                  | `POST /products`                                                                              |
| `CATEGORY_ALREADY_EXISTS` |   **409**   | Danh mục đã tồn tại                  | `POST /categories`                                                                            |
| `CATEGORY_IN_USE`         |   **409**   | Danh mục đang được sản phẩm sử dụng  | `DELETE /categories/{id}`                                                                     |
| `INSUFFICIENT_STOCK`      |   **409**   | Số lượng sản phẩm trong kho không đủ | `POST /orders`                                                                                |
| `INTERNAL_SERVER_ERROR`   |   **500**   | Lỗi không mong muốn phía server      | Mọi endpoint                                                                                  |
