/**********************/
/*                    */
/* createDataBase.SQL */
/*                    */
/**********************/

/* Begin Transaction */
begin transaction;

/* Database [CarShop] */
pragma auto_vacuum=0;
pragma default_cache_size=2000;
pragma encoding='UTF-8';
pragma page_size=1024;

/* Drop table [Car] */
drop table if exists [Car];

/* Table structure [Car] */
CREATE TABLE [Car] (
  [id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 
  [car_type] INTEGER NOT NULL CONSTRAINT [fk_cartype] REFERENCES [CarType]([id]), 
  [car_brand_id] INTEGER NOT NULL CONSTRAINT [fk_car_brand] REFERENCES [CarBrand]([id]), 
  [car_color_id] INTEGER NOT NULL CONSTRAINT [fk_car_color] REFERENCES [CarColor]([id]), 
  [car_drive_id] INTEGER NOT NULL CONSTRAINT [fk_car_drive] REFERENCES [CarDrive]([id]), 
  [car_model_id] INTEGER NOT NULL CONSTRAINT [fk_car_model] REFERENCES [CarModel]([id]), 
  [car_state_id] INTEGER NOT NULL CONSTRAINT [fk_car_state_id] REFERENCES [CarState]([id]), 
  [car_transmition_id] INTEGER NOT NULL CONSTRAINT [fk_car_transmition] REFERENCES [CarTransmission]([id]), 
  [cost] DECIMAL, 
  [year] INTEGER);

/* Data [Car] */
insert into [Car] values(1, 2, 1, 1, 1, 1, 2, 1, 1235, 2000);


/* Drop view [carBrandView] */
drop view if exists [carBrandView];

/* View [carBrandView] */
CREATE VIEW [carBrandView] AS 
SELECT [Car].id as айди,
       [CarModel].[name_ru],
       [CarState].[name_ru],
       [CarBrand].[name_ru] as CarBrand,
       [CarTransmission].[name_ru] as 'CarTransmission',
       [CarType].[name_ru] as CarType,
       [CarColor].[name_ru],
       [CarDrive].[name_ru]
FROM [Car]
left JOIN [CarModel] ON [Car].[car_model_id] = [CarModel].[id]

left JOIN [CarBrand] ON [Car].[car_brand_id] = [CarBrand].[id]

left JOIN [CarColor] ON  [Car].[car_color_id]=[CarColor].[id]

left JOIN [CarDrive] ON [Car].[car_drive_id]= [CarDrive].[id] 

left JOIN [CarState] ON   [Car].[car_state_id]=[CarState].[id]

left JOIN [CarTransmission] ON  [Car].[car_transmition_id]= [CarTransmission].[id]

left JOIN [CarType] ON [Car].[car_type] = [CarType].[id];

/* Drop table [CarBrand] */
drop table if exists [CarBrand];

/* Table structure [CarBrand] */
CREATE TABLE [CarBrand] (
  [id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 
  [name_ru] NVARCHAR(100) NOT NULL, 
  [name_en] NVARCHAR(100));

/* Data [CarBrand] */
insert into [CarBrand] values(1, 'ГАЗ', null);
insert into [CarBrand] values(2, 'ВАЗ', null);
insert into [CarBrand] values(3, 'КамАЗ', null);
insert into [CarBrand] values(4, 'МАЗ', null);
insert into [CarBrand] values(5, 'Урал', null);
insert into [CarBrand] values(6, 'ЗиЛ', null);
insert into [CarBrand] values(7, 'Лаз', null);
insert into [CarBrand] values(8, 'БелАЗ', null);
insert into [CarBrand] values(9, 'Краз', null);
insert into [CarBrand] values(10, 'УАЗ', null);


/* Drop table [CarColor] */
drop table if exists [CarColor];

/* Table structure [CarColor] */
CREATE TABLE [CarColor] (
  [id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 
  [name_ru] NVARCHAR(100) NOT NULL, 
  [name_en] NVARCHAR(100));

/* Data [CarColor] */
insert into [CarColor] values(1, 'красный
', null);
insert into [CarColor] values(2, 'желтый', null);
insert into [CarColor] values(3, 'белый', null);
insert into [CarColor] values(4, 'зеленый', null);
insert into [CarColor] values(5, 'синий', null);
insert into [CarColor] values(6, 'серый', null);
insert into [CarColor] values(7, 'хаки', null);
insert into [CarColor] values(8, 'оранжевый', null);


/* Drop table [CarDrive] */
drop table if exists [CarDrive];

/* Table structure [CarDrive] */
CREATE TABLE [CarDrive] (
  [id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 
  [name_ru] NVARCHAR(100) NOT NULL, 
  [name_en] NVARCHAR(100));

/* Data [CarDrive] */
insert into [CarDrive] values(1, 'Переднеприводная', 'Front-wheel drive');
insert into [CarDrive] values(2, 'Заднеприводная', 'Rear-wheel-drive layouts');
insert into [CarDrive] values(3, 'Полноприводная', 'Four-wheel-drive layouts');


/* Drop table [CarModel] */
drop table if exists [CarModel];

/* Table structure [CarModel] */
CREATE TABLE [CarModel] (
  [id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 
  [name_ru] NVARCHAR(100) NOT NULL, 
  [name_en] NVARCHAR(100));

/* Data [CarModel] */
insert into [CarModel] values(1, '5490', null);
insert into [CarModel] values(2, 'КАМАЗ 5511', null);
insert into [CarModel] values(3, 'КАМАЗ 54115 ', null);
insert into [CarModel] values(4, 'КАМАЗ 6540', null);
insert into [CarModel] values(5, 'КАМАЗ 6522', null);
insert into [CarModel] values(6, 'КАМАЗ 6520 ', null);
insert into [CarModel] values(7, 'КАМАЗ 4326-9 ', null);
insert into [CarModel] values(8, 'ВАЗ 2101', null);
insert into [CarModel] values(9, 'ВАЗ 2109', null);
insert into [CarModel] values(10, 'ВАЗ 2121', null);
insert into [CarModel] values(11, 'Kalina 1118', null);
insert into [CarModel] values(12, 'ГАЗ-21Р', null);
insert into [CarModel] values(13, 'ГАЗ-14 ', null);
insert into [CarModel] values(14, 'ГАЗ-66', null);
insert into [CarModel] values(15, ' ГАЗ-4301', null);


/* Drop table [CarState] */
drop table if exists [CarState];

/* Table structure [CarState] */
CREATE TABLE [CarState] (
  [id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 
  [name_ru] NVARCHAR(100) NOT NULL, 
  [name_en] NVARCHAR(100));

/* Data [CarState] */
insert into [CarState] values(1, 'Новая', 'New');
insert into [CarState] values(2, 'Б/у', 'Second-hand');


/* Drop table [CarTransmission] */
drop table if exists [CarTransmission];

/* Table structure [CarTransmission] */
CREATE TABLE [CarTransmission] (
  [id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 
  [name_ru] NVARCHAR(100) NOT NULL, 
  [name_en] NVARCHAR(100));

/* Data [CarTransmission] */
insert into [CarTransmission] values(1, 'ручная', 'manual');
insert into [CarTransmission] values(2, 'автомат', 'auto');


/* Drop table [CarType] */
drop table if exists [CarType];

/* Table structure [CarType] */
CREATE TABLE [CarType] (
  [id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 
  [name_ru] NVARCHAR(100), 
  [name_en] NVARCHAR(100));

/* Data [CarType] */
insert into [CarType] values(1, 'легковая', null);
insert into [CarType] values(2, 'грузовик', null);
insert into [CarType] values(3, 'мотоцикл', null);
insert into [CarType] values(4, 'автобус', null);


/* Drop table [Sale] */
drop table if exists [Sale];

/* Table structure [Sale] */
CREATE TABLE [Sale] (
  [id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 
  [saler_id] INTEGER NOT NULL CONSTRAINT [fk_saler] REFERENCES [Saler]([id]), 
  [car_id] INTEGER NOT NULL CONSTRAINT [fk_car] REFERENCES [Car]([id]), 
  [sale_date] INTEGER);

/* Data [Sale] */


/* Drop table [Saler] */
drop table if exists [Saler];

/* Table structure [Saler] */
CREATE TABLE [Saler] (
  [id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 
  [fname] NVARCHAR(100), 
  [lname] NVARCHAR(256), 
  [birth_date] BIGINT);

/* Data [Saler] */
insert into [Saler] values(1, 'иванов ', 'иван ', 578227266000);
insert into [Saler] values(2, 'смирнов', 'семен', 1398681666000);


/* Commit Transaction */
commit transaction;

