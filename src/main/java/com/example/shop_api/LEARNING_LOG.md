1  xóa @SpringBootApplication chạy thử
Web application could not be started as there was no 
org.springframework.boot.web.server.servlet.ServletWebServerFactory bean defined in the context.
Spring Boot muốn chạy ứng dụng Web nhưng không tìm thấy Bean cần thiết để 
tạo Web Server (ví dụ Tomcat), nên ứng dụng không chạy được.

Check your application's dependencies
for a supported servlet web server.
Spring đang gợi ý kiểm tra dependency web server và kiểu ứng dụng web.

2  ; đổi port trùng ứng dụng khác
Web server failed to start. Port 8081 was already in use.
Máy chủ web không khởi động được. Cổng 8081 đã được sử dụng.

Identify and stop the process that's listening on port 8081 or configure 
this application to listen on another port.
Xác định và dừng quá trình đang nghe trên cổng 8081 hoặc cấu hình
lại ứng dụng này để nghe trên cổng khác.

3  ; xóa dependency web trong pom rồi refresh
“Ứng dụng vẫn khởi động thành công, exit code 0, nhưng không còn các dòng khởi động Tomcat. 
Vì đã bỏ dependency Web nên ứng dụng không còn chạy Web Server để nhận HTTP request

4  đặt 1 class ngoài package gốc (Spring không quét thấy)
5  viết 2 constructor cho 1 bean    

6  sai chính tả application.yml thành aplication.yml
Failed to configure a DataSource:
'url' attribute is not specified and no embedded datasource could be configured.
Spring không cấu hình được kết nối database vì không tìm thấy url của database.
Reason: Failed to determine a suitable driver class
không xác định được cấu hình DataSource phù hợp để thiết lập kết nối.

7  để version Java trong pom lệch JDK máy
java: Không thể biên dịch module 'shop_api' được cấu hình cho mục tiêu JVM 21:
phiên bản JDK java "17.0.17"hiện đang liên kết với module này không hỗ trợ mục tiêu JVM 21 yêu cầu.

### Buổi 26 — JPA & Hibernate: ORM, Entity đầu tiên
BẢNG ĐỐI CHIẾU KIỂU JAVA ↔ SQL ↔ ANNOTATION JPA

1. BẢNG PRODUCTS

| Cột         | Kiểu SQL                       | Kiểu Java   | Annotation JPA cần dùng                                      |
|-------------|---------------------------------|-------------|--------------------------------------------------------------|
| id          | INT PRIMARY KEY AUTO_INCREMENT  | Integer     | @Id, @GeneratedValue(strategy = GenerationType.IDENTITY)     |
| name        | VARCHAR(255)                    | String      | @Column(length = 255)                                        |
| description | VARCHAR(255)                    | String      | @Column(length = 255)                                        |
| price       | DECIMAL(38,2)                   | BigDecimal  | @Column(precision = 38, scale = 2)                           |
| quantity    | INT                             | Integer     | Không bắt buộc @Column                                       |
| category_id | INT                             | Integer     | @Column(name = "category_id")                                |


2. BẢNG CUSTOMERS

| Cột   | Kiểu SQL                       | Kiểu Java | Annotation JPA cần dùng                                      |
|-------|---------------------------------|-----------|--------------------------------------------------------------|
| id    | INT PRIMARY KEY AUTO_INCREMENT  | Integer   | @Id, @GeneratedValue(strategy = GenerationType.IDENTITY)     |
| name  | VARCHAR(100) NOT NULL           | String    | @Column(nullable = false, length = 100)                      |
| email | VARCHAR(200) UNIQUE             | String    | @Column(unique = true, length = 200)                         |
| sdt   | VARCHAR(20)                     | String    | @Column(length = 20)                                         |


3. BẢNG STUDENTS

| Cột   | Kiểu SQL                       | Kiểu Java   | Annotation JPA cần dùng                                      |
|-------|---------------------------------|-------------|--------------------------------------------------------------|
| id    | INT PRIMARY KEY AUTO_INCREMENT  | Integer     | @Id, @GeneratedValue(strategy = GenerationType.IDENTITY)     |
| name  | VARCHAR(100)                    | String      | @Column(length = 100)                                        |
| score | DECIMAL(3,1)                    | BigDecimal  | @Column(precision = 3, scale = 1)                            |


GHI CHÚ

- @Entity: đánh dấu class là Entity để JPA quản lý.
- @Table(name = "..."): chỉ định tên bảng trong database.
- @Id: đánh dấu khóa chính (PRIMARY KEY).
- @GeneratedValue(strategy = GenerationType.IDENTITY): ID do database tự tăng (AUTO_INCREMENT).
- @Column: cấu hình cột trong database.
- nullable = false: tương ứng NOT NULL.
- unique = true: tương ứng UNIQUE.
- length = 100: tương ứng độ dài VARCHAR(100).
- precision: tổng số chữ số của DECIMAL.
- scale: số chữ số sau dấu thập phân.
- BigDecimal thường dùng với DECIMAL, đặc biệt đối với tiền.
- Nếu thuộc tính không có cấu hình đặc biệt thì không bắt buộc viết @Column.
- @Column(name = "category_id"): dùng để ánh xạ tên thuộc tính Java với tên cột SQL khác nhau.
- category_id sau này khi học quan hệ Entity có thể dùng @ManyToOne và @JoinColumn thay vì lưu Integer trực tiếp.

### BÀi 3
Hibernate: alter table products add column brand varchar(255)
Hibernate:
→ Câu SQL này do Hibernate thực hiện.

alter table products
→ Thay đổi cấu trúc bảng products.

add column brand
→ Thêm một cột mới tên là brand.

varchar(255)
→ Cột brand có kiểu chuỗi, tối đa 255 ký tự.