# BDD tesztek

## Cucumber hozzáadása a projekthez

task hozzáadása a gradle-höz:
![](BDD_assets/add_cucumber_task.png)

## BDD tesztek készítése

### Read primitives from graph

Ellenőrzi az olyan gráf beolvasását amely primitíveket tartalmaz.

Ehhez létre kellett hozni egy konvertálót ami a DataTable-ből egy ClassWithPrimitives példányt hoz létre:
<img src="D:\BME_sajat\6\IET\iet-hf2021-hat-ez-szomoru-hir\doc\BDD_assets\class_primitive_datatabletype.png" style="zoom:67%;" />

Teszt leírása a .feature fájlban:
<img src="bdd_assets/read_primitives_feature.png" style="zoom:67%;" />

Step definitions:
<img src="bdd_assets/read_primitives_steps.png" style="zoom:67%;" />

Sikeres lefutás:
<img src="bdd_assets/read_primitives_test.png" style="zoom:67%;" />



