# Build keretrendszer beüzemelése + CI beüzemelése

A projektet gradle-ös projektként hoztuk létre, hogy build-elhető legyen. Ebbe a build.gradle fájlba bekerült a sonarqube mint plugin.

![](figure/gradle.png)

Majd GitHub-on létrehoztuk a gradle action-t, hogy push-olás és pull request esetén fordítsa le a programot. Ebbe a gradle.yaml-be belekerült az is, hogy a sonarqube is értékelje ki a projektünket.

![](figure/gradleyaml1.png)
![](figure/gradleyaml2.png)

Build lefutása:

![](figure/ci_running.png)

Az első SonarQube futtatás eredménye az alábbi volt:

![](figure/sonarcloud_overview.png)

![](figure/sonacloud_issues.png)

A CI-ba bekerült, hogy a cucumberben elkészített teszteket automatikusan kiértékelje:

![](figure/run_cucumbers_ci.png)