(use 'clojure.contrib.monads)

(defmonad dot-m
  [m-result (fn [n] (apply str (repeat n ".")))
   m-bind (fn [mv f] (f (count mv)))])

(defn add-dots [da db]
  (domonad dot-m
    [x da
     y db]
    (+ x y)))

(defn mul-dots [da db]
  (domonad dot-m
    [x da
     y db]
    (* x y)))

(defn dcd [dt du]
  (domonad dot-m
    [tens dt
     units du]
    (+ (* 10 tens) units)))

(println (add-dots ".." "...."))
(println (mul-dots "..." "...."))
(println (dcd "..." "....."))


(printf "should be five dots %s\n"
  (with-monad dot-m
    (m-bind ".."
      (fn [x] (m-bind "..."
        (fn [y]
          (m-result
            (+ x y))))))))

(def subtract-dots
  (with-monad dot-m
    (m-lift 2 -)))

(printf "5-2 is 3 %s\n" (subtract-dots "....." ".."))

(defn mean-4 [a b c d] (/ (+ a b c d) 4))

(def dmean-4 (with-monad dot-m (m-lift 4 mean-4)))

(printf "(dmean-4 3 2 3 4) %s\n" (dmean-4 "..." ".." "..." "...."))