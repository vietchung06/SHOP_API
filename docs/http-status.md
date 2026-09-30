| Mã  | Tên                   | Khi nào dùng                          | Ví dụ trong shop-api                       |
| --- | --------------------- | ------------------------------------- |--------------------------------------------|
| 200 | OK                    | Request thành công                    | GET sản phẩm thành công                    |
| 201 | Created               | Tạo dữ liệu thành công                | POST tạo Product                           |
| 204 | No Content            | Thành công nhưng không trả body       | DELETE Product                             |
| 400 | Bad Request           | Request gửi lên không hợp lệ          | Dữ liệu Product không hợp lệ               |
| 401 | Unauthorized          | Chưa xác thực                         | API yêu cầu đăng nhập nhưng chưa đăng nhập |
| 403 | Forbidden             | Đã xác thực nhưng không có quyền      | User không có quyền xóa Product            |
| 404 | Not Found             | Không tìm thấy dữ liệu                | GET Product có id không tồn tại            |
| 405 | Method Not Allowed    | Dùng HTTP method không được hỗ trợ    | Gửi DELETE vào API chỉ cho GET             |
| 409 | Conflict              | Request hợp lệ nhưng xung đột dữ liệu | Xóa Category đang có Product, trùng tên     |
| 500 | Internal Server Error | Lỗi bất ngờ phía server               | Server xảy ra exception chưa xử lý         |


### Bài 4
## Product
| Method | Path             | Thành công                    | Status lỗi có thể |
| ------ | ---------------- | ----------------------------- | ----------------- |
| GET    | `/products`      | 200 OK                        | 400               |
| GET    | `/products/{id}` | 200 OK                        | 404               |
| POST   | `/products`      | 201 Created + Product vừa tạo | 400, 404, 409     |
| PUT    | `/products/{id}` | 200 OK + Product sau khi sửa  | 400, 404          |
| DELETE | `/products/{id}` | 204 No Content                | 404, 409          |
# Nếu categoryId gửi lên không tồn tại:
POST /products
→ 404
# 1. Tại sao POST Product có thể có 409?
Ví dụ Product của bạn có thể có quy tắc không được trùng tên.
Trong database đã có tên đấy nhưng vẫn cố post tên đó:
hệ thống quy định không được tạo Product trùng tên. → Bị xung đột với Product đã tồn tại.
→ 409 Conflict

## Category
| Method | Path               | Thành công                     | Status lỗi có thể |
| ------ | ------------------ | ------------------------------ | ----------------- |
| GET    | `/categories`      | 200 OK                         | 400               |
| GET    | `/categories/{id}` | 200 OK                         | 404               |
| POST   | `/categories`      | 201 Created + Category vừa tạo | 400, 409          |
| PUT    | `/categories/{id}` | 200 OK + Category sau khi sửa  | 400, 404, 409     |
| DELETE | `/categories/{id}` | 204 No Content                 | 404, 409          |

## Order
| Method | Path                     | Thành công                  | Status lỗi có thể |
| ------ | ------------------------ | --------------------------- | ----------------- |
| GET    | `/orders/{id}`           | 200 OK + Order              | 404               |
| POST   | `/orders`                | 201 Created + Order vừa tạo | 400, 404, 409     |
| PUT    | `/orders/{id}`           | 200 OK + Order sau khi sửa  | 400, 404          |
| DELETE | `/orders/{id}`           | 204 No Content              | 404, 409          |
| GET    | `/customers/{id}/orders` | 200 OK + danh sách Order    | 404               |
# Tại sao POST Order có 409?
Cái này liên quan trực tiếp đến bài @Transactional
Cần mua: 5
Tồn kho: 2
→ Không đủ hàng.
Trong code của bạn có:
InsufficientStock
Đây là xung đột với trạng thái hiện tại của kho. → Có thể trả:
-> 409 Conflict

