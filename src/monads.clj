(defn square [n] (* n n))

(defn enlist1 [f] (partial map f))

(def square-list (enlist1 square))

(map
  (fn [a]
    (map
      (fn [b] (* a b))
      '(1 2 3)))
  '(4 5 6)
  )
