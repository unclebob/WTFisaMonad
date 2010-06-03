(defn dots-to-n [dots] (count dots))
(defn n-to-dots [n] (apply str (repeat n ".")))

(defn add-dots [da db]
  (let [a (dots-to-n da)
        b (dots-to-n db)
        s (+ a b)]
    (n-to-dots s)))

(println (add-dots "..." "....."))