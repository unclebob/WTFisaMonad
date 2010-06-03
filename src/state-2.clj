(use 'clojure.contrib.monads)

(defn get-s [k] (fn [s] [(s k) s]))

(defn set-s [k v] (fn [s]
  (let [old (s k)]
    [old (assoc s k v)])))

(defn sum-s [ka kb kt]
  (domonad state-m
    [a (get-s ka)
     b (get-s kb)
     s (set-s kt (+ a b))]
    s))

(println
  ((sum-s :a :b :t) {:a 1 :b 2}))