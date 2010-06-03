(use 'clojure.contrib.monads)

(defmonad list-m
  [m-result (fn [v] (list v))
   m-bind (fn [mv f] (apply concat (map f mv)))])

(println
  (domonad list-m
    [x [1 2 3]
     y [4 5 6]]
    (+ x y)))

(def mul-list
  (with-monad list-m
    (m-lift 2 *)))

(println (mul-list [1 2 3] [4 5 6]))
