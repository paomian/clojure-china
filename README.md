#Clojure-china

- This is the source code of Clojure China website.
- **Please look at the branch whose name is development**

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

- if you want to join us. you can join the QQ group which number is `130107204`

## Before runing 

Step 1: Create a file named dbconn.clj under src/clojure_china/dbutil with the content as stated **[this](https://github.com/paomian/clojure-china/tree/paomian/src/clojure_china/dbutil)**, and create a local postgres database named clojure_china.

Step 2: Execute all sql files under src/clojure_china/dbutil

## Running

To start a web server for the application, run:

    lein ring server

## License

Copyright � 2014 FIXME
