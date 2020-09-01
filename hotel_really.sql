create database hotel_real;
drop database hotel_real;
create table hotel_real.booking(
	ID int auto_increment,
    Name_User  varchar(20) not null,
    CMND varchar(13) not null,
    Check_In_Date date not null,
    Check_Out_Date date not null,
    Booking_Date date not null,
    Booking_Status char not null,
    primary key(ID)
);
create table hotel_real.belong(
	ID_Booking int not null,
    ID_Room char(10) not null,
    primary key(ID_Booking,ID_Room),
    constraint FK_ID_Booking foreign key (ID_Booking) references booking(ID),
    constraint FK_ID_Room foreign key (ID_Room) references room(ID)
);
create table hotel_real.room(
	ID char(10) not null unique,
    Type_Room char(30) not null,
    Floor_Room int not null,
    Price int not null,
    Availability char not null,
    Room_Status char not null,
    primary key(ID)
);
-- insert booking
-- insert room
insert into hotel_real.room(ID, Type_Room, Floor_Room, Price, Availability, Room_Status) values 
('101', 'phòng đơn', '1', 200000, '0', '1');
insert into hotel_real.room(ID,  Type_Room, Floor_Room, Price, Availability, Room_Status) values 
('102', 'phòng đơn', '1', 200000, '0', '1');
insert into hotel_real.room(ID,  Type_Room, Floor_Room, Price, Availability, Room_Status) values 
('103', 'phòng đơn', '1', 200000, '0', '1');
insert into hotel_real.room(ID, Type_Room, Floor_Room, Price, Availability, Room_Status) values 
('104', 'phòng đơn', '2', '250000', '0', '1');
insert into hotel_real.room(ID, Type_Room, Floor_Room, Price, Availability, Room_Status) values 
('105', 'phòng đơn', '2', '250000', '0', '1');
insert into hotel_real.room(ID, Type_Room, Floor_Room, Price, Availability, Room_Status) values 
('201', 'phòng đôi', '1', '250000', '0', '1');
insert into hotel_real.room(ID, Type_Room, Floor_Room, Price, Availability, Room_Status) values 
('202','phòng đôi', '1', '250000', '0', '1');
insert into hotel_real.room(ID, Type_Room, Floor_Room, Price, Availability, Room_Status) values 
('203', 'phòng đôi', '2', '300000', '0', '1');
insert into hotel_real.room(ID, Type_Room, Floor_Room, Price, Availability, Room_Status) values 
('204',  'phòng đôi', '2', '300000', '0', '1');
insert into hotel_real.room(ID, Type_Room, Floor_Room, Price, Availability, Room_Status) values 
('205', 'phòng đôi', '2', '300000', '0', '1');
insert into hotel_real.room(ID,  Type_Room, Floor_Room, Price, Availability, Room_Status) values 
('301', 'phòng gia đình', '2', '300000', '0', '1');
insert into hotel_real.room(ID, Type_Room, Floor_Room, Price, Availability, Room_Status) values 
('302', 'phòng gia đình', '1', '250000', '0', '1');
insert into hotel_real.room(ID, Type_Room, Floor_Room, Price, Availability, Room_Status) values 
('303', 'phòng gia đình', '2', '300000', '0', '0');
insert into hotel_real.room(ID, Type_Room, Floor_Room, Price, Availability, Room_Status) values 
('304', 'phòng gia đình', '3', '350000', '0', '0');
insert into hotel_real.room(ID, Type_Room, Floor_Room, Price, Availability, Room_Status) values 
('305', 'phòng gia đình', '3', '350000', '0', '0');
select * from hotel_real.room;
-- check id phong co dang trong hay khong?
-- check id phòng
drop procedure check_id
delimiter //
create procedure check_id(id varchar(50))
begin
	select * from hotel_real.room 
    where room.ID = id
    and room.Availability ='1'
    and room.Room_Status='1'
    ;
end //
delimiter ;
call check_id('202')
-- lay het phong
delimiter //
create procedure getAllRoom()
begin
	select*from hotel_real.room;
end //
delimiter ;
call getAllRoom
-- lay phong customer
delimiter //
create procedure view_room()
begin
	select * from hotel_real.room where
    room.Room_Status='1'
    and room.Availability='0'
    ;
 end //
 delimiter ;
call view_room;
-- xem infor booking
delimiter //
create procedure view_booking(id_booking int)
begin
	select hotel_real.booking.ID as ID_Booking,booking.Name_User,booking.CMND,booking.Check_In_Date,booking.Check_Out_Date,booking.Booking_Date,booking.Booking_Status, hotel_real.room.ID,room.Type_Room,room.Floor_Room,room.Price
	from hotel_real.booking
    inner join belong
    on booking.ID=belong.ID_Booking
    inner join room
    on belong.ID_Room=room.ID
    where booking.ID=id_booking;
end //
delimiter ;
-- lay so ngay
delimiter //
select*from booking;
select*from hotel_real.belong where belong.ID_Booking=4;
select datediff('2020-09-29','2020-08-28');
call view_booking(10);
select*from hotel_real.belong;
select*from hotel_real.booking;
select*from hotel_real.room;