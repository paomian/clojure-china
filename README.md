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
Step 1 : To create a file name dbconn.clj under by src/clojure_china/dbutil and write  follow example  to file, then create a database name clojure_china. 
 	(ns clojure-china.dbutil.dbconn)

	(def db-spec {:subprotocol "postgresql"
           :subname (str "//localhost/clojure_china") 
           :user "postgres"
           :password "123456"})

Step 2 : Excecute all sql file under by src/clojure_china/dbutil/. 

## Running

To start a web server for the application, run:

    lein ring server

## License

Copyright © 2014 FIXME
