(use 'clojure.contrib.monads)
(use 'clojure.contrib.probabilities.finite-distributions)

(defn die-n [n] (uniform (range 1 (inc n))))

(def d4 (die-n 4))
(def d6 (die-n 6))
(def d8 (die-n 8))
(def d12 (die-n 12))
(def d20 (die-n 20))

(defn d2d [d]
  (domonad dist-m
    [first d
     second d]
    (+ first second)))

(def d2d4 (d2d d4))
(def d2d6 (d2d d6))
(def d2d8 (d2d d8))
(def d2d12 (d2d d12))

(defn scenario [dispell-magic
                kill-beast-1
                kill-beast-2
                please-sorceress
                miss-trap]
  (if (< dispell-magic 4) ;d4
    :dazed-and-confused
    (if
      (or (< kill-beast-1 15) ;d20
        (< kill-beast-2 15)) ;d20
      :eaten
      (if (< please-sorceress 20) ;2d12
        :frozen
        (if (< miss-trap 10) ;2d8
          :poisoned
          :win)))))

(println
  (domonad dist-m
    [dispell-magic d4
     kill-beast-1 d20
     kill-beast-2 d20
     please-sorceress d2d12
     miss-trap d2d8]
    (scenario
      dispell-magic
      kill-beast-1
      kill-beast-2 
      please-sorceress
      miss-trap)))