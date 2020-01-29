# mate-clj


Mate-clj is a Clojure library that will help you to control the flow of core macros and functions and will help you debug your code out of the box.


*  The functionality of the macros and functions will remain the same.
*  Every step of the flow will be printed to the REPL.

[![Clojars Project](https://img.shields.io/clojars/v/mate-clj.svg)](https://clojars.org/mate-clj)


## Getting Started:

Add the dependency to your project.clj:
```clojure
[mate-clj "0.1.6"]
```

## Usage:

```clojure
user=> (require '[mate-clj.core :as mate])
user=> (def m {:body "flow test"})
user=> (mate/d-> m
                :body ;step #1
                (clojure.string/upper-case) ;step #2
                (clojure.string/reverse)) ;step #3
(:body m) => "flow test" ;step #1 result 
(clojure.string/upper-case (:body m)) => "FLOW TEST" ;step #2 result
(clojure.string/reverse (clojure.string/upper-case (:body m))) => "TSET WOLF" ;step #3 result
"TSET WOLF" ;the returned value

user=> (mate/dreduce + [1 3 5 7 9])
(#function[clojure.core/+] 1 3) => 4
(#function[clojure.core/+] 4 5) => 9
(#function[clojure.core/+] 9 7) => 16
(#function[clojure.core/+] 16 9) => 25
25 ;the returned value

user=> (mate/dcond->> 1
                      true inc
                      (= 3 2) (* 42)
                      true (+ 100)
                      (= 2 2) (* 9))
(inc 1) => 2
(+ 100 (inc 1)) => 102
(* 9 (+ 100 (inc 1))) => 918
918 ;the returned value

user=> (mate/das-> 1 n
                   (* 2 n)
                   (+ n n)
                   (+ n 2 3 4))
n => 1
(* 2 n) => 2
(+ n n) => 4
(+ n 2 3 4) => 13
13 ;the returned value

user=> (dfilter even? [1  2  3  4  5  6])
#function[clojure.core/even?] 1 => false
#function[clojure.core/even?] 2 => true
#function[clojure.core/even?] 3 => false
#function[clojure.core/even?] 4 => true
#function[clojure.core/even?] 5 => false
#function[clojure.core/even?] 6 => true
(2 4 6) ;the returned value
```


License
----

MIT



## Contributing
PRs are always welcome!
