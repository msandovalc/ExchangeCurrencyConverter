# Currency Converter

## Descripción

Este proyecto es un **Conversor de Monedas** desarrollado en Java que permite convertir entre distintas monedas utilizando las tasas de cambio proporcionadas por una API externa. El usuario puede elegir la moneda base, la moneda objetivo y la cantidad a convertir. El sistema maneja la validación de monedas soportadas y proporciona un flujo robusto para asegurar que se ingresen valores válidos.

## Funcionalidades

- Permite elegir la moneda base y la moneda objetivo de una lista soportada.
- Muestra un menú con opciones para iniciar la conversión o salir del programa.
- La conversión se realiza usando tasas de cambio obtenidas de una API externa.
- Soporta validación de entradas para monedas y cantidad.
- Muestra las monedas soportadas de forma organizada.

## Requisitos

- Java 11 o superior.
- Acceso a la API de tasas de cambio (configurada en el archivo `config.properties`).

## Uso

1. Ejecuta la aplicación.
2. Selecciona la opción de inicio del conversor desde el menú principal.
3. Ingresa la moneda base (por ejemplo, USD).
4. Ingresa la moneda objetivo (por ejemplo, EUR).
5. Ingresa la cantidad que deseas convertir.
6. La conversión se realizará y mostrará el resultado.

## Estructura del Proyecto

src/ │ ├── com/ │ └── am/ │ └── exchangeconverter/ │ ├── api/ │ │ └── ExchangeRateApiProvider.java │ ├── model/ │ │ └── ExchangeRate.java │ ├── resources/ │ │ └── config.properties │ └── CurrencyConverter.java │ └── resources/ └── config.properties



## Configuración

1. En el archivo `config.properties`, coloca tu **API Key** para el servicio de tasas de cambio.
   - **URL API**: `https://api.exchangerate-api.com/v4/latest/`
   - **API Key**: (Obtenido de tu servicio de tasas de cambio)

## Contribuciones

Si deseas contribuir, siéntete libre de hacer un fork de este proyecto y enviar tus pull requests. ¡Cualquier mejora es bienvenida!

## Licencia

Este proyecto está licenciado bajo la [MIT License](LICENSE).
