# curso-testing-java-platzi

----
1) Tests Unitarios sin Librerias.
2) Test Unitario con JUnit.

### Nota
- Los test tiene el nombre de la clase y pueden terminar con la palabra test o should
## Mockito
- Es una herramienta que nos permite simular clases que dependen de implementaciones no controlables y complejas
- Ejemplo: salida aleatoria de un dado, pasarela de pagos.

## TDD
- Si escribir los terst es complejo, quizas el diseño no es el adecuado.
- Tecnica para desarrollar a partir de los test.
- Consiste en comenzar escribiendo los test lo cual nos ayuda a pensar despues en el diseño.
- Al terminar ya tendremos todos los test realizados.

### 3 Reglas para implementar TDD

1) RED: Escribirás el mínimo código de test que falle.
2) GREEN:  Escribiras el mínimo código de producción para que pase el test.
3) REFACTOR: sólo cuando los test estén pasando.