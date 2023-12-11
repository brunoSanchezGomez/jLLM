# jLLM

Para iniciar la aplicación, acceda en la Terminal a la carpeta "dist" del programa e introduzca:

java -jar jLLM.jar [REPOSITORY] [MODEL] [VIEW] [URL (opcional)]

Donde los posibles valores para los argumentos son:

REPOSITORY:
	-json
	-xml
Determina el tipo de archivo que se utilizará en importación/exportación.

MODEL:
	-fake
	-csv
	-smart
Determina el tipo de modelo que responderá durante las conversaciones.
En caso de seleccionar "smart", habrá que introducir el argumento URL (si no, no es necesario), indicando la dirección URL de un modelo LLM de Ollama, que tendremos que haber iniciado previamente.

VIEW:
	-consola
	-voz
Determina si la aplicación sólo se muestra por consola o si además se narra con TTS.



También es posible no introducir ningún argumento, en cuyo caso se tomarán los valores json, fake y consola.