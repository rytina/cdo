Porting CDO to Android
----------------------

The idea of porting CDO to Android started at the beginning of 2014 where I switched form Eclipse IDE development to Android & iOS App development. As I started with the Android development I realized soon that there is something very important missing: "A ORM framework". Instead of using an existing built-in object relational mapping framework, like it is the case with iOS and it's CoreData Framework, you have to write your SQL queries manually. Okey, there are some third-party libraries for Android which are trying to bridge that gap like androrm (https://github.com/androrm/androrm), OrmLite (http://ormlite.com/sqlite_java_android_orm.shtml) and greenDAO (http://greendao-orm.com/) which is really good but in my opinion they all are missing a proper IDE integration like it is with CoreData and it's fantastic tool support in XCode with the graphical CoreData Model Editor.

With this fork of CDO I'm trying not only to build an ORM framework for Android, I want also to provide a graphical and form-based editor like the CoreData Model Editor from XCode. During my job as consultant at itemis I gained expertise in the Eclipse Modeling Framework (EMF), the Connected Data Objects Framework (CDO) and last but not least in the Graphical Modeling Framework (GMF). This is a big challenge, but I'm pretty sure that I can master this challenge with the help of these three great Eclipse technologies. Maybe you also have some experience in Eclipse & Android development and want to support me to achieve this goal? Great! Just drop me a message.
