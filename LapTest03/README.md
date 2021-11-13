
จงเขียนโปรแกรม Client/Server ที่ทำงานผ่าน UDP ด้วยภาษาจาวา โดยโปรแกรมฝั่งของ Client ชื่อ Client.java และโปรแกรมฝั่ง Server ชื่อ Server.java

Client ส่งคำสั่งไปยัง Server (คะแนน 8%)
Client ส่งคำสั่งการคำนวณเดือนดังนี้ => java Client  ชื่อเดือน  เลขจำนวนเต็ม
Client จะส่งข้อความที่รับมาจาก argument (args[0] และ args[1]) ไปยัง Server
ถ้าผู้ใช้เรียกใช้ Client โดยไม่ได้ป้อน argument ครบ 2 ตัวให้แสดงว่า “incorrect command”

โดยชื่อเดือนที่ผู้ใช้ป้อน ต้องเป็นคำย่อเดือน 12 เดือน ไม่สนใจตัวพิมพ์ใหญ่หรือพิมพ์เล็ก คือ
### "JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"

ถ้าผู้ใช้เรียกใช้ Client โดยไม่ได้ป้อนชื่อเดือนตรงตามที่กำหนด ให้แสดงว่า “illegal month abbreviation”
ถ้าผู้ใช้เรียกใช้ Client ไม่ได้ป้อนเลขเป็นจำนวนเต็ม ให้แสดงว่า “invalid number”

ตัวอย่างของคำสั่ง   
C:\Final>java Client jAn -2  หมายถึง สองเดือนก่อนหน้าคือเดือนอะไร
C:\Final>java Client APR 3  หมายถึง สามเดือนถัดไปคือเดือนอะไร

Client รับคำตอบจาก Server แล้ว แสดงชื่อเดือนออกทางหน้าจอ 
Server จะให้บริการที่ port หมายเลข 55555  (คะแนน 12%)
Server เพื่อเขียนโปรแกรมคำนวณเดือนถัดไปหรือเดือนก่อนหน้า ที่รับข้อมูลมาจากตัว Client
Server ส่งคำย่อเดือนที่คำนวณได้ กลับไปที่ Client เครื่องที่ส่งข้อมูลมาให้
เมื่อส่งไปแล้วให้แสดงข้อความที่ฝั่ง Server ถ้าเป็นเดือนถัดไป แสดงคำว่า “Future” ถ้าเป็นเดือนก่อนหน้า แสดงคำว่า “Past”

หมายเหตุ: ถ้าเขียนแบบ TCP จะได้ 0 คะแนนทันที

**********************************************************************************

ตัวอย่างการรัน

#Client                                              Server
                                             E:\AA\FS>java Server

C:\Final>java Client                                
incorrect command


C:\Final>java Client jan 3 5
incorrect command

D:\Final>java Client dck 6
illegal month abbreviation

E:\Final>java Client nov 3.4
invalid number

C:\Final>java Client dec -2                        Past
OCT

E:\Final>java Client jan -2                        Past
NOV

D:\Final>java Client dEc -13                       Past
NOV

F:\Final>java Client apR 3                         Future
JUL

C:\Final>java Client NOV 14                        Future
JAN

F:\Final>java Client Nov -4                        Past
JUL

D:\Final>java Client dEc 0                         Now
DEC
