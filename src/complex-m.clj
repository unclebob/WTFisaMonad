(use 'clojure.contrib.monads)

(defstruct complex :r :i)

(def c-0 (struct complex 0 0))
(def c-1 (struct complex 1 0))
(def c-i (struct complex 0 1))

(defmonad complex-m
  [m-result (fn [x] (struct complex x 0))
   m-bind (fn [mv f]
    (if (= 0 (mv :i))
      (f (mv :r))
      :imaginary))])

(def c+
  (with-monad complex-m
    (m-lift 2 +)))

(println (c+ c-1 c-1))
(println (c+ c-1 c-i))
(println (c+ c-i c-1))

