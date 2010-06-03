(defn dot-result [n]
  (apply str (repeat n ".")))

(defn bind-dots-to-n [d f]
  (f (count d)))

(defn add-dots [da db]
  (bind-dots-to-n
    da
    (fn [a] (bind-dots-to-n
      db
      (fn [b] (dot-result (+ a b)))))))

(println (add-dots "..." "..."))