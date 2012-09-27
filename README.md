uPaste
======

See http://pastebin.com/ or any other online pasting tool. This one is special because you can easily download and run you own local version of it from your computer. This is especially good for paranoid people.

Use
===
Download the .jar file from [here](https://github.com/downloads/bostrt/upaste/uPaste-local.jar). Run with `java -jar uPaste-local.jar`.

build
=====

For now, simple check out from this repository and run `mvn clean install -Dpublic=true` to build. The .jar will be in the target/ directory. Run `java -jar uPaste-jar-with-dependencies.jar` and navigate to localhost:4567.