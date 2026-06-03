# 🖥️ Shop Hub — Admin & Seller Web Panel

Trang quản trị web cho hệ thống thương mại điện tử Shop Hub,
xây dựng bằng Spring MVC + Thymeleaf.

## 📌 Giới thiệu

Giao diện web dành cho Admin và Seller quản lý toàn bộ
hoạt động của sàn thương mại điện tử: sản phẩm, đơn hàng,
khuyến mãi, người dùng và thống kê doanh thu.

## ⚙️ Công nghệ sử dụng

- Java + Spring MVC
- Thymeleaf (template engine)
- HTML5 / CSS3 / JavaScript
- Spring Boot
- Maven

## 🚀 Tính năng chính

- Dashboard thống kê tổng quan
- Quản lý sản phẩm (thêm, sửa, xóa, hình ảnh)
- Quản lý đơn hàng và xử lý trạng thái
- Quản lý khuyến mãi
- Quản lý Seller và Shipper
- Chat với khách hàng realtime
- Hệ thống thông báo

## 🗂️ Cấu trúc project
src/
├── controller/
│   ├── admin/       # Chức năng Admin
│   └── seller/      # Chức năng Seller
├── apis/            # Gọi REST API từ backend
└── resources/
└── templates/   # Giao diện Thymeleaf
├── admin/
├── seller/
└── services/

## ▶️ Cách chạy project

1. Clone repo về máy
2. Đảm bảo **API backend** đang chạy tại `localhost:8080`
   → Xem: [Shop Hub API](https://github.com/minhdo20043006/shop-hub-api)
3. Chạy lệnh: `mvn spring-boot:run`
4. Truy cập tại: `http://localhost:8081`

## 🔗 Các repo liên quan

- [Shop Hub API](https://github.com/minhdo20043006/shop-hub-api)
  — REST API Backend (Spring Boot)
- [Shop Hub Mobile](https://github.com/minhdo20043006/shop-hub-mobile)
  — Ứng dụng mobile (Flutter)

## 👨‍💻 Nhóm phát triển

Dự án nhóm — Computing Project, Aptech 2026  
**Đỗ Quốc Minh** (minhdo20043006) — Fullstack Developer
