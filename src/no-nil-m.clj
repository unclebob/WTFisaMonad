(use 'clojure.contrib.monads)

(defmonad no-nil-m
  [m-result (fn [mv] mv)
   m-bind (fn [mv f]
    (if (nil? mv) nil (f mv) ))])

(defn fragile [a b c] 
  (if (or (nil? a) (nil? b) (nil? c))
    (println "CRASH")
    (println (+ a b c))))

(println "fragile-----------------")
(fragile 1 2 3)
(fragile 1 nil 2)

(def safe-fragile (with-monad no-nil-m (m-lift 3 fragile)))

(println "safe--------------------")
(safe-fragile 1 2 3)
(safe-fragile 1 nil 2)

(println "done--------------------")

