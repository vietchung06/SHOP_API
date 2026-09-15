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

