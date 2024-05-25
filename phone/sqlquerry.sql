-- drop table customer;
-- drop database phone;
CREATE DATABASE phone;
-- drop table product;
-- drop table category;

-- drop table customer;
-- drop table invoicedetail;
-- drop table receiptdetail;
-- drop table invoice;
-- drop table receipt;
-- drop table supplier;
-- drop table product;
-- drop table category;
-- drop table employee;
-- drop table person;

CREATE TABLE `phone`.`category` (`categoryId` INT NOT NULL AUTO_INCREMENT , `categoryName` VARCHAR(50) NULL , `status` TINYINT(1) NULL DEFAULT '1', PRIMARY KEY (`categoryId`)) ENGINE = InnoDB;
CREATE TABLE `phone`.`product` (`productId` INT NOT NULL AUTO_INCREMENT , `productName` VARCHAR(50) NULL , `categoryId` INT(50) NULL DEFAULT NULL , `purchasePrice` DOUBLE NULL , `sellingPrice` DOUBLE NULL , `quantity` INT NOT NULL DEFAULT '0' , `imgURL` VARCHAR(100) NULL , `description` VARCHAR(500) NULL , `status` TINYINT(1) NULL DEFAULT '1' , PRIMARY KEY (`productId`)) ENGINE = InnoDB;
ALTER TABLE phone.product ADD INDEX categoryId (categoryId) USING BTREE;
ALTER TABLE phone.product ADD CONSTRAINT product_ibfk_1 FOREIGN KEY (categoryId) REFERENCES category(categoryId) ON DELETE NO ACTION ON UPDATE NO ACTION;
CREATE TABLE `phone`.`Person`(
    personID INT AUTO_INCREMENT PRIMARY KEY,
    fullName NVARCHAR(255),
    Gender NVARCHAR(10),
    dateOfBirth DATE,
    Email NVARCHAR(255),
    phoneNumber NVARCHAR(20),
    Address NVARCHAR(255),
    Status TINYINT NULL DEFAULT 1,
    Comment NVARCHAR(50)
);
CREATE TABLE `phone`.`Employee` (
    EmployeeID INT AUTO_INCREMENT PRIMARY KEY,
    Position NVARCHAR(255),
    Salary DECIMAL(10, 2),
    Password NVARCHAR(255),
    Status TINYINT NULL DEFAULT 1,
    PersonID INT,
    FOREIGN KEY (PersonID) REFERENCES Person(PersonID)
);
CREATE TABLE `phone`.`Customer` (
    customerID INT AUTO_INCREMENT PRIMARY KEY,
    Status TINYINT NULL DEFAULT 1,
    PersonID INT,
    FOREIGN KEY (PersonID) REFERENCES Person(PersonID)
);
CREATE TABLE `phone`.`supplier` (
		  `supplierId` INT NOT NULL AUTO_INCREMENT,
		  `supplierName` VARCHAR(50) NOT NULL,
		  `supplierPhone` VARCHAR(50) NULL,
		  `supplierAddress` VARCHAR(255) NULL,
		  `supplierEmail` VARCHAR(50) NULL,
		  `supplierNote` VARCHAR(255) NULL,
		  `status` TINYINT NULL DEFAULT 1,
		  PRIMARY KEY (`supplierId`));
CREATE TABLE `phone`.`receipt` (`receiptId` INT NOT NULL AUTO_INCREMENT , `supplierId` INT NOT NULL , `staffId` INT NOT NULL , `date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP , `totalPrice` DOUBLE NULL , `status` TINYINT(1) NOT NULL DEFAULT '1' , PRIMARY KEY (`receiptId`)) ENGINE = InnoDB;
ALTER TABLE `phone`.`receipt` ADD INDEX `supplierId` (`supplierId`) USING BTREE;
ALTER TABLE `phone`.`receipt` ADD INDEX(`staffId`);
ALTER TABLE `phone`.`receipt` ADD CONSTRAINT `receipt_ibfk_11` FOREIGN KEY (`supplierId`) REFERENCES `supplier`(`supplierId`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `phone`.`receipt` ADD CONSTRAINT `receipt_ibfk_2` FOREIGN KEY (`staffId`) REFERENCES `employee`(`EmployeeID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

CREATE TABLE `phone`.`receiptdetail` (`receiptId` INT NOT NULL , `productId` INT NOT NULL , `quantity` INT NOT NULL DEFAULT '0' , `purchasePrice` DOUBLE NULL DEFAULT '0' , PRIMARY KEY (`receiptId`, `productId`)) ENGINE = InnoDB;
ALTER TABLE `phone`.`receiptdetail` ADD `status` TINYINT(1) NOT NULL DEFAULT '1' AFTER `purchasePrice`;
ALTER TABLE `phone`.`receiptdetail` ADD CONSTRAINT `receiptdetail_ibfk_1` FOREIGN KEY (`receiptId`) REFERENCES `receipt`(`receiptId`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `phone`.`receiptdetail` ADD CONSTRAINT `receiptdetail_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `product`(`productId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

CREATE TABLE `phone`.`invoice`(`invoiceId` INT NOT NULL AUTO_INCREMENT , `customerId` INT NOT NULL , `staffId` INT NOT NULL , `date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP , `totalPrice` INT NOT NULL , `status` TINYINT NOT NULL DEFAULT '1' , PRIMARY KEY (`invoiceId`)) ENGINE = InnoDB;
ALTER TABLE `phone`.`invoice` ADD INDEX `customerId` (`customerId`, `staffId`) USING BTREE;
CREATE TABLE `phone`.`invoicedetail` (`invoiceId` INT NOT NULL AUTO_INCREMENT , `productId` INT NOT NULL , `quantity` INT NOT NULL , `sellingPrice` INT NOT NULL , `status` TINYINT(1) NOT NULL , PRIMARY KEY (`invoiceId`, `productId`)) ENGINE = InnoDB;         
ALTER TABLE `phone`.`invoicedetail` ADD CONSTRAINT invoicedetail_ibfk_1 FOREIGN KEY (invoiceId) REFERENCES invoice(invoiceId) ON DELETE CASCADE ON UPDATE CASCADE; 
ALTER TABLE `phone`.`invoicedetail` ADD CONSTRAINT invoicedetail_ibfk_2 FOREIGN KEY (productId) REFERENCES product(productId) ON DELETE NO ACTION ON UPDATE NO ACTION;
-- Thêm Nhà Cung Cấp
INSERT INTO `phone`.`supplier` (`supplierId`, `supplierName`, `supplierPhone`, `supplierAddress`, `supplierEmail`, `supplierNote`, `status`)VALUES ('1', 'CÔNG TY CỔ PHẦN MISA', '0564243269', '52 Nguyễn Thanh', 'nisa@gmail.com', 'CỔ PHẦN MISA', '1');
INSERT INTO `phone`.`supplier` (`supplierId`, `supplierName`, `supplierPhone`, `supplierAddress`, `supplierEmail`, `supplierNote`, `status`) VALUES ('2', 'TỔNG CÔNG TY DỊCH VỤ VIỄN THÔNG (VNPT VINAPHONE)', '0498737670', '13/2 Trường Chinh', 'vnpt@gmail.com', 'VIỄN THÔNG (VNPT VINAPHONE)', '1');
INSERT INTO `phone`.`supplier` (`supplierId`, `supplierName`, `supplierPhone`, `supplierAddress`, `supplierEmail`, `supplierNote`, `status`) VALUES ('3', 'CÔNG TY TNHH PHÁT TRIỂN CÔNG NGHỆ THÁI SƠN', '0756497034', '89/2 Đường số 9', 'thaison@gmail.com', 'CÔNG NGHỆ THÁI SƠN', '1');
INSERT INTO `phone`.`supplier` (`supplierId`, `supplierName`, `supplierPhone`, `supplierAddress`, `supplierEmail`, `supplierNote`, `status`) VALUES ('4', 'CÔNG TY TNHH HÓA ĐƠN ĐIỆN TỬ M-INVOICE', '0105804358', '1 Tân Xã', 'minvoice@gmail.com', 'ĐIỆN TỬ M-INVOICE', '1');
INSERT INTO `phone`.`supplier` (`supplierId`, `supplierName`, `supplierPhone`, `supplierAddress`, `supplierEmail`, `supplierNote`, `status`) VALUES ('5', 'CTCP ĐẦU TƯ CÔNG NGHỆ VÀ THƯƠNG MẠI SOFTDREAMS', '064304983', '12 Tân Bình', 'softdreams@gmail.com', 'ÔNG NGHỆ VÀ THƯƠNG MẠI SOFTDREAMS', '1');
INSERT INTO `phone`.`supplier` (`supplierId`, `supplierName`, `supplierPhone`, `supplierAddress`, `supplierEmail`, `supplierNote`, `status`) VALUES ('6', 'CÔNG TY CP DỊCH VỤ VIỄN THÔNG VÀ IN BƯU ĐIỆN', '0672133096', '272 Nguyễn Trãi', 'buudien@gmail.com', 'IỄN THÔNG VÀ IN BƯU ĐIỆN', '1');
INSERT INTO `phone`.`supplier` (`supplierId`, `supplierName`, `supplierPhone`, `supplierAddress`, `supplierEmail`, `supplierNote`, `status`) VALUES ('7', 'CÔNG TY CỔ PHẦN CÔNG NGHỆ TIN HỌC EFY VIỆT NAM', '0086225556', '52 Nguyễn Thiệu', 'efyvietnam@gmail.com', 'CÔNG NGHỆ TIN HỌC EFY VIỆT NAM', '1');
INSERT INTO `phone`.`supplier` (`supplierId`, `supplierName`, `supplierPhone`, `supplierAddress`, `supplierEmail`, `supplierNote`, `status`) VALUES ('8', 'CÔNG TY CỔ PHẦN DỊCH VỤ T-VAN HILO', '0652399487', '9 Washington', 'tvanhilo@gmail.com', 'ỊCH VỤ T-VAN HILO', '1');
INSERT INTO `phone`.`supplier` (`supplierId`, `supplierName`, `supplierPhone`, `supplierAddress`, `supplierEmail`, `supplierNote`, `status`) VALUES ('9', 'TẬP ĐOÀN CÔNG NGHIỆP VIỄN THÔNG QUÂN ĐỘI VIETTEL', '0976257753', '89 Đường số 1', 'viettel@gmail.com', 'VIỄN THÔNG QUÂN ĐỘI VIETTEL', '1');
INSERT INTO `phone`.`supplier` (`supplierId`, `supplierName`, `supplierPhone`, `supplierAddress`, `supplierEmail`, `supplierNote`, `status`) VALUES ('10', 'CÔNG TY CỔ PHẦN CYBERLOTUS', '0908689573', '25 Nguyễn Trường Thanh', 'cyberlotus@gmail.com', 'CYBERLOTUS', '1');
INSERT INTO `phone`.`supplier` (`supplierId`, `supplierName`, `supplierPhone`, `supplierAddress`, `supplierEmail`, `supplierNote`, `status`) VALUES ('11', 'CÔNG TY CỔ PHẦN CÔNG NGHỆ THẺ NACENCOMM', '0407605348', '12 Nguyễn Văn Thiệu', 'nacencomm@gmail.com', 'CÔNG NGHỆ THẺ NACENCOMM', '1');
INSERT INTO `phone`.`supplier` (`supplierId`, `supplierName`, `supplierPhone`, `supplierAddress`, `supplierEmail`, `supplierNote`, `status`) VALUES ('12', 'CÔNG TY CỔ PHẦN CÔNG NGHỆ THÔNG TIN ĐÔNG NAM Á', '0487710985', '90 Đường số 2', 'dongnama@gmail.com', 'CÔNG NGHỆ THÔNG TIN ĐÔNG NAM Á', '1');

-- Thêm Nhân viên 
INSERT INTO `phone`.`Person` ( fullName, Gender, dateOfBirth, Email, phoneNumber, Address, Status, Comment) VALUES ('David ', 'Nam', '1995-02-20', 'david@email.com', '0923456781', '121 Washington', 1, 'không');
INSERT INTO `phone`.`Employee` (EmployeeID, Position, Salary, Password, Status) VALUES (1, 'Nhân viên', 6000000, '123456', 1);
INSERT INTO `phone`.`Person` ( fullName, Gender, dateOfBirth, Email, phoneNumber, Address, Status, Comment) VALUES ('Sara ', 'Nữ', '1996-04-12', 'sara@email.com', '0923456782', '122 Grove', 1, 'không');
INSERT INTO `phone`.`Employee` (EmployeeID, Position, Salary, Password, Status) VALUES (2, 'Nhân viên', 6000000, '123456', 1);
INSERT INTO `phone`.`Person` ( fullName, Gender, dateOfBirth, Email, phoneNumber, Address, Status, Comment) VALUES ( 'Furina ', 'Nam', '1997-05-13', 'furina@email.com', '0923456783', '123 Trình Trọng', 1, 'không');
INSERT INTO `phone`.`Employee` (EmployeeID, Position, Salary, Password, Status) VALUES (3, 'Nhân viên', 6000000, '123456', 1);
INSERT INTO `phone`.`Person` ( fullName, Gender, dateOfBirth, Email, phoneNumber, Address, Status, Comment) VALUES ('Quốc Hưng', 'Nam', '1998-06-13', 'quochung@email.com', '0923456784', '124 Hoyoverb', 1, 'không');
INSERT INTO `phone`.`Employee` (EmployeeID, Position, Salary, Password, Status) VALUES (4, 'Nhân viên', 6000000, '123456', 1);
INSERT INTO `phone`.`Person` ( fullName, Gender, dateOfBirth, Email, phoneNumber, Address, Status, Comment) VALUES ( 'Quỳnh Như', 'Nữ', '1999-07-14', 'quynhnhu@email.com', '0923456785', '125 Tân Phát', 1, 'không');
INSERT INTO `phone`.`Employee` (EmployeeID, Position, Salary, Password, Status) VALUES (5, 'Nhân viên', 6000000, '123456', 1);
-- Thêm Nhân viên role quản lý
INSERT INTO `phone`.`Person` ( fullName, Gender, dateOfBirth, Email, phoneNumber, Address, Status, Comment) VALUES ('Hinata ', 'Nam', '1980-03-21', 'hinata@email.com', '0923456781', '121 Trình Đình Trọng', 1, 'không');
INSERT INTO `phone`.`Employee` (EmployeeID, Position, Salary, Password, Status) VALUES (6, 'Quản lí', 7000000, '123456', 1);
INSERT INTO `phone`.`Person` ( fullName, Gender, dateOfBirth, Email, phoneNumber, Address, Status, Comment) VALUES ('Johnny Cage ', 'Nam', '1981-05-13', 'johnnycage@email.com', '0923456782', '123 Xuân Bắc', 1, 'không');
INSERT INTO `phone`.`Employee` (EmployeeID, Position, Salary, Password, Status) VALUES (7, 'Quản lí', 7000000, '123456', 1);
INSERT INTO `phone`.`Person` ( fullName, Gender, dateOfBirth, Email, phoneNumber, Address, Status, Comment) VALUES ( 'yasuo ', 'Nam', '1982-06-14', 'yasuo@email.com', '0923456783', '123 Hồ Điệp', 1, 'không');
INSERT INTO `phone`.`Employee` (EmployeeID, Position, Salary, Password, Status) VALUES (8, 'Quản lí', 7000000, '123456', 1);
INSERT INTO `phone`.`Person` ( fullName, Gender, dateOfBirth, Email, phoneNumber, Address, Status, Comment) VALUES ('Hồ Giang', 'Nam', '1972-06-13', 'hogiang@email.com', '0923456784', '125 Thanh Tuyền', 1, 'không');
INSERT INTO `phone`.`Employee` (EmployeeID, Position, Salary, Password, Status) VALUES (9, 'Quản lí', 7000000, '123456', 1);
INSERT INTO `phone`.`Person` ( fullName, Gender, dateOfBirth, Email, phoneNumber, Address, Status, Comment) VALUES ( 'Hồ Xúy', 'Nữ', '1979-07-14', 'hoxuy@email.com', '0923456785', '126 Lê Phát', 1, 'không');
INSERT INTO `phone`.`Employee` (EmployeeID, Position, Salary, Password, Status) VALUES (10, 'Quản lí', 7000000, '123456', 1);
-- Thêm khách hàng
INSERT INTO `phone`.`Person` (fullName, Gender, dateOfBirth, Email, phoneNumber, Address, Status, Comment) VALUES ('John Doe', 'Male', '1990-01-15', 'johndoe@email.com', '123-456-7890', '123 Main St', 1, 'Comment 1');
INSERT INTO `phone`.`Customer` (PersonID, Status) VALUES (11, 1);
INSERT INTO `phone`.`Person` (fullName, Gender, dateOfBirth, Email, phoneNumber, Address, Status, Comment) VALUES ('Alice Johnson', 'Female', '1988-12-10', 'alicejohnson@email.com', '555-123-7890', '789 Elm St', 1, 'Comment 2');
INSERT INTO `phone`.`Customer` (PersonID, Status) VALUES (12, 1);
INSERT INTO `phone`.`Person` (fullName, Gender, dateOfBirth, Email, phoneNumber, Address, Status, Comment) VALUES ('Bob Smith', 'Male', '1995-05-20', 'bobsmith@email.com', '777-999-5555', '456 Oak St', 1, 'Comment 3');
INSERT INTO `phone`.`Customer` (PersonID, Status) VALUES (13, 1);
INSERT INTO `phone`.`Person` (fullName, Gender, dateOfBirth, Email, phoneNumber, Address, Status, Comment) VALUES ('Eva Davis', 'Female', '1992-08-08', 'evadavis@email.com', '333-888-4444', '987 Pine St', 1, 'Comment 4');
INSERT INTO `phone`.`Customer` (PersonID, Status) VALUES (14, 1);
INSERT INTO `phone`.`Person` (fullName, Gender, dateOfBirth, Email, phoneNumber, Address, Status, Comment) VALUES ('Chris Wilson', 'Male', '1987-04-25', 'chriswilson@email.com', '222-777-3333', '654 Maple St', 1, 'Comment 5');
INSERT INTO `phone`.`Customer` (PersonID, Status) VALUES (15, 1);
-- Thêm loại sản phẩm
INSERT INTO phone.category (`categoryId`, `categoryName`, `status`) VALUES ('1', 'Iphone', '1');
INSERT INTO phone.category (`categoryId`, `categoryName`, `status`) VALUES ('2', 'XiaoMi', '1');
INSERT INTO phone.category (`categoryId`, `categoryName`, `status`) VALUES ('3', 'HuaWei', '1');
INSERT INTO phone.category (`categoryId`, `categoryName`, `status`) VALUES ('4', 'Oppo', '1');
INSERT INTO phone.category (`categoryId`, `categoryName`, `status`) VALUES ('5', 'SamSung', '1');
-- Thêm sản phẩm
INSERT INTO `phone`.`product` (`productId`,`productName`,`categoryId`,`purchasePrice`,`sellingPrice`, `quantity`, `imgURL`, `description`,`status`) VALUES ('1', 'iPhone 15 Pro Max 256GB', '1', '26590000', '30590000', '10', '185iphone-15-pro-max-blue-1-1.jpg','iPhone 15 Pro Max là một chiếc điện thoại thông minh cao cấp được mong đợi nhất năm 2023. Với nhiều tính năng mới và cải tiến, iPhone 15 Pro Max chắc chắn sẽ là một lựa chọn tuyệt vời cho những người dùng đang tìm kiếm một chiếc điện thoại có hiệu năng mạnh mẽ, camera chất lượng và thiết kế sang trọng.', '1');
INSERT INTO `phone`.`product` (`productId`,`productName`,`categoryId`,`purchasePrice`,`sellingPrice`, `quantity`, `imgURL`, `description`,`status`) VALUES ('2', 'iPhone 14 Pro Max 128GB', '2', '21000000', '23590000', '20', '556iphone-14-pro-max-purple-1.jpg','iPhone 14 Pro Max một siêu phẩm trong giới smartphone được nhà Táo tung ra thị trường vào tháng 09/2022. Máy trang bị con chip Apple A16 Bionic vô cùng mạnh mẽ, đi kèm theo đó là thiết kế màn hình mới, hứa hẹn mang lại những trải nghiệm đầy mới mẻ cho người dùng iPhone.', '1');
INSERT INTO `phone`.`product` (`productId`,`productName`,`categoryId`,`purchasePrice`,`sellingPrice`, `quantity`, `imgURL`, `description`,`status`) VALUES ('3', 'iPhone 13 128GB', '1', '12000000', '14060000', '5', '966Frame-427319967-600x600-1.png','Trong khi sức hút đến từ bộ 4 phiên bản iPhone 12 vẫn chưa nguội đi, thì hãng điện thoại Apple đã mang đến cho người dùng một siêu phẩm mới iPhone 13 với nhiều cải tiến thú vị sẽ mang lại những trải nghiệm hấp dẫn nhất cho người dùng.', '1');
INSERT INTO `phone`.`product` (`productId`,`productName`,`categoryId`,`purchasePrice`,`sellingPrice`, `quantity`, `imgURL`, `description`,`status`) VALUES ('4', 'Samsung Galaxy A24 6GB ', '5', '4000000', '5690000', '19', '206samsung-galaxy-a24-den-1.jpg','Samsung Galaxy A24 6GB tiếp tục là mẫu điện thoại tầm trung được nhà Samsung giới thiệu đến thị trường Việt Nam vào tháng 04/2023, máy nổi bật với giá thành rẻ, màn hình Super AMOLED cùng camera 50 MP chụp ảnh sắc nét.', '1');
INSERT INTO `phone`.`product` (`productId`,`productName`,`categoryId`,`purchasePrice`,`sellingPrice`, `quantity`, `imgURL`, `description`,`status`) VALUES ('5', 'iPhone 15 Pro Max 256GB', '1', '4690000', '5690000', '23', '185iphone-15-pro-max-blue-1-1.jpg','không có','1');
INSERT INTO `phone`.`product` (`productId`,`productName`,`categoryId`,`purchasePrice`,`sellingPrice`, `quantity`, `imgURL`, `description`,`status`) VALUES ('6', 'Samsung Galaxy Z Fold4 5G 256GB', '5', '26590000', '30590000', '10', '853vi-vn-samsung-galaxy-a24-6gb--(6).jpg','Tại sự kiện Samsung Unpacked thường niên, Samsung Galaxy Z Fold4 256GB chính thức được trình làng thị trường công nghệ, mang trên mình nhiều cải tiến đáng giá giúp Galaxy Z Fold trở thành dòng điện thoại gập đã tốt nay càng tốt hơn.', '1');
INSERT INTO `phone`.`product` (`productId`,`productName`,`categoryId`,`purchasePrice`,`sellingPrice`, `quantity`, `imgURL`, `description`,`status`) VALUES ('7', 'Xiaomi 13T Pro 5G', '2', '12000000', '14490000', '2', '185iphone-15-pro-max-blue-1-1.jpg','Xiaomi 13T Pro 5G là mẫu máy thuộc phân khúc tầm trung đáng chú ý tại thị trường Việt Nam. Điện thoại ấn tượng nhờ được trang bị chip Dimensity 9200+, camera 50 MP có kèm sự hợp tác với Leica cùng kiểu thiết kế tinh tế đầy sang trọng.', '1');
INSERT INTO `phone`.`product` (`productId`,`productName`,`categoryId`,`purchasePrice`,`sellingPrice`, `quantity`, `imgURL`, `description`,`status`) VALUES ('8', 'Xiaomi Redmi 12 4GB', '2', '2690000', '3690000', '10', '515xiaomi-redmi-12-bac-1-2.jpg','Xiaomi Redmi 12 mẫu điện thoại mới nhất được nhà Xiaomi tung ra vào tháng 06/2023 khiến cho cộng đồng đam mê công nghệ vô cùng thích thú. Máy khoác lên mình một vẻ ngoài cá tính, màn hình lớn đem đến trải nghiệm đã mắt cùng một hiệu năng ổn định với mọi tác vụ.', '1');
INSERT INTO `phone`.`product` (`productId`,`productName`,`categoryId`,`purchasePrice`,`sellingPrice`, `quantity`, `imgURL`, `description`,`status`) VALUES ('9','OPPO Find N3 Flip 5G Hồng', '4', '20000000', '22990000', '16', '647oppo-find-n3-flip-pink-1.jpg','OPPO Find N3 Flip 5G Hồng được OPPO cho ra mắt chính thức tại thị trường Việt Nam vào tháng 10/2023. Sản phẩm được hãng đầu tư mạnh mẽ về camera với độ phân giải lên đến 50 MP, cấu hình sử dụng chip Dimensity 9200 5G và thiết kế được thay đổi với bản lề gập mở tốt hơn cùng màu hồng sang trọng và nữ tính.', '1');
INSERT INTO `phone`.`product` (`productId`,`productName`,`categoryId`,`purchasePrice`,`sellingPrice`, `quantity`, `imgURL`, `description`,`status`) VALUES ('10','OPPO Reno10 5G 256GB Xanh', '4', '8000000', '10490000', '3', '807oppo-reno10-xanh-1.jpg','Là một trong những chiếc smartphone mới nhất của OPPO trên thị trường hiện nay, OPPO Reno10 5G mang trên mình bộ áo đẹp mắt, hiệu năng ổn định đi cùng với đó là khả năng nhiếp ảnh vượt trội, đáp ứng mượt mà hầu hết các nhu cầu của người dùng.', '1');
-- Thêm phiếu nhập
INSERT INTO `phone`.`receipt`(`receiptId`, `supplierId`, `staffId`, `date`, `totalPrice`, `status`) VALUES ('1','1','1','2023-11-28 23:34:55','685900000','1');
INSERT INTO `phone`.`receipt`(`receiptId`, `supplierId`, `staffId`, `date`, `totalPrice`, `status`) VALUES ('2','2','1','2023-11-29 19:25:36','275980000','1');
INSERT INTO `phone`.`receipt`(`receiptId`, `supplierId`, `staffId`, `date`, `totalPrice`, `status`) VALUES ('3','3','1','2023-11-29 19:25:36','60000000','1');
INSERT INTO `phone`.`receipt`(`receiptId`, `supplierId`, `staffId`, `date`, `totalPrice`, `status`) VALUES ('4','4','1','2023-11-28 19:25:36','289900000','1');
INSERT INTO `phone`.`receipt`(`receiptId`, `supplierId`, `staffId`, `date`, `totalPrice`, `status`) VALUES ('5','5','1','2023-11-28 19:25:36','370900000','1');
-- Thêm chi tiết phiếu nhập
INSERT INTO `phone`.`receiptdetail`(`receiptId`, `productId`, `quantity`, `purchasePrice`, `status`) VALUES ('1','1','10','26590000','1');
INSERT INTO `phone`.`receiptdetail`(`receiptId`, `productId`, `quantity`, `purchasePrice`, `status`) VALUES ('1','2','20','21000000','1');
INSERT INTO `phone`.`receiptdetail`(`receiptId`, `productId`, `quantity`, `purchasePrice`, `status`) VALUES ('2','3','5','12000000','1');
INSERT INTO `phone`.`receiptdetail`(`receiptId`, `productId`, `quantity`, `purchasePrice`, `status`) VALUES ('2','4','19','5690000','1');
INSERT INTO `phone`.`receiptdetail`(`receiptId`, `productId`, `quantity`, `purchasePrice`, `status`) VALUES ('2','5','23','4690000','1');
INSERT INTO `phone`.`receiptdetail`(`receiptId`, `productId`, `quantity`, `purchasePrice`, `status`) VALUES ('3','6','10','26590000','1');
INSERT INTO `phone`.`receiptdetail`(`receiptId`, `productId`, `quantity`, `purchasePrice`, `status`) VALUES ('3','7','2','12000000','1');
INSERT INTO `phone`.`receiptdetail`(`receiptId`, `productId`, `quantity`, `purchasePrice`, `status`) VALUES ('4','8','10','2690000','1');
INSERT INTO `phone`.`receiptdetail`(`receiptId`, `productId`, `quantity`, `purchasePrice`, `status`) VALUES ('4','9','16','20000000','1');
INSERT INTO `phone`.`receiptdetail`(`receiptId`, `productId`, `quantity`, `purchasePrice`, `status`) VALUES ('4','10','3','8000000','1');
-- Thêm hóa đơn
INSERT INTO `phone`.`invoice`(`invoiceId`, `customerId`, `staffId`, `date`, `totalPrice`, `status`) VALUES ('1','11','2','2023-11-26 13:30:56','30590000','1');
INSERT INTO `phone`.`invoice`(`invoiceId`, `customerId`, `staffId`, `date`, `totalPrice`, `status`) VALUES ('2','12','2','2023-11-26 19:25:36','61180000','1');
INSERT INTO `phone`.`invoice`(`invoiceId`, `customerId`, `staffId`, `date`, `totalPrice`, `status`) VALUES ('3','13','3','2023-11-27 08:30:55','29380000','1');
INSERT INTO `phone`.`invoice`(`invoiceId`, `customerId`, `staffId`, `date`, `totalPrice`, `status`) VALUES ('4','14','3','2023-11-27 11:45:25','45070000','1');
INSERT INTO `phone`.`invoice`(`invoiceId`, `customerId`, `staffId`, `date`, `totalPrice`, `status`) VALUES ('5','15','3','2023-11-28 16:22:32','65180000','1');
INSERT INTO `phone`.`invoice`(`invoiceId`, `customerId`, `staffId`, `date`, `totalPrice`, `status`) VALUES ('6','16','4','2023-11-28 16:22:32','12000000','1');
-- Thêm chi tiết hóa đơn
INSERT INTO `phone`.`invoicedetail`(`invoiceId`, `productId`, `quantity`, `sellingPrice`, `status`) VALUES ('1','1','1','30590000','1');
INSERT INTO `phone`.`invoicedetail`(`invoiceId`, `productId`, `quantity`, `sellingPrice`, `status`) VALUES ('2','1','2','30590000','1');
INSERT INTO `phone`.`invoicedetail`(`invoiceId`, `productId`, `quantity`, `sellingPrice`, `status`) VALUES ('3','5','2','4690000','1');
INSERT INTO `phone`.`invoicedetail`(`invoiceId`, `productId`, `quantity`, `sellingPrice`, `status`) VALUES ('3','9','1','20000000','1');
INSERT INTO `phone`.`invoicedetail`(`invoiceId`, `productId`, `quantity`, `sellingPrice`, `status`) VALUES ('4','10','2','8000000','1');
INSERT INTO `phone`.`invoicedetail`(`invoiceId`, `productId`, `quantity`, `sellingPrice`, `status`) VALUES ('4','2','1','12000000','1');
INSERT INTO `phone`.`invoicedetail`(`invoiceId`, `productId`, `quantity`, `sellingPrice`, `status`) VALUES ('4','4','3','5690000','1');
INSERT INTO `phone`.`invoicedetail`(`invoiceId`, `productId`, `quantity`, `sellingPrice`, `status`) VALUES ('5','3','1','12000000','1');
INSERT INTO `phone`.`invoicedetail`(`invoiceId`, `productId`, `quantity`, `sellingPrice`, `status`) VALUES ('5','6','2','26590000','1');
INSERT INTO `phone`.`invoicedetail`(`invoiceId`, `productId`, `quantity`, `sellingPrice`, `status`) VALUES ('6','7','1','12000000','1');