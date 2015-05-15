call gradlew build
copy /y build\libs\*.jar ..\MCMA\Server\mods\
copy /y build\libs\*.jar %appdata%\.minecraft\mods\
PAUSE