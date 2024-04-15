(ns mate-clj.core-test
  (:require [clojure.test :refer :all]
            [mate-clj.core :refer :all]))

(def debug (atom []))

(defn append-to-debug [e]
  (swap! debug conj e))

(add-tap append-to-debug)

(deftest threading-macros-tests
  (testing "->>"
    (let [_ (reset! debug [])
          actual (d->> 1 (+ 2))]
    (is (= actual 3))
    (is (= @debug [{:fn 1 :ret 1} {:fn '(+ 2) :ret 3}]))))
  (testing "->"
    (let [_ (reset! debug [])
          actual (d-> 1 (+ 2))]
    (is (= actual 3))
    (is (= @debug [{:fn 1 :ret 1} {:fn '(+ 2) :ret 3}])))))

