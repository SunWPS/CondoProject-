# **CondoProject**
จัดทำโดย
นายวงศกร ปิ่นวาสี 6210400175 หมู่1

---

**Table of Contents**

[TOC]

---

## **How to install and run**
1. ติดตั้ง font ทั้งหมดในโฟลเดอร์ font
2. ไฟล์ .jar จะอยู่ในโฟลเดอร์ project_jar ให้ทำการ double click ที่ jar ไฟล์
3. ถ้า double click ไม่ได้ให้ทำการรันผ่านคำสั่ง java -jar 6210400175.jar
---
## วิธีเริ่มต้นใช้งาน
---
## **CSV**
อยู่ในโฟเดอร์  ./project_jar/data

### admin
>ไฟลนี้เก็บข้อมูลของผู้ดูแลระบบ ประกอบด้วยข้อมูลดังต่อไปนี้

>- Username
>- Password

### officer
>ไฟลนี้เก็บข้อมูลของเจ้าหน้าที่ส่วนกลาง ประกอบด้วยข้อมูลดังต่อไปนี้

- Username
- Password
- ชื่อ
- วันเวลาที่เข้าทำงานล่าสุด
- สถานะ
- จำนวนการพยายามเข้าใช้ถ้าสถานะเป็น deactivate
-  ชื่อไฟล์รูปภาพ

### resident
>ไฟลนี้เก็บข้อมูลของผู้เข้าพัก ประกอบด้วยข้อมูลดังต่อไปนี้

>- Username
>- Password
>- ชื่อ
>- เลขที่ห้องที่พัก

### condo
>ไฟลนี้เก็บข้อมูลของห้องพักอาศัย ซึ่งแบ่งเป็น 2 ประเภท ประกอบด้วยข้อมูลดังต่อไปนี้

1. Simplex (ห้องพัก 1 คน)
  >- อาคาร
  >- ชั้น
  >- เลขห้อง
  >- ประเภท
  >- สถานะ
  >- เจ้าของห้อง

2. Duplex (ห้องพัก 2 คน)
  >- อาคาร
  >- ชั้น
  >- เลขห้อง
  >- ประเภท
  >- สถานะ
  >- เจ้าของห้อง
  >- ผู้พักคนที่ 2

### storage
>ไฟลนี้เก็บข้อมูลของจดมหาย/เอกสาร/พัสดุ ประกอบด้วยข้อมูลดังต่อไปนี้

1. จดหมาย
  >- ประเภทของสิ่งของ
  >- ผู้รับ
  >- อาคาร
  >- ชั้น
  >- เลขห้อง
  >- ขนาด
  >- วันที่เจ้าหน้าที่รับ
  >- ผู้ส่ง
  >- ชื่อไฟล์รูปภาพ
  >- สถานะ
  >- ขนาด
  >- วันที่เจ้าหน้าที่รับ
  >- เจ้าหน้าที่ที่รับของเข้า
  >- เจ้าหน้าที่ที่ดูแลการนำของออก
  >- ผู้ที่มารับของออก
  >- วันเวลาที่มีผู้มารับของออก

   
2. เอกสาร
  >- ข้อมูลเหมือนจดหมายแต่เพิ่ม ความสำคัญของเอกสารเข้าไปต่อท้าย


   
3. พัสดุ
  >- ข้อมูลเหมือนจดหมายแต่เพิ่ม บริษัทไปรษณีย์ และ tracking number เข้าไปต่อท้ายด้วย
---

## **โครงสร้าง ไฟล์ ใน โฟร์เดอร์ 6210400175**

 >- uml diagram อยู่ใน folder นี้

### **ใน โฟร์เดอร์ project_jar**
    
  >- folder "data" เก็บ csv
  >- folder "imageForTest" เก็บ รูปสำหรับ Test
  >- folder "itemPictuer" เก็บ รูปภาพ จดหมาย / เอกสาร / พัสดุ
  >- folder "officerPicture" เก็บ รูปภาพเจ้าหน้าที่ส่วนกลาง
  >- folder "font" เก็บ font ที่ต้องติดตั้งก่อนใช้โปรแกรม
  >- 6210400175.jar
  >- 6210400175.pdf

### **ใน src.main.java.ku.cs.condo**
#### **1. controllers**
##### 1.1 adminControllers 
รวบรวม controller ของผู้ดูแลระบบ ได้แก่

  >- AddOfficerFinishController
  >- AdminAddOfficerController
  >- AdminPageHomeController
  >- AdminParentController
  >- AdminRePasswordController
  >- AdminShowOfficerController
  >- ConfirmOfficerController
  >- ConfirmChangeStatusController

##### 1.2 officerControllers
รวบรวม controller ของเจ้าหน้าที่ส่วนกลาง  ได้แก่
  >โฟร์เดอร์ OfficerMainController ( controller หลัก ของเจ้าหน้าที่ส่วนกลาง )
  >
  >- OfficerAddRoomController
  >- OfficerHomeController
  >- OfficerParentController
  >- OfficerRePasswordController
  >- OfficerShowItemController
  >- OfficerShowItmeOutController
  >- OfficerShowRoomController
  >
  >โฟร์เดอร์ OfficerSubController ( controller ย่อย ของเจ้าหน้าที่ส่วนกลาง )
  >
  >- AddResidentToDuplexController
  >- AddResidentToSimplexController
  >- ConfirmAddItemController
  >- ConfirmAddResidentController
  >- ConfirmAddRoomController
  >- ConfirmCheckOutController
  >- ConfirmItemExitController
  >- EditDuplexInfoController
  >- EditSuplexInfoController
  >- OfficerAddDocumentController
  >- OfficerAddLetterController
  >- OfficerAddPackageController

##### 1.3 residentController
รวบรวม controller ของผู้พักอาศัย  ได้แก่
 
  >- ConfirmRegisterController
  >- ResidentParentController
  >- ResidentRegisterController
  >- ResidentRePasswordController
  >- ResidentShowItemController
  >- ResidentShowItemOutController
  
##### 1.4 fullInformationController
รวบรวม controller ของการโชว์ข้อมูล  ได้แก่

  >- FullDocumentController
  >- FullLetterController
  >- FullPackageController
  >- FullInformationAccountController

##### 1.5 etcController
รวบรวมการ controller ส่วนกลางที่มีการใช้งาานร่วมกัน ได้แก่
  
  >- ConfirmRePasswordController
  >- FinishRePasswordController
  >- HomeController
  >- InformationAndSettingController
  >- LoginController

#### **2. models**
สามารถดู UML Diagram ไดที่นี้ [6210400175-UML.jpg](https://drive.google.com/file/d/1cZu_L8H-7B7RSHn3llfz4jyqr0htHMtE/view?usp=sharing)
##### 2.1 account
รวบรวม models ที่เกียวข้องกับ account ได้แก่
 
  >- <<Interface>> AccountList
  >- AdminAccount
  >- AdminList
  >- OfficerAccount
  >- OfficerList
  >- ResidentAccount
  >- ResidentList
  
##### 2.2 room
รวบรวม models ที่เกียวข้องกับ อาคารและห้องพัก ได้แก่

  >- Building
  >- Room

##### 2.3 storage
รวบรวม models ที่เกียวข้องกับ จดหมาย/เอกสาร/พัสดุ ได้แก่
  
  >- Item
  >- Document
  >- Package
  >- Storage
  
#### **3. services**
##### 3.1 accountDataSoure
รวบรวมการทำานการอ่านเขียน ไฟล์ csv ของ account ชนิดต่างๆ ได้แก่

  >- <<Interface>> AccountsFileDataSource
  >- AdminFileDataSource
  >- OfficerFileDataSource
  >- ResidentFileDataSource
  
##### 3.2 etcService
รวบรวมการทำงาน การอ่านเขียนไฟล์ csv ของ จดหมาย/เอกสาร/พัสดุ และ ห้องพัก
รวมถึง การทำงานที่เกี่ยวข้องกับรูปภาพด้วย

  >- StorageFileDataSource
  >- BuildingFileDataSource
  >- ImageService

#### 4. exception
รวบรวบ exception ต่างๆ ได้แก่

  >- BothPassIsSameException
  >- BothPassNotMatchException
  >- DeactivatedAccountException
  >- IllegalArgumentForRegisterException
  >- NoAccountException
  >- WrongAddRoomInfoException
  >- WrongCurrentPasswordException
  >- WrongUsernameOrPasswordException
  
###  **ใน src.main.resources**

#### **1. fxml**
รวบรวมไฟล์ .fxml ซึ่งไฟล์ใน folder ย่อยต่อไปนี้มีรูปแบบการเก็บไฟล์คล้ายกับ ku.cs.condo.controllers ซึ่งมีโฟล์เดอร์ย่อยดังนี้

  >- adminPage
  >- officerPage
      - officerMainPage
      - officerSubPage
    
  >- residentPage
  >- fullInformationPage
  >- etcPage
  
#### **2. IconAndLogo**
เก็บรูปภาพต่างๆที่ใช้กับ ไฟล์ fxml ได้แก่

  >- abstract
  >- classic
  >- etcImage
  
#### **3. StyleSheet**
เก็บ ไฟล์ .css ซึ่งใช้เป็น theme ให้กับไฟล์ fxml ได้แก่

  >- abstract.css
  >- classic.css

---
## สรุปสิ่งที่พัฒนาในแต่ละสัปดาห์
**สัปดาห์ที่ 1**
 
 - 2020-08-20 [Designed Admin GUI](https://bitbucket.org/6210400175/6210400175/commits/5e2c911) 
 > ออกแบบ gui ของผู้ดูแลระบบ
 - 2020-08-22 [Designed Officer and Resident GUI](https://bitbucket.org/6210400175/6210400175/commits/228db11) 
 > ออกแบบ gui ของเจ้าหน้าที่ส่วนกลาง และ ผู้พักอาศัย
 - 2020-08-22 [Update GUI and add CommonnLang3.jar and OpenCSV.jar](https://bitbucket.org/6210400175/6210400175/commits/aab7926) 
 > ออกแบบ gui และทดลองการทำอ่านไฟล์ เขียนไฟล์

**สัปดาห์ที่ 2**

  - 2020-08-23 [make Re Admin Password and add Officer](https://bitbucket.org/6210400175/6210400175/commits/e52062a) 
  > ทำหน้าการเปลี่ยนรหัสของ ผู้ดูแลระบบ และ เริ่มทำงานบางส่วนของเจ้าหน้าที่ส่วนกลาง 
  - 2020-08-25 [make OfficerLogin, Officer re-password and showOfficer version1](https://bitbucket.org/6210400175/6210400175/commits/e01694b) 
  > ทำหน้าการเปลี่ยนรหัส / login / โชว์ข้อมูล ของเจ้าหน้าที่ส่วนกลาง 
  
**สัปดาห์ที่ 3**
  
  - 2020-09-03 [Finish showOfficer](https://bitbucket.org/6210400175/6210400175/commits/6a9b8d7) 
  > สร้างหน้า โชว์ข้อมูล ของเจ้าหน้าที่ส่วนกลาง แบบที่1 เสร็จสมบูรณ์
  
**สัปดาห์ที่ 4**

  - 2020-09-06 [Update AdminAddOfficer](https://bitbucket.org/6210400175/6210400175/commits/1a06241) 
  > อัพเดตการทำงานของหน้า เพิ่มเจ้าหน้าที่ส่วนกลาง
  - 2020-09-12 [add maven to project](https://bitbucket.org/6210400175/6210400175/commits/0ac17e0) 
  > นำ maven เข้าสู่โปรเจค และ มีการอัพเดท Controller ของผู้ดูแลระบบ และ เจ้าหน้าที่ส่วนกลาง
  
**สัปดาห์ที่ 5**

  - 2020-09-22 [connected all paeges](https://bitbucket.org/6210400175/6210400175/commits/0c97cb9) 
  > เชื่อมทุกหน้าเข้าด้วยกัน และ มีการ update การทำงาน Controller ต่าง ของผู้ดูแลระบบ, เจ้าหน้าที่ส่วนกลาง และ ผู้พักอาศัย
  - 2020-09-26 [use polymorphism with Login...](https://bitbucket.org/6210400175/6210400175/commits/d75ab3e) 
  > ทำการอัพเดท model ให้มีการเกิด polymorphism

**สัปดาห์ที่ 6**
  
  - 2020-09-28 [Update to MVC](https://bitbucket.org/6210400175/6210400175/commits/011baba) 
  > อัพเดท model ทำการนำงานที่เกี่ยวข้องกับ IO ออกจาก model ไปใส่ใน service และจัดไฟล์ให้ตรงตามหลัก MVC
  - 2020-09-28 [Upadte to MVC Version2 and Update Storage](https://bitbucket.org/6210400175/6210400175/commits/598dd84) 
  > แก้ขการจัดไฟล์ และ อัพเดตการทำงานของ จดหมาย/เอกสาร/พัสดุ
  - 2020-09-28 [Storage.java edited](https://bitbucket.org/6210400175/6210400175/commits/28a730c) 
  > แก้ไขลบ import ที่ไม่ได้ใช้ใน storage.java
  - 2020-10-01 [update show officer ui](https://bitbucket.org/6210400175/6210400175/commits/85cc1ea) 
  > อัพเดทหน้าแสดงข้อมูล เจ้าหน้าที่ส่วนกลางในระบบ
  
**สัปดาห์ที่ 7**
  
  - 2020-10-05 [update read and write csv](https://bitbucket.org/6210400175/6210400175/commits/7344cca) 
  > อัพเดทการอ่านและเขียนไฟล์ และ อัพเดท Controller และ model ให้เข้ากับการทำงานการอ่านเขียนไฟล์

**สัปดาห์ที่ 8**

  - 2020-10-14 [before make theme](https://bitbucket.org/6210400175/6210400175/commits/03304ff) 
  > อัพเดท controller ต่างๆให้สมบูรณ์ก่อนทำ theme
  - 2020-10-15 [Update theme feature](https://bitbucket.org/6210400175/6210400175/commits/29d5505) 
  > สร้าง theme
  - 2020-10-15 [manage fxml directory](https://bitbucket.org/6210400175/6210400175/commits/4b1e570) 
  > แก้ไขการจัดเรียงไฟล์
  - 2020-10-15 [change some file's name](https://bitbucket.org/6210400175/6210400175/commits/f10871e) 
  > แก้ไขชื่อไฟล์
  - 2020-10-16 [manage all directory](https://bitbucket.org/6210400175/6210400175/commits/b3cc99e) 
  > แก้ไขการจัดเรียงไฟล์
  
**สัปดาห์ที่ 9**
  
  - 2020-10-20 [make exception , add pdf and uml, edit readme.md](https://bitbucket.org/6210400175/6210400175/commits/75e2a7f)
  >- มีการใช้ throws exception มาใช้ใน code
  >- แก้ไขการเปลี่ยน theme
  >- เพิ่ม pdf, uml
  >- แก้ไข readme
  
  - 2020-10-20 [Delete Duplex.java, edit UML, add .trim() behind getText()](https://bitbucket.org/6210400175/6210400175/commits/e8dfca4)
  >- ลบ Duplex.java นำไปรวมใน Room.java แทน
  >- แก้ไข UML
  >- เพิ่ม .trim() หลัง .getText()
  
  - 2020-10-23 [edit ImageService](https://bitbucket.org/6210400175/6210400175/commits/ff7b998)
  >- แก้ไข error การเจอรูปซ้ำ
  
  - 2020-10-24 [edit ImageService2](https://bitbucket.org/6210400175/6210400175/commits/356deb4)
  >- แก้ไข การเจอไฟล์รูปที่ชื่อซ้ำกัน
  
**สัปดาห์ที่ 10**

  - 2020-10-28 [add dir-ImageForTest and add Howto](https://bitbucket.org/6210400175/6210400175/commits/b8b6fb9)
  >- เพิ่มรูปภาพสำหรับการ Test และ เพิ่มปุ่มดู pdf คู่มือการใช้งาน ในหน้าข้อมูลโปรแกรม
   
  - 2020-10-30 [edit ImageService about file that have same name](https://bitbucket.org/6210400175/6210400175/commits/13f0482)
  >- แก้ไขชื่อ file รูป ถ้า file มีชื่อซ้ำกันกับที่มีอยู่
 

