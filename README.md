# BookAvo

Book avo es una aplicación de libros creada para el laboratorio 5 de la clase programación gráfica.

Esta app tiene como propósito explorar los diferentes libros que incluye nuestra base de datos, puedas guardar tus libros, leerlos y explorar la información de cada uno tanto de su autor como de sus capítulos, etc.

## Installation

Puede clonarlo con [git](https://git-scm.com/) o descargarlo en esta [url](https://github.com/DemonSlayer412/Bookavo/archive/refs/heads/main.zip)

**Usando http**

```bash
git clone https://github.com/DemonSlayer412/Bookavo.git
```

**Vía SSH (recomendada)**

```bash
git clone git@github.com:DemonSlayer412/Bookavo.git
```


## Requiremimientos

### Requerimientos generales

1.	La aplicación debe utilizar la internacionalización de textos, tal como se estudió en la práctica de laboratorio 4.

2.	En la medida de lo posible debe realizarse la programación haciendo uso del Binding View tal como se estudió en el laboratorio 4.

3.	La navegación de la app debe controlarse a través del componente Navigation.

4.	El código debe ordenarse y estructurarse en base a lo sugerido por la arquitectura MVVM.

5.	La app debe consultar de un servidor de base de datos Cloud Firestore

### Requerimientos específicos
1.	la **Pantalla 1** Debe incluir un splash screen de la app, con algún tipo de animación, hay distintas formas de implementar esto, una de ellas haciendo uso de recursos drawable XML, que es como se enseña, sin embargo, hay otros métodos incluso propuestos por el mismo Material Design. Queda a libre albeldrío el método de implementación.

2.	la **Pantalla 2** La pantalla de Login debe desarrollarse en base a los siguientes requerimientos: (Tal como se practicó en el lab4)
    *	El diseño debe utilizar los edittext de Material Design.
    *	Limitar la cantidad de líneas y caracteres que pueden digitarse.
    *	Utilizar iconos livianos como Vector Assets
    *	Debe validar el usuario haciendo uso de la autenticación Firebase
    *	Al hacer click en crear cuenta debe crear una nueva cuenta en el servidor, la cual debe validarse al momento de ingresar a la app.
    *	Utilizar el validator para marcar los campos requeridos.


## Algunos ejemplos

<p>
<img src="https://i.imgur.com/lWuuO5J.jpg" width="200px" hspace="20" />

<img src="https://i.imgur.com/vMCGvSt.jpg" width="200px" hspace="20" />

<img src="https://i.imgur.com/1eAzB6g.jpg" width="200px" />
</p>

### video ejemplo: [video ejemplo](https://youtu.be/r7O2RzDI82Y)
  
  
## Contribuidores

1. André Valentín Rivas Velásquez

2. Rene Enrique Urbina Rivera

3. Miguel Angel Hernández Gaitan

## License
[MIT](https://choosealicense.com/licenses/mit/)
