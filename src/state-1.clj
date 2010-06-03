(def state {:a 1 :b 2 :c 3 :d 4 :t 0})

(defn get-s [k s] [(s k) s])

(defn set-s [k v s]
  (let [old (s k)]
    [old (assoc s k v)]))

(defn sum-s [ka kb kt state]
  (let [a (first (get-s ka state))
        b (first (get-s kb state))
        sf (second (set-s kt (+ a b) state))]
    (first (get-s :t sf))))

(println (sum-s :a :b :t state))
