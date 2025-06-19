
# 🍔 FoodDeliveryWeb

**FoodDeliveryWeb** là một hệ thống **đặt đồ ăn trực tuyến toàn diện**, hỗ trợ nhiều vai trò khác nhau: **khách hàng**, **admin nhà hàng**, và **super admin** quản lý toàn hệ thống.

---

## 🧰 Công nghệ sử dụng

| Thành phần | Công nghệ |
|------------|-----------|
| **Back-end** | Java 17+, Spring Boot 3.x, Maven |
| **Front-end** | ReactJS, Node.js 16+, npm (hoặc yarn) |
| **Cơ sở dữ liệu** | MySQL (hoặc MariaDB) |
| **Thanh toán** | Stripe (môi trường test) |

---

## 📁 Cấu trúc thư mục chính

```plaintext
backend/
├── src/main/java/...         # Source code Spring Boot
├── src/main/resources/       # File cấu hình (application.properties)
frontend/
├── src/                      # Source code React
├── public/                   # File tĩnh React
```

---

## 🚀 Cài đặt & chạy dự án

### 1. Tạo Database
- Tạo CSDL tên: `huyle_food`  
- Có thể để Spring Boot tự tạo bảng, hoặc import SQL mẫu (nếu có)

### 2. Cấu hình & chạy **Back-end**
```bash
# Build
./mvnw clean install

# Chạy server
./mvnw spring-boot:run
```
- ⚙️ Cấu hình DB, Stripe Key trong:  
  `backend/src/main/resources/application.properties`

### 3. Cài đặt & chạy **Front-end**
```bash
# Cài thư viện
npm install

# Chạy chế độ dev
npm start
```
- App sẽ chạy tại: [http://localhost:3000](http://localhost:3000)

---

## 🔗 Tích hợp hệ thống

- Front-end gọi các API `/api/...` từ back-end (mặc định chạy cổng `5454`)
- Kiểm tra cấu hình API tại: `frontend/src/config/api.js`

---

## 🧩 Các chức năng chính

| Vai trò | Chức năng |
|---------|-----------|
| **Khách hàng** | Đăng ký, đăng nhập, xem thực đơn, đặt món, quản lý giỏ hàng, thanh toán, xem lịch sử, đánh giá |
| **Admin nhà hàng** | Quản lý thực đơn, đơn hàng, nguyên liệu, sự kiện, báo cáo |
| **Super Admin** | Duyệt nhà hàng, quản lý toàn bộ người dùng & hệ thống |

---

## 💻 Một số lệnh hữu ích

### ✅ Back-end
```bash
./mvnw clean install      # Build
./mvnw spring-boot:run    # Chạy server
./mvnw test               # Chạy test
```

### ✅ Front-end
```bash
npm install               # Cài thư viện
npm start                 # Chạy chế độ dev
npm run build             # Build production
npm test                  # Chạy test
```

---

## 📝 Ghi chú

- Có thể reset cart hoặc restaurant bằng các script SQL trong thư mục `backend/`.
- Stripe key chỉ dùng cho test, không dùng cho production.
- Nếu gặp lỗi CSDL, kiểm tra lại thông tin `username/password` trong `application.properties`.

---

## 🤝 Đóng góp

Mọi đóng góp, báo lỗi hoặc ý tưởng mới đều được hoan nghênh!  
📬 Hãy tạo **issue** hoặc **pull request** tại repository này.

---

> Dự án thực hiện bởi nhóm sinh viên với mong muốn mô phỏng hệ thống giao đồ ăn hiện đại, dễ mở rộng và triển khai thực tế.
