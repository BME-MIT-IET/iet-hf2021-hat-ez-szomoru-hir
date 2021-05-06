# BDD tesztek

## Cucumber hozzáadása a projekthez

task hozzáadása a gradle-höz:
<img src="BDD_assets/add_cucumber_task.png" style="zoom:67%;" />

## BDD tesztek készítése

### Read primitives from graph

Ellenőrzi az olyan gráf beolvasását amely primitíveket tartalmaz.

Ehhez létre kellett hozni egy konvertálót ami a DataTable-ből egy ClassWithPrimitives példányt hoz létre:
<img src="BDD_assets/class_primitive_datatabletype.png" style="zoom:67%;" />

Teszt leírása a .feature fájlban:
<img src="BDD_assets/read_primitives_feature.png" style="zoom:67%;" />

Step definitions:
<img src="BDD_assets/read_primitives_steps.png" style="zoom:67%;" />

Sikeres lefutás:
<img src="BDD_assets/read_primitives_test.png" style="zoom:67%;" />

### Read lists of primitives from graph

Ellenőrzi az olyan gráf beolvasását amely primitívekből álló listákat tartalmaz.

Az előző teszthez hasonlóan létrehoztam egy új konvertáló függvényt a ClassWithPrimitiveLists osztályhoz:
<img BDD_assets\class_primitivelists_datatabletype.png" style="zoom:67%;" />

Teszt leírása a .feature fájlban:
<img src="BDD_assets/read_lists_primitives_feature.png" style="zoom:67%;" />

A lépések leírása hasonló az előző teszthez.