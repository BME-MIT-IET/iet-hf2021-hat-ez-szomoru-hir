# Manuális kód analízis

A SonarLint álltal jelzett hibák fájlonként:

## Beans.java:

* Hiba: Egy nem használt import maradt a fájlban.
	* Javítás: eltávolítottam a felesleges importot.
	![](MCR_assets/import.png)
* Hibák: A.class.isInstance()-t használ az instanceof operátor helyett
	* Javítás: Minden helyen átírtam a kódot, hogy instanceof operátort használjon.
	![](MCR_assets/instanceof.png)
* Hiba: Generikus típust használ parametrizált típus nélkül
  	* Javítás: Class helyett Class<Object> használata.
  	![](MCR_assets/generic.png)

## UUIDCodec.java:

* ![image-20210505201234247](MCR_assets/image-20210505201234247.png)
* Hiba: A SonarLint egy kód smell-t talált. Az enum-okat csupa nagybetűvel szokás elnevezni, azonban az Instance esetén ez nem így történt.
* Javítás: Átírtuk nagybetűs INSTANCE-re, és így már rendben volt.

## RDFMapper.java:

* ![image-20210505202426191](MCR_assets/image-20210505202426191.png)
* Hibák: Az "A.class.isInstance()" helyett az instanceof metódus használata javasolt. A kódban itt többször egymás után előfordul ez a hiba.
* Javítás: Kicserélbem `theObj instanceof Boolean` formára az ilyen hibás sorokat.