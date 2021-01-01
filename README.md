# **CondoProject**
จัดทำโดย
นายวงศกร ปิ่นวาสี 6210400175 หมู่1

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
