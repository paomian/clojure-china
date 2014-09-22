#Clojure-china

- This is the source code of Clojure China website.
- **Please look at at the development branch for the most recent progress.**

## Prerequisites

- [Leiningen][1] 1.7.0 or above

[1]: https://github.com/technomancy/leiningen

- [JAVA][2] 6 or above

[2]:http://www.oracle.com/technetwork/java/index.html

- [postgresql][3]

[3]: http://www.postgresql.org/

- [clojure][4] 1.4 or above
 
[4]:http://clojure.org/

- [redis][5] 2.8.7

[5]:http://redis.io/

##Contact & Contribution

- if you want to join us. you can join the QQ group `130107204`

## Before runing 

Step 1: Create a file named dbconn.clj under src/clojure_china/db with the content as stated **[here](https://github.com/paomian/clojure-china/tree/paomian/src/clojure_china/db)**, and create a local postgres database named clojure_china.

Step 2: run`lein repl` under project root,and run`(use 'lobos.core 'lobos.connectivity 'lobos.migration 'lobos.migrations)` then `(migrate)`

## Running

To start a web server for the application, run:

    lein ring server

## License

Copyright � 2014 FIXME
